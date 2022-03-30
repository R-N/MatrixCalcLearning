/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import matrices.main.type.WrapTextPane;
import matrices.Util;
import matrices.math.Matrix;
import matrices.main.type.renderer.MatrixDeterminantTableRenderer;
import java.util.Arrays;
/**
 *
 * @author MojoMacW7
 */
public class MatrixDeterminant extends javax.swing.JPanel {

    /**
     * Creates new form MatrixDeterminant
     */
    MatrixShow matrixShow;
    MainFrame root;
    Matrix matrix;
    JFrame parent;
    MatrixDeterminantTableRenderer renderer;
    
    WrapTextPane activeResultTA = null;
    int[] indexTranslation;
    
    boolean matrixCBVisible = true;
    
    public MatrixDeterminant(MainFrame root) {
        this(root, root);
    }
    public MatrixDeterminant(MainFrame root, JFrame parent) {
        initComponents();
        setDoubleBuffered(true);
        this.root = root;
        this.parent = parent;
        matrixShow = new MatrixShow();
        matrixShowHolder.add(matrixShow);
        setRenderer(new MatrixDeterminantTableRenderer());
        
        matrixShow.addTableMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent mouseEvent) {
                    JTable table =(JTable) mouseEvent.getSource();
                    if (mouseEvent.getClickCount() == 2) {
                        int row = table.getSelectedRow();
                        int col = table.getSelectedColumn();
                        showHistory(row, col);
                    }
                }
            }
        );
        this.activeResultTA = resultTA;
    }
    public void showHistory(int row, int col){
        if(matrix.rowCount > 1 && renderer.rowOrColumnUsed(row, col)){
            MatrixElementHistory history = new MatrixElementHistory(root, root.topmostFrame, matrix, row, col, 6);
        }
    }
    public Matrix getSelectedMatrix(){
        int index = matrixCB.getSelectedIndex();
        return root.getMatrix(indexTranslation[index]);
    }
    public void showMatrix(){
        showMatrix(getSelectedMatrix());
    }
    public void repaintTable(){
        matrixShow.repaintTable();
    }
    public void refreshMatrixCB(boolean keepSelection){
        int index = matrixCB.getSelectedIndex();
        
        int count = root.getMatrixCount();
        int c = 0;
        int[] buffer = new int[count];
        for(int i = 0; i < count; ++i){
            Matrix m = root.getMatrix(i);
            if(m.hasDeterminant()){
                buffer[c++] = i;
            }
        }
        
        indexTranslation = new int[c];
        String[] nameArray = new String[c];
        for(int i = 0; i < c; ++i){
            indexTranslation[i] = buffer[i];
            nameArray[i] = root.getMatrix(buffer[i]).name;
        }
        
        matrixCB.setModel(new DefaultComboBoxModel(nameArray));
        if(keepSelection && index >= 0){
            matrixCB.setSelectedIndex(index);
        }
        showMatrix();
    }
    public void showMatrixCB(boolean show){
        if(this.matrixCBVisible == show){
            return;
        }
        this.matrixCBVisible = show;
        matrixCBPanel.setVisible(show);
    }
    public void addToResultTA(String s){
        activeResultTA.setText(activeResultTA.getText() + s);
    }
    
    public String getResultText() {
        if(matrix.rowCount == 1 && matrix.columnCount == 1){
            return Util.toString(matrix.getElementAt(0,0));
        }
        boolean row = rowColCB.getSelectedIndex() == 0;
        int index = rowColNumCB.getSelectedIndex();
        String det = "";
        int index1 = index+1;
        if(row){
            det = det + String.format(
                "Det = element<sub>%d, %d</sub> * cofactor<sub>%d, %d</sub>", 
                index1, 1,
                index1, 1
            );
            for(int i = 2; i <= matrix.columnCount; ++i){
               det = det + String.format(
                    " + element<sub>%d, %d</sub> * cofactor<sub>%d, %d</sub>", 
                    index1, i,
                    index1, i
               );
            }
            det = det + String.format(
                "\nDet = (%s)*(%s)", 
                Util.toString(matrix.getElementAt(index, 0)),
                Util.toString(matrix.getCofactorAt(index, 0))
            );
            for(int i = 1; i < matrix.columnCount; ++i){
               det = det + String.format(
                    " + (%s)*(%s)",
                    Util.toString(matrix.getElementAt(index, i)),
                    Util.toString(matrix.getCofactorAt(index, i))
               );
            }
            det = det + String.format(
                "\nDet = %s", 
                Util.toString(matrix.getElementAt(index, 0)*matrix.getCofactorAt(index, 0))
            );
            for(int i = 1; i < matrix.columnCount; ++i){
                det = det + String.format(
                    "+ %s", 
                    Util.toString(matrix.getElementAt(index, i)*matrix.getCofactorAt(index, i))
                );
            }
        }else{
            det = det + String.format(
                "Det = element<sub>%d, %d</sub> * cofactor<sub>%d, %d</sub>", 
                1, index1,
                1, index1
            );
            for(int i = 2; i <= matrix.columnCount; ++i){
               det = det + String.format(
                    " + element<sub>%d, %d</sub> * cofactor<sub>%d, %d</sub>", 
                    i, index1,
                    i, index1
               );
            }
            det = det + String.format(
                "\nDet = (%s)*(%s)", 
                Util.toString(matrix.getElementAt(0, index)),
                Util.toString(matrix.getCofactorAt(0, index))
            );
            for(int i = 1; i < matrix.columnCount; ++i){
               det = det + String.format(
                    " + (%s)*(%s)",
                    Util.toString(matrix.getElementAt(i, index)),
                    Util.toString(matrix.getCofactorAt(i, index))
               );
            }
            det = det + String.format(
                "\nDet = %s", 
                Util.toString(matrix.getElementAt(index, 0)*matrix.getCofactorAt(0, index))
            );
            for(int i = 1; i < matrix.columnCount; ++i){
                det = det + String.format(
                    "+ %s", 
                    Util.toString(matrix.getElementAt(index, i)*matrix.getCofactorAt(i, index))
                );
            }
        }
        det = det + "\nDet = " + Util.toString(matrix.getDeterminant());
        return det;
    }
    
    public void showResultSP(boolean show){
        resultSP.setVisible(show);
    }
    
    public void setResultTA(WrapTextPane resultTA){
        this.activeResultTA = resultTA;
        if(this.activeResultTA == null){
            this.activeResultTA = resultTA;
            showResultSP(true);
        }else{
            showResultSP(false);
        }
    }
    
    public MatrixDeterminant(Matrix matrix){
        this((MainFrame)null);
        showMatrix(matrix);
    }
    
    public void setRenderer(MatrixDeterminantTableRenderer renderer){
        this.renderer = renderer;
        matrixShow.setRenderer(renderer);
    }
    
    public void showMatrix(Matrix m){
        this.matrix = m;
        rowColCB.setSelectedIndex(0);
        String[] ids = new String[matrix.rowCount];
        for(int i = 0; i < matrix.rowCount; ++i){
            ids[i] = String.valueOf(i+1);
        }
        rowColNumCB.setModel(new DefaultComboBoxModel(ids));
        matrixShow.loadMatrix(matrix);
        showDeterminant();
    }
    
    public void showDeterminant(){
        boolean row = rowColCB.getSelectedIndex() == 0;
        int index = rowColNumCB.getSelectedIndex();
        renderer.setRow(row);
        renderer.setIndex(index);
        matrixShow.repaintTable();
        
        activeResultTA.setText(getResultText());
        
    }
    
    public void setDeterminant(int id, boolean row, int num){
        id = Arrays.binarySearch(indexTranslation, id);
        matrixCB.setSelectedIndex(id);
        setRowCol(row, num);
    }
    
    public void setRowCol(boolean row, int num){
        rowColCB.setSelectedIndex(row ? 0 : 1);
        rowColNumCB.setSelectedIndex(num);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        detLabel = new javax.swing.JLabel();
        matrixCBPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        matrixCB = new matrices.main.type.ComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rowColCB = new matrices.main.type.ComboBox<>();
        rowColNumCB = new matrices.main.type.ComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        matrixShowHolder = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        resultSP = new javax.swing.JScrollPane();
        resultTA = new matrices.main.type.WrapTextPane();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        detLabel.setText("Determinant");
        detLabel.setDoubleBuffered(true);
        jPanel1.add(detLabel, new java.awt.GridBagConstraints());

        matrixCBPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("(");
        jLabel3.setDoubleBuffered(true);
        matrixCBPanel.add(jLabel3, new java.awt.GridBagConstraints());

        matrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matrix 1", "Matrix 2" }));
        matrixCB.setDoubleBuffered(true);
        matrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrixCBActionPerformed(evt);
            }
        });
        matrixCBPanel.add(matrixCB, new java.awt.GridBagConstraints());

        jLabel4.setText(")");
        jLabel4.setDoubleBuffered(true);
        matrixCBPanel.add(jLabel4, new java.awt.GridBagConstraints());

        jPanel1.add(matrixCBPanel, new java.awt.GridBagConstraints());

        jLabel2.setText(" using ");
        jLabel2.setDoubleBuffered(true);
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        rowColCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Row", "Column" }));
        rowColCB.setDoubleBuffered(true);
        rowColCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowColCBActionPerformed(evt);
            }
        });
        jPanel1.add(rowColCB, new java.awt.GridBagConstraints());

        rowColNumCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        rowColNumCB.setDoubleBuffered(true);
        rowColNumCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowColNumCBActionPerformed(evt);
            }
        });
        jPanel1.add(rowColNumCB, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
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

        resultSP.setDoubleBuffered(true);

        resultTA.setDoubleBuffered(true);
        resultSP.setViewportView(resultTA);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(resultSP, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void rowColCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowColCBActionPerformed
        // TODO add your handling code here:
        showDeterminant();
    }//GEN-LAST:event_rowColCBActionPerformed

    private void rowColNumCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rowColNumCBActionPerformed
        // TODO add your handling code here:
        showDeterminant();
    }//GEN-LAST:event_rowColNumCBActionPerformed

    private void matrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrixCBActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_matrixCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel detLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private matrices.main.type.ComboBox<String> matrixCB;
    private javax.swing.JPanel matrixCBPanel;
    private javax.swing.JPanel matrixShowHolder;
    private javax.swing.JScrollPane resultSP;
    private matrices.main.type.WrapTextPane resultTA;
    private matrices.main.type.ComboBox<String> rowColCB;
    private matrices.main.type.ComboBox<String> rowColNumCB;
    // End of variables declaration//GEN-END:variables
}
