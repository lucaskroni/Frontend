package com.mic_cust.frontend;

import com.mic_cust.frontend.Data.Module;

import java.util.ArrayList;

public enum Configs {
    //Configs
    SPLITTER(""),

    QUOTATIONS(new String[0]),
    //Module & Scopes
    MOD_SCOPES(new ArrayList<Module>());

    //Configs
    public String splitter;

    public String[] Quotations;

    //Module & Scopes
    public ArrayList<Module> modScopes;

    private Configs(String splitter) {
        this.splitter = splitter;
    }

    private Configs(String[] quotations) {
        this.Quotations = quotations;
    }

    private Configs(ArrayList<Module> modScopes) {
        this.modScopes = modScopes;
    }

    public ArrayList<Module> getModScopes() {
        return modScopes;
    }

    public String getSplitter() {
        return splitter;
    }

    public void setModScopes(ArrayList<Module> modScopes) {
        this.modScopes = modScopes;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public void setQuotations(String[] quotations) {
        Quotations = quotations;
    }

    public String[] getQuotations() {
        return Quotations;
    }
}

