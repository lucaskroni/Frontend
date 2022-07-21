package com.mic_cust.frontend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mic_cust.frontend.Data.Conv_Output;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ConfigsWriter {

    //=================================STATIC-KEYs=========================================

    //<-------------------FILTER_REPLACEMENTS----------------->
    private final String Key_Filter_RepsObj = "Filter_Replacements";
    private final String Key_Rep_Company = "Company_Replacement";
    private final String Key_Rep_Title = "Title_Replacement";
    private final String Key_Rep_Quotation = "Quotation_Replacement";
    private final String Key_Rep_Maintenance = "Maintenance_Replacement";
    private final String Key_Rep_Duration = "Duration_Replacement";
    //<---------------------SELECTED_INPUTS-------------------->
    private final String Key_Selected_MSs = "Selected_ModuleScopes";
    private final String Key_Selected_Splitter = "Selected_Array_Splitter";
    //<-------------------------PATHS-------------------------->
    private final String Key_Paths_Obj = "Paths";
    private final String Key_Slct_Out = "Selected_Out_Path";
    private final String Key_Slct_Est = "Selected_Estim_Path";
    private final String Key_Slct_Sta = "Selected_Statics_Path";
    //<----------------------DATES_INPUT----------------------->
    private final String Key_Date_Object = "Selected_Dates";
    private final String Key_Date_Valid_Till = "Selected_Valid_Till";
    private final String Key_Date_Created_On = "Selected_Created_On";
    //<----------------------DAILY_RATES----------------------->
    private final String Key_DRs_Obj = "Daily_Rates";
    private final String Key_DR_SenMMT = "DR_Senior_Management";
    private final String Key_DR_ProMMT = "DR_Project_Management";
    private final String Key_DR_Dev = "DR_Developer";
    private final String Key_DR_Sup = "DR_Support";
    //<--------------------PERCENTAGES-------------------------> //Here we mean the percentages of the Senior_Mng and the Project_Mng in the Price of a Scope some like that :)
    private final String Key_PCG_Obj = "Percentages"; //Write Percentage-Values in double
    private final String Key_Sen_Pcg = "Senior_Mng_Pcg";
    private final String Key_Pro_Pcg = "Project_Mng_Pcg";
    //=====================================================================================

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
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //This man right here is just for the pretty printing
        JSONObject obj = readInTemp();
        setFilterReps(obj, output);
        setSelected_ModuleScopes(obj, output);
        setModuleSplitter(obj, output);
        setExtras(obj, output);
        return gson.toJson(obj);
    }

    private void setFilterReps(JSONObject obj, Conv_Output out){
        JSONObject FltrReps = new JSONObject();
        FltrReps.put(Key_Rep_Company, out.CompName);
        //Add Title_Rep, Quotation_Rep
        FltrReps.put(Key_Rep_Title, out.Title);
        FltrReps.put(Key_Rep_Quotation, out.Quotation);
        //Optional Maintenance_Rep, Duration_Rep

        obj.put(Key_Filter_RepsObj, FltrReps);
    }

    private void setSelected_ModuleScopes(JSONObject obj, Conv_Output out){
        //Pretty eh yeah straight forward but look its less work which hmm idk sus to me
        JSONArray MSs = new JSONArray();
        for(String item : out.TempScopeMod){
            if(item != null){
                MSs.add(item);
            }
        }
        obj.put(Key_Selected_MSs, MSs);
    }

    private void setModuleSplitter(JSONObject obj, Conv_Output out){
        obj.put(Key_Selected_Splitter, Configs.SPLITTER.getSplitter());
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
