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
    public double Maintenance;
    public int Duration;
    //Other Selectables:
    public String[] TempScopeMod;
    public String Pth_ExcelOut;
    //-----> Optional Output
    //Paths:
    public String Pth_Estimation;
    public String Pth_Statics;
    //Dates:
    public String TillValid_Date;
    public String CreatedOn_Date;
    //Daily_Rates:
    public double Dr_SeniorMMT = 0;
    public double Dr_ProjectMMT = 0;
    public double Dr_Developer = 0;
    public double Dr_Support = 0;
    //Precentages:
    public double Pcg_SeniorMNG;
    public double Pcg_ProjectMNG;
    //------------>

}

