package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Module;

import java.util.ArrayList;

public enum Configs {
    NAME(""), //Useless but idk maybe someone need it someday
    MOD_SCOPES(new ArrayList<Module>());

    public String name; //Eh yeah that is a Karl der Komplitze von Useless
    public ArrayList<Module> modScopes;

    private Configs(String name){
        this.name = name;
    } //OMG DJ Another Khaled

    private Configs(ArrayList<Module> modScopes){
        this.modScopes = modScopes;
    }

    public ArrayList<Module> getModScopes() {
        return modScopes;
    }

    public String getName() {
        return name;
    } //What nah that must be veryyyyyy Useless

    public void setModScopes(ArrayList<Module> modScopes) {
        this.modScopes = modScopes;
    }

    public void setName(String name) {
        this.name = name;
    } //I only see useless here
}
