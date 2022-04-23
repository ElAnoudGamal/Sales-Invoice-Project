
package com.view;


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvHeaderDialog extends JDialog {
    
    private JTextField invoiceDateField;
    private JLabel invoiceDateLbl;
    private JTextField customerNameField;
    private JLabel customerNameLbl;
    private JButton okButton;
    private JButton cancelButton;

    public InvHeaderDialog(InvoiceForm form) {
        invoiceDateLbl = new JLabel("Invoice Date");
        invoiceDateField = new JTextField(20);
        customerNameLbl = new JLabel("Customer Name");
        customerNameField = new JTextField(20);
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("newInvOk");
        cancelButton.setActionCommand("newInvCancel");
        
        okButton.addActionListener(form.getActionL());
        cancelButton.addActionListener(form.getActionL());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLbl);
        add(invoiceDateField);
        add(customerNameLbl);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
     
      }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

 }
        
    
    

   
    

   
    
    
    
    
   
    
    

