package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Module;
import com.mic_cust.frontend.Data.Conv_Output;
import com.mic_cust.frontend.Threads.FileWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
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

    protected Thread runWriter;

    public FormController(){
        new ConfigsReader().readConfig();
        inModules = Configs.MOD_SCOPES.getModScopes();
        outConvs = new Conv_Output();
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
        FileWriter writer = new FileWriter(outConvs, outConvs.Pth_ExcelOut);
        writer.run();
        return "donePage";
     }
}
