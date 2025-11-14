package UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace;

import TheBusiness.Business.Business;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aksha
 */
public class BrowseProductPricePerformanceJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Business business;
    Product selectedProduct;
    
    public BrowseProductPricePerformanceJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        loadAllProducts();
    }
    
    /**
     * Load all products from all suppliers into the table
     */
    private void loadAllProducts() {
        clearTable();
        
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                ProductSummary summary = new ProductSummary(product);
                
                Object[] row = new Object[8];
                row[0] = supplier.getName();
                row[1] = product.toString();
                row[2] = product.getTargetPrice();
                row[3] = summary.getSalesRevenues();
                row[4] = summary.getNumberAboveTarget();
                row[5] = summary.getNumberBelowTarget();
                row[6] = summary.getProductPricePerformance();
                row[7] = calculateStatus(summary);
                
                ((DefaultTableModel) productPerformanceTable.getModel()).addRow(row);
            }
        }
    }
    
    /**
     * Determine if product is performing above or below target
     */
    private String calculateStatus(ProductSummary summary) {
        int above = summary.getNumberAboveTarget();
        int below = summary.getNumberBelowTarget();
        
        if (above > below) {
            return "Above Target";
        } else if (below > above) {
            return "Below Target";
        } else {
            return "At Target";
        }
    }
    
    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) productPerformanceTable.getModel();
        model.setRowCount(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productPerformanceTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        txtSupplierSearch = new javax.swing.JTextField();
txtSupplierSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Supplier"));
txtSupplierSearch.setPreferredSize(new java.awt.Dimension(200, 80));

txtProductSearch = new javax.swing.JTextField();
txtProductSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Product"));
txtProductSearch.setPreferredSize(new java.awt.Dimension(200, 80));

btnSearch = new javax.swing.JButton("Search");
btnSearch.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSearchActionPerformed(evt);
    }
});

        setBackground(new java.awt.Color(0, 153, 153));

        productPerformanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Supplier", "Product", "Target Price", "Revenue", "Sales Above", "Sales Below", "Profit Margin", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productPerformanceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                productPerformanceTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(productPerformanceTable);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Browse Product Price Performance");

        backButton.setText("<< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
   .addComponent(txtSupplierSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(btnSearch)


)
.addGap(20, 20, 20)

                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refreshButton)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
    .addComponent(txtSupplierSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addComponent(txtProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addComponent(btnSearch)
)
.addGap(15, 15, 15)

                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadAllProducts();
    }

    private void productPerformanceTableMousePressed(java.awt.event.MouseEvent evt) {
        int selectedRow = productPerformanceTable.getSelectedRow();
        if (selectedRow >= 0) {
            String supplierName = (String) productPerformanceTable.getValueAt(selectedRow, 0);
            String productName = (String) productPerformanceTable.getValueAt(selectedRow, 1);
            
            Supplier supplier = business.getSupplierDirectory().findSupplier(supplierName);
            if (supplier != null) {
                for (Product p : supplier.getProductCatalog().getProductList()) {
                    if (p.toString().equals(productName)) {
                        selectedProduct = p;
                        break;
                    }
                }
            }
        }
    }

    // Variables declaration
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable productPerformanceTable;
    private javax.swing.JButton refreshButton;
    // End of variables declaration
}