
package com.model;

import com.view.InvoiceForm;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvHeaderTableModel extends AbstractTableModel {
    
    private ArrayList<InvoiceHeader> invoiceArr;
    private String[] cols = {"Invoice No", "Invoice Date", "Customer Name", "Invoice Total"};

    public InvHeaderTableModel(ArrayList<InvoiceHeader> invoiceArr) {
        this.invoiceArr = invoiceArr;
    }
    

    @Override
    public int getRowCount() {
        return invoiceArr.size();
    }

    @Override
    public int getColumnCount() {
       return cols.length; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = invoiceArr.get(rowIndex);
        switch (columnIndex) {
            case 0:  return invoice.getInvNum();
            case 1:  return InvoiceForm.dF.format(invoice.getInvDate());
            case 2:  return invoice.getCustName();
            case 3:  return invoice.getInvoiceTotal();
           }
            return "";
        }
  
    @Override
    public String getColumnName(int column) {
         return cols[column];
    }
}
