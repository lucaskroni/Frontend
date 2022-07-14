package com.mic_cust.frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mic_cust.frontend.Data.Module;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConfigsReader {

    protected final String _PTH = "src\\main\\resources\\Data\\configs.json";

    public void readConfig(){
        StringBuilder bob = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(_PTH).getAbsolutePath()))) {
            while(reader.ready()){
                bob.append(reader.readLine());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            Gson g = new GsonBuilder().create();
            ArrayList<Module> mods = new ArrayList<>(Arrays.asList(g.fromJson(bob.toString(), Module[].class)));
            Configs.MOD_SCOPES.setModScopes(mods);
        }
    }

}
