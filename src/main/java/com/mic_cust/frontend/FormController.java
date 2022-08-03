package com.mic_cust.frontend;

import Data.ConfigReaders.ConfigReader_Content;
import Data.ConfigReaders.ConfigReader_Global;
import Data.ConfigReaders.ConfigReader_Normal;
import Data.ConfigReaders.ConfigReader_Summary;
import Data.ExcelData.ExcelReader;
import Data.Helpers.Helper_Global;
import Data.Helpers.Helper_Static_Rows;
import com.mic_cust.frontend.Data.Conv_Output;
import com.mic_cust.frontend.Data.Module;
import com.mic_cust.frontend.Threads.FileWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;


@Controller
public class FormController {

    //TODO: We have to build everything up over form elements so yeah lets gooo
    protected ArrayList<Module> inModules;
    protected Conv_Output outConvs;
    protected LocalDateTime lastLoad = null;

    protected ConfigsWriter Writer;

    public FormController(){
        new ConfigsReader().readConfig();
        inModules = Configs.MOD_SCOPES.getModScopes();
        outConvs = new Conv_Output();
        Writer = new ConfigsWriter();
    }

    private void readDefaults(){
        ConfigReader_Content reader_content = new ConfigReader_Content();
        ConfigReader_Global reader_global = new ConfigReader_Global();
        ConfigReader_Normal reader_normal = new ConfigReader_Normal();
        ConfigReader_Summary reader_summary = new ConfigReader_Summary();
        ExcelReader excelReader = new ExcelReader();
        setDefaults();
    }

    private void setDefaults() {
        outConvs.setMaintenance(Helper_Global.MAINTENANCE_DEFAULT);
        outConvs.setDuration(Integer.parseInt(Helper_Global.DURATION_DEFAULT + ""));
        outConvs.setPth_Estimation(Helper_Global.EXCEL_PATH);
        outConvs.setPth_Statics(Helper_Global.EXCEL_TEMPLATE_PATH);
        String[] roleNames = Helper_Global.ROLES.keySet().toArray(new String[0]);
        outConvs.setDr_SeniorMMT(Double.parseDouble(Helper_Global.ROLES.get(roleNames[0])));
        outConvs.setDr_ProjectMMT(Double.parseDouble(Helper_Global.ROLES.get(roleNames[1])));
        outConvs.setDr_Developer(Double.parseDouble(Helper_Global.ROLES.get(roleNames[2])));
        outConvs.setDr_Support(Double.parseDouble(Helper_Global.ROLES.get(roleNames[3])));
        outConvs.setPcg_SeniorMNG(Helper_Static_Rows.SENIOR_MNG_PERCENTAGE);
        outConvs.setPcg_ProjectMNG(Helper_Static_Rows.PROJECT_MNG_PERCENTAGE);
    }

    @ModelAttribute("outVal_conv")
    public Conv_Output outConvVal(){
        return outConvs;
    }

    @ModelAttribute("inModuleNames")
    public ArrayList<String> inModuleNames(){
        return (ArrayList<String>) inModules.stream().map(Module::getModule).collect(Collectors.toList());
    }

    @ModelAttribute("quotations")
    public String[] getQuotations(){
        return Configs.QUOTATIONS.getQuotations();
    }

    @ModelAttribute("inScopesNames")
    public ArrayList<String> inScopesNames(){
        ArrayList<String> output = new ArrayList<>();
        inModules.stream().forEach(modules -> {
            Arrays.stream(modules.getScopes()).forEach(x -> {
                output.add(modules.getModule() + Configs.SPLITTER.getSplitter() + x);
            });
        });
        return output;
    }

    @ModelAttribute("inModScopeMap")
    public HashMap<String, ArrayList<String>> inModScopeMap(){
        HashMap<String, ArrayList<String>> inMap = new HashMap<>();
        for (Module m:inModules) {
            inMap.put(m.module, new ArrayList<>(Arrays.asList(m.scopes)));
        }
        return inMap;
    }

    @GetMapping("/form")
    public String showForm(Model mdl){
        outConvs = new Conv_Output(); // Renew it because we could reload you know
        readDefaults();
        mdl.addAttribute("outConvs",outConvs);
        return "InputForm";
    }

    @PostMapping("/form")
    public String saveConfigs(@ModelAttribute("outVal_conv") Conv_Output out, Model mdl){
        mdl.addAttribute("done", false);
        outConvs = out;
        return "actPage";
    }

     @PostMapping("/done")
    public String donePage(@ModelAttribute("finishVal") Conv_Output out,Model mdl){
        mdl.addAttribute(out);
        FileWriter writer = new FileWriter(outConvs, outConvs.Pth_ExcelOut, Writer);
        writer.run();
        return "donePage";
     }



}
