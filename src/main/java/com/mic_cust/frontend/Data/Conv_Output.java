package com.mic_cust.frontend.Data;

import lombok.*;

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
    public String Maintenance;
    public String Duration;
    //Paths:
    public String Pth_Estimation;
    public String Pth_Statics;
    //Dates:
    public String TillValid_Date;
    public String CreatedOn_Date;
    //Daily_Rates:
    public String Dr_SeniorMMT;
    public String Dr_ProjectMMT;
    public String Dr_Developer;
    public String Dr_Support;
    //Precentages:
    public String Pcg_SeniorMNG;
    public String Pcg_ProjectMNG;
    //------------>

}

