
package com.controller;

import com.model.InvoiceHeader;
import com.model.InvHeaderTableModel;
import com.model.InvLineTableModel;
import com.model.InvoiceLines;
import com.view.InvHeaderDialog;
import com.view.InvLineDialog;
import com.view.InvoiceForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class InvoiceActionListener implements ActionListener {
    
    private InvoiceForm form;
    private InvHeaderDialog headerD;
    private InvLineDialog lineDialog;
    

    public InvoiceActionListener(InvoiceForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
          // calling load file function
          case "Load File" : loadFile();
              break;
          // calling save file function   
          case "Save File" : saveFile();  
              break;
          // calling create new invoice function    
          case "New Invoice" : createNewInvoice();  
              break;
          // calling delete invoice function    
          case "Delete Invoice" : deleteInvoice(); 
              break;
          // calling create new line function    
          case "New Line" : createNewLine();      
              break;
          // calling delete line function    
          case "Delete Line" : deleteLine();     
              break;
              
          case "newInvOk" : newInvDialogOk();    
              break;
              
          case "newInvCancel" : newInvDialogCancel();
              break;
              
          case "newLineOk" : newLineDialogOk();
              break;
              
          case "newLineCancel" : newLineDialogCancel();
              break;
        }
    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(form);
            if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fc.getSelectedFile();
            Path headerPath = Paths.get(headerFile.getAbsolutePath());
            List<String> headerLines = Files.readAllLines(headerPath);
            ArrayList<InvoiceHeader> invHeaders = new ArrayList<>();
            //Looping
            for (String headerLine : headerLines) {
                String[] array = headerLine.split(",");
                String st1 = array[0];
                String st2 = array[1];
                String st3 = array[2];
                int code = Integer.parseInt(st1);
                Date invoiceDate = InvoiceForm.dF.parse(st2);
                InvoiceHeader invHeader = new InvoiceHeader(code, invoiceDate, st3);
                invHeaders.add(invHeader);
            }
            form.setInvoiceArr(invHeaders);
            
            result = fc.showOpenDialog(form);
            if (result == JFileChooser.APPROVE_OPTION) {
                File lineFile = fc.getSelectedFile();
                Path linePath = Paths.get(lineFile.getAbsolutePath());
                List<String> lineLines = Files.readAllLines(linePath);
                ArrayList<InvoiceLines> invLines = new ArrayList<>();
                //Looping
                for (String lineLine : lineLines) {
                    String[] array = lineLine.split(",");
                    // Invoice number
                    String st1 = array[0];  
                    // Item name
                    String st2 = array[1];  
                    // Price
                    String st3 = array[2];
                    // Count
                    String st4 = array[3];   
                    int invoiceCode = Integer.parseInt(st1);
                    double price = Double.parseDouble(st3);
                    int count = Integer.parseInt(st4);
                    InvoiceHeader inv = form.getInvObj(invoiceCode);
                    InvoiceLines invLine = new InvoiceLines(inv, st2, price, count);
                    inv.getLines().add(invLine);
                }
                
              }
              InvHeaderTableModel invHeaderTableModel = new InvHeaderTableModel(invHeaders);
              form.setInvHeaderTableModel(invHeaderTableModel);
              form.getInvHeadTbl().setModel(invHeaderTableModel);
               System.out.println("Files read"); 
            }
             
        }  catch (IOException e) {
              JOptionPane.showMessageDialog(form, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }  catch (ParseException e) {
              JOptionPane.showMessageDialog(form, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        form.displayInvoices();
    }
    
    private void saveFile() {
        ArrayList<InvoiceHeader> invoiceArr = form.getInvoiceArr();
        JFileChooser fc = new JFileChooser();
        try {
        int result = fc.showSaveDialog(form);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fc.getSelectedFile();
            FileWriter headerFileWriter = new FileWriter(headerFile);
            String headers = "";
            String lines = "";
            for (InvoiceHeader invoice : invoiceArr) {
                headers += invoice.toString();
                headers += "\n";
                for (InvoiceLines line : invoice.getLines()) {
                    lines += line.toString();
                    lines +="\n";
                    
                  }
               }
            
            headers = headers.substring(0, headers.length()-1);
            lines = lines.substring(0, lines.length()-1);
            result = fc.showSaveDialog(form);
            File lineFile = fc.getSelectedFile();
            FileWriter lineFileWriter = new FileWriter(lineFile);
            headerFileWriter.write(headers);
            lineFileWriter.write(lines);
            headerFileWriter.close();
            lineFileWriter.close();
           }
        
        } catch (IOException e) {
            JOptionPane.showMessageDialog(form, e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        form.displayInvoices();
    }
    
    private void createNewInvoice() {
        headerD = new InvHeaderDialog(form);
        headerD.setVisible(true);  
    }

    private void deleteInvoice() {
        int selectedInvoiceInd = form.getInvHeadTbl().getSelectedRow();
        if (selectedInvoiceInd != -1){
            form.getInvoiceArr().remove(selectedInvoiceInd);   //delete selected invoice
            form.getInvHeaderTableModel().fireTableDataChanged();
            form.getInvLineTbl().setModel(new InvLineTableModel(null));
            form.setLinesArr(null);
            form.getInvNumLabel().setText("");
            form.getInvDateLabel().setText("");
            form.getCustNameLabel().setText("");
            form.getInvTotalLabel().setText("");
        }
        form.displayInvoices();
    }

    private void createNewLine() {
        lineDialog = new InvLineDialog(form);
        lineDialog.setVisible(true);  
    }

    private void deleteLine() {
     int selectedLineInd = form.getInvLineTbl().getSelectedRow();
     int selectedInvoiceInd = form.getInvHeadTbl().getSelectedRow();
     if (selectedLineInd != -1) {
         form.getLinesArr().remove(selectedLineInd);   //delete selected line
         InvLineTableModel lineTableModel = (InvLineTableModel) form.getInvLineTbl().getModel();
         lineTableModel.fireTableDataChanged();
         form.getInvHeaderTableModel().fireTableDataChanged();   //Refresh to remove/hide deleted line
         form.getInvTotalLabel().setText(""+form.getInvoiceArr().get(selectedInvoiceInd).getInvoiceTotal());
         form.getInvHeaderTableModel().fireTableDataChanged();
         form.getInvHeadTbl().setRowSelectionInterval(selectedInvoiceInd, selectedInvoiceInd);
       }
       form.displayInvoices();
    }

    private void newInvDialogOk() {
        headerD.setVisible(false);
        
        String customerName = headerD.getCustomerNameField().getText();
        String st = headerD.getInvoiceDateField().getText();
        Date date = new Date();
        try {
              date = InvoiceForm.dF.parse(st);
        } catch (ParseException e){
            JOptionPane.showMessageDialog(form, "Cannot change date format , reseting to today's date", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        int invNumber = 0;
        for (InvoiceHeader invoice : form.getInvoiceArr()){
            if (invoice.getInvNum() > invNumber){
                invNumber = invoice.getInvNum();
            }
        }
        invNumber++;
        
        InvoiceHeader newInvoice = new InvoiceHeader(invNumber, date , customerName);
        form.getInvoiceArr().add(newInvoice);
        form.getInvHeaderTableModel().fireTableDataChanged();
        headerD.dispose();
        headerD = null;
        form.displayInvoices();
    }

    private void newInvDialogCancel() {
        headerD.setVisible(false);
        headerD.dispose();
        headerD = null;
    }

    private void newLineDialogCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }
    
    private void newLineDialogOk() {
        lineDialog.setVisible(false);
        String itemName = lineDialog.getItemNameF().getText();
        String st1 = lineDialog.getItemPriceF().getText();
        String st2 = lineDialog.getItemCountF().getText();
        double price = 1;
        int count = 1;
        try {
            price = Double.parseDouble(st1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(form, "Cannot change price format", "Incorrect price format", JOptionPane.ERROR_MESSAGE);
        }
        try {
            count = Integer.parseInt(st2);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(form, "Cannot change number format", "Incorrect number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = form.getInvHeadTbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            InvoiceHeader invoiceHeader = form.getInvoiceArr().get(selectedInvHeader);
            InvoiceLines line = new InvoiceLines(invoiceHeader, itemName, price, count);
            form.getLinesArr().add(line);
            InvLineTableModel lineTableModel = (InvLineTableModel) form.getInvLineTbl().getModel();
            lineTableModel.fireTableDataChanged();
            form.getInvHeaderTableModel().fireTableDataChanged();
        }
        form.getInvHeadTbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialog.dispose();
        lineDialog = null;
        form.displayInvoices();
    }
    
}
