
package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvLineTableModel extends AbstractTableModel {
    
    private ArrayList<InvoiceLines> linesArr;
    private String[] cols = {"Item Name", "Item Price", "Count", "Line Total"};

    public InvLineTableModel(ArrayList<InvoiceLines> linesArr) {
        this.linesArr = linesArr;
    }
    
   
    @Override
    public int getRowCount() {
        return linesArr == null ? 0 : linesArr.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (linesArr == null){
            return "";
        } else {
        InvoiceLines line = linesArr.get(rowIndex);
        switch (columnIndex) {
            case 0:  return line.getInvItem();
            case 1:  return line.getPrice();
            case 2:  return line.getCount();
            case 3:  return line.getLineTotal();
            }
        
        } return "";
    }
    
     @Override
    public String getColumnName(int column) {
         return cols[column];
    }
    
}
    

