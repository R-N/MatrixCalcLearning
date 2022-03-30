/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import javax.swing.DefaultComboBoxModel;
import matrices.Util;
import matrices.math.Matrix;
import matrices.main.type.renderer.MatrixCramerTableRenderer;
import matrices.main.type.MatrixPanel;
import java.util.Arrays;
/**
 *
 * @author MojoMacW7
 */
public class MatrixCramer extends MatrixPanel {

    /**
     * Creates new form MatrixCramer
     */
    MainFrame root;
    MatrixDeterminant matrixDeterminant;
    MatrixCramerTableRenderer renderer;
    int[] mainIndexTranslation;
    int[] subIndexTranslation;
    public MatrixCramer(MainFrame root){
        super(root);
        initComponents();
        setDoubleBuffered(true);
        this.root = root;
        matrixDeterminant = new MatrixDeterminant(root);
        this.renderer = new MatrixCramerTableRenderer();
        matrixDeterminant.setRenderer(renderer);
        matrixDeterminantHolder.add(matrixDeterminant);
        matrixDeterminant.showMatrixCB(false);
        matrixDeterminant.setVisible(true);
    }
    
    
    public Matrix getMainMatrix(){
        return root.getMatrix(mainIndexTranslation[mainMatrixCB.getSelectedIndex()]);
    }
    
    public Matrix getSubMatrix(){
        return root.getMatrix(subIndexTranslation[subMatrixCB.getSelectedIndex()]);
    }
    
    public void refreshMainMatrixCB(boolean keepSelection){
        int index = mainMatrixCB.getSelectedIndex();
        int count = root.getMatrixCount();
        int[] buffer = new int[count];
        int c = 0;
        for(int i = 0; i < count; ++i){
            Matrix m = root.getMatrix(i);
            if(m.columnCount > 1 && m.hasInverse()){
                for(int j = 0; j < count; ++j){
                    if(j == i) continue;
                    Matrix n = root.getMatrix(j);
                    if(n.rowCount == m.rowCount && n.columnCount == 1){
                        buffer[c++] = i;
                        break;
                    }
                }
            }
        }
        mainIndexTranslation = new int[c];
        String[] names = new String[c];
        for(int i = 0; i < c; ++i){
            mainIndexTranslation[i] = buffer[i];
            names[i] = root.getMatrix(buffer[i]).name;
        }
        mainMatrixCB.setModel(new DefaultComboBoxModel(names));
        if(keepSelection && index >= 0) mainMatrixCB.setSelectedIndex(index);
        refreshSubMatrixCB(keepSelection);
        refreshColumnCB(keepSelection);
        showMatrix();
    }
    
    public void refreshSubMatrixCB(boolean keepSelection){
        int index = subMatrixCB.getSelectedIndex();
        int count = root.getMatrixCount();
        int[] buffer = new int[count];
        int c = 0;
        int rowCount = getMainMatrix().rowCount;
        for(int i = 0; i < count; ++i){
            Matrix m = root.getMatrix(i);
            if(m.columnCount == 1 && m.rowCount == rowCount){
                buffer[c++] = i;
            }
        }
        subIndexTranslation = new int[c];
        String[] names = new String[c];
        for(int i = 0; i < c; ++i){
            subIndexTranslation[i] = buffer[i];
            names[i] = root.getMatrix(buffer[i]).name;
        }
        subMatrixCB.setModel(new DefaultComboBoxModel(names));
        if(keepSelection && index >= 0) subMatrixCB.setSelectedIndex(index);
    }
    
    public void refreshColumnCB(boolean keepSelection){
        int index = columnCB.getSelectedIndex();
        Matrix mainMatrix = getMainMatrix();
        String[] entries = new String[mainMatrix.columnCount+1];
        for(int i = 0; i <= mainMatrix.columnCount; ++i){
            entries[i] = String.valueOf(i);
        }
        columnCB.setModel(new DefaultComboBoxModel(entries));
        if(keepSelection) columnCB.setSelectedIndex(index);
        
    }
    
