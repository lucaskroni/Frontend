package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Config;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    @GetMapping("/form")
    public String showForm(Model mdl){
        //Now to expose the thingy yeah
        mdl.addAttribute("configuration",new Config());
        return "InputForm";
    }

    @PostMapping("/form")
    public String saveConfigs(@ModelAttribute Config c, Model mdl){
        mdl.addAttribute("configuration", c);
        return "actPage";
    }
}
