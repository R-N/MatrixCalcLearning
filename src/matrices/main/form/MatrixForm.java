/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import matrices.Util;
import matrices.math.Matrix;
import matrices.main.type.MatrixPanel;

/**
 *
 * @author MojoMacW7
 */
public class MatrixForm extends MatrixPanel{ 
    public static final int FORM_TRANSPOSE = 0;
    public static final int FORM_MAGNITUDE = 1;
    public static final int FORM_MINOR = 2;
    public static final int FORM_COFACTOR = 3;
    public static final int FORM_ADJOINT = 4;
    public static final int FORM_INVERSE = 5;
    /**
     * Creates new form MatrixForm
     */
    DefaultComboBoxModel matrixFormCBModel0 = new DefaultComboBoxModel(new String[]{"Transpose", "Magnitude"});
    DefaultComboBoxModel matrixFormCBModel1 = new DefaultComboBoxModel(new String[]{"Transpose", "Magnitude", "Minor", "Cofactor", "Adjoint"});
    DefaultComboBoxModel matrixFormCBModel2 = new DefaultComboBoxModel(new String[]{"Transpose", "Magnitude", "Minor", "Cofactor", "Adjoint", "Inverse"});
    int activeFormCBModel = 0;
    MatrixShow matrixShow;
    Matrix processedMatrix;
    Matrix baseMatrix;
    public MatrixForm(MainFrame root) {
        super(root);
        initComponents();
        setDoubleBuffered(true);
        matrixFormCB.setModel(matrixFormCBModel0);
        matrixShow = new MatrixShow();
        matrixShow.showMatrixNameLabel(false);
        matrixShowHolder.add(matrixShow);
        matrixShow.addTableMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent mouseEvent) {
                    JTable table =(JTable) mouseEvent.getSource();
                    if (mouseEvent.getClickCount() == 2) {
                        int row = table.getSelectedRow();
                        int col = table.getSelectedColumn();
                        showElementHistory(row, col);
                    }
                }
            }
        );
    }
    
    public void showElementHistory(int row, int col){
        int formCBIndex = matrixFormCB.getSelectedIndex();
        MatrixElementHistory history = new MatrixElementHistory(root, root, baseMatrix, row, col, formCBIndex);
    }
    
    public void refreshMatrixCB(boolean keepSelection){
        int index = matrixCB.getSelectedIndex();
        matrixCB.setModel(new DefaultComboBoxModel(root.getMatrixNameArray()));
        if(keepSelection && index >= 0) matrixCB.setSelectedIndex(index);
        refreshMatrixFormCB(keepSelection);
    }
    
    public void refreshMatrixFormCB(boolean keepSelection){
        Matrix m = getSelectedMatrix();
        int index = matrixFormCB.getSelectedIndex();
        if(m.hasInverse()){
            if(activeFormCBModel != 2){
                matrixFormCB.setModel(matrixFormCBModel2);
                activeFormCBModel = 2;
                matrixFormCB.setSelectedIndex(index);
            }
        }else if (m.hasDeterminant()){
            if(activeFormCBModel != 1){
                matrixFormCB.setModel(matrixFormCBModel1);
                activeFormCBModel = 1;
                if(index < 5) matrixFormCB.setSelectedIndex(index);
            }
        }else{
            if(activeFormCBModel != 0){
                matrixFormCB.setModel(matrixFormCBModel0);
                activeFormCBModel = 0;
                if(index < 2) matrixFormCB.setSelectedIndex(0);
            }
        }
        showMatrix();
    }
    
    public Matrix getSelectedMatrix(){
        int index = matrixCB.getSelectedIndex();
        return root.getMatrix(index);
    }
    
    public void showMatrix(){
        baseMatrix = getSelectedMatrix();
        switch(matrixFormCB.getSelectedIndex()){
            case FORM_TRANSPOSE:{
                this.processedMatrix = baseMatrix.getTransposeMatrix();
                break;
            }
            case FORM_MAGNITUDE:{
                this.processedMatrix = baseMatrix.getMagnitudeMatrix();
                break;
            }
            case FORM_MINOR:{
                this.processedMatrix = baseMatrix.getMinorMatrix();
                break;
            }
            case FORM_COFACTOR:{
                this.processedMatrix = baseMatrix.getCofactorMatrix();
                break;
            }
            case FORM_ADJOINT:{
                this.processedMatrix = baseMatrix.getAdjointMatrix();
                break;
            }
            case FORM_INVERSE:{
                this.processedMatrix = baseMatrix.getInverseMatrix();
                break;
            }
        }

        matrixShow.loadMatrix(this.processedMatrix);
    }
    
    public void setForm(int id, int form){
        matrixCB.setSelectedIndex(id);
        matrixFormCB.setSelectedIndex(form);
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

        jPanel1 = new javax.swing.JPanel();
        matrixFormCB = new matrices.main.type.ComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        matrixCB = new matrices.main.type.ComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        matrixShowHolder = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        saveAsButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        matrixFormCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transpose", "Minor", "Cofactor", "Adjoint", "Inverse" }));
        matrixFormCB.setDoubleBuffered(true);
        matrixFormCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrixFormCBActionPerformed(evt);
            }
        });
        jPanel1.add(matrixFormCB, new java.awt.GridBagConstraints());

        jLabel1.setText("(");
        jLabel1.setDoubleBuffered(true);
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        matrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        matrixCB.setDoubleBuffered(true);
        matrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrixCBActionPerformed(evt);
            }
        });
        jPanel1.add(matrixCB, new java.awt.GridBagConstraints());

        jLabel2.setText(")");
        jLabel2.setDoubleBuffered(true);
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jSeparator1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSeparator1, gridBagConstraints);

        matrixShowHolder.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(matrixShowHolder, gridBagConstraints);

        jSeparator2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSeparator2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        saveAsButton.setText("Save As");
        saveAsButton.setDoubleBuffered(true);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        jPanel3.add(saveAsButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void matrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrixCBActionPerformed
        // TODO add your handling code here:
        refreshMatrixFormCB(true);
    }//GEN-LAST:event_matrixCBActionPerformed

    private void matrixFormCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrixFormCBActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_matrixFormCBActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        // TODO add your handling code here:
        MatrixSaveAs msa = new MatrixSaveAs(root, root.topmostFrame, processedMatrix);
    }//GEN-LAST:event_saveAsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private matrices.main.type.ComboBox<String> matrixCB;
    private matrices.main.type.ComboBox<String> matrixFormCB;
    private javax.swing.JPanel matrixShowHolder;
    private javax.swing.JButton saveAsButton;
    // End of variables declaration//GEN-END:variables
}
