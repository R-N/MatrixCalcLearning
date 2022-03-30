/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.JOptionPane;
import matrices.math.Matrix;
import matrices.Util;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import matrices.main.type.MatrixOBEHistory3;
import matrices.main.type.renderer.MatrixOBETableRenderer;
import matrices.main.type.MatrixPanel;
import matrices.main.type.MatrixList2;
/**
 *
 * @author MojoMacW7
 */
public class MatrixOBE extends MatrixPanel {
    
    private MatrixOBEHistory3 matrices = new MatrixOBEHistory3(new MatrixList2(), -1);
    
    DefaultTableModel dtm;
    
    MatrixShow otherMatrixShow = null;
    MatrixShow mainMatrixShow = null;
   
    
    boolean otherMatrixShowVisible = true;
    boolean mainPanelVisible = true;
    
    MatrixOBETableRenderer renderer;
    
    public void setOBE(Matrix[] matrices, int main){
        int mainId = -1;
        for(int i = 0; i < matrices.length; ++i){
            if(i == main)
                mainId = addMatrixIfNotExists(matrices[i]);
            else
                addMatrixIfNotExists(matrices[i]);
        }
        setMainMatrix(mainId);
    }
    
    public MatrixOBE(MainFrame root) {
        super(root);
        initComponents();
        setDoubleBuffered(true);
        mainMatrixShow = new MatrixShow();
        this.renderer = new MatrixOBETableRenderer();
        mainMatrixShow.setRenderer(renderer);
        mainMatrixShowHolder.add(mainMatrixShow);
        
        otherMatrixShow = new MatrixShow();
        otherMatrixShowHolder.add(otherMatrixShow);
        
        matrixList.setModel(new DefaultListModel());
        
    }
    public void init2(){
        showMainPanel(false);
        showSelectedMatrixPanel(false);
    }
    public void refreshUndoRedo(){
        undoButton.setEnabled(matrices.canUndo());
        redoButton.setEnabled(matrices.canRedo());
    }
    public void refreshSetRowLabel(){
        String s = setRowNumField1.getText();
        setRowLabel.setText(String.format("Row %s = ", s));
    }
    public void refreshMultiplyRowLabel(){
        multiplyRowLabel.setText(String.format("Row %s = ", multiplyRowNumField.getText()));
    }
    
    
    public int addMatrix(Matrix m){
        //m = m.clone();
        int ret = matrices.addMatrix(m);
        refreshList(true);
        refreshUndoRedo();
        matrixList.setSelectedIndex(matrices.getMatrixCount()-1);
        return ret;
    }
    
    public int indexOf(Matrix m){
        return matrices.indexOf(m);
    }
    
