/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.form;

import java.awt.Dimension;
import matrices.tutorial.form.MatrixTutorial2;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import matrices.math.Matrix;
import matrices.main.deprecated.MatrixArrayList;
import matrices.main.deprecated.MatrixOBEHistory;
import matrices.main.deprecated.MatrixOBEHistory1;
import matrices.main.type.MatrixOBEHistory3;
import javax.swing.JFrame;
import matrices.Util;
import matrices.main.type.MatrixFrame;
import matrices.tutorial.tutorials.*;
import matrices.main.type.MatrixList2;
import matrices.tutorial.type.Tutorial2;

/**
 *
 * @author MojoMacW7
 */
public class MainFrame extends MatrixFrame {

    /**
     * Creates new form Main
     */
    MatrixOBEHistory3 matrices = new MatrixOBEHistory3(new MatrixList2(), -1);
    MatrixShow selectedMatrixShow;
    MatrixOBE matrixOBE;
    MatrixOperation matrixOperation;
    MatrixForm matrixForm;
    MatrixDeterminant matrixDeterminant;
    MatrixCramer matrixCramer;
    boolean selectedMatrixShowVisible = true;
    MainTab matrixOBETab;
    MainTab matrixOperationTab;
    MainTab matrixFormTab;
    MainTab matrixDeterminantTab;
    MainTab matrixCramerTab;
    public MatrixFrame topmostFrame = null;
    
    public MainFrame() {
        super(null, null);
        initComponents();
        
        root = this;
        topmostFrame = this;
        selectedMatrixShow = new MatrixShow();
        matrixShowHolder.add(selectedMatrixShow);
        
        matrixOBE = new MatrixOBE(this);
        matrixOperation = new MatrixOperation(this);
        matrixForm = new MatrixForm(this);
        matrixDeterminant = new MatrixDeterminant(this);
        matrixCramer = new MatrixCramer(this);
        matrixOBETab = new MainTab(mainTabbedPane, matrixOBE, "OBE/GJE", "Operasi baris elementer (Eliminasi Gauss-Jordan)", 0);
        matrixOperationTab = new MainTab(mainTabbedPane, matrixOperation, "Operation", "Operasi matrix", 1);
        matrixFormTab = new MainTab(mainTabbedPane, matrixForm, "Form", "Bentuk-bentuk matrix", 2);
        matrixDeterminantTab = new MainTab(mainTabbedPane, matrixDeterminant, "Determinant", "Determinan Laplace", 3);
        matrixCramerTab = new MainTab(mainTabbedPane, matrixCramer, "Cramer", "Penyelesaian persamaan linear dengan cara Cramer", 4);
        
        pack();
        setPreferredSize(getSize());
        
        showSelectedMatrixPanel(false);
        
        matrixOBE.init2();
        
        refreshList();
        
    }
    
    public void refreshTabs(){
        boolean hasMatrix = getMatrixCount() > 0;
        matrixOBETab.showTab(hasMatrix || matrixOBE.getMatrixCount() > 0);
        matrixOperationTab.showTab(hasMatrix);
        matrixFormTab.showTab(hasMatrix);
        
        int c = matrices.getMatrixCount();
        boolean hasSquareMatrix = false;
        for(int i = 0; i < c; ++i){
            if(getMatrix(i).hasDeterminant()){
                hasSquareMatrix = true;
                break;
            }
        }
        matrixDeterminantTab.showTab(hasSquareMatrix);
        
        boolean canCramer = false;
        outerLoop:
        for(int i = 0; i < c; ++i){
            Matrix m = getMatrix(i);
            if(m.columnCount > 1 && m.hasInverse()){
                for(int j = 0; j < c; ++j){
                    if(j == i) continue;
                    Matrix n = getMatrix(j);
                    if(n.rowCount == m.rowCount && n.columnCount == 1){
                        canCramer = true;
                        break outerLoop;
                    }
                }
            }
        }
        matrixCramerTab.showTab(canCramer);
    }
    
