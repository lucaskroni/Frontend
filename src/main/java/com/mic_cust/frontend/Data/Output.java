package com.mic_cust.frontend.Data;

import java.io.Serializable;
import java.util.List;

public class Output implements Serializable {

    public List<Module> modScopes;
    public String CompName;

    public Output(List<Module> modules, String name){
        this.CompName = name;
        this.modScopes = modules;
    }

    public Output(){}

    public List<Module> getModScopes() {
        return modScopes;
    }

    public String getCompName() {
        return CompName;
    }

    public void setModScopes(List<Module> modScopes) {
        this.modScopes = modScopes;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public void createJsonConfig(){
        //Create JSON Config for the Java-App
    }
}

