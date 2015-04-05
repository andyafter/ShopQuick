package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.util.*;
import sg.edu.nus.iss.ussa.exception.*;

public class ProdDia extends JDialog {

    private Shopping manager;
    private StoreBase mainScreen;
    private String id;

    private JTextField idText;
    private JTextField nameText;
    //private JTextField categoryText;
    private JComboBox<String> categoryList;
    private JTextField descriptionText;
    private JTextField quantityText;
    private JTextField priceText;
    private JTextField barCodeText;
    private JTextField reorderQtyText;
    private JTextField orderQtyText;

    public ProdDia(Shopping manager, String title) {
        super(manager.getStoreWindow(), title);
        this.manager = manager;
        this.mainScreen = manager.getStoreWindow();
        initGUI();
        add("South", createAddBottomPanel());
        String code = (String) categoryList.getSelectedItem();
        this.id = manager.getNewProductIdByCategory(code);
        idText.setText(id);
    }

    public ProdDia(Shopping manager, String title, String id) {
        super(manager.getStoreWindow(), title);
        this.manager = manager;
        this.mainScreen = manager.getStoreWindow();
        this.id = id;
        initGUI();
        add("South", createModifyBottomPanel());
        Product p = manager.getProductById(id);
        setData(p.getProductId(), p.getName(), p.getCategory().getCode(), p.getBriefDescription(), p.getQuantityAvailable(),
                p.getPrice(), p.getBarCodeNumber(), p.getReorderQuantity(), p.getOrderQuantity());
        categoryList.setEnabled(false);
    }

