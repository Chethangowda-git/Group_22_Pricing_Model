package UserInterface.Main.WorkSpaceProfiles;

import TheBusiness.Business.Business;
import UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace.BrowseProductPricePerformanceJPanel;
import UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace.AdjustTargetPricesJPanel;
import UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace.RunSimulationJPanel;
import UserInterface.Main.WorkSpaceProfiles.MarketingWorkspace.GenerateReportJPanel;
import javax.swing.JPanel;

/**
 *
 * @author aksha
 */
public class MarketingManagerWorkAreaJPanel1 extends javax.swing.JPanel {

    javax.swing.JPanel CardSequencePanel;
    Business business;

    public MarketingManagerWorkAreaJPanel1(Business b, JPanel clp) {
        business = b;
        this.CardSequencePanel = clp;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setForeground(new java.awt.Color(51, 51, 51));

        jButton4.setBackground(new java.awt.Color(102, 153, 255));
        jButton4.setFont(getFont());
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Browse Product Performance");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setMaximumSize(new java.awt.Dimension(200, 40));
        jButton4.setMinimumSize(new java.awt.Dimension(20, 23));
        jButton4.setPreferredSize(new java.awt.Dimension(240, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 153, 255));
        jButton10.setFont(getFont());
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Adjust Target Prices");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.setMaximumSize(new java.awt.Dimension(200, 40));
        jButton10.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton10.setPreferredSize(new java.awt.Dimension(240, 25));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 153, 255));
        jButton6.setFont(getFont());
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Run Simulation");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.setMaximumSize(new java.awt.Dimension(145, 40));
        jButton6.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton6.setPreferredSize(new java.awt.Dimension(240, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(102, 153, 255));
        jButton11.setFont(getFont());
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Generate Performance Report");
        jButton11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton11.setMaximumSize(new java.awt.Dimension(200, 40));
        jButton11.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton11.setPreferredSize(new java.awt.Dimension(240, 25));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel1.setText("Marketing Workspace");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(632, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(221, Short.MAX_VALUE))
        );
    }// </editor-fold>

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    BrowseProductPricePerformanceJPanel panel =
            new BrowseProductPricePerformanceJPanel(business, CardSequencePanel);

    CardSequencePanel.add("BrowsePerformance", panel);
    ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}


   private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
    AdjustTargetPricesJPanel panel =
            new AdjustTargetPricesJPanel(business, CardSequencePanel);

    CardSequencePanel.add("AdjustPrices", panel);
    ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}


   private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    RunSimulationJPanel panel =
            new RunSimulationJPanel(business, CardSequencePanel);

    CardSequencePanel.add("RunSimulation", panel);
    ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}


   private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
    GenerateReportJPanel panel =
            new GenerateReportJPanel(business, CardSequencePanel);

    CardSequencePanel.add("GenerateReport", panel);
    ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);
}


    // Variables declaration
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration
}