package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Configs {
    NAME(""),
    MOD_SCOPES(new ArrayList<Module>());

    public String name;
    public ArrayList<Module> modScopes;

    private Configs(String name){
        this.name = name;
    }

    private Configs(ArrayList<Module> modScopes){
        this.modScopes = modScopes;
    }

    public ArrayList<Module> getModScopes() {
        return modScopes;
    }

    public String getName() {
        return name;
    }

    public void setModScopes(ArrayList<Module> modScopes) {
        this.modScopes = modScopes;
    }

    public void setName(String name) {
        this.name = name;
    }
}
