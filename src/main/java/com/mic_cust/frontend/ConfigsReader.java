package com.mic_cust.frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mic_cust.frontend.Data.Module;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ConfigsReader {

    protected final String _PTH = "src\\main\\resources\\Data\\configs.json";

    //=================================STATIC_KEYS===============================

    //===================================Configs=================================
    private final String KEY_CONFIGS_OBJ = "configs";
    private final String KEY_SPLITTER = "Splitter";
    private final String KEY_RELOADPS = "ReloadPS_min";

    private final String KEY_QUOT_TYPES = "Quotation_Types";
    //=================================ModuleScopes==============================
    private final String KEY_MODSCOPE = "ModulesScopes";
    //===========================================================================

    public void readConfig(){
        JSONParser parser = new JSONParser();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(_PTH).getAbsolutePath()))) {
            setConfigs((JSONObject) parser.parse(reader));
        } catch (IOException | ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setConfigs(JSONObject obj) {
        Gson g = new GsonBuilder().create();
        JSONObject configsObj = (JSONObject) obj.get(KEY_CONFIGS_OBJ);

        Configs.SPLITTER.setSplitter((String)configsObj.get(KEY_SPLITTER));
        JSONArray quotations = (JSONArray)(configsObj.get(KEY_QUOT_TYPES));
        ArrayList<Object> quotationsArr = new ArrayList<>(Arrays.asList(g.fromJson(quotations.toJSONString(), Object[].class)));
        ArrayList<String> StringArr = new ArrayList<>();
        for (Object o: quotationsArr) {
            StringArr.add(o.toString());
        }
        Configs.QUOTATIONS.setQuotations(StringArr.toArray(new String[0]));



        JSONArray arr = (JSONArray)(obj.get(KEY_MODSCOPE));
        ArrayList<Module> mods = new ArrayList<>(Arrays.asList(g.fromJson(arr.toJSONString(), Module[].class)));
        Configs.MOD_SCOPES.setModScopes(mods);
    }

}
