package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Conv_Output;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ConfigsWriter {

    protected final String WRT_PTH = "src\\main\\resources\\Data\\EnteredConfigs.json";
    protected final String RD_PTH = "src\\main\\resources\\Data\\TemplateJson\\TempEntrdJSON.json";
    public void buildConfigs(Conv_Output output) {
        new File(new File(WRT_PTH).getAbsolutePath()).delete(); //Delete File
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(WRT_PTH).getAbsolutePath())))){
            writer.write(createJSONStr(output));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String createJSONStr(Conv_Output output){
        JSONObject obj = readInTemp();

    }

    private void setFilterRep(JSONObject obj, Conv_Output out){

    }

    private void setSelected_ModuleScopes(JSONObject obj, Conv_Output out){

    }

    private void setExtras(JSONObject obj, Conv_Output out){

    }

    private JSONObject readInTemp() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(RD_PTH).getAbsolutePath())))){
            Object object = new JSONParser().parse(reader);
            return (JSONObject) object;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
