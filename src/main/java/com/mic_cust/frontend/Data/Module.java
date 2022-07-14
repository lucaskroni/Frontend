package com.mic_cust.frontend.Data;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Module implements Serializable {

    public String module;
    public String[] scopes;

    public Module(String mod, String[] scopes){
        this.module = mod;
        this.scopes = scopes;
    }

    public Module(){}

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