    private void initGUI() {
        try {
            setLayout(new BorderLayout());
            add("Center", createCenterPanel());
            setSize(400, 300);
            setLocationRelativeTo(null);
            setModal(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private JPanel createCenterPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new EmptyBorder(10, 10, 0, 10));

        JPanel panel = new JPanel(new GridLayout(9, 1));
        panel.add(new JLabel("Category: "));
        panel.add(new JLabel("Id: "));
        panel.add(new JLabel("Name: "));
        panel.add(new JLabel("Description: "));
        panel.add(new JLabel("Price: "));
        panel.add(new JLabel("Avaliable Quantity: "));
        panel.add(new JLabel("Bar Code Number: "));
        panel.add(new JLabel("Reorder Quantity: "));
        panel.add(new JLabel("Order Quantity: "));
        p.add("West", panel);

        loadCategoryList();
        nameText = new JTextField();
        nameText.setDocument(new StringDocument());
        idText = new JTextField();
        idText.setEditable(false);
        //categoryText = new JTextField();
        descriptionText = new JTextField();
        descriptionText.setDocument(new StringDocument());
        priceText = new JTextField();
        priceText.setDocument(new DigitDocument());
        quantityText = new JTextField();
        quantityText.setDocument(new IntDocument());
        barCodeText = new JTextField();
        barCodeText.setDocument(new StringDocument());
        reorderQtyText = new JTextField();
        reorderQtyText.setDocument(new IntDocument());
        orderQtyText = new JTextField();
        orderQtyText.setDocument(new IntDocument());
        panel = new JPanel(new GridLayout(9, 1));

        panel.add(categoryList);
        panel.add(idText);
        panel.add(nameText);
        panel.add(descriptionText);
        panel.add(priceText);
        panel.add(quantityText);
        panel.add(barCodeText);
        panel.add(reorderQtyText);
        panel.add(orderQtyText);
        p.add("Center", panel);

        return p;
    }

    public void loadCategoryList() {
        categoryList = new JComboBox<String>();
        int lenght = manager.getCategoryList().size();
        if (lenght > 0) {
            for (int i = 0; i < lenght; i++) {
                categoryList.addItem(manager.getCategoryList().get(i).getCode());
            }
            categoryList.updateUI();
        }
    }

    public void setData(String id, String name, String categoryCode, String briefDescription,
            int quantityAvailable, double price, String barCode, int threshold, int orderQuantity) {
        //categoryList.setSelectedItem(categoryCode);
        nameText.setText(name);
        idText.setText(id);
        categoryList.setSelectedItem(categoryCode);
        descriptionText.setText(briefDescription);
        priceText.setText(Double.toString(price));
        quantityText.setText(Integer.toString(quantityAvailable));
        barCodeText.setText(barCode);
        reorderQtyText.setText(Integer.toString(threshold));
        orderQtyText.setText(Integer.toString(orderQuantity));

    }

    public boolean validateData() throws DataInputException {
        if (idText.getText().isEmpty()) {
            throw new DataInputException("Id cannot be void");
        } else if (nameText.getText().isEmpty()) {
            throw new DataInputException("Name cannot be void");
        } else if (descriptionText.getText().isEmpty()) {
            throw new DataInputException("description cannot be void");
        } else if (priceText.getText().isEmpty()) {
            throw new DataInputException("price cannot be void");
        } else if (quantityText.getText().isEmpty()) {
            throw new DataInputException("quantity cannot be void");
        } else if (barCodeText.getText().isEmpty()) {
            throw new DataInputException("barCode cannot be void");
        } else if (reorderQtyText.getText().isEmpty()) {
            throw new DataInputException("reorderQty cannot be void");
        } else if (orderQtyText.getText().isEmpty()) {
            throw new DataInputException("orderQty cannot be void");
        } else {
            return true;
        }
    }

    private JPanel createAddBottomPanel() {
        // TODO Auto-generated method stub
        JPanel panel = new JPanel(new FlowLayout());
        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //Product newProduct = new Product("4","animal","pig","something",12,20,"c123",100,200);
                //System.out.println("add");
                try {
                    if (validateData()) {
                        manager.addProduct(idText.getText(), getNameText(), getCategoryText(), getDescriptionText(),
                                getQuantityText(), getPriceText(), getBarCodeText(), getReorderQtyText(), getOrderQtyText());
                        mainScreen.getProductListPanel().refreshList();
                        dispose();
                    }
                } catch (DataInputException e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
                    //System.out.println(e.getMessage());
                    //e.printStackTrace();
                }
            }
        });
        panel.add(button);
        button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //setVisible(false);
                dispose();
            }
        });
        panel.add(button);
        categoryList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (categoryList.getSelectedIndex() != -1) {
                    String code = (String) categoryList.getSelectedItem();
                    idText.setText(manager.getNewProductIdByCategory(code));

                }
            }
        });
        return panel;
    }

    private JPanel createModifyBottomPanel() {
        // TODO Auto-generated method stub
        JPanel panel = new JPanel(new FlowLayout());
        JButton button = new JButton("Modify");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //Product newProduct = new Product("4","animal","pig","something",12,20,"c123",100,200);

                try {
                    if (validateData()) {
                        manager.modifyProduct(idText.getText(), getNameText(), getCategoryText(), getDescriptionText(), getQuantityText(),
                                getPriceText(), getBarCodeText(), getReorderQtyText(), getOrderQtyText());

                        //manager.modifyProduct(newProduct, index);
                        mainScreen.getProductListPanel().refreshList();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Invalid data", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (DataInputException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panel.add(button);

        button = new JButton("Delete");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                deleteVendorMouseClicked(arg0);
            }
        });
        panel.add(button);

        button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //setVisible(false);
                dispose();
            }
        });
        panel.add(button);
        return panel;
    }

    private boolean validDel(String productId) {
        boolean result = true;

        for (Transaction trans : manager.getTransactionList()) {
            for (CartItem ti : trans.getItemList()) {
                if (ti.getProduct() == manager.getProductById(productId)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    private void deleteVendorMouseClicked(ActionEvent evt) {
        if (validDel(idText.getText())) {
            String msg = "The product '" + idText.getText() + "' will be deleted";
            int n = JOptionPane.showConfirmDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                manager.deleteProduct(idText.getText());
                mainScreen.getProductListPanel().refreshList();
                dispose();
            }
        } else {
            String msg = "This product `" + idText.getText() + "` is associated with some transaction";
            JOptionPane.showMessageDialog(this, msg, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String getIdText() {
        return idText.getText();
    }

    public String getNameText() {
        return nameText.getText();
    }

    public String getCategoryText() {
        return (String) categoryList.getSelectedItem();
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    public int getQuantityText() {
        return Integer.parseInt(quantityText.getText());
    }

    public float getPriceText() {
        return Float.parseFloat(priceText.getText());
    }

    public String getBarCodeText() {
        return barCodeText.getText();
    }

    public int getReorderQtyText() {
        return Integer.parseInt(reorderQtyText.getText());
    }

    public int getOrderQtyText() {
        return Integer.parseInt(orderQtyText.getText());
    }

}