    public Matrix getCramerMatrix(Matrix main, Matrix sub, int col){
        double[][] data = new double[main.rowCount][main.columnCount];
        for (int i = 0; i < main.rowCount; ++i){
            for(int j = 0; j < main.columnCount; ++j){
                if(j == col){
                    data[i][j] = sub.getElementAt(i, 0);
                }else{
                    data[i][j] = main.getElementAt(i, j);
                }
            }
        }
        return new Matrix(data, String.format("cramer(%s, %s, %d)", main.name, sub.name, col+1));
        
    }
    
    public void showMatrix(){
        Matrix main = getMainMatrix();
        int col = columnCB.getSelectedIndex();
        Matrix m;
        if(col == 0){
            m = main;
            matrixDeterminant.showMatrix(m);
            renderer.setColumn(-1);
            matrixDeterminant.repaintTable();
            return;
        }else{
            --col;
            m = getCramerMatrix(main, getSubMatrix(), col);
            matrixDeterminant.showMatrix(m);
        
            double mainDet = main.getDeterminant();
            double mDet = m.getDeterminant();
            matrixDeterminant.addToResultTA(
                String.format(
                    "\n(%s)/(%s)=%s", 
                    Util.toString(mDet),
                    Util.toString(mainDet),
                    Util.toString(mDet/mainDet)
                )
            );
            renderer.setColumn(col);
            matrixDeterminant.repaintTable();
        }
    }
    
    public void setCramer(int mainId, boolean row, int num, int subId, int column){
        mainId = Arrays.binarySearch(mainIndexTranslation, mainId);
        subId = Arrays.binarySearch(subIndexTranslation, subId);
        
        mainMatrixCB.setSelectedIndex(mainId);
        subMatrixCB.setSelectedIndex(subId);
        columnCB.setSelectedIndex(column);
        
        matrixDeterminant.setRowCol(row, num);
        
        showMatrix();
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
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        mainMatrixCB = new matrices.main.type.ComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabel3 = new javax.swing.JLabel();
        subMatrixCB = new matrices.main.type.ComboBox<>();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        columnCB = new matrices.main.type.ComboBox<>();
        matrixDeterminantHolder = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        filler3.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        jPanel1.add(filler3, gridBagConstraints);

        jLabel1.setText("Main : ");
        jLabel1.setDoubleBuffered(true);
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        mainMatrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        mainMatrixCB.setDoubleBuffered(true);
        mainMatrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMatrixCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(mainMatrixCB, gridBagConstraints);

        filler1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        jPanel1.add(filler1, gridBagConstraints);

        jLabel3.setText("Sub : ");
        jLabel3.setDoubleBuffered(true);
        jPanel1.add(jLabel3, new java.awt.GridBagConstraints());

        subMatrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        subMatrixCB.setDoubleBuffered(true);
        subMatrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMatrixCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(subMatrixCB, gridBagConstraints);

        filler2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        jPanel1.add(filler2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Column : ");
        jLabel2.setDoubleBuffered(true);
        jPanel2.add(jLabel2, new java.awt.GridBagConstraints());

        columnCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        columnCB.setDoubleBuffered(true);
        columnCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnCBActionPerformed(evt);
            }
        });
        jPanel2.add(columnCB, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        matrixDeterminantHolder.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(matrixDeterminantHolder, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void mainMatrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMatrixCBActionPerformed
        // TODO add your handling code here:
        refreshSubMatrixCB(false);
    }//GEN-LAST:event_mainMatrixCBActionPerformed

    private void subMatrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMatrixCBActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_subMatrixCBActionPerformed

    private void columnCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnCBActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_columnCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private matrices.main.type.ComboBox<String> columnCB;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private matrices.main.type.ComboBox<String> mainMatrixCB;
    private javax.swing.JPanel matrixDeterminantHolder;
    private matrices.main.type.ComboBox<String> subMatrixCB;
    // End of variables declaration//GEN-END:variables
}
