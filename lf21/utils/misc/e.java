/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class e {
    public static void a(File file, byte[] byArray) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);){
            fileOutputStream.write(byArray);
        }
    }

    public static File a(File file, String string) {
        return new File(file, string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String string;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string).append('\n');
            }
            return stringBuilder.toString();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static byte[] a(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file);){
            int n2;
            byte[] byArray = new byte[fileInputStream.available()];
            for (int i2 = 0; i2 < byArray.length; i2 += n2) {
                n2 = fileInputStream.read(byArray, i2, byArray.length - i2);
                if (n2 >= 0) continue;
                break;
            }
            byte[] byArray2 = byArray;
            return byArray2;
        }
    }

    public static void a(FileReader fileReader) {
        try {
            fileReader.close();
        }
        catch (IOException iOException) {
            System.out.println("Failed to close reader");
        }
    }

    public static FileReader b(File file) {
        try {
            return new FileReader(file);
        }
        catch (IOException iOException) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static File c(File file) {
        try {
            if (file.exists()) return file;
            File file2 = file.getParentFile();
            if (file2 != null && !file2.exists()) {
                file2.mkdirs();
            }
            file.createNewFile();
            return file;
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return file;
    }

    public static void a(File file, JsonObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(file);){
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)jsonObject));
        }
        catch (IOException iOException) {
            file.delete();
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        return inputStream.readAllBytes();
    }

    public static byte[] a(String string) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(string);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + string);
        }
        return e.b(inputStream);
    }

    public static InputStream a(byte[] byArray) {
        return new ByteArrayInputStream(byArray);
    }
}

