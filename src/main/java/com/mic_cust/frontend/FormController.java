package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Module;
import com.mic_cust.frontend.Data.Conv_Output;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;


@Controller
public class FormController {

    //TODO: We have to build everything up over form elements so yeah lets gooo
    //TODO: Ma man you got this u a legend come on
    //TODO: OK next time is the fun or eehh rather fun part of the styling at or even earlier we make the config-Json that goes in the "Backend" (which I don't really see as a Backend) and does great thing
    //TODO: Yoo have a good start in the week ma man you got this and yo you have to keep training (consistence and yeah also dont lay in bed before everything is done AMEN)

    protected ArrayList<Module> inModules;
    protected String inName;
    protected Conv_Output outConvs;

    public FormController(){
        new ConfigsReader().readConfig();
        inModules = Configs.MOD_SCOPES.getModScopes();
        inName = Configs.NAME.getName();
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

    @ModelAttribute("inScopesNames")
    public ArrayList<String> inScopesNames(){
        ArrayList<String> output = new ArrayList<>();
        inModules.stream().forEach(modules -> {
            Arrays.stream(modules.getScopes()).forEach(x -> {
                output.add(modules.getModule() + ":" + x);
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
        return "InputForm";
    }

    @PostMapping("/form")
    public String saveConfigs(@ModelAttribute("outVal_conv") Conv_Output out, Model mdl){
        mdl.addAttribute("outPut", out);
        return "actPage";
    }
}
