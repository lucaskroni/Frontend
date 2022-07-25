package com.mic_cust.frontend.Threads;

import com.mic_cust.frontend.Data.Conv_Output;
import com.mic_cust.frontend.FormController;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileInputStream;

public class FileWatcher implements Runnable{

    private String FileName;

    private Model model;

    public FileWatcher(String fn, Model mdl){
        this.FileName = fn;
        this.model = mdl;
    }

    @Override
    public void run() {
        if(new File(FileName).exists()){
            System.out.println("done");
            model.addAttribute("done", true);
            int o = 0;
        }else{
            System.out.println("another one dj khaled leeets go");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.run();
        }
    }
}
