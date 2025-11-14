package UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace;

import TheBusiness.Business.Business;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aksha
 */
public class RunSimulationJPanel extends javax.swing.JPanel {
    
    JPanel CardSequencePanel;
    Business business;
    
    // Store original target prices for comparison
    private Map<Product, Integer> originalTargets = new HashMap<>();
    private Map<Product, Integer> simulatedTargets = new HashMap<>();
    
    // Simulation results
    private int originalRevenue = 0;
    private int simulatedRevenue = 0;
    private int originalProfitMargin = 0;
    private int simulatedProfitMargin = 0;
    
    public RunSimulationJPanel(Business bz, JPanel jp) {
        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        calculateOriginalMetrics();
        displayOriginalMetrics();
    }
    
    /**
     * Calculate current business metrics before simulation
     */
    private void calculateOriginalMetrics() {
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        originalRevenue = 0;
        originalProfitMargin = 0;
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                // Store original target
                originalTargets.put(product, product.getTargetPrice());
                
                ProductSummary summary = new ProductSummary(product);
                originalRevenue += summary.getSalesRevenues();
                originalProfitMargin += summary.getProductPricePerformance();
            }
        }
    }
    
    /**
     * Display original metrics
     */
    private void displayOriginalMetrics() {
        originalRevenueField.setText("$" + String.format("%,d", originalRevenue));
        originalProfitField.setText("$" + String.format("%,d", originalProfitMargin));
    }
    
    /**
     * Run simulation with optimized target prices
     */
    private void runSimulation() {
        ArrayList<Supplier> suppliers = business.getSupplierDirectory().getSuplierList();
        
        simulatedRevenue = 0;
        simulatedProfitMargin = 0;
        int productsAdjusted = 0;
        
        clearTable();
        
        for (Supplier supplier : suppliers) {
            ProductCatalog catalog = supplier.getProductCatalog();
            
            for (Product product : catalog.getProductList()) {
                ProductSummary summary = new ProductSummary(product);
                
                int originalTarget = originalTargets.get(product);
                int newTarget = originalTarget;
                boolean adjusted = false;
                
                // Optimization logic
                int salesAbove = summary.getNumberAboveTarget();
                int salesBelow = summary.getNumberBelowTarget();
                
                if (salesBelow > salesAbove && salesBelow > 2) {
                    // More sales below target - lower price by 5-15%
                    double decreasePercent = 0.05 + (Math.random() * 0.10);
                    newTarget = (int)(originalTarget * (1 - decreasePercent));
                    newTarget = Math.max(product.getFloorPrice(), newTarget);
                    adjusted = true;
                    productsAdjusted++;
                    
                } else if (salesAbove > salesBelow && salesAbove > 2) {
                    // More sales above target - raise price by 5-15%
                    double increasePercent = 0.05 + (Math.random() * 0.10);
                    newTarget = (int)(originalTarget * (1 + increasePercent));
                    newTarget = Math.min(product.getCeilingPrice(), newTarget);
                    adjusted = true;
                    productsAdjusted++;
                }
                
                simulatedTargets.put(product, newTarget);
                
                // Calculate projected revenue with new target
                // Assumption: revenue improves proportionally to target adjustment
                int projectedRevenue = summary.getSalesRevenues();
                int projectedMargin = summary.getProductPricePerformance();
                
                if (adjusted) {
                    // Simulate improved performance
                    double improvementFactor = 1.0 + ((newTarget - originalTarget) * 0.0001);
                    projectedRevenue = (int)(projectedRevenue * improvementFactor);
                    projectedMargin = (int)(projectedMargin * improvementFactor);
                }
                
                simulatedRevenue += projectedRevenue;
                simulatedProfitMargin += projectedMargin;
                
                // Add to table if adjusted
                if (adjusted) {
                    Object[] row = new Object[7];
                    row[0] = supplier.getName();
                    row[1] = product.toString();
                    row[2] = originalTarget;
                    row[3] = newTarget;
                    row[4] = String.format("%+d%%", ((newTarget - originalTarget) * 100 / originalTarget));
                    row[5] = projectedRevenue;
                    row[6] = projectedMargin;
                    
                    ((DefaultTableModel) simulationTable.getModel()).addRow(row);
                }
            }
        }
        
        // Display simulated results
        simulatedRevenueField.setText("$" + String.format("%,d", simulatedRevenue));
        simulatedProfitField.setText("$" + String.format("%,d", simulatedProfitMargin));
        
        int revenueIncrease = simulatedRevenue - originalRevenue;
        int profitIncrease = simulatedProfitMargin - originalProfitMargin;
        
        revenueImpactField.setText("$" + String.format("%+,d", revenueIncrease));
        profitImpactField.setText("$" + String.format("%+,d", profitIncrease));
        
        JOptionPane.showMessageDialog(this,
            "Simulation Complete!\n\n" +
            "Products Adjusted: " + productsAdjusted + "\n" +
            "Revenue Impact: $" + String.format("%,d", revenueIncrease) + "\n" +
            "Profit Impact: $" + String.format("%,d", profitIncrease),
            "Simulation Results",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Apply simulated prices to actual products
     */
    private void applySimulation() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to apply these simulated prices?\n" +
            "This will update all product target prices.",
            "Confirm Apply",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            for (Map.Entry<Product, Integer> entry : simulatedTargets.entrySet()) {
                Product product = entry.getKey();
                int newTarget = entry.getValue();
                
                product.updateProduct(product.getFloorPrice(), product.getCeilingPrice(), newTarget);
            }
            
            JOptionPane.showMessageDialog(this,
                "Simulation applied successfully!\n" +
                "All target prices have been updated.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Refresh original metrics with new values
            originalTargets.clear();
            simulatedTargets.clear();
            calculateOriginalMetrics();
            displayOriginalMetrics();
            clearTable();
        }
    }
    
    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) simulationTable.getModel();
        model.setRowCount(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        originalRevenueField = new javax.swing.JTextField();
        originalProfitField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        simulatedRevenueField = new javax.swing.JTextField();
        simulatedProfitField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        revenueImpactField = new javax.swing.JTextField();
        profitImpactField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        simulationTable = new javax.swing.JTable();
        runSimulationButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24));
        jLabel1.setText("Run Pricing Simulation");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Performance"));

        jLabel2.setText("Total Revenue:");

        jLabel3.setText("Total Profit Margin:");

        originalRevenueField.setEditable(false);

        originalProfitField.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(originalRevenueField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(originalProfitField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(originalRevenueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(originalProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulated Performance"));

        jLabel4.setText("Projected Revenue:");

        jLabel5.setText("Projected Profit Margin:");

        simulatedRevenueField.setEditable(false);

        simulatedProfitField.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(simulatedRevenueField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(simulatedProfitField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(simulatedRevenueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(simulatedProfitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Impact Analysis"));

        jLabel6.setText("Revenue Impact:");

        jLabel7.setText("Profit Impact:");

        revenueImpactField.setEditable(false);
        revenueImpactField.setFont(new java.awt.Font("Dialog", 1, 12));

        profitImpactField.setEditable(false);
        profitImpactField.setFont(new java.awt.Font("Dialog", 1, 12));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(revenueImpactField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(profitImpactField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(revenueImpactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(profitImpactField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        simulationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Supplier", "Product", "Current Target", "New Target", "Change %", "Proj. Revenue", "Proj. Margin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(simulationTable);

        runSimulationButton.setText("Run Simulation");
        runSimulationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runSimulationButtonActionPerformed(evt);
            }
        });

        applyButton.setText("Apply Simulation");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        backButton.setText("<< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel8.setText("Proposed Price Adjustments:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(runSimulationButton)
                        .addGap(18, 18, 18)
                        .addComponent(applyButton)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runSimulationButton)
                    .addComponent(applyButton)
                    .addComponent(backButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void runSimulationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        runSimulation();
    }

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        applySimulation();
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
    }

    // Variables declaration
    private javax.swing.JButton applyButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField originalProfitField;
    private javax.swing.JTextField originalRevenueField;
    private javax.swing.JTextField profitImpactField;
    private javax.swing.JTextField revenueImpactField;
    private javax.swing.JTextField simulatedProfitField;
    private javax.swing.JTextField simulatedRevenueField;
    private javax.swing.JTable simulationTable;
    private javax.swing.JButton runSimulationButton;
    // End of variables declaration
}