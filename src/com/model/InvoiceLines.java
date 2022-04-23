
package com.model;


public class InvoiceLines {
    
    private InvoiceHeader header;
    private String invItem;
    private double price;
    private int count;

    public InvoiceLines() {
    }

    public InvoiceLines(InvoiceHeader header, String invItem, double price, int count) {
        this.header = header;
        this.invItem = invItem;
        this.price = price;
        this.count = count;
    }
    
    //Creating getter and setter for defind variables
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getInvItem() {
        return invItem;
    }

    public void setInvItem(String invItem) {
        this.invItem = invItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getLineTotal(){
        return price * count;
    }

    @Override
    public String toString() {
        return header.getInvNum() + "," + invItem + "," + price + "," + count;                        
    }
   
}
