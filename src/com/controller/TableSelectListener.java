
package com.controller;

import com.model.InvLineTableModel;
import com.model.InvoiceHeader;
import com.model.InvoiceLines;
import com.view.InvoiceForm;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TableSelectListener implements ListSelectionListener {
    
    private InvoiceForm form;

    public TableSelectListener(InvoiceForm form) {
        this.form = form;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvoiceInd = form.getInvHeadTbl().getSelectedRow();
        System.out.println("Invoice selected: " + selectedInvoiceInd);
        if (selectedInvoiceInd != -1) {
        InvoiceHeader selectedInvoice = form.getInvoiceArr().get(selectedInvoiceInd);
        ArrayList<InvoiceLines> lines = selectedInvoice.getLines();
        InvLineTableModel lineTableModel = new InvLineTableModel(lines);
        form.setLinesArr(lines);
        form.getInvLineTbl().setModel(lineTableModel);
        form.getInvNumLabel().setText(""+selectedInvoice.getInvNum());
        form.getInvDateLabel().setText(InvoiceForm.dF.format(selectedInvoice.getInvDate()));
        form.getCustNameLabel().setText(selectedInvoice.getCustName());
        form.getInvTotalLabel().setText(""+selectedInvoice.getInvoiceTotal());
      }
    
   }
    
}
