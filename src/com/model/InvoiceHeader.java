
package com.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    
    private int invNum;
    private Date invDate;
    private String custName;
    private ArrayList<InvoiceLines> lines;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invNum, Date invDate, String custName) {
        this.invNum = invNum;
        this.invDate = invDate;
        this.custName = custName;
    }
    
    //Creating getter and setter for defind variables
   
    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public ArrayList<InvoiceLines> getLines() {
        if (lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLines> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal() {
        double total = 0.0;
       
        for (int i = 0; i < getLines().size(); i++){
            total += getLines().get(i).getLineTotal();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return invNum + "," + dateFormat.format(invDate) + "," + custName;
   
    }
    
}
     
     

