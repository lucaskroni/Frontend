package com.mic_cust.frontend.Threads;

import com.mic_cust.frontend.ConfigsWriter;
import com.mic_cust.frontend.Data.Conv_Output;

import java.io.File;

public class FileWriter implements Runnable{

    private Conv_Output output;

    private String FileName;

    private ConfigsWriter writer;

    public FileWriter(Conv_Output out, String name, ConfigsWriter writer){
        this.output = out;
        this.FileName = name;
        this.writer = writer;
    }

    @Override
    public void run() {
        writer.buildConfigs(output);
        if(new File(FileName).exists()){
            System.out.println("done");
        }
    }
}
