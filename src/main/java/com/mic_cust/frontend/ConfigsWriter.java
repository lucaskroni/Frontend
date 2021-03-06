package com.mic_cust.frontend;

import Data.Helpers.Helper_Global;
import Runnables.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mic_cust.frontend.Data.Conv_Output;
import Writers.ConfigWirter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfigsWriter {

    //=================================SPLITTERs=========================================

    private final String SPLITTER_PHASEMSs = ";";
    private final String SPLITTER_PHASE = "%%%%";

    private final String SPLITTER_OPTIONALS = "WWWW";

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
    private final String Key_Selected_Phases = "Selected_Phases";
    private final String Key_Selected_Optionals = "Selected_Optionals";
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
        Helper_Global.setWORKBOOK(null);
        ConfigWirter writer = new ConfigWirter();
        writer.write(createJSONStr(output));
        Main.main(new String[0]); //That is on god never gonna work

    }

    public Map<String, ArrayList<String>> createPhases(Conv_Output output){

        Map<String, ArrayList<String>> map = new HashMap<>();
        String[] splitFirst = output.getPhases().split(SPLITTER_PHASEMSs);
        for(String item : splitFirst){
            String[] splitSec = item.split(SPLITTER_PHASE);
            if(map.containsKey(splitSec[0])){
                map.get(splitSec[0]).add(splitSec[1]);
            }else{
                ArrayList<String> strings = new ArrayList<>();
                strings.add(splitSec[1]);
                map.put(splitSec[0], strings);
            }
        }
        return map;
    }

    public String createJSONStr(Conv_Output output){
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //This man right here is just for the pretty printing
        JSONObject obj = readInTemp();
        setFilterReps(obj, output);
        setSelected_ModuleScopes(obj, output);
        if(!output.getPhases().equals("") && output.getPhases() != null) {
            setSelected_Phases(obj, output);
        }
        if(!output.getOptionals().equals("") && output.getOptionals() != null){
            setSelected_Optionals(obj, output);
        }
        setModuleSplitter(obj, output);
        setExtras(obj, output);
        return gson.toJson(obj);
    }

    private void setSelected_Optionals(JSONObject obj, Conv_Output output) {
        String[] OptionalMSs = createOptionals(output);
        obj.put(Key_Selected_Optionals, OptionalMSs);
    }


    private String[] createOptionals(Conv_Output output) {
        String[] OptionalMSs = output.getOptionals().
                split(SPLITTER_OPTIONALS);
        return OptionalMSs;
    }

    private void setFilterReps(JSONObject obj, Conv_Output out){
        JSONObject FltrReps = new JSONObject();
        FltrReps.put(Key_Rep_Company, out.CompName);
        //Add Title_Rep, Quotation_Rep, Maintenance_Rep, Duration_Rep
        FltrReps.put(Key_Rep_Title, out.Title);
        FltrReps.put(Key_Rep_Quotation, out.Quotation);
        FltrReps.put(Key_Rep_Maintenance, (double)out.Maintenance);
        FltrReps.put(Key_Rep_Duration, out.Duration);
        obj.put(Key_Filter_RepsObj, FltrReps);
    }

    private void setSelected_ModuleScopes(JSONObject obj, Conv_Output out){
        //Pretty eh yeah straight forward but look it's less work which hmm idk sus to me
        JSONArray MSs = new JSONArray();
        if(!out.getPhases().equals("") && out.getPhases() != null && !out.getOptionals().equals("") && out.getOptionals() != null){
            Map<String, ArrayList<String>> map = createPhases(out);
            for(Map.Entry<String, ArrayList<String>> item : map.entrySet()){
                for(String str : item.getValue()){
                    MSs.add(str);
                }
            }
            if(out.TempScopeMod.length > MSs.size()){
                for(String Ms : out.TempScopeMod){
                    if(!MSs.contains(Ms) && !Arrays.asList(createOptionals(out)).contains(Ms)){
                        MSs.add(Ms);
                    }
                }
            }
            for(String Ms : out.TempScopeMod){
                if(Arrays.asList(createOptionals(out)).contains(Ms)){
                    MSs.add(Ms);
                }
            }
        } else if(!out.getOptionals().equals("") && out.getOptionals() != null){
            for(String Ms : out.TempScopeMod){
                if(!MSs.contains(Ms) && !Arrays.asList(createOptionals(out)).contains(Ms)){
                    MSs.add(Ms);
                }
            }
        for(String Ms : out.TempScopeMod){
            if(Arrays.asList(createOptionals(out)).contains(Ms)){
                MSs.add(Ms);
            }
        }
        } else if(!out.getPhases().equals("") && out.getPhases() != null ){
            Map<String, ArrayList<String>> map = createPhases(out);
            for(Map.Entry<String, ArrayList<String>> item : map.entrySet()){
                for(String str : item.getValue()){
                    MSs.add(str);
                }
            }
            if(out.TempScopeMod.length > MSs.size()){
                for(String Ms : out.TempScopeMod){
                    if(!MSs.contains(Ms)){
                        MSs.add(Ms);
                    }
                }
            }
        }else {
            for (String item : out.TempScopeMod) {
                if (item != null) {
                    MSs.add(item);
                }
            }
        }
        obj.put(Key_Selected_MSs, MSs);
    }

    private void setModuleSplitter(JSONObject obj, Conv_Output out){
        obj.put(Key_Selected_Splitter, Configs.SPLITTER.getSplitter());
    }

    private void setSelected_Phases(JSONObject obj, Conv_Output output) {
        obj.put(Key_Selected_Phases, makePhases(output));
    }

    private JSONArray makePhases(Conv_Output output) {
        JSONArray array = new JSONArray();
        Map<String, ArrayList<String>> mapPhases = createPhases(output);
        for(Map.Entry<String, ArrayList<String>> entry : mapPhases.entrySet()){
            JSONObject jsnObj = new JSONObject();
            jsnObj.put(entry.getKey(), entry.getValue());
            array.add(jsnObj);
        }
        return array;
    }

    private void setExtras(JSONObject obj, Conv_Output out){
        setPaths(obj, out);
        setDates(obj, out);
        setDailyRates(obj, out);
        setPercentages(obj, out);
    }

    private void setPercentages(JSONObject obj, Conv_Output out) {
        JSONObject pctgObj = new JSONObject();
        if(out.getPcg_SeniorMNG() != 0){
            pctgObj.put(Key_Sen_Pcg, out.getPcg_SeniorMNG());
        }
        if(out.getPcg_ProjectMNG() != 0){
            pctgObj.put(Key_Pro_Pcg, out.getPcg_ProjectMNG());
        }
        if(!pctgObj.isEmpty()){
            obj.put(Key_PCG_Obj, pctgObj);
        }
    }

    private void setDailyRates(JSONObject obj, Conv_Output out) {
        JSONObject dailyRatesObj = new JSONObject();
        if(out.getDr_SeniorMMT() != 0){
            dailyRatesObj.put(Key_DR_SenMMT, out.Dr_SeniorMMT);
        }
        if (out.getDr_ProjectMMT() != 0){
            dailyRatesObj.put(Key_DR_ProMMT, out.Dr_ProjectMMT);
        }
        if(out.getDr_Developer() != 0){
            dailyRatesObj.put(Key_DR_Dev, out.Dr_Developer);
        }
        if(out.getDr_Support() != 0){
            dailyRatesObj.put(Key_DR_Sup, out.Dr_Support);
        }
        if(!dailyRatesObj.isEmpty()){
            obj.put(Key_DRs_Obj, dailyRatesObj);
        }
    }

    private void setDates(JSONObject obj, Conv_Output out) {
        JSONObject datesObj = new JSONObject();
        if(out.getCreatedOn_Date() != null || !out.getCreatedOn_Date().equals("")){
            datesObj.put(Key_Date_Created_On, out.getCreatedOn_Date());
        }
        if(out.getTillValid_Date() != null || !out.getTillValid_Date().equals("")){
            datesObj.put(Key_Date_Valid_Till, out.getTillValid_Date());
        }
        if(!datesObj.isEmpty()){
            obj.put(Key_Date_Object, datesObj);
        }
    }

    private void setPaths(JSONObject obj, Conv_Output out) {
        JSONObject pathsObj = new JSONObject();
        pathsObj.put(Key_Slct_Out, out.Pth_ExcelOut);
        if(out.getPth_Estimation() != null || !out.getPth_Estimation().equals("")){
            pathsObj.put(Key_Slct_Est, out.Pth_Estimation);
        }
        if(out.getPth_Statics() != null || !out.getPth_Statics().equals("")){
            pathsObj.put(Key_Slct_Sta, out.Pth_Statics);
        }
        obj.put(Key_Paths_Obj, pathsObj);
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
