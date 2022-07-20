package com.mic_cust.frontend.Data;

import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conv_Output implements Serializable {

    //-----> Required Output
    //Replacements:
    public String CompName;
    public String Quotation;
    public String Title;
    //Other Selectables:
    public String[] TempScopeMod;
    public String Pth_ExcelOut;
    //-----> Optional Output
    //Replaceable:
    @Nullable public String Maintenance;
    @Nullable public String Duration;
    //Paths:
    @Nullable public String Pth_Estimation;
    @Nullable public String Pth_Statics;
    //Dates:
    @Nullable public String TillValid_Date;
    @Nullable public String CreatedOn_Date;
    //Daily_Rates:
    @Nullable public String Dr_SeniorMMT;
    @Nullable public String Dr_ProjectMMT;
    @Nullable public String Dr_Developer;
    @Nullable public String Dr_Support;
    //Precentages:
    @Nullable public String Pcg_SeniorMNG;
    @Nullable public String Pcg_ProjectMNG;
    //------------>

}

