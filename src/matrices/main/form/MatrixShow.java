/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import matrices.Util;
import matrices.main.type.MatrixTableModel;
import matrices.math.Matrix;
import matrices.main.type.MatrixCellEditor;
import matrices.main.type.renderer.MatrixTableRenderer;

/**
 *
 * @author MojoMacW7
 */
public class MatrixShow extends javax.swing.JPanel {

    /**
     * Creates new form MatrixShow
     */
    private MatrixTableModel model;
    int cellEditMode = MatrixTableModel.CELL_EDIT_NONE;
    private TableCellRenderer renderer;
    private MatrixCellEditor editor;
    
    public MatrixShow() {
        this(MatrixTableModel.CELL_EDIT_NONE);
    }
    public MatrixShow(int cellEditMode) {
        initComponents();
        setDoubleBuffered(true);
        this.cellEditMode = cellEditMode;
        this.renderer = matrixTable.getDefaultRenderer(Double.class);
        loadMatrix(new Double[1][1], "");
        
        matrixTable.setShowHorizontalLines(true);
        matrixTable.setShowVerticalLines(true);
        
        editor = new MatrixCellEditor(new JTextField());
        matrixTable.setDefaultEditor(Double.class, editor);
        
        setRenderer(new MatrixTableRenderer());
        
        addTableSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    int row = matrixTable.getSelectedRow();
                    int col = matrixTable.getSelectedColumn();
                    if(row >= 0 && col >= 0){
                        elementSelectionLabel.setText(String.format("Selected row %d column %d", row+1, col+1));
                    }else{
                        elementSelectionLabel.setText("No element selected");  
                    }
                }
            }
        );
    }
    
    
    public void setRenderer (TableCellRenderer renderer){
        this.renderer = renderer;
        matrixTable.setDefaultRenderer(Double.class, renderer);
        repaintTable();
    }
    
    public TableCellRenderer getRenderer(){
        return renderer;
    }
    
    public void showSelectionLabel(boolean show){
        elementSelectionLabel.setVisible(show);
    }
    
    public void clearSelection(){
        matrixTable.clearSelection();
    }
    
    public void clearTable(){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.clearTable();
        matrixTable.setEnabled(true);
    }
    
    public void addRow(){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.addRow();
        matrixTable.setEnabled(true);
    }
    public void insertRow(int row){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.insertRow(row);
        matrixTable.setEnabled(true);
    }
    public void addColumn(){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.addColumn();
        
        matrixTable.setEnabled(true);
    }
    public void insertColumn(int col){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.insertColumn(col);
        
        matrixTable.setEnabled(true);
    }
    
    public void removeRow(int row){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.removeRow(row);
        
        matrixTable.setEnabled(true);
    }
    public void removeRow(){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.removeRow();
        
        matrixTable.setEnabled(true);
    }
    public void removeColumn(int col){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.removeColumn(col);
        
        matrixTable.setEnabled(true);
    }
    
    public void removeColumn(){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.removeColumn();
        
        matrixTable.setEnabled(true);
    }
    public void setRowCount(int rowCount){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.setRowCount(rowCount);
        
        matrixTable.setEnabled(true);
    }
    public void setColumnCount(int columnCount){
        stopCellEditing();
        matrixTable.setEnabled(false);
        model.setColumnCount(columnCount);
        
        matrixTable.setEnabled(true);
    }
    
    
    
    public void stopCellEditing(){
        if(matrixTable.isEditing()){
            matrixTable.getCellEditor().stopCellEditing();
        }
    }
    
    
    public int getSelectedRow(){
        return matrixTable.getSelectedRow();
    }
    
    public int getSelectedColumn(){
        return matrixTable.getSelectedColumn();
    }
    
    public int getRowCount(){
        return model.getRowCount();
    }
    
    public int getColumnCount(){
        return model.getColumnCount();
    }
    
    public Double getValueAt(int row, int col){
        return (Double)model.getValueAt(row, col);
    }
    
    public void showMatrixNameLabel(boolean show){
        matrixNameLabel.setVisible(show);
    }
    
    public MatrixTableModel getModel(){
        return model;
    }
    
    public void setModel(MatrixTableModel model){
        stopCellEditing();
        this.model = model;
        this.cellEditMode = model.getCellEditMode();
        matrixTable.setModel(model);
        matrixTable.setShowHorizontalLines(true);
        matrixTable.setShowVerticalLines(true);
        matrixTable.setTableHeader(null);
        matrixTable.setColumnSelectionAllowed(true);
        matrixTable.setRowSelectionAllowed(true);
        matrixTable.setCellSelectionEnabled(true);
        
    }
    
    public void loadMatrix(Matrix matrix){
        loadMatrix(matrix.toDoubleArray(), matrix.name);
    }

    public void loadMatrix(Double[][] data, String matrixName){
        model = new MatrixTableModel(data, new String[data[0].length], cellEditMode);
        //setCellEditMode(cellEditMode);
        setModel(model);
        matrixNameLabel.setText(matrixName);
        this.setVisible(true);
    }
    public void repaintTable(){
        matrixTable.repaint();
    }
    
    public void setCellEditMode(int cellEditMode){
        this.cellEditMode = cellEditMode;
        model.setCellEditMode(cellEditMode);
        setModel(model);
    }
    
    public void addTableSelectionListener(ListSelectionListener listener){
        matrixTable.getSelectionModel().addListSelectionListener(listener);
        matrixTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
    }
    public void addTableMouseListener(MouseAdapter listener){
        matrixTable.addMouseListener(listener);
    }
    
    public double[][] readToArray() {
        stopCellEditing();
        int rc = getRowCount();
        int cc = getColumnCount();
        double[][] ret = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                Double val =getValueAt(i, j);
                double parsed = Util.parseDouble(val);
                ret[i][j] = parsed;
            }
        }
        return ret;
    }
    
    public Matrix readToMatrix() {
        return new Matrix(readToArray(), matrixNameLabel.getText());
    }
    
    public Dimension getTableSize(){
        return matrixTable.getPreferredSize();
        //return new Dimension(matrixTable.getPreferredSize().width, matrixTable.getRowCount()*matrixTable.getRowHeight());
    }
    
    public Dimension getSPSize(){
        return matrixTableSP.getViewport().getSize();
    }
    
    /*@Override
    public Dimension getPreferredSize(){
        Dimension spSize = matrixTableSP.getPreferredSize();
        Dimension nameSize = matrixNameLabel.getPreferredSize();
        Dimension elSize = elementSelectionLabel.getPreferredSize();
        return new Dimension(
                Util.max(spSize.width, Util.max(nameSize.width, elSize.width)),
                spSize.height + nameSize.height + elSize.height
        );
    }*/
    
    public void fitTable(){
        
       /* Dimension pTableSize = matrixTable.getPreferredSize();
        Dimension spVSize = matrixTableSP.getViewport().getSize();
        Dimension spSize = matrixTableSP.getSize();
        Dimension newSize = new Dimension(
                pTableSize.width + (spSize.width - spVSize.width),
                pTableSize.height + (spSize.height - spVSize.height)
        );
        matrixTableSP.setPreferredSize(newSize);
        matrixTableSP.setSize(newSize);*/
       matrixTableSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       matrixTableSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
       matrixTableSPGBC.fill = java.awt.GridBagConstraints.NONE;
       this.add(matrixTableSP, matrixTableSPGBC);
       Dimension size = matrixTable.getPreferredSize();
       size = new Dimension(size.width + 3, size.height + 3);
       matrixTableSP.setPreferredSize(size);
       matrixTableSP.setSize(size);
    }
    
    java.awt.GridBagConstraints matrixTableSPGBC;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        matrixNameLabel = new javax.swing.JLabel();
        elementSelectionLabel = new javax.swing.JLabel();
        matrixTableSP = new javax.swing.JScrollPane();
        matrixTable = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        matrixNameLabel.setText("Matrix");
        matrixNameLabel.setToolTipText("Matrix name");
        matrixNameLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(matrixNameLabel, gridBagConstraints);

        elementSelectionLabel.setText(" ");
        elementSelectionLabel.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        add(elementSelectionLabel, gridBagConstraints);

        matrixTableSP.setToolTipText("");
        matrixTableSP.setDoubleBuffered(true);
        matrixTableSP.setPreferredSize(new java.awt.Dimension(300, 64));

        matrixTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        matrixTable.setToolTipText("");
        matrixTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        matrixTable.setCellSelectionEnabled(true);
        matrixTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        matrixTable.setDoubleBuffered(true);
        matrixTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        matrixTableSP.setViewportView(matrixTable);
        matrixTable.getAccessibleContext().setAccessibleName("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(matrixTableSP, gridBagConstraints);
        matrixTableSPGBC = gridBagConstraints;
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel elementSelectionLabel;
    private javax.swing.JLabel matrixNameLabel;
    private javax.swing.JTable matrixTable;
    private javax.swing.JScrollPane matrixTableSP;
    // End of variables declaration//GEN-END:variables
}
