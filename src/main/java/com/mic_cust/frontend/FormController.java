package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.mic_cust.frontend.ConfigsReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FormController {

    //TODO: We have to build everything up over form elements so yeah lets gooo
    //TODO: Hello tomorrow me i hope you slept well but yeah today we go on and try to make a form which can read all configs from the User
    //TODO: Configs are: Company Name, Module with Scopes --> List of strings
    //TODO: Ma man you got this u a legend come on
    public FormController(){
        new ConfigsReader().readConfig();
    }

    @GetMapping("/form")
    public String showForm(Model mdl){
        //Now to expose the thingy yeah
        mdl.addAttribute("modules", Configs.MOD_SCOPES.getModScopes());
        mdl.addAttribute("output", new Output());
        mdl.addAttribute("str", "");
        return "InputForm";
    }

    @PostMapping("/form")
    public String saveConfigs(@ModelAttribute("out") Output out, Model mdl){
        mdl.addAttribute("outPut", out);
        return "actPage";
    }
}