    public int addMatrixIfNotExists(Matrix m){
        int i = indexOf(m);
        if(i < 0){
            return addMatrix(m);
        }else{
            matrixList.setSelectedIndex(i);
            return i;
        }
    }
    
    
    public void removeSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index < 0){
            return;
        }
        matrices.removeMatrixAt(index);
        refreshOBE();
    }
    
    public void showSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index >= 0){
            otherMatrixShow.loadMatrix(matrices.getMatrix(index));
        }
        showSelectedMatrixPanel(index >= 0);
        
    }
    
    public void showMainMatrix(int index){
        if(index >= 0) mainMatrixShow.loadMatrix(this.matrices.getMainMatrix());
        showMainPanel(index >= 0);
    }
    public void setMainMatrix(int index){
        if (index >= 0){
            MatrixList2 matrices = this.matrices.getData();
            Matrix m = matrices.get(index);
            int rowCount = m.rowCount;
            for(int i = matrices.size()-1; i >= 0; --i){
                if(matrices.get(i).rowCount != rowCount){
                    if(Util.askConfirmation(root, "There is a matrix or more that don't have the same row count as the matrix you've chosen to use.\nContinuing will remove said matrices from the OBE matrix list.\nThis will not affect the main matrix list.\nDo you want to continue?")){
                        MatrixList2 newMatrices = matrices.clone();
                        for(int j = i; j >= 0; j--){
                            if(newMatrices.get(j).rowCount != rowCount){
                                newMatrices.remove(j);
                                if(j < index){
                                    --index;
                                }
                            }
                        }
                        this.matrices.setAll(newMatrices, index);
                        refreshList(false);
                    }else{
                        return;
                    }
                    break;
                }
            }
        }
        this.matrices.setMainMatrix(index);
        showMainMatrix(index);
    }
    public void refreshOBE(){
        if(matrices.hasMainMatrix()&& matrices.getMatrixCount() <= matrices.getMainMatrixIndex()){
            matrices.setMainMatrix(-1);
        }
        showMainMatrix(matrices.getMainMatrixIndex());
        refreshUndoRedo();
        refreshList(false);
        showSelectedMatrix();
    }
    public void refreshList(boolean keepSelection){
        DefaultListModel model = new DefaultListModel();
        for(String name : matrices.getMatrixNameArray()){
            model.addElement(name);
        }
        int index = matrixList.getSelectedIndex();
        matrixList.setModel(model);
        if(keepSelection && index >= 0) matrixList.setSelectedIndex(index);
    }
    public int getMatrixCount(){
        return matrices.getMatrixCount();
    }
    public Matrix getSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index < 0){
            return null;
        }
        return matrices.getMatrix(index);
    }
    
    /**
     * Creates new form MatrixEdit
     */
    public void showMainPanel(boolean show){
        if (show == mainPanelVisible){
            return;
        }
        mainPanelVisible = show;
        mainPanel.setVisible(show);
        refreshRemoveAddUse();
        refreshUndoRedo();
    }
    public void refreshRemoveAddUse(){
        int index = matrixList.getSelectedIndex();
        boolean x = index >= 0 && index != matrices.getMainMatrixIndex() && !mainPanelVisible;
        removeButton.setEnabled(x);
        setMainButton.setEnabled(x);
        enableAddButton(root.getSelectedMatrixIndex() >= 0);
    }
    public void showSelectedMatrixPanel(boolean show){
        if (show == otherMatrixShowVisible){
            return;
        }
        otherMatrixShowVisible = show;
        otherMatrixShow.setVisible(show);
    }
    
    public void enableAddButton(boolean enable){
        addButton.setEnabled(enable && !mainPanelVisible);
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
        jPanel8 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        mainMatrixShowHolder = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        multiplyRowLabel = new javax.swing.JLabel();
        multiplyRowCoefField = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        multiplyRowNumField = new javax.swing.JFormattedTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        switchRowsNumField1 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        switchRowsNumField2 = new javax.swing.JFormattedTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel3 = new javax.swing.JPanel();
        setRowLabel = new javax.swing.JLabel();
        setRowCoefField1 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        setRowNumField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        setRowCoefField2 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        setRowNumField2 = new javax.swing.JFormattedTextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        multiplyRowButton = new javax.swing.JButton();
        switchRowButton = new javax.swing.JButton();
        setRowButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        hightlightButton = new javax.swing.JButton();
        legendButton = new javax.swing.JButton();
        matrixSplitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        removeButton = new javax.swing.JButton();
        setMainButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        matrixList = new javax.swing.JList<>();
        otherMatrixShowHolder = new javax.swing.JPanel();

        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(350, 350));
        setName(""); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        mainSplitPane.setResizeWeight(0.75);
        mainSplitPane.setDoubleBuffered(true);
        mainSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mainSplitPanePropertyChange(evt);
            }
        });

        jPanel8.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel8.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel8.setLayout(new java.awt.BorderLayout());

        mainPanel.setMinimumSize(new java.awt.Dimension(400, 200));
        mainPanel.setPreferredSize(new java.awt.Dimension(400, 200));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        mainMatrixShowHolder.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(mainMatrixShowHolder, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jSeparator1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jSeparator1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        multiplyRowLabel.setText("Row X = ");
        multiplyRowLabel.setDoubleBuffered(true);
        jPanel5.add(multiplyRowLabel, new java.awt.GridBagConstraints());

        multiplyRowCoefField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.#############################"))));
        multiplyRowCoefField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        multiplyRowCoefField.setDoubleBuffered(true);
        multiplyRowCoefField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyRowCoefFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(multiplyRowCoefField, gridBagConstraints);

        jLabel8.setText(" Row ");
        jLabel8.setDoubleBuffered(true);
        jPanel5.add(jLabel8, new java.awt.GridBagConstraints());

        multiplyRowNumField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        multiplyRowNumField.setDoubleBuffered(true);
        multiplyRowNumField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                multiplyRowNumFieldFocusLost(evt);
            }
        });
        multiplyRowNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyRowNumFieldActionPerformed(evt);
            }
        });
        multiplyRowNumField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                multiplyRowNumFieldPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(multiplyRowNumField, gridBagConstraints);

        filler1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(filler1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel5, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Row ");
        jLabel5.setDoubleBuffered(true);
        jPanel2.add(jLabel5, new java.awt.GridBagConstraints());

        switchRowsNumField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        switchRowsNumField1.setDoubleBuffered(true);
        switchRowsNumField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchRowsNumField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(switchRowsNumField1, gridBagConstraints);

        jLabel6.setText(" <=> Row ");
        jLabel6.setDoubleBuffered(true);
        jPanel2.add(jLabel6, new java.awt.GridBagConstraints());

        switchRowsNumField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        switchRowsNumField2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(switchRowsNumField2, gridBagConstraints);

        filler2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(filler2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        setRowLabel.setText("Row X =");
        setRowLabel.setDoubleBuffered(true);
        jPanel3.add(setRowLabel, new java.awt.GridBagConstraints());

        setRowCoefField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.#############################"))));
        setRowCoefField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        setRowCoefField1.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(setRowCoefField1, gridBagConstraints);

        jLabel2.setText(" Row ");
        jLabel2.setDoubleBuffered(true);
        jPanel3.add(jLabel2, new java.awt.GridBagConstraints());

        setRowNumField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        setRowNumField1.setDoubleBuffered(true);
        setRowNumField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                setRowNumField1FocusLost(evt);
            }
        });
        setRowNumField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRowNumField1ActionPerformed(evt);
            }
        });
        setRowNumField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                setRowNumField1PropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(setRowNumField1, gridBagConstraints);

        jLabel3.setText(" + ");
        jLabel3.setDoubleBuffered(true);
        jPanel3.add(jLabel3, new java.awt.GridBagConstraints());

        setRowCoefField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.#############################"))));
        setRowCoefField2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        setRowCoefField2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(setRowCoefField2, gridBagConstraints);

        jLabel4.setText(" Row ");
        jLabel4.setDoubleBuffered(true);
        jPanel3.add(jLabel4, new java.awt.GridBagConstraints());

        setRowNumField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        setRowNumField2.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(setRowNumField2, gridBagConstraints);

        filler3.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(filler3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel3, gridBagConstraints);

        multiplyRowButton.setText("Multiply Row");
        multiplyRowButton.setDoubleBuffered(true);
        multiplyRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyRowButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel10.add(multiplyRowButton, gridBagConstraints);

        switchRowButton.setText("Switch Rows");
        switchRowButton.setDoubleBuffered(true);
        switchRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchRowButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel10.add(switchRowButton, gridBagConstraints);

        setRowButton.setText("Set Row");
        setRowButton.setDoubleBuffered(true);
        setRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRowButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel10.add(setRowButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(jPanel10, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        closeButton.setText("Close");
        closeButton.setDoubleBuffered(true);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel4.add(closeButton, new java.awt.GridBagConstraints());

        hightlightButton.setText("Highlighting");
        hightlightButton.setDoubleBuffered(true);
        hightlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hightlightButtonActionPerformed(evt);
            }
        });
        jPanel4.add(hightlightButton, new java.awt.GridBagConstraints());

        legendButton.setText("Legend");
        legendButton.setDoubleBuffered(true);
        legendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendButtonActionPerformed(evt);
            }
        });
        jPanel4.add(legendButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        mainPanel.add(jPanel4, gridBagConstraints);

        jPanel8.add(mainPanel, java.awt.BorderLayout.CENTER);

        mainSplitPane.setLeftComponent(jPanel8);

        matrixSplitPane.setDividerSize(9);
        matrixSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        matrixSplitPane.setResizeWeight(0.75);
        matrixSplitPane.setDoubleBuffered(true);
        matrixSplitPane.setMinimumSize(new java.awt.Dimension(200, 100));
        matrixSplitPane.setPreferredSize(new java.awt.Dimension(200, 200));
        matrixSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                matrixSplitPanePropertyChange(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        removeButton.setText("-");
        removeButton.setDoubleBuffered(true);
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(removeButton, gridBagConstraints);

        setMainButton.setText("Use");
        setMainButton.setDoubleBuffered(true);
        setMainButton.setEnabled(false);
        setMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setMainButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(setMainButton, gridBagConstraints);

        saveAsButton.setText("Save");
        saveAsButton.setDoubleBuffered(true);
        saveAsButton.setEnabled(false);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(saveAsButton, gridBagConstraints);

        addButton.setText("+");
        addButton.setDoubleBuffered(true);
        addButton.setEnabled(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(addButton, gridBagConstraints);

        undoButton.setText("Undo");
        undoButton.setDoubleBuffered(true);
        undoButton.setEnabled(false);
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        jPanel9.add(undoButton, gridBagConstraints);

        redoButton.setText("Redo");
        redoButton.setDoubleBuffered(true);
        redoButton.setEnabled(false);
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        jPanel9.add(redoButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel9, gridBagConstraints);

        jScrollPane2.setDoubleBuffered(true);

        matrixList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        matrixList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        matrixList.setDoubleBuffered(true);
        matrixList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                matrixListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(matrixList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        matrixSplitPane.setBottomComponent(jPanel1);

        otherMatrixShowHolder.setLayout(new java.awt.BorderLayout());
        matrixSplitPane.setLeftComponent(otherMatrixShowHolder);

        mainSplitPane.setRightComponent(matrixSplitPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(mainSplitPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void switchRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchRowButtonActionPerformed
        // TODO add your handling code here:
        
        String sRowNum1 = switchRowsNumField1.getText();
        int rowNum1 = 0;
        try{
            rowNum1 = Integer.parseInt(sRowNum1);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Nomor baris pertama \"%s\" salah", sRowNum1));
            return;
        }
        int rc = matrices.getMainMatrix().rowCount;
        if(rowNum1 < 1 || rowNum1 > rc){
            Util.showError(this, String.format("Baris pertama %d tidak ada", rowNum1));
            return;
        }
        
        String sRowNum2 = switchRowsNumField2.getText();
        int rowNum2 = 0;
        try{
            rowNum2 = Integer.parseInt(sRowNum2);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Nomor baris kedua \"%s\" salah", sRowNum2));
            return;
        }
        if(rowNum2 < 1 || rowNum2 > rc){
            Util.showError(this, String.format("Baris kedua %d tidak ada", rowNum2));
            return;
        }
        if(rowNum1 == rowNum2){
            Util.showError(this, "Anda mencoba menukar baris yang sama");
            return;
        }
        
        int c = matrices.getMatrixCount();
        int r1 = rowNum1-1;
        int r2 = rowNum2-1;
        
        MatrixList2 newMatrices = new MatrixList2(matrices.getData().size());
        for(int i = 0; i < c; ++i){
            Matrix m = matrices.getMatrix(i);
            if(m.rowCount > r1 && m.rowCount > r2){
                newMatrices.add(m.switchRows(r1, r2));
            }else{
                newMatrices.add(m);
            }
        }
        matrices.setData(newMatrices);
        
        refreshOBE();
    }//GEN-LAST:event_switchRowButtonActionPerformed

    private void setRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRowButtonActionPerformed
        // TODO add your handling code here:
        
        String sCoef1 = setRowCoefField1.getText();
        double coef1 = 1;
        try{
            coef1 = Util.parseCoefficient(sCoef1);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Koefisien pertama \"%s\" salah", sCoef1));
            return;
        }
        if(coef1 == 0){
            Util.showError(this, "Koefisien pertama tidak boleh nol");
            return;
        }
        
        String sCoef2 = setRowCoefField2.getText();
        double coef2 = 1;
        try{
            coef2 = Util.parseCoefficient(sCoef2);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Koefisien kedua \"%s\" salah", sCoef2));
            return;
        }
        if(coef2 == 0){
            Util.showError(this, "Koefisien kedua tidak boleh nol");
            return;
        }
        
        String sRowNum1 = setRowNumField1.getText();
        int rowNum1 = 0;
        try{
            rowNum1 = Integer.parseInt(sRowNum1);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Nomor baris pertama \"%s\" salah", sRowNum1));
            return;
        }
        int rc = matrices.getMainMatrix().rowCount;
        if(rowNum1 < 1 || rowNum1 > rc){
            Util.showError(this, String.format("Baris pertama %d tidak ada", rowNum1));
            return;
        }
        
        String sRowNum2 = setRowNumField2.getText();
        int rowNum2 = 0;
        try{
            rowNum2 = Integer.parseInt(sRowNum2);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Nomor baris \"%s\" salah", sRowNum2));
            return;
        }
        if(rowNum2 < 1 || rowNum2 > rc){
            Util.showError(this, String.format("Baris kedua %d tidak ada", rowNum2));
            return;
        }
        if(rowNum1 == rowNum2){
            Util.showError(this, "Anda mencoba menjumlahkan baris yang sama");
            return;
        }
        
        int c = matrices.getMatrixCount();
        int r1 = rowNum1-1;
        int r2 = rowNum2-1;
        
        MatrixList2 newMatrices = new MatrixList2(matrices.getData().size());
        for(int i = 0; i < c; ++i){
            Matrix m = matrices.getMatrix(i);
            if(m.rowCount > r1 && m.rowCount > r2){
                newMatrices.add(m.addRow2ToRow1(coef1, r1, coef2, r2));
            }else{
                newMatrices.add(m);
            }
        }
        
        matrices.setData(newMatrices);
        refreshOBE();
    }//GEN-LAST:event_setRowButtonActionPerformed

    private void multiplyRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyRowButtonActionPerformed
        // TODO add your handling code here:
        String sCoef = multiplyRowCoefField.getText();
        double coef = 0;
        try{
            coef = Util.parseCoefficient(sCoef);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Koefisien \"%s\" salah", sCoef));
            return;
        }
        if(coef == 0 || coef == 1){
            Util.showError(this, "Koefisien tidak boleh 0 atau 1");
            return;
        }
        String sRowNum = multiplyRowNumField.getText();
        int rowNum = 0;
        try{
            rowNum = Integer.parseInt(sRowNum);
        }catch(NumberFormatException ex){
            Util.showError(this, String.format("Nomor baris \"%s\" salah", sRowNum));
            return;
        }
        int rc = matrices.getMainMatrix().rowCount;
        if(rowNum < 1 || rowNum > rc){
            Util.showError(this, String.format("Baris %d tidak ada", rowNum));
            return;
        }
        int c = matrices.getMatrixCount();
        int r = rowNum-1;
        
        MatrixList2 newMatrices = new MatrixList2(matrices.getData().size());
        for(int i = 0; i < c; ++i){
            Matrix m = matrices.getMatrix(i);
            if(m.rowCount > r){
                newMatrices.add(m.multiplyRow(coef, r));
            }else{
                newMatrices.add(m);
            }
        }
        matrices.setData(newMatrices);
        refreshOBE();
    }//GEN-LAST:event_multiplyRowButtonActionPerformed

    private void setMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setMainButtonActionPerformed
        // TODO add your handling code here:
        int index = matrixList.getSelectedIndex();
        setMainMatrix(index);
        if(matrices.getMainMatrixIndex() == index){
            setMainButton.setEnabled(false);
        }
    }//GEN-LAST:event_setMainButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        removeSelectedMatrix();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void matrixListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_matrixListValueChanged
        // TODO add your handling code here:
        int index = matrixList.getSelectedIndex();
        saveAsButton.setEnabled(index >= 0);
        refreshRemoveAddUse();
        showSelectedMatrix();
    }//GEN-LAST:event_matrixListValueChanged

    private void switchRowsNumField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchRowsNumField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_switchRowsNumField1ActionPerformed

    private void matrixSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_matrixSplitPanePropertyChange
        // TODO add your handling code here:
        //lastMatrixDividerLocation = matrixSplitPane.getDividerLocation();
    }//GEN-LAST:event_matrixSplitPanePropertyChange

    private void mainSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mainSplitPanePropertyChange
        // TODO add your handling code here:
        
        //lastMainDividerLocation = mainSplitPane.getDividerLocation();
    }//GEN-LAST:event_mainSplitPanePropertyChange

    private void multiplyRowCoefFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyRowCoefFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_multiplyRowCoefFieldActionPerformed

    private void multiplyRowNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyRowNumFieldActionPerformed
        // TODO add your handling code here:
        refreshMultiplyRowLabel();
    }//GEN-LAST:event_multiplyRowNumFieldActionPerformed

    private void setRowNumField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRowNumField1ActionPerformed
        // TODO add your handling code here:
        refreshSetRowLabel();
    }//GEN-LAST:event_setRowNumField1ActionPerformed

    private void setRowNumField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_setRowNumField1PropertyChange
        // TODO add your handling code here:
        refreshSetRowLabel();
    }//GEN-LAST:event_setRowNumField1PropertyChange

    private void multiplyRowNumFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_multiplyRowNumFieldPropertyChange
        // TODO add your handling code here:
        refreshMultiplyRowLabel();
    }//GEN-LAST:event_multiplyRowNumFieldPropertyChange

    private void multiplyRowNumFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_multiplyRowNumFieldFocusLost
        // TODO add your handling code here:
        refreshMultiplyRowLabel();
    }//GEN-LAST:event_multiplyRowNumFieldFocusLost

    private void setRowNumField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_setRowNumField1FocusLost
        // TODO add your handling code here:
        refreshSetRowLabel();
    }//GEN-LAST:event_setRowNumField1FocusLost

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        // TODO add your handling code here:
        if(matrices.redo()) refreshOBE();
    }//GEN-LAST:event_redoButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        setMainMatrix(-1);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        if(matrices.undo()) refreshOBE();
    }//GEN-LAST:event_undoButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        Matrix m = root.getSelectedMatrix();
        if(m == null){
            return;
        }
        addMatrix(m);
    }//GEN-LAST:event_addButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        // TODO add your handling code here:
        Matrix m = getSelectedMatrix();
        if(m == null){
            return;
        }
        MatrixSaveAs dialog = new MatrixSaveAs(root, root.topmostFrame, m, String.format("OBE(%s)", m.name));
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void hightlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hightlightButtonActionPerformed
        // TODO add your handling code here:
        renderer.setActive(!renderer.isActive());
        mainMatrixShow.repaintTable();
    }//GEN-LAST:event_hightlightButtonActionPerformed

    private void legendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendButtonActionPerformed
        // TODO add your handling code here:
        MatrixOBEHighlightLegend legend = new MatrixOBEHighlightLegend(root, root.topmostFrame);
        
    }//GEN-LAST:event_legendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MatrixOBE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatrixOBE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatrixOBE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatrixOBE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton closeButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JButton hightlightButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton legendButton;
    private javax.swing.JPanel mainMatrixShowHolder;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JList<String> matrixList;
    private javax.swing.JSplitPane matrixSplitPane;
    private javax.swing.JButton multiplyRowButton;
    private javax.swing.JFormattedTextField multiplyRowCoefField;
    private javax.swing.JLabel multiplyRowLabel;
    private javax.swing.JFormattedTextField multiplyRowNumField;
    private javax.swing.JPanel otherMatrixShowHolder;
    private javax.swing.JButton redoButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JButton setMainButton;
    private javax.swing.JButton setRowButton;
    private javax.swing.JFormattedTextField setRowCoefField1;
    private javax.swing.JFormattedTextField setRowCoefField2;
    private javax.swing.JLabel setRowLabel;
    private javax.swing.JFormattedTextField setRowNumField1;
    private javax.swing.JFormattedTextField setRowNumField2;
    private javax.swing.JButton switchRowButton;
    private javax.swing.JFormattedTextField switchRowsNumField1;
    private javax.swing.JFormattedTextField switchRowsNumField2;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
