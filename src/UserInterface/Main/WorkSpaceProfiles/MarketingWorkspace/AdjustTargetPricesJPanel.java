package UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace;

import TheBusiness.Business.Business;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aksha
 */
public class AdjustTargetPricesJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Business business;
    Product selectedProduct;
    
    public AdjustTargetPricesJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        loadProductsBelowTarget();
        loadProductsAboveTarget();
    }
    
    /**
     * Load products performing below target
     */
    private void loadProductsBelowTarget() {
        clearTable(belowTargetTable);
        
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                ProductSummary summary = new ProductSummary(product);
                
                // Only show products with more sales below target
                if (summary.getNumberBelowTarget() > summary.getNumberAboveTarget()) {
                    Object[] row = new Object[6];
                    row[0] = supplier.getName();
                    row[1] = product.toString();
                    row[2] = product.getTargetPrice();
                    row[3] = summary.getNumberBelowTarget();
                    row[4] = summary.getSalesRevenues();
                    row[5] = product.getTargetPrice(); // New target price (editable)
                    
                    ((DefaultTableModel) belowTargetTable.getModel()).addRow(row);
                }
            }
        }
    }
    
    /**
     * Load products performing above target
     */
    private void loadProductsAboveTarget() {
        clearTable(aboveTargetTable);
        
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                ProductSummary summary = new ProductSummary(product);
                
                // Only show products with more sales above target
                if (summary.getNumberAboveTarget() > summary.getNumberBelowTarget()) {
                    Object[] row = new Object[6];
                    row[0] = supplier.getName();
                    row[1] = product.toString();
                    row[2] = product.getTargetPrice();
                    row[3] = summary.getNumberAboveTarget();
                    row[4] = summary.getSalesRevenues();
                    row[5] = product.getTargetPrice(); // New target price (editable)
                    
                    ((DefaultTableModel) aboveTargetTable.getModel()).addRow(row);
                }
            }
        }
    }
    
    private void clearTable(javax.swing.JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        belowTargetTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        aboveTargetTable = new javax.swing.JTable();
        lowerPricesButton = new javax.swing.JButton();
        raisePricesButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Adjust Target Prices");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel2.setText("Products Below Target (Consider Lowering Price)");

        belowTargetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Supplier", "Product", "Current Target", "Sales Below", "Revenue", "New Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(belowTargetTable);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel3.setText("Products Above Target (Consider Raising Price)");

        aboveTargetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Supplier", "Product", "Current Target", "Sales Above", "Revenue", "New Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(aboveTargetTable);

        lowerPricesButton.setText("Apply Lower Prices");
        lowerPricesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowerPricesButtonActionPerformed(evt);
            }
        });

        raisePricesButton.setText("Apply Higher Prices");
        raisePricesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                raisePricesButtonActionPerformed(evt);
            }
        });

        backButton.setText("<< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lowerPricesButton)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(raisePricesButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowerPricesButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(raisePricesButton)
                    .addComponent(backButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void lowerPricesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowCount = belowTargetTable.getRowCount();
        int updatedCount = 0;
        
        for (int i = 0; i < rowCount; i++) {
            String supplierName = (String) belowTargetTable.getValueAt(i, 0);
            String productName = (String) belowTargetTable.getValueAt(i, 1);
            Object newTargetObj = belowTargetTable.getValueAt(i, 5);
            
            try {
                int newTarget = Integer.parseInt(newTargetObj.toString());
                int currentTarget = (int) belowTargetTable.getValueAt(i, 2);
                
                if (newTarget != currentTarget) {
                    // Find and update the product
                    Supplier supplier = business.getSupplierDirectory().findSupplier(supplierName);
                    if (supplier != null) {
                        for (Product p : supplier.getProductCatalog().getProductList()) {
                            if (p.toString().equals(productName)) {
                                p.updateProduct(p.getFloorPrice(), p.getCeilingPrice(), newTarget);
                                updatedCount++;
                                break;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
            }
        }
        
        JOptionPane.showMessageDialog(this, 
            "Updated " + updatedCount + " product target prices",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        
        loadProductsBelowTarget();
    }

    private void raisePricesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rowCount = aboveTargetTable.getRowCount();
        int updatedCount = 0;
        
        for (int i = 0; i < rowCount; i++) {
            String supplierName = (String) aboveTargetTable.getValueAt(i, 0);
            String productName = (String) aboveTargetTable.getValueAt(i, 1);
            Object newTargetObj = aboveTargetTable.getValueAt(i, 5);
            
            try {
                int newTarget = Integer.parseInt(newTargetObj.toString());
                int currentTarget = (int) aboveTargetTable.getValueAt(i, 2);
                
                if (newTarget != currentTarget) {
                    // Find and update the product
                    Supplier supplier = business.getSupplierDirectory().findSupplier(supplierName);
                    if (supplier != null) {
                        for (Product p : supplier.getProductCatalog().getProductList()) {
                            if (p.toString().equals(productName)) {
                                p.updateProduct(p.getFloorPrice(), p.getCeilingPrice(), newTarget);
                                updatedCount++;
                                break;
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                // Skip invalid entries
            }
        }
        
        JOptionPane.showMessageDialog(this, 
            "Updated " + updatedCount + " product target prices",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        
        loadProductsAboveTarget();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }

    // Variables declaration
    private javax.swing.JTable aboveTargetTable;
    private javax.swing.JButton backButton;
    private javax.swing.JTable belowTargetTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton lowerPricesButton;
    private javax.swing.JButton raisePricesButton;
    // End of variables declaration
}