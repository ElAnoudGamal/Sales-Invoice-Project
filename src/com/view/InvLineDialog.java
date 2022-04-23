
package com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvLineDialog extends JDialog {
    
    private JTextField itemNameF;
    private JLabel itemNameL;
    private JTextField itemPriceF;
    private JLabel itemPriceL;
    private JTextField itemCountF;
    private JLabel itemCountL;
    private JButton okButton;
    private JButton cancelButton;

    public InvLineDialog(InvoiceForm form) {
        
        itemNameF = new JTextField(30);
        itemNameL = new JLabel("Item Name");
        
        itemPriceF = new JTextField(30);
        itemPriceL = new JLabel("Item Price");
        
        itemCountF = new JTextField(30);
        itemCountL = new JLabel("Item Count");
        
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("newLineOk");
        cancelButton.setActionCommand("newLineCancel");
        
        okButton.addActionListener(form.getActionL());
        cancelButton.addActionListener(form.getActionL());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameL);
        add(itemNameF);
        add(itemPriceL);
        add(itemPriceF);
        add(itemCountL);
        add(itemCountF);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getItemNameF() {
        return itemNameF;
    }

    public JTextField getItemPriceF() {
        return itemPriceF;
    }

    public JTextField getItemCountF() {
        return itemCountF;
    }

}
