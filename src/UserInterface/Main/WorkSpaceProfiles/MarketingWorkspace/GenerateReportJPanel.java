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
public class GenerateReportJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Business business;
    
    public GenerateReportJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        generateReport();
    }
    
    /**
     * Generate comprehensive product performance report
     */
    private void generateReport() {
        clearTable();
        
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        int totalRevenue = 0;
        int totalProfit = 0;
        int totalSalesAbove = 0;
        int totalSalesBelow = 0;
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                ProductSummary summary = new ProductSummary(product);
                
                Object[] row = new Object[9];
                row[0] = supplier.getName();
                row[1] = product.toString();
                row[2] = product.getFloorPrice();
                row[3] = product.getCeilingPrice();
                row[4] = product.getTargetPrice();
                row[5] = summary.getSalesRevenues();
                row[6] = summary.getNumberAboveTarget();
                row[7] = summary.getNumberBelowTarget();
                row[8] = summary.getProductPricePerformance();
                
                ((DefaultTableModel) reportTable.getModel()).addRow(row);
                
                // Accumulate totals
                totalRevenue += summary.getSalesRevenues();
                totalProfit += summary.getProductPricePerformance();
                totalSalesAbove += summary.getNumberAboveTarget();
                totalSalesBelow += summary.getNumberBelowTarget();
            }
        }
        
        // Display summary metrics
        totalRevenueField.setText("$" + String.format("%,d", totalRevenue));
        totalProfitField.setText("$" + String.format("%,d", totalProfit));
        totalAboveField.setText(String.valueOf(totalSalesAbove));
        totalBelowField.setText(String.valueOf(totalSalesBelow));
        
        // Calculate overall performance percentage
        int totalSales = totalSalesAbove + totalSalesBelow;
        if (totalSales > 0) {
            double performancePercent = (totalSalesAbove * 100.0) / totalSales;
            performanceField.setText(String.format("%.1f%%", performancePercent));
        } else {
            performanceField.setText("N/A");
        }
    }
    
    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalRevenueField = new javax.swing.JTextField();
        totalProfitField = new javax.swing.JTextField();
        totalAboveField = new javax.swing.JTextField();
        totalBelowField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        performanceField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Final Product Performance Report");

        reportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Supplier", "Product", "Floor Price", "Ceiling Price", "Target Price", "Revenue", "Sales Above", "Sales Below", "Profit Margin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(reportTable);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Summary Statistics"));

        jLabel2.setText("Total Revenue:");

        jLabel3.setText("Total Profit Margin:");

        jLabel4.setText("Total Sales Above Target:");

        jLabel5.setText("Total Sales Below Target:");

        totalRevenueField.setEditable(false);
        totalRevenueField.setFont(new java.awt.Font("Dialog", 1, 12));

        totalProfitField.setEditable(false);
        totalProfitField.setFont(new java.awt.Font("Dialog", 1, 12));

        totalAboveField.setEditable(false);

        totalBelowField.setEditable(false);

        jLabel6.setText("Performance Rate:");

        performanceField.setEditable(false);
        performanceField.setFont(new java.awt.Font("Dialog", 1, 12));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalRevenueField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(totalProfitField)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalAboveField)
                            .addComponent(totalBelowField)
                            .addComponent(performanceField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(totalRevenueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalAboveField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(totalBelowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(performanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        refreshButton.setText("Refresh Report");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        backButton.setText("<< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export to CSV");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(exportButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(exportButton)
                    .addComponent(backButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        generateReport();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Optional: Implement CSV export functionality
        javax.swing.JOptionPane.showMessageDialog(this,
            "Export functionality can be implemented here.\n" +
            "For now, you can copy data from the table.",
            "Export",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration
    private javax.swing.JButton backButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField performanceField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable reportTable;
    private javax.swing.JTextField totalAboveField;
    private javax.swing.JTextField totalBelowField;
    private javax.swing.JTextField totalProfitField;
    private javax.swing.JTextField totalRevenueField;
    // End of variables declaration
}