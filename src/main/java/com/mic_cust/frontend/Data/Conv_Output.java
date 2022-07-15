package com.mic_cust.frontend.Data;

import com.mic_cust.frontend.Configs;
import lombok.*;
import org.yaml.snakeyaml.constructor.Construct;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conv_Output implements Serializable {

    public HashMap<String, Collection<String>> ModScopes; // String = Module , Collection<String> = Scopes
    public String CompName;

    public String TempModule;
    public String[] TempScope;

    public void createJsonConfig(){
        //Create JSON Config for the Java-App
    }

    private void add(String tempModule, String tempScope){
        if(ModScopes.containsKey(tempModule)){
            ModScopes.get(tempModule).add(tempScope);
        }else{
            ModScopes.put(tempModule, new ArrayList<>(Collections.singletonList(tempScope)));
        }
    }
}

