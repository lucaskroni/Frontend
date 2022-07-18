package com.mic_cust.frontend.Data;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conv_Output implements Serializable {


    public String CompName;
    public String[] TempScopeMod;

    public void createJsonConfig(){
        //Create JSON Config for the Java-App
    }

}

