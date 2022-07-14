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
    //TODO: Schau in das InputForm.html und versuch irgendwie in dem javaScript inline block des so zu lösen das wenn sich das Module von dem Dropdown ändert sich die angeführten Checkboxen auch ändern
    //TODO: Sonst kannst du auch machen das du pro module eine Reiche von checkboxen machst für die scopes aber nur die viseble machst welche gerade ausgewählt sind hmmm...
    //TODO: OK do kannst nd in diesem inline Script wos auf th: Vars zuweisen hmm wie geht des
    //TODO: Guten Morgen ma man nothing is impossible außer thymeleaf na joke u got this everything is possible
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