    public void showSelectedMatrixPanel(boolean show){
        if (show == selectedMatrixShowVisible){
            return;
        }
        selectedMatrixShowVisible = true;
        selectedMatrixShow.setVisible(show);
    }
    public Matrix getMatrix(int index){
        return matrices.getMatrix(index);
    }
    public Matrix getSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index < 0){
            return null;
        }
        return getMatrix(index);
    }
    public int getMatrixCount(){
        return matrices.getMatrixCount();
    }
    public String[] getMatrixNameArray(){
        return matrices.getMatrixNameArray();
    }
        
        
    public void refreshList(){
        DefaultListModel model = new DefaultListModel();
        for(String name : matrices.getMatrixNameArray()){
            model.addElement(name);
        }
        matrixList.setModel(model);
        refreshUndoRedo();
        refreshTabs();
    }
    
    public int addMatrix(Matrix m){
        int ret = matrices.addMatrix(m);
        refreshList();
        matrixList.setSelectedIndex(matrices.getMatrixCount()-1);
        if(matrixOperationTab.isShown()) matrixOperation.refreshLhsCB(true);
        if(matrixFormTab.isShown()) matrixForm.refreshMatrixCB(true);
        if(matrixDeterminantTab.isShown()) matrixDeterminant.refreshMatrixCB(true);
        if(matrixCramerTab.isShown()) matrixCramer.refreshMainMatrixCB(true);
        return ret;
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
    
    public int indexOf(Matrix m){
        return matrices.indexOf(m);
    }
    
    public void alterMatrix(int index, Matrix m){
        matrices.setMatrix(index, m);
        if(index == matrixList.getSelectedIndex()){
            selectedMatrixShow.loadMatrix(m);
        }
        refreshList();
        matrixList.setSelectedIndex(index);
        if(matrixOperationTab.isShown()) matrixOperation.refreshLhsCB(false);
        if(matrixFormTab.isShown()) matrixForm.refreshMatrixCB(true);
        if(matrixDeterminantTab.isShown()) matrixDeterminant.refreshMatrixCB(false);
        if(matrixCramerTab.isShown()) matrixCramer.refreshMainMatrixCB(false);
    }
    
    public void removeSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index < 0){
            return;
        }
        matrices.removeMatrixAt(index);
        refreshList();
        if(matrixOperationTab.isShown()) matrixOperation.refreshLhsCB(false);
        if(matrixFormTab.isShown()) matrixForm.refreshMatrixCB(false);
        if(matrixDeterminantTab.isShown()) matrixDeterminant.refreshMatrixCB(false);
        if(matrixCramerTab.isShown()) matrixCramer.refreshMainMatrixCB(false);
    }
    
    public void showSelectedMatrix(){
        int index = matrixList.getSelectedIndex();
        if(index >= 0){
            selectedMatrixShow.loadMatrix(getMatrix(index));
        }
        showSelectedMatrixPanel(index >= 0);
    }
    
    public void refreshUndoRedo(){
        undoButton.setEnabled(matrices.canUndo());
        redoButton.setEnabled(matrices.canRedo());
    }
    
    public void showOperation(double lhsCoef, Matrix lhsMatrix, int operator, double rhsCoef, Matrix rhsMatrix){
        int lhsId = addMatrixIfNotExists(lhsMatrix);
        int rhsId = -1;
        
        if(rhsMatrix != null) rhsId = addMatrixIfNotExists(rhsMatrix);
        
        matrixOperation.setOperation(lhsCoef, lhsId, operator, rhsCoef, rhsId);
        mainTabbedPane.setSelectedComponent(matrixOperationTab);
        toFront();
    }
    public void showOBE(Matrix[] matrices, int mainMatrix){
        for(int i = 0; i < matrices.length; ++i){
            addMatrixIfNotExists(matrices[i]);
        }
        matrixOBE.setOBE(matrices, mainMatrix);
        mainTabbedPane.setSelectedComponent(matrixOBETab);
        toFront();
    }
    public void showForm(Matrix matrix, int form){
        int id = addMatrixIfNotExists(matrix);
        
        matrixForm.setForm(id, form);
        mainTabbedPane.setSelectedComponent(matrixFormTab);
        
        toFront();
    }
    public void showDeterminant(Matrix matrix, boolean row, int num){
        int id = addMatrixIfNotExists(matrix);
        
        matrixDeterminant.setDeterminant(id, row, num);
        mainTabbedPane.setSelectedComponent(matrixDeterminantTab);
        
        toFront();
    }
    public void showCramer(Matrix mainMatrix, boolean row, int num, Matrix subMatrix, int column){
        
        int mainId = addMatrixIfNotExists(mainMatrix);
        int subId = addMatrixIfNotExists(subMatrix);
        
        matrixCramer.setCramer(mainId, row, num, subId, column);
        mainTabbedPane.setSelectedComponent(matrixCramerTab);
        
        toFront();
    }
    
    public MatrixTutorial2 guideTutorial = null;
    public MatrixTutorial2 lessonTutorial = null;
    public MatrixTutorial2 exerciseTutorial = null;
    
    public void showGuide(int tutorialIndex, int slideIndex){
        showGuide(tutorialIndex, slideIndex, true);
    }
    public void showLesson(int tutorialIndex, int slideIndex){
        showLesson(tutorialIndex, slideIndex, true);
    }
    
    public void showGuide(int tutorialIndex, int slideIndex, boolean show){
        if(guideTutorial == null) guideTutorial = new MatrixTutorial2(this, Guide.tutorials){
                @Override
                public void dispose(){
                    guideTutorial = null;
                    super.dispose();
                }
        };
        
        guideTutorial.setTitle("Guide");
        initTutorial(guideTutorial, tutorialIndex, slideIndex, show);
    }
    
    public void showLesson(int tutorialIndex, int slideIndex, boolean show){
        if(lessonTutorial == null) lessonTutorial = new MatrixTutorial2(this, Lesson.tutorials){
                @Override
                public void dispose(){
                    lessonTutorial = null;
                    super.dispose();
                }
        };
        lessonTutorial.setTitle("Lesson");
        initTutorial(lessonTutorial, tutorialIndex, slideIndex, show);
    }
    
    public void showExercise(int tutorialIndex, int slideIndex){
        showExercise(tutorialIndex, slideIndex, true);
    }
    public void showExercise(int tutorialIndex, int slideIndex, boolean show){
        if(exerciseTutorial == null) exerciseTutorial = new MatrixTutorial2(this, Exercise.getExercise()){
                @Override
                public void dispose(){
                    if(!Util.askConfirmation(exerciseTutorial, "If you close this window, the next time you open Exercise, a new one will be generated instead. Continue?")){
                        return;
                    }
                    exerciseTutorial = null;
                    super.dispose();
                }
        };
        exerciseTutorial.setTitle("Exercise");
        initTutorial(exerciseTutorial, tutorialIndex, slideIndex, show);
    }
    public void closeExercise(){
        if(exerciseTutorial!=null){
            exerciseTutorial.forceDispose();
            exerciseTutorial = null;
        }
    }
    
    public int getSelectedMatrixIndex(){
        return matrixList.getSelectedIndex();
    }
    
    public void initTutorial(MatrixTutorial2 tutorialFrame, int tutorialIndex, int slideIndex, boolean show){
        tutorialFrame.loadTutorial(tutorialIndex, slideIndex, true);
        tutorialFrame.pack();
        Dimension dim = getSize();
        tutorialFrame.setSize(new Dimension((int)(dim.width * 0.8), (int)(dim.height * 0.8)));
        tutorialFrame.setLocationRelativeTo(this);
        tutorialFrame.setVisible(show);
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
        mainTabbedPane = new javax.swing.JTabbedPane();
        matrixSplitPane = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        removeButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        matrixList = new javax.swing.JList<>();
        matrixShowHolder = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matrices");
        setMinimumSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new java.awt.CardLayout());

        mainSplitPane.setResizeWeight(0.75);
        mainSplitPane.setDoubleBuffered(true);
        mainSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mainSplitPanePropertyChange(evt);
            }
        });

        mainTabbedPane.setDoubleBuffered(true);
        mainTabbedPane.setMinimumSize(new java.awt.Dimension(600, 200));
        mainTabbedPane.setPreferredSize(new java.awt.Dimension(600, 200));
        mainSplitPane.setLeftComponent(mainTabbedPane);

        matrixSplitPane.setBorder(null);
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
        removeButton.setToolTipText("Remove selected matrix");
        removeButton.setDoubleBuffered(true);
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(removeButton, gridBagConstraints);

        editButton.setText("Edit");
        editButton.setToolTipText("Edit selected matrix");
        editButton.setDoubleBuffered(true);
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(editButton, gridBagConstraints);

        newButton.setText("+");
        newButton.setToolTipText("Create new matrix");
        newButton.setDoubleBuffered(true);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(newButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel9, gridBagConstraints);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        undoButton.setText("Undo");
        undoButton.setDoubleBuffered(true);
        undoButton.setEnabled(false);
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        jPanel2.add(undoButton);

        redoButton.setText("Redo");
        redoButton.setDoubleBuffered(true);
        redoButton.setEnabled(false);
        redoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoButtonActionPerformed(evt);
            }
        });
        jPanel2.add(redoButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jScrollPane2.setDoubleBuffered(true);

        matrixList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        matrixList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        matrixList.setToolTipText("");
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

        matrixShowHolder.setLayout(new java.awt.BorderLayout());
        matrixSplitPane.setLeftComponent(matrixShowHolder);

        mainSplitPane.setRightComponent(matrixSplitPane);

        getContentPane().add(mainSplitPane, "card2");

        jMenuBar1.setDoubleBuffered(true);

        jMenu1.setText("Guide");
        jMenu1.setDoubleBuffered(true);

        jMenuItem1.setText("Introduction");
        jMenuItem1.setDoubleBuffered(true);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenu4.setText("Right Pane");
        jMenu4.setDoubleBuffered(true);
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem10.setText("New Matrix");
        jMenuItem10.setDoubleBuffered(true);
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem12.setText("Show Matrix");
        jMenuItem12.setDoubleBuffered(true);
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem17.setText("Edit Matrix");
        jMenuItem17.setDoubleBuffered(true);
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem20.setText("Remove Matrix");
        jMenuItem20.setDoubleBuffered(true);
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenu1.add(jMenu4);

        jMenu5.setText("Left Pane");
        jMenu5.setDoubleBuffered(true);

        jMenuItem7.setText("Overview");
        jMenuItem7.setDoubleBuffered(true);
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem2.setText("Gauss-Jordan Elimination");
        jMenuItem2.setDoubleBuffered(true);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuItem3.setText("Operation");
        jMenuItem3.setDoubleBuffered(true);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Form");
        jMenuItem4.setDoubleBuffered(true);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem5.setText("Determinant");
        jMenuItem5.setDoubleBuffered(true);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuItem6.setText("Cramer");
        jMenuItem6.setDoubleBuffered(true);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenu1.add(jMenu5);

        jMenu11.setText("Misc");
        jMenu11.setDoubleBuffered(true);

        jMenuItem8.setText("Matrix Element History");
        jMenuItem8.setDoubleBuffered(true);
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem8);

        jMenu1.add(jMenu11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Lesson");
        jMenu2.setDoubleBuffered(true);

        jMenuItem9.setText("Introduction");
        jMenuItem9.setDoubleBuffered(true);
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem11.setText("Operations");
        jMenuItem11.setDoubleBuffered(true);
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem14.setText("Determinant");
        jMenuItem14.setDoubleBuffered(true);
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem13.setText("Forms");
        jMenuItem13.setDoubleBuffered(true);
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem15.setText("Inverse with Adjoint");
        jMenuItem15.setDoubleBuffered(true);
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem26.setText("Elementary Row Operations");
        jMenuItem26.setDoubleBuffered(true);
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem26);

        jMenuItem27.setText("Inverse with ERO");
        jMenuItem27.setDoubleBuffered(true);
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem27);

        jMenuItem16.setText("Linear Equation");
        jMenuItem16.setDoubleBuffered(true);
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Exercise");
        jMenu3.setDoubleBuffered(true);
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem19.setText("Operation");
        jMenuItem19.setDoubleBuffered(true);
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);

        jMenuItem21.setText("Determinant");
        jMenuItem21.setDoubleBuffered(true);
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);

        jMenuItem22.setText("Form");
        jMenuItem22.setDoubleBuffered(true);
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem22);

        jMenuItem23.setText("Linear Equation");
        jMenuItem23.setDoubleBuffered(true);
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem23);

        jMenuItem24.setText("All");
        jMenuItem24.setDoubleBuffered(true);
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem24);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        removeSelectedMatrix();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        int index = matrixList.getSelectedIndex();
        if(index < 0){
            return;
        }
        MatrixEdit editor = new MatrixEdit(this, topmostFrame, index);
    }//GEN-LAST:event_editButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        MatrixNew dialog = new MatrixNew(this, topmostFrame);
        
    }//GEN-LAST:event_newButtonActionPerformed

    private void matrixListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_matrixListValueChanged
        // TODO add your handling code here:
        int index = matrixList.getSelectedIndex();
        boolean x = index >= 0;
        editButton.setEnabled(x);
        removeButton.setEnabled(x);
        matrixOBE.enableAddButton(x);
        //refreshTabs();
        showSelectedMatrix();
    }//GEN-LAST:event_matrixListValueChanged

    private void matrixSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_matrixSplitPanePropertyChange
        // TODO add your handling code here:
        //lastMatrixDividerLocation = matrixSplitPane.getDividerLocation();
    }//GEN-LAST:event_matrixSplitPanePropertyChange

    private void mainSplitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mainSplitPanePropertyChange
        // TODO add your handling code here:

        //lastMainDividerLocation = mainSplitPane.getDividerLocation();
    }//GEN-LAST:event_mainSplitPanePropertyChange

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoButtonActionPerformed
        // TODO add your handling code here:
        if(matrices.redo()) refreshList();
    }//GEN-LAST:event_redoButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        if(matrices.undo()) refreshList();
    }//GEN-LAST:event_undoButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        showGuide(0, 0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        showLesson(0, 0);
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        showLesson(2, 0);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        showLesson(4, 0);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
        showGuide(1, 0);
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        showGuide(1, 0);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        showGuide(1,1);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        showGuide(1,2);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        showGuide(1,3);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        showLesson(8,0);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        showLesson(5,0);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        showLesson(6,0);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        showGuide(2, 0);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        showGuide(3, 0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        showGuide(4, 0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        showGuide(5, 0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        showGuide(6, 0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        showGuide(7, 0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        showGuide(8, 0);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        showLesson(7, 0);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        showLesson(7, 6);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        showExercise(0, 0);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        showExercise(1, 0);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        showExercise(2, 0);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        showExercise(3, 0);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        showExercise(4, 0);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JList<String> matrixList;
    private javax.swing.JPanel matrixShowHolder;
    private javax.swing.JSplitPane matrixSplitPane;
    private javax.swing.JButton newButton;
    private javax.swing.JButton redoButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
