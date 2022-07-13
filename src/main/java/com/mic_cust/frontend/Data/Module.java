package com.mic_cust.frontend.Data;

import java.util.List;
import java.util.Map;

public class Module {

    public String Module;
    public List<String> Scopes;

    public Module(String mod, List<String> scopes){
        this.Module = mod;
        this.Scopes = scopes;
    }

    public Module(){}

    public List<String> getScopes() {
        return Scopes;
    }

    public void setScopes(List<String> scopes) {
        Scopes = scopes;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String module) {
        Module = module;
    }
}
