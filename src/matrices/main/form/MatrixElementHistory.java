/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import javax.swing.JFrame;
import matrices.Util;
import matrices.math.Matrix;
import matrices.main.type.MatrixHistoryEntry;
import matrices.main.type.MatrixFrame;

/**
 *
 * @author MojoMacW7
 */
public class MatrixElementHistory extends MatrixFrame {

    /**
     * Creates new form MatrixMinor
     */
    MatrixDeterminant matrixDeterminant;
    Matrix matrix;
    
    MatrixHistoryEntry[] entries;
    
    int row;
    int col;
    int type;
    public MatrixElementHistory(MainFrame root, MatrixFrame parent, Matrix matrix, int row, int col, int type) {
        super(root, parent);
        initComponents();
        this.row = row;
        this.col = col;
        this.type = type;
        this.matrix = matrix;
        matrixDeterminant = new MatrixDeterminant(root, this);
        matrixDeterminantHolder.add(matrixDeterminant);
        matrixDeterminant.showMatrixCB(false);
        matrixDeterminant.setVisible(false);
        entries = new MatrixHistoryEntry[]{
            new MatrixHistoryEntry(minorTypeLabel, minorCalcPanel, minorCalcTA, minorValueLabel),
            new MatrixHistoryEntry(cofactorTypeLabel, cofactorCalcPanel, cofactorCalcTA, cofactorValueLabel),
            new MatrixHistoryEntry(adjointTypeLabel, adjointCalcPanel, adjointCalcTA, adjointValueLabel),
            new MatrixHistoryEntry(inverseTypeLabel, inverseCalcPanel, inverseCalcTA, inverseValueLabel)
        };
        for(int i = 0; i < 4; ++i){
            entries[i].hide();
        }
        init();
        this.setVisible(true);
    }
    
    public void init(){
        if(type == 1){
            int rc = matrix.rowCount;
            double el = matrix.getElementAt(0, col);
            String calc1 = String.format(
                "element<sub>%d, %d</sub><sup>2</sup>",
                1,
                col+1
            );
            String calc2 = String.format(
                "(%s)<sup>2</sup>",
                el
            );
            String calc3 = String.format(
                "%s",
                el*el
            );
            for(int i = 1; i < rc; ++i){
                el = matrix.getElementAt(i, col);
                calc1 = String.format(
                    "%s + element<sub>%d, %d</sub><sup>2</sup>",
                    calc1,
                    i+1,
                    col+1
                );
                calc2 = String.format(
                    "%s + (%s)<sup>2</sup>",
                    calc2,
                    el
                );
                calc3 = String.format(
                    "%s + %s",
                    calc3,
                    el*el
                );
                      
            }
            entries[0].show(
                String.format(
                    "magnitude<sub>%d</sub>",
                    col+1
                ),
                String.format(
                    "%s\n= %s\n= %s",
                    calc1,
                    calc2,
                    calc3
                ),
                matrix.getMagnitudeAt(col)
            );
        }
        if(type == 0){
            double el = matrix.getElementAt(col, row);
            entries[0].show(
                String.format(
                    "transpose<sub>%d, %d</sub>",
                    row+1,
                    col+1
                ),
                String.format(
                    "element<sub>%d, %d</sub>",
                    col+1,
                    row+1
                ),
                el
            );
        }
        if(type == 6){
            double el = matrix.getElementAt(row, col);
            double cof = matrix.getCofactorAt(row, col);
            entries[2].show(
                String.format(
                    "element<sub>%d, %d</sub>*cofactor<sub>%d, %d</sub>",
                    row+1,
                    col+1,
                    row+1,
                    col+1
                ),
                String.format(
                    "%s*%s",
                    Util.toString(el),
                    Util.toString(cof)
                ),
                el*cof
            );
            type = 3;
        }
        switch(type){
            case 5:{
                double cof = matrix.getCofactorAt(col, row);
                double det = matrix.getDeterminant();
                entries[3].show(
                    String.format(
                        "inverse<sub>%d, %d</sub>",
                        row+1,
                        col+1
                    ),
                    String.format(
                        "cofactor<sub>%d, %d</sub>/det\n= %s/%s", 
                        col+1,
                        row+1,
                        Util.toString(cof), 
                        Util.toString(det)
                    ),
                    cof/det
                );
            }
            case 4:{
                double cof = matrix.getCofactorAt(col, row);
                entries[2].show(
                    String.format(
                        "adjoint<sub>%d, %d</sub>",
                        row+1,
                        col+1
                    ),
                    String.format(
                        "cofactor<sub>%d, %d</sub>", 
                        col+1, 
                        row+1
                    ),
                    cof
                );
                int temp = row;
                row = col;
                col = row;
            }
            case 3:{
                entries[1].show(
                    String.format(
                        "cofactor<sub>%d, %d</sub>",
                        row+1,
                        col+1
                    ),
                    String.format(
                        "((-1)<sup>(%d+%d)</sup>)*minor<sub>%d, %d</sub>\n= (%d)*(%s)", 
                        row+1,
                        col+1,
                        row+1,
                        col+1,
                        (row+col)%2 == 0 ? 1 : -1,
                        Util.toString(matrix.getMinorAt(row, col))
                    ),
                    matrix.getCofactorAt(row, col)
                );
            }
            case 2:{
                matrixDeterminant.setResultTA(minorCalcTA);
                matrixDeterminant.showMatrix(matrix.getSubMatrixExcluding(row, col));
                matrixDeterminant.setVisible(true);
                entries[0].show(
                    String.format(
                        "minor<sub>%d, %d</sub>", 
                        row+1,
                        col+1
                    ),
                    matrixDeterminant.getResultText(),
                    matrix.getMinorAt(row, col)
                );
            }
        }
        if(!matrixDeterminant.isVisible()){
            mainSplitPane.setDividerLocation(-1);
            mainSplitPane.setDividerSize(0);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainSplitPane = new javax.swing.JSplitPane();
        matrixDeterminantHolder = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        minorTypeLabel = new matrices.main.type.WrapTextPane();
        minorCalcPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        minorCalcTA = new matrices.main.type.WrapTextPane();
        minorValueLabel = new javax.swing.JLabel();
        cofactorTypeLabel = new matrices.main.type.WrapTextPane();
        cofactorCalcPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        cofactorCalcTA = new matrices.main.type.WrapTextPane();
        cofactorValueLabel = new javax.swing.JLabel();
        adjointTypeLabel = new matrices.main.type.WrapTextPane();
        adjointCalcPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        adjointCalcTA = new matrices.main.type.WrapTextPane();
        adjointValueLabel = new javax.swing.JLabel();
        inverseTypeLabel = new matrices.main.type.WrapTextPane();
        inverseCalcPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        inverseCalcTA = new matrices.main.type.WrapTextPane();
        inverseValueLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Matrix Element History");

        mainSplitPane.setDividerLocation(125);
        mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setResizeWeight(0.75);
        mainSplitPane.setDoubleBuffered(true);

        matrixDeterminantHolder.setLayout(new java.awt.BorderLayout());
        mainSplitPane.setTopComponent(matrixDeterminantHolder);

        jPanel2.setAlignmentY(0.0F);
        jPanel2.setPreferredSize(new java.awt.Dimension(420, 373));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setAlignmentY(0.0F);
        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(420, 341));

        jPanel1.setAlignmentY(0.0F);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Type");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Calculation");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Value");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        jPanel1.add(jLabel3, gridBagConstraints);

        minorTypeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        minorTypeLabel.setText("Minor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(minorTypeLabel, gridBagConstraints);

        minorCalcPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        minorCalcPanel.setPreferredSize(new java.awt.Dimension(225, 80));
        minorCalcPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(223, 80));
        jScrollPane3.setViewportView(minorCalcTA);

        minorCalcPanel.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(minorCalcPanel, gridBagConstraints);

        minorValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minorValueLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        minorValueLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(minorValueLabel, gridBagConstraints);

        cofactorTypeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cofactorTypeLabel.setText("Cofactor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cofactorTypeLabel, gridBagConstraints);

        cofactorCalcPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cofactorCalcPanel.setPreferredSize(new java.awt.Dimension(225, 80));
        cofactorCalcPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setPreferredSize(new java.awt.Dimension(223, 80));
        jScrollPane4.setViewportView(cofactorCalcTA);

        cofactorCalcPanel.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cofactorCalcPanel, gridBagConstraints);

        cofactorValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cofactorValueLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cofactorValueLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cofactorValueLabel, gridBagConstraints);

        adjointTypeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adjointTypeLabel.setText("Adjoint");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(adjointTypeLabel, gridBagConstraints);

        adjointCalcPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adjointCalcPanel.setPreferredSize(new java.awt.Dimension(225, 80));
        adjointCalcPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setPreferredSize(new java.awt.Dimension(223, 80));
        jScrollPane5.setViewportView(adjointCalcTA);

        adjointCalcPanel.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(adjointCalcPanel, gridBagConstraints);

        adjointValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adjointValueLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adjointValueLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(adjointValueLabel, gridBagConstraints);

        inverseTypeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inverseTypeLabel.setText("Inverse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(inverseTypeLabel, gridBagConstraints);

        inverseCalcPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inverseCalcPanel.setPreferredSize(new java.awt.Dimension(225, 80));
        inverseCalcPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setPreferredSize(new java.awt.Dimension(223, 80));
        jScrollPane6.setViewportView(inverseCalcTA);

        inverseCalcPanel.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(inverseCalcPanel, gridBagConstraints);

        inverseValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inverseValueLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        inverseValueLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(inverseValueLabel, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.BorderLayout());

        closeButton.setText("Close");
        closeButton.setDoubleBuffered(true);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel3.add(closeButton, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel2.add(jPanel3, gridBagConstraints);

        mainSplitPane.setRightComponent(jPanel2);

        getContentPane().add(mainSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adjointCalcPanel;
    private matrices.main.type.WrapTextPane adjointCalcTA;
    private matrices.main.type.WrapTextPane adjointTypeLabel;
    private javax.swing.JLabel adjointValueLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel cofactorCalcPanel;
    private matrices.main.type.WrapTextPane cofactorCalcTA;
    private matrices.main.type.WrapTextPane cofactorTypeLabel;
    private javax.swing.JLabel cofactorValueLabel;
    private javax.swing.JPanel inverseCalcPanel;
    private matrices.main.type.WrapTextPane inverseCalcTA;
    private matrices.main.type.WrapTextPane inverseTypeLabel;
    private javax.swing.JLabel inverseValueLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JPanel matrixDeterminantHolder;
    private javax.swing.JPanel minorCalcPanel;
    private matrices.main.type.WrapTextPane minorCalcTA;
    private matrices.main.type.WrapTextPane minorTypeLabel;
    private javax.swing.JLabel minorValueLabel;
    // End of variables declaration//GEN-END:variables
}
