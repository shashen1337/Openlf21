/*
 * Decompiled with CFR 0.152.
 */
package lf21.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import lf21.LF21Client;
import lf21.config.Config;
import lf21.exception.ModuleNotFoundException;
import lf21.manager.ModuleManager;
import lf21.modules.Module;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModulesConfig
extends Config {
    private static final Logger LOGGER = LogManager.getLogger(ModulesConfig.class);

    public ModulesConfig() {
        super("modules.cfg");
    }

    @Override
    public void read(BufferedReader bufferedReader) throws IOException {
        String string;
        ModuleManager moduleManager = LF21Client.getInstance().getModuleManager();
        while ((string = bufferedReader.readLine()) != null) {
            String[] stringArray = string.split(":", 3);
            if (stringArray.length != 3) {
                LOGGER.error("Failed to read line {}!", (Object)string);
                continue;
            }
            String string2 = stringArray[0];
            if ("AutoWebPlace".equalsIgnoreCase(string2)) {
                string2 = "AutoWeb";
            }
            int n2 = Integer.parseInt(stringArray[1]);
            boolean bl = Boolean.parseBoolean(stringArray[2]);
            try {
                Module module = moduleManager.getModule(string2);
                module.setKey(n2);
                module.setEnabled(bl);
            }
            catch (ModuleNotFoundException moduleNotFoundException) {
                LOGGER.error("Failed to find module {}!", (Object)string2);
            }
        }
    }

    @Override
    public void save(BufferedWriter bufferedWriter) throws IOException {
        ModuleManager moduleManager = LF21Client.getInstance().getModuleManager();
        ArrayList<Module> arrayList = new ArrayList<Module>(moduleManager.getModules());
        for (Module module : arrayList) {
            bufferedWriter.write(String.format("%s:%d:%s\n", module.getName(), module.getKey(), module.isEnabled()));
        }
    }
}

