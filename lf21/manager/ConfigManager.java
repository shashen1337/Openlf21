/*
 * Decompiled with CFR 0.152.
 */
package lf21.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import lf21.LF21Client;
import lf21.config.Config;
import lf21.config.ModulesConfig;
import lf21.config.ValuesConfig;
import lf21.manager.c;
import lf21.manager.d;
import lf21.modules.Module;
import lf21.settings.Setting;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
    public static final Logger a;
    public static final File CONFIG_DIR;
    public static final File b;
    private static final File c;
    private static final long d;
    private final List<Config> e;
    private final List<d> f = new ArrayList<d>();
    private final AtomicReference<List<c>> g = new AtomicReference();
    private final AtomicBoolean h = new AtomicBoolean();
    private final AtomicLong i = new AtomicLong();
    private final Object j = new Object();
    private String k = "";
    private long l;
    private static final ExecutorService m;
    private static final String[] n;

    public ConfigManager() {
        this.e = new ArrayList<Config>();
        if (!CONFIG_DIR.exists()) {
            if (CONFIG_DIR.mkdir()) {
                a.info("Created config directory");
            }
        }
        if (!b.exists()) {
            b.mkdirs();
        }
        this.e.add(new ModulesConfig());
        this.e.add(new ValuesConfig());
        this.h();
    }

    public synchronized void loadAll() {
        block7: {
            this.b();
            boolean bl = false;
            for (Config config : this.e) {
                try {
                    File file = config.getFile();
                    if (!file.exists()) continue;
                    this.a(config, file);
                    bl = true;
                }
                catch (IOException | RuntimeException exception) {
                    a.error("Failed to load config " + config.getName(), (Throwable)exception);
                }
            }
            if (!bl || ThreadLocalRandom.current().nextInt(100) >= 25) break block7;
            this.a();
        }
    }

    private void a() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (Module module : LF21Client.getInstance().getModuleManager().getModules()) {
            module.setEnabled(threadLocalRandom.nextBoolean());
            for (Setting<?> setting : module.getSettings()) {
                try {
                    if (setting instanceof BooleanSetting) {
                        BooleanSetting booleanSetting = (BooleanSetting)setting;
                        booleanSetting.setValue(threadLocalRandom.nextBoolean());
                        continue;
                    }
                    if (setting instanceof NumberSetting) {
                        NumberSetting numberSetting = (NumberSetting)setting;
                        double d2 = numberSetting.getMin().doubleValue();
                        double d3 = numberSetting.getMax().doubleValue();
                        double d4 = Math.max(1.0E-9, numberSetting.getStep().doubleValue());
                        int n2 = (int)Math.max(0L, Math.round((d3 - d2) / d4));
                        double d5 = d2 + (double)threadLocalRandom.nextInt(n2 + 1) * d4;
                        numberSetting.setValue(d5);
                        continue;
                    }
                    if (setting instanceof ModeSetting) {
                        ModeSetting modeSetting = (ModeSetting)setting;
                        String[] stringArray = modeSetting.getModes();
                        if (stringArray.length <= 0) continue;
                        modeSetting.setValue(stringArray[threadLocalRandom.nextInt(stringArray.length)]);
                        continue;
                    }
                    if (!(setting instanceof MultiSelectSetting)) continue;
                    MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (String string : multiSelectSetting.getOptions()) {
                        if (!threadLocalRandom.nextBoolean()) continue;
                        arrayList.add(string);
                    }
                    multiSelectSetting.setValue(arrayList);
                }
                catch (RuntimeException runtimeException) {
                    a.debug("Failed to randomize {}.{}", (Object)module.getName(), (Object)setting.getName(), (Object)runtimeException);
                }
            }
        }
    }

    public synchronized void captureDefaults() {
        this.f.clear();
        for (Config config : this.e) {
            try {
                StringWriter stringWriter = new StringWriter(4096);
                try (BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);){
                    config.save(bufferedWriter);
                }
                this.f.add(new d(config, stringWriter.toString()));
            }
            catch (IOException | RuntimeException exception) {
                a.error("Failed to capture default config " + config.getName(), (Throwable)exception);
            }
        }
    }

    public synchronized boolean resetToDefaults() {
        if (this.f.size() != this.e.size()) {
            return false;
        }
        this.b();
        try {
            for (d d2 : this.f) {
                try (BufferedReader bufferedReader = new BufferedReader(new StringReader(d2.b()));){
                    d2.a().read(bufferedReader);
                }
            }
            return true;
        }
        catch (IOException | RuntimeException exception) {
            a.error("Failed to reset working config", (Throwable)exception);
            return false;
        }
    }

    private void b() {
        this.i.incrementAndGet();
        this.g.set(null);
    }

    private void a(Config config, File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath(), new OpenOption[0]), StandardCharsets.UTF_8));){
            config.read(bufferedReader);
        }
        catch (IOException | RuntimeException exception) {
            File file2;
            block15: {
                block16: {
                    String[] stringArray = n;
                    file2 = new File(file.getParentFile(), file.getName() + ".bak");
                    if (file2.isFile()) break block15;
                    if (!(exception instanceof IOException)) break block16;
                    IOException iOException = (IOException)exception;
                    throw iOException;
                }
                throw exception;
            }
            a.warn("Primary config {} is damaged; loading backup", (Object)file);
            try (BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Files.newInputStream(file2.toPath(), new OpenOption[0]), StandardCharsets.UTF_8));){
                config.read(bufferedReader2);
            }
        }
    }

    public synchronized void saveAll() {
        this.c();
    }

    public synchronized void c() {
        for (Config config : this.e) {
            this.a(config);
        }
        a.debug("Saved working config state");
    }

    public synchronized void d() {
        this.c();
    }

    private void e() {
        if (!this.h.compareAndSet(false, true)) {
            return;
        }
        m.execute(() -> {
            try {
                List list;
                while ((list = (List)this.g.getAndSet(null)) != null) {
                    for (c c2 : list) {
                        this.a(c2);
                    }
                }
            }
            catch (Throwable throwable) {
                this.h.set(false);
                if (this.g.get() == null) throw throwable;
                this.e();
                throw throwable;
            }
            this.h.set(false);
            if (this.g.get() == null) return;
            this.e();
            return;
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(c c2) {
        if (c2.c() != this.i.get()) {
            return;
        }
        File file = c2.a();
        String[] stringArray = n;
        File file2 = new File(file.getParentFile(), file.getName() + ".tmp");
        File file3 = new File(file.getParentFile(), file.getName() + ".bak");
        try {
            Object object = this.j;
            synchronized (object) {
                if (c2.c() != this.i.get()) {
                    return;
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Files.writeString(file2.toPath(), (CharSequence)c2.b(), StandardCharsets.UTF_8, new OpenOption[0]);
                if (file.isFile()) {
                    Files.copy(file.toPath(), file3.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                try {
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (AtomicMoveNotSupportedException atomicMoveNotSupportedException) {
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (IOException iOException) {
            a.error("Failed to asynchronously save " + file.getName(), (Throwable)iOException);
            try {
                Files.deleteIfExists(file2.toPath());
            }
            catch (IOException iOException2) {
                // empty catch block
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(Config config) {
        File file = config.getFile();
        String[] stringArray = n;
        File file2 = new File(file.getParentFile(), file.getName() + ".tmp");
        File file3 = new File(file.getParentFile(), file.getName() + ".bak");
        try {
            Object object = this.j;
            synchronized (object) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file2.toPath(), new OpenOption[0]), StandardCharsets.UTF_8));){
                    config.save(bufferedWriter);
                }
                if (file.isFile()) {
                    Files.copy(file.toPath(), file3.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                try {
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (AtomicMoveNotSupportedException atomicMoveNotSupportedException) {
                    Files.move(file2.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (IOException iOException) {
            a.error("Failed to save config " + config.getName(), (Throwable)iOException);
            try {
                Files.deleteIfExists(file2.toPath());
            }
            catch (IOException iOException2) {
                // empty catch block
            }
        }
    }

    public synchronized List<String> f() {
        if (!b.exists()) {
            b.mkdirs();
        }
        File[] fileArray = b.listFiles(File::isDirectory);
        if (fileArray == null) {
            return Collections.emptyList();
        }
        ArrayList<File> arrayList = new ArrayList<File>(List.of(fileArray));
        arrayList.sort(Comparator.comparingLong(File::lastModified).reversed().thenComparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        ArrayList<String> arrayList2 = new ArrayList<String>();
        for (File file : arrayList) {
            if (!new File(file, "modules.cfg").isFile() || !new File(file, "values.cfg").isFile()) continue;
            arrayList2.add(file.getName());
        }
        return arrayList2;
    }

    public synchronized boolean a(String string) throws IOException {
        String string2 = ConfigManager.f(string);
        File file = this.e(string2);
        if (file.exists()) {
            return false;
        }
        if (!file.mkdirs()) {
            throw new IOException("Failed to create profile directory " + String.valueOf(file));
        }
        this.a(file);
        return true;
    }

    public synchronized void b(String string) throws IOException {
        File file;
        block4: {
            String string2 = ConfigManager.f(string);
            file = this.e(string2);
            if (file.exists() || file.mkdirs()) break block4;
            throw new IOException("Failed to create profile directory " + String.valueOf(file));
        }
        this.a(file);
    }

    public synchronized void c(String string) throws IOException {
        this.b();
        String string2 = ConfigManager.f(string);
        File file = this.e(string2);
        for (Config config : this.e) {
            File file2 = new File(file, config.getName());
            if (!file2.isFile()) {
                throw new IOException("Profile file is missing: " + String.valueOf(file2));
            }
            this.a(config, file2);
        }
        if (ThreadLocalRandom.current().nextInt(100) < 25) {
            this.a();
        }
        this.c();
        this.k = string2;
        this.i();
        this.publishRuntimeStatus();
    }

    public synchronized String g() {
        return this.k;
    }

    public synchronized boolean pollExternalCommand() {
        long l2 = System.currentTimeMillis();
        if (l2 - this.l < 150L) {
            return false;
        }
        this.l = l2;
        String[] stringArray = n;
        Path path = ConfigManager.j().resolve("config-command-" + d + ".txt");
        if (!Files.isRegularFile(path, new LinkOption[0])) {
            return false;
        }
        try {
            String string = Files.readString(path, StandardCharsets.UTF_8).trim();
            Files.deleteIfExists(path);
            this.c(string);
            return true;
        }
        catch (IOException | RuntimeException exception) {
            a.error("Failed to process external config command", (Throwable)exception);
            try {
                Files.deleteIfExists(path);
            }
            catch (IOException iOException) {
                // empty catch block
            }
            return false;
        }
    }

    public synchronized void publishRuntimeStatus() {
        try {
            Path path = ConfigManager.j();
            Files.createDirectories(path, new FileAttribute[0]);
            String[] stringArray = n;
            Files.writeString(path.resolve("config-status-" + d + ".txt"), (CharSequence)this.k, StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (IOException iOException) {
            a.warn("Failed to publish runtime config status", (Throwable)iOException);
        }
    }

    public synchronized void d(String string) throws IOException {
        File file = this.e(ConfigManager.f(string));
        String[] stringArray = n;
        Files.deleteIfExists(new File(file, "modules.cfg").toPath());
        Files.deleteIfExists(new File(file, "values.cfg").toPath());
        Files.deleteIfExists(file.toPath());
    }

    private void a(File file) throws IOException {
        for (Config config : this.e) {
            File file2 = new File(file, config.getName());
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file2.toPath(), new OpenOption[0]), StandardCharsets.UTF_8));){
                config.save(bufferedWriter);
            }
        }
        file.setLastModified(System.currentTimeMillis());
    }

    private File e(String string) throws IOException {
        File file = new File(b, string);
        String string2 = b.getCanonicalPath() + File.separator;
        if (!file.getCanonicalPath().startsWith(string2)) {
            throw new IOException("Invalid profile path");
        }
        return file;
    }

    private void h() {
        block7: {
            if (!c.isFile()) {
                return;
            }
            try {
                String string = ConfigManager.f(Files.readString(c.toPath(), StandardCharsets.UTF_8));
                File file = this.e(string);
                if (!new File(file, "modules.cfg").isFile() || !new File(file, "values.cfg").isFile()) break block7;
                this.k = string;
            }
            catch (IOException | RuntimeException exception) {
                a.warn("Failed to restore active config profile name", (Throwable)exception);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void i() throws IOException {
        if (!CONFIG_DIR.exists() && !CONFIG_DIR.mkdirs()) {
            throw new IOException("Failed to create config directory " + String.valueOf(CONFIG_DIR));
        }
        Path path = c.toPath();
        Path path2 = path.resolveSibling(String.valueOf(path.getFileName()) + ".tmp");
        Files.writeString(path2, (CharSequence)this.k, StandardCharsets.UTF_8, new OpenOption[0]);
        try {
            Files.move(path2, path, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            return;
        }
        catch (AtomicMoveNotSupportedException atomicMoveNotSupportedException) {
            Files.move(path2, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static Path j() {
        Path path;
        block5: {
            String string;
            block4: {
                String[] stringArray = n;
                string = System.getenv("LOCALAPPDATA");
                if (string != null && !string.isBlank()) break block4;
                path = Path.of(System.getProperty("java.io.tmpdir"), new String[0]);
                break block5;
            }
            path = Path.of(string, new String[0]);
        }
        Path path2 = path;
        return path2.resolve("LF21");
    }

    public static String f(String string) {
        String string2;
        block11: {
            block10: {
                String string3 = string == null ? "" : string.trim();
                string2 = string3;
                String[] stringArray = n;
                string2 = string2.replaceAll("[\\\\/:*?\"<>|\\p{Cntrl}]", "_");
                while (string2.endsWith(".")) {
                    string2 = string2.substring(0, string2.length() - 1).trim();
                }
                if (string2.isBlank() || string2.equals(".")) break block10;
                if (!string2.equals("..")) break block11;
            }
            throw new IllegalArgumentException("Profile name is empty");
        }
        if (string2.length() > 24) {
            string2 = string2.substring(0, 24).trim();
        }
        return string2;
    }

    private static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable, "LF21 config writer");
        thread.setDaemon(true);
        thread.setPriority(1);
        return thread;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[40];
                var3_1 = 0;
                var2_2 = "c\u000b2\u0017=\"\u000bQ\u0005{\t9(OJ\u00072\u0001=fPXD \u0006\u0004i,iJ)c\u000b2\u0017=\"\u000bQ\u0005{\u000b*)H@\u0019([=>_@\u00185\u001a4fHJ\u0004=\u0012?fHJ\u00076\u001a6\"\u000bH\u0005?\u000e4#X\u000b\t=\u001c\u0012i,iJx%DK\f2\u001cx1YL\u001e>\t\u0002\u000bD'c\u000b2\u0017=\"\u000bQ\u0005{\u000b-$GL\u00193[*3EQ\u00036\u001ex%DK\f2\u001cx5_D\u001e.\b\fi%\u0018:\u0014\u0007{u.\u001a/\u0019#c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u000b*)ML\u0006>[</Y@\t/\u0014*?\u000b\"c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u00187(ML\r{\u001f14NF\u001e4\t!f\u001ec\u000b2\u0017=\"\u000bQ\u0005{\u001a+?EF\u0002)\u00146)^V\u0006\"[+']@J\nS\u000b7\u000e=5\u0005F\f<\u0004\u000b\b:\u0010\u0004\u000b\u001e6\u000b\u000eF\u00055\u001d1!\u0006V\u001e:\u000f-5\u0006\u000eO\u000b-\u001av/D\u000b\u001e6\u000b</Y\u0004\u000b\u001e#\u000f\u0004\u000b\u001e#\u000f#c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u000b*)ML\u0006>[</Y@\t/\u0014*?\u000b\bU\u00184\u001d1*NV\u0019u\u00184\u001d1*N\u0005\f2\u0017=fBVJ6\u0012+5BK\ra[\rf\u00055\u001d1!fD\u0004:\u001c=4\u001av\u000b-\u001e<f\\J\u00180\u00126!\u000bF\u00055\u001d1!\u000bV\u001e:\u000f=,c\u000b2\u0017=\"\u000bQ\u0005{\t=5_J\u0018>[9%_L\u001c>[;)EC\u0003<[(4DC\u00037\u001ex(JH\u000f!c\u000b2\u0017=\"\u000bQ\u0005{\u001896_P\u0018>[<#MD\u001f7\u000fx%DK\f2\u001cx\u0015u\u00184\u001d1*N\u0005\u0004:\u0016=fBVJ>\u0016(2R\u0016c\u000b2\u0017=\"\u000bQ\u0005{\b90N\u0005\t4\u0015>/L\u0005\u0015~6\u0007Tbl\u0014\u0007Ve\u0007\u00046Pf\u0004/\t4;v\u0018f\u0018>\u001a,#O\u0005\t4\u0015>/L\u0005\u000e2\t=%_J\u0018\"\nS\u000b7\u000e=5\u0005F\f<,u\u00182\u001694R\u0005\t4\u0015>/L\u0005\u0011&[15\u000bA\u000b6\u001a?#O\u001eJ7\u00149\"BK\r{\u00199%@P\u001a\u000fF\u00055\u001d1!\u0006F\u00056\u00169(O\b\u0004\u000b\b:\u0010\u0012D\t/\u0012.#\u0006U\u00184\u001d1*N\u000b\u001e#\u000f\u0004\u000b\u001e6\u000b\u000bH\u0005?\u000e4#X\u000b\t=\u001c\u0007F\u00055\u001d1!X\u001ec\u000b2\u0017=\"\u000bQ\u0005{\t=5NQJ,\u0014*-BK\r{\u00187(ML\r";
                var4_3 = "c\u000b2\u0017=\"\u000bQ\u0005{\t9(OJ\u00072\u0001=fPXD \u0006\u0004i,iJ)c\u000b2\u0017=\"\u000bQ\u0005{\u000b*)H@\u0019([=>_@\u00185\u001a4fHJ\u0004=\u0012?fHJ\u00076\u001a6\"\u000bH\u0005?\u000e4#X\u000b\t=\u001c\u0012i,iJx%DK\f2\u001cx1YL\u001e>\t\u0002\u000bD'c\u000b2\u0017=\"\u000bQ\u0005{\u000b-$GL\u00193[*3EQ\u00036\u001ex%DK\f2\u001cx5_D\u001e.\b\fi%\u0018:\u0014\u0007{u.\u001a/\u0019#c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u000b*)ML\u0006>[</Y@\t/\u0014*?\u000b\"c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u00187(ML\r{\u001f14NF\u001e4\t!f\u001ec\u000b2\u0017=\"\u000bQ\u0005{\u001a+?EF\u0002)\u00146)^V\u0006\"[+']@J\nS\u000b7\u000e=5\u0005F\f<\u0004\u000b\b:\u0010\u0004\u000b\u001e6\u000b\u000eF\u00055\u001d1!\u0006V\u001e:\u000f-5\u0006\u000eO\u000b-\u001av/D\u000b\u001e6\u000b</Y\u0004\u000b\u001e#\u000f\u0004\u000b\u001e#\u000f#c\u000b2\u0017=\"\u000bQ\u0005{\u0018*#JQ\u000f{\u000b*)ML\u0006>[</Y@\t/\u0014*?\u000b\bU\u00184\u001d1*NV\u0019u\u00184\u001d1*N\u0005\f2\u0017=fBVJ6\u0012+5BK\ra[\rf\u00055\u001d1!fD\u0004:\u001c=4\u001av\u000b-\u001e<f\\J\u00180\u00126!\u000bF\u00055\u001d1!\u000bV\u001e:\u000f=,c\u000b2\u0017=\"\u000bQ\u0005{\t=5_J\u0018>[9%_L\u001c>[;)EC\u0003<[(4DC\u00037\u001ex(JH\u000f!c\u000b2\u0017=\"\u000bQ\u0005{\u001896_P\u0018>[<#MD\u001f7\u000fx%DK\f2\u001cx\u0015u\u00184\u001d1*N\u0005\u0004:\u0016=fBVJ>\u0016(2R\u0016c\u000b2\u0017=\"\u000bQ\u0005{\b90N\u0005\t4\u0015>/L\u0005\u0015~6\u0007Tbl\u0014\u0007Ve\u0007\u00046Pf\u0004/\t4;v\u0018f\u0018>\u001a,#O\u0005\t4\u0015>/L\u0005\u000e2\t=%_J\u0018\"\nS\u000b7\u000e=5\u0005F\f<,u\u00182\u001694R\u0005\t4\u0015>/L\u0005\u0011&[15\u000bA\u000b6\u001a?#O\u001eJ7\u00149\"BK\r{\u00199%@P\u001a\u000fF\u00055\u001d1!\u0006F\u00056\u00169(O\b\u0004\u000b\b:\u0010\u0012D\t/\u0012.#\u0006U\u00184\u001d1*N\u000b\u001e#\u000f\u0004\u000b\u001e6\u000b\u000bH\u0005?\u000e4#X\u000b\t=\u001c\u0007F\u00055\u001d1!X\u001ec\u000b2\u0017=\"\u000bQ\u0005{\t=5NQJ,\u0014*-BK\r{\u00187(ML\r".length();
                var1_4 = 25;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ConfigManager.a(ConfigManager.g(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "l\u0004-\u001a4/O\u0005\u001a)\u0014>/G@J+\u001a,.\u0016c\u000b2\u0017=\"\u000bQ\u0005{\u00177'O\u0005\t4\u0015>/L\u0005";
                    var4_3 = "l\u0004-\u001a4/O\u0005\u001a)\u0014>/G@J+\u001a,.\u0016c\u000b2\u0017=\"\u000bQ\u0005{\u00177'O\u0005\t4\u0015>/L\u0005".length();
                    var1_4 = 20;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = ConfigManager.a(ConfigManager.g(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        ConfigManager.n = var5;
        var0_6 = ConfigManager.n;
        ConfigManager.a = LogManager.getLogger((String)"ConfigManager");
        ConfigManager.CONFIG_DIR = new File(LF21Client.configDir, "configs");
        ConfigManager.b = new File(ConfigManager.CONFIG_DIR, "profiles");
        ConfigManager.c = new File(ConfigManager.CONFIG_DIR, "active-profile.txt");
        ConfigManager.d = ProcessHandle.current().pid();
        ConfigManager.m = Executors.newSingleThreadExecutor((ThreadFactory)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Runnable;)Ljava/lang/Thread;, a(java.lang.Runnable ), (Ljava/lang/Runnable;)Ljava/lang/Thread;)());
    }

    private static char[] g(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x2B);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 37;
                    case 1 -> 106;
                    case 2 -> 91;
                    case 3 -> 123;
                    case 4 -> 88;
                    case 5 -> 70;
                    default -> 43;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

