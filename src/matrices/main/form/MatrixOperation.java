/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import matrices.Util;
import matrices.math.Matrix;
import matrices.main.type.MatrixPanel;
import java.util.Arrays;
/**
 *
 * @author MojoMacW7
 */
public class MatrixOperation extends MatrixPanel {

    public static final int OPERATOR_ADD = 0;
    public static final int OPERATOR_SUBTRACT = 1;
    public static final int OPERATOR_MULTIPLY = 2;
    public static final int OPERATOR_DIVIDE = 3;
    public static final int OPERATOR_MOD = 4;
    /**
     * Creates new form MatrixOperation
     */
    MatrixShow matrixShow;
    Matrix matrix = null;
    int[] canAddOrSubtractIds;
    String[] canAddOrSubtractNames;
    int[] canMultiplyIds;
    String[] canMultiplyNames;
    
    double lhsCoef=1;
    double rhsCoef=1;
    Matrix lhs = null;
    Matrix rhs = null;
    String singleName = "Uniform single";
    DefaultComboBoxModel singleCBModel = new DefaultComboBoxModel(new String[]{singleName});
    public MatrixOperation(MainFrame root) {
        super(root);
        initComponents();
        setDoubleBuffered(true);
        matrixShow = new MatrixShow();
        matrixShow.showMatrixNameLabel(false);
        matrixShowHolder.add(matrixShow);
        showCalcTA(false);
        
        matrixShow.addTableSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    refreshCalc();
                }
            }
        );
    }
    
    public void setOperation(double lhsCoef, int lhsId, int operator, double rhsCoef, int rhsId){
        lhsCoefField.setText(Util.toString(lhsCoef));
        lhsMatrixCB.setSelectedIndex(lhsId);
        refreshRhsCandidates();
        operatorCB.setSelectedIndex(operator);
        rhsCoefField.setText(Util.toString(rhsCoef));
        if(rhsId < 0){
            rhsMatrixCB.setSelectedIndex(0);
        }else{
            rhsMatrixCB.setSelectedIndex(Arrays.binarySearch(canMultiplyIds, rhsId)+1);
        }
    }
    
    public void showCalcTA(boolean show){
        calcSP.setVisible(show);
        showCalcButton.setText(show ? "Hide Calculation" : "Show Calculation");
    }
    
    
    public void refreshLhsCB(boolean keepSelection){
        int count = root.getMatrixCount();
        int index = lhsMatrixCB.getSelectedIndex();
        lhsMatrixCB.setModel(new DefaultComboBoxModel(root.getMatrixNameArray()));
        if(keepSelection && index >= 0) lhsMatrixCB.setSelectedIndex(index);
        refreshRhsCandidates();
        refreshRhsCB();
    }
    
    public void refreshRhsCandidates(){
        Matrix lhs = getLhsMatrix();
        int count = root.getMatrixCount();
        int[] canAddOrSubtracts = new int[count];
        int canAddOrSubtractsCount = 0;
        int[] canMultiplies = new int[count];
        int canMultipliesCount = 0;
        
        for(int i = 0; i < count; ++i){
            Matrix rhs = root.getMatrix(i);
            if(Matrix.canAddOrSubtract(lhs, rhs)){
                canAddOrSubtracts[canAddOrSubtractsCount++] = i;
            }
            if(Matrix.canMultiply(lhs, rhs)){
                canMultiplies[canMultipliesCount++] = i;
            }
        }
        
        this.canAddOrSubtractIds = new int[canAddOrSubtractsCount];
        this.canAddOrSubtractNames = new String[canAddOrSubtractsCount+1];
        this.canAddOrSubtractNames[0] = singleName;
        for(int i = 0; i < canAddOrSubtractsCount; ++i){
            this.canAddOrSubtractIds[i] = canAddOrSubtracts[i];
            this.canAddOrSubtractNames[i+1] = root.getMatrix(canAddOrSubtracts[i]).name;
        }
        this.canMultiplyIds = new int[canMultipliesCount];
        this.canMultiplyNames = new String[canMultipliesCount+1];
        this.canMultiplyNames[0] = singleName;
        for(int i = 0; i < canMultipliesCount; ++i){
            this.canMultiplyIds[i] = canMultiplies[i];
            this.canMultiplyNames[i+1] = root.getMatrix(canMultiplies[i]).name;
        }
    }
    
    public void refreshRhsCB(){
        int op = operatorCB.getSelectedIndex();
        int index = rhsMatrixCB.getSelectedIndex();
        if(op < OPERATOR_MULTIPLY){
            rhsMatrixCB.setModel(new DefaultComboBoxModel(canAddOrSubtractNames));
        }else if (op == OPERATOR_MULTIPLY && canMultiplyIds.length > 0){
            rhsMatrixCB.setModel(new DefaultComboBoxModel(canMultiplyNames));
        }else{
            rhsMatrixCB.setModel(singleCBModel);
        }
        showMatrix();
    }
    
    public Matrix getLhsMatrix(){
        return root.getMatrix(lhsMatrixCB.getSelectedIndex());
    }
    
    public Matrix getRhsMatrix(){
        int op = operatorCB.getSelectedIndex();
        int index = rhsMatrixCB.getSelectedIndex();
        if(index <= 0){
            return null;
        }else{
            --index;
            if(op < OPERATOR_MULTIPLY){
                return root.getMatrix(canAddOrSubtractIds[index]);
            }else {
                return root.getMatrix(canMultiplyIds[index]);
            }
        }
            
        /*if(op < OPERATOR_MULTIPLY){
            return root.getMatrix(canAddOrSubtractIds[index]);
        }else if (op == OPERATOR_MULTIPLY && index > 0){
            return root.getMatrix(canMultiplyIds[index-1]);
        }else{
            return null;
        }*/
    }
    
    public void showMatrix(){
        checkZeroDivision();
        String sLhsCoef = lhsCoefField.getText();
        lhsCoef = 1;
        try{
            lhsCoef = Util.parseCoefficient(sLhsCoef);
        }catch(NumberFormatException ex){
            Util.showError(root, "Koefisien matrix pertama salah");
            return;
        }
        String sRhsCoef = rhsCoefField.getText();
        rhsCoef = 1;
        try{
            rhsCoef = Util.parseCoefficient(sRhsCoef);
        }catch(NumberFormatException ex){
            Util.showError(root, "Koefisien matrix kedua salah");
            return;
        }
        lhs = getLhsMatrix();
        rhs = getRhsMatrix();
        Matrix lhs2 = lhsCoef == 1 ? lhs : Matrix.multiply(lhsCoef, lhs);
        Matrix rhs2 = rhs == null ? null : rhsCoef == 1 ? rhs : Matrix.multiply(rhsCoef, rhs);
        switch(operatorCB.getSelectedIndex()){
            case OPERATOR_ADD:{
                if(rhs2 == null){
                    matrix = Matrix.add(lhs2, rhsCoef);
                }else{
                    matrix = Matrix.add(lhs2, rhs2);
                }
                break;
            }
            case OPERATOR_SUBTRACT:{
                if(rhs2 == null){
                    matrix = Matrix.subtract(lhs2, rhsCoef);
                }else{
                    matrix = Matrix.subtract(lhs2, rhs2);
                }
                break;
            }
            case OPERATOR_MULTIPLY:{
                if(rhs2 == null){
                    if(rhsCoef == 1){
                        matrix = lhs2.clone();
                    }else{
                        matrix = Matrix.multiply(lhs2, rhsCoef);
                    }
                }else{
                    matrix = Matrix.multiply(lhs2, rhs2);
                }
                break;
            }
            case OPERATOR_DIVIDE:{
                if(rhsCoef == 1){
                    matrix = lhs2.clone();
                }else{
                    matrix = Matrix.divide(lhs2, rhsCoef);
                }
                break;
            }
            case OPERATOR_MOD:{
                if(rhsCoef == 1){
                    matrix = lhs2.clone();
                }else{
                    matrix = Matrix.mod(lhs2, rhsCoef);
                }
                break;
            }
        }
        matrixShow.loadMatrix(matrix);
        refreshCalc();
    }
    
    public void refreshCalc(){
        int row = matrixShow.getSelectedRow();
        int col = matrixShow.getSelectedColumn();
        if(row < 0 || col < 0){
            calcTA.setText("Please select an element");
            return;
        }
        String text = "";
        int op = operatorCB.getSelectedIndex();
        if(rhs == null){
            int row1 = row+1;
            int col1 = col+1;
            double eLhs = lhs.getElementAt(row, col);
            String sLhs = Util.toString(eLhs);
            String lhsName = String.format("%s<sub>%d, %d</sub>", lhs.name, row1, col1);
            if(lhsCoef != 1){
                lhsName = String.format("(%s*%s)", Util.toString(lhsCoef), lhsName);
                sLhs = String.format("(%s*%s)", Util.toString(lhsCoef), sLhs);
            }
            String sRhs = Util.toString(rhsCoef);
            String sOp = "";
            double y = 0;
            if(op == OPERATOR_ADD){
                sOp = "+";
                y = lhsCoef*eLhs + rhsCoef;
            }else if (op == OPERATOR_SUBTRACT){
                sOp = "-";
                y = lhsCoef*eLhs - rhsCoef;
            }else if(op == OPERATOR_MULTIPLY){
                sOp = "*";
                y = lhsCoef*eLhs * rhsCoef;
            }else if (op == OPERATOR_DIVIDE){
                sOp = "/";
                y = lhsCoef*eLhs / rhsCoef;
            }else if (op == OPERATOR_MOD){
                sOp = "%";
                y = (lhsCoef*eLhs) % rhsCoef;
            }
            text = String.format("%s %s %s",
                    lhsName,
                    sOp,
                    sRhs
            );
            text = text + String.format("\n%s %s %s\n=%s %s %s\n=%s", 
                    sLhs, 
                    sOp,
                    sRhs,
                    Util.toString(lhsCoef*eLhs),
                    sOp,
                    sRhs,
                    y
            );
        }else{
            if(op < OPERATOR_MULTIPLY){
                int row1 = row+1;
                int col1 = col+1;
                double eLhs = lhs.getElementAt(row, col);
                double eRhs = rhs.getElementAt(row, col);
                String sLhs = Util.toString(eLhs);
                String lhsName = String.format("%s<sub>%d, %d</sub>", lhs.name, row1, col1);
                if(lhsCoef != 1){
                    lhsName = String.format("(%s*%s)", Util.toString(lhsCoef), lhsName);
                    sLhs = String.format("(%s*%s)", Util.toString(lhsCoef), sLhs);
                }
                String sRhs = Util.toString(eRhs);
                String rhsName = String.format("%s<sub>%d, %d</sub>", rhs.name, row1, col1);
                if(rhsCoef != 1){
                    rhsName = String.format("(%s*%s)", Util.toString(rhsCoef), rhsName);
                    sRhs = String.format("(%s*%s)", Util.toString(rhsCoef), sRhs);
                }
                String sOp = op == OPERATOR_ADD ? "+" : "-";
                text = String.format("%s %s %s",
                        lhsName,
                        sOp,
                        rhsName
                );
                text = text + String.format("\n%s %s %s\n=%s %s %s\n=%s", 
                        sLhs, 
                        sOp,
                        sRhs,
                        Util.toString(lhsCoef*eLhs),
                        sOp,
                        Util.toString(rhsCoef*eRhs),
                        lhsCoef*eLhs + (op == 0 ? 1 : -1) * rhsCoef*eRhs
                );
            }else if (op == OPERATOR_MULTIPLY){
                int row1 = row+1;
                int col1 = col+1;
                int rc = lhs.rowCount;
                int cc = rhs.columnCount;
                double[][] newData = new double[rc][cc];
                int c = lhs.columnCount;
                double x = 0;
                String text0 = "";
                String text1 = "= ";
                String text2 = "= ";
                String text3 = "= ";
                for (int k = 0; k < c; ++k){
                    if(k > 0){
                        text0 = text0 + " + ";
                        text1 = text1 + " + ";
                        text2 = text2 + " + ";
                        text3 = text3 + " + ";
                    }
                    int k1 = k+1;
                    double eLhs = lhs.getElementAt(row, k);
                    double eRhs = rhs.getElementAt(k, col);
                    String lhsName = String.format("%s<sub>%d, %d</sub>", lhs.name, row1, k1);
                    String sLhs = Util.toString(eLhs);
                    if(lhsCoef != 1){
                        sLhs = String.format("(%s*%s)", Util.toString(lhsCoef), sLhs);
                        lhsName = String.format("(%s*%s)", Util.toString(lhsCoef), lhsName);
                    }
                    String sRhs = Util.toString(eRhs);
                    String rhsName = String.format("%s<sub>%d, %d</sub>", rhs.name, k1, col1);
                    if(rhsCoef != 1){
                        sRhs = String.format("(%s*%s)", Util.toString(rhsCoef), sRhs);
                        rhsName = String.format("(%s*%s)", Util.toString(rhsCoef), rhsName);
                    }
                    text0 = String.format("%s(%s*%s)", text0, lhsName, rhsName);
                    text1 = String.format("%s(%s*%s)", text1, sLhs, sRhs);
                    text2 = String.format("%s(%s*%s)", text2, Util.toString(lhsCoef*eLhs), Util.toString(rhsCoef*eRhs));
                    double y = lhsCoef*eLhs * rhsCoef*eRhs;
                    text3 = text3 + Util.toString(y);
                    x+=y;
                }
                text = String.format("%s\n%s\n%s\n%s\n= %s", text0, text1, text2, text3, Util.toString(x));
            }
        }
        calcTA.setText(text);
    }
    
    public boolean checkZeroDivision(){
        if(operatorCB.getSelectedIndex() == 3){
            double rhsCoef = Util.parseCoefficient(rhsCoefField.getText());
            if(rhsCoef == 0){
                Util.showError(root, "Divisor can't be zero");
                rhsCoefField.setText("");
                return true;
            }
        }
        return false;
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
        lhsCoefField = new javax.swing.JFormattedTextField();
        lhsMatrixCB = new matrices.main.type.ComboBox<>();
        operatorCB = new matrices.main.type.ComboBox<>();
        rhsCoefField = new javax.swing.JFormattedTextField();
        rhsMatrixCB = new matrices.main.type.ComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        matrixShowHolder = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        calcSP = new javax.swing.JScrollPane();
        calcTA = new matrices.main.type.WrapTextPane();
        jPanel2 = new javax.swing.JPanel();
        showCalcButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lhsCoefField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.#######################"))));
        lhsCoefField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        lhsCoefField.setDoubleBuffered(true);
        lhsCoefField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lhsCoefFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(lhsCoefField, gridBagConstraints);

        lhsMatrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "N" }));
        lhsMatrixCB.setDoubleBuffered(true);
        lhsMatrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lhsMatrixCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(lhsMatrixCB, gridBagConstraints);

        operatorCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+", "-", "*", "/", "%" }));
        operatorCB.setDoubleBuffered(true);
        operatorCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operatorCBActionPerformed(evt);
            }
        });
        jPanel1.add(operatorCB, new java.awt.GridBagConstraints());

        rhsCoefField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.#######################"))));
        rhsCoefField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        rhsCoefField.setDoubleBuffered(true);
        rhsCoefField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                rhsCoefFieldFocusLost(evt);
            }
        });
        rhsCoefField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rhsCoefFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        jPanel1.add(rhsCoefField, gridBagConstraints);

        rhsMatrixCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "N" }));
        rhsMatrixCB.setDoubleBuffered(true);
        rhsMatrixCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rhsMatrixCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(rhsMatrixCB, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel1, gridBagConstraints);

        jSeparator2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSeparator2, gridBagConstraints);

        matrixShowHolder.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(matrixShowHolder, gridBagConstraints);

        jSeparator1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSeparator1, gridBagConstraints);

        calcSP.setDoubleBuffered(true);

        calcTA.setDoubleBuffered(true);
        calcSP.setViewportView(calcTA);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(calcSP, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        showCalcButton.setText("Show Calculation");
        showCalcButton.setDoubleBuffered(true);
        showCalcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCalcButtonActionPerformed(evt);
            }
        });
        jPanel2.add(showCalcButton, new java.awt.GridBagConstraints());

        saveAsButton.setText("Save As");
        saveAsButton.setDoubleBuffered(true);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(saveAsButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void lhsCoefFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lhsCoefFieldActionPerformed
        // TODO add your handling code here:
        showMatrix();
        
    }//GEN-LAST:event_lhsCoefFieldActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        // TODO add your handling code here:
        MatrixSaveAs msa = new MatrixSaveAs(root, root.topmostFrame, matrix);
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void lhsMatrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lhsMatrixCBActionPerformed
        // TODO add your handling code here:
        refreshRhsCandidates();
        refreshRhsCB();
        showMatrix();
    }//GEN-LAST:event_lhsMatrixCBActionPerformed

    private void rhsCoefFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rhsCoefFieldActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_rhsCoefFieldActionPerformed

    private void rhsMatrixCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rhsMatrixCBActionPerformed
        // TODO add your handling code here:
        showMatrix();
    }//GEN-LAST:event_rhsMatrixCBActionPerformed

    private void operatorCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operatorCBActionPerformed
        // TODO add your handling code here:
        refreshRhsCB();
        showMatrix();
    }//GEN-LAST:event_operatorCBActionPerformed

    private void showCalcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCalcButtonActionPerformed
        // TODO add your handling code here:
        showCalcTA(!calcSP.isVisible());
    }//GEN-LAST:event_showCalcButtonActionPerformed

    private void rhsCoefFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rhsCoefFieldFocusLost
        // TODO add your handling code here:
        checkZeroDivision();
    }//GEN-LAST:event_rhsCoefFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane calcSP;
    private matrices.main.type.WrapTextPane calcTA;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JFormattedTextField lhsCoefField;
    private matrices.main.type.ComboBox<String> lhsMatrixCB;
    private javax.swing.JPanel matrixShowHolder;
    private matrices.main.type.ComboBox<String> operatorCB;
    private javax.swing.JFormattedTextField rhsCoefField;
    private matrices.main.type.ComboBox<String> rhsMatrixCB;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JButton showCalcButton;
    // End of variables declaration//GEN-END:variables
}
