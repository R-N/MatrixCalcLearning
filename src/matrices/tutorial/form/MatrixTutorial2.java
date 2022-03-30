/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.form;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import matrices.main.form.MainFrame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import matrices.tutorial.type.section.TutorialSection;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.ImageSection;
import matrices.tutorial.type.MatrixSection;
import java.util.LinkedList;
import javax.swing.JViewport;
import matrices.math.Matrix;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialSlide2;
/**
 *
 * @author MojoMacW7
 */
public class MatrixTutorial2 extends JFrame {
    
    
    Tutorial2[] tutorials = null;
    
    int tutorialIndex = 0;
    int slideIndex = 0;
    
    public MainFrame main;
    
    TutorialSlide2 activeSlide = null;
    
    /**
     * Creates new form MatrixTutorial
     */
    LinkedList<TutorialSection> sections = new LinkedList<TutorialSection>();
    public JPanel createMainPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainPanelComponentResized(evt);
            }
        });
        panel.setLayout(new java.awt.GridBagLayout());
        Dimension mpSize = getMainPanelSize();
        panel.setSize(mpSize);
        return panel;
    }
    @Override
    public void toFront(){
        super.toFront();
        setFocusable(true);
        requestFocus();
        repaint();
    }
    public void forceDispose(){
        super.dispose();
    }
    public MatrixTutorial2(MainFrame main, Tutorial2[] tutorials) {
        this.main = main;
        initComponents();
        mainSP.getViewport().setAlignmentY(0);
        mainSP.getViewport().setAutoscrolls(false);
        mainSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE); //BACKINGSTORE_SCROLL_MODE or SIMPLE_SCROLL_MODE
        mainSP.getVerticalScrollBar().setUnitIncrement(16);
        mainSP.getHorizontalScrollBar().setUnitIncrement(16);
        loadTutorials(tutorials, 0, 0);
    }
    
    public void loadTutorials(Tutorial2[] tutorials, int index, int slide){
        this.tutorials = tutorials;
        String[] tutorialTitles = new String[tutorials.length];
        for(int i = 0; i < tutorials.length; ++i){
            tutorialTitles[i] = String.format("%d. %s", i+1, tutorials[i].getTitle());
        }
        tutorialCB.setModel(new DefaultComboBoxModel(tutorialTitles));
        loadTutorial(index, slide, true);
    }
    
    public void loadTutorial(int index, int slide, boolean setCB){
        this.tutorialIndex = index;
        if(setCB) tutorialCB.setSelectedIndex(index);
        slideCB.setModel(tutorials[index].getComboBoxModel());
        loadSlide(slide, true);
    }
    
    public void loadTutorialFirst(int index, boolean setCB){
        loadTutorial(index, 0, setCB);
    }
    public void loadTutorialLast(int index, boolean setCB){
        loadTutorial(index, tutorials[index].getSlideCount()-1, setCB);
    }
    public void addSection(Component component, GridBagConstraints constraints){
        mainPanel.add(component, constraints);
    }
    public void addSection(TutorialSection section){
        sections.add(section);
    }
    public void loadSlide(TutorialSlide2 slide){
        JPanel oldPanel = mainPanel;
        sections.clear();
        mainPanel = createMainPanel();
        for(TutorialSection ts : slide.sections){
            ts.addTo(this);
        }
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() { 
                mainSP.setViewportView(mainPanel);
                //revalidate();
                //mainPanel.repaint();
                /*Dimension size = getSize();
                pack();
                setSize(size);*/
                Dimension mpSize = getMainPanelSize();
                for(TutorialSection ts : slide.sections){
                    ts.init(mpSize);
                }
                oldPanel.removeAll();
                if(activeSlide != null && activeSlide != slide){
                    activeSlide.clean();
                }
                activeSlide = slide;
                mainSP.getViewport().setViewPosition(new Point(0,0));
                mainSP.getVerticalScrollBar().setValue(0);
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() { 
                        mainSP.getViewport().setViewPosition(new Point(0,0));
                        mainSP.getVerticalScrollBar().setValue(0);
                    }
                });
        //    }
        // });
    }
    
    
    public void loadSlide(int index, boolean setCB){
        if(index > 0){
            prevButton.setEnabled(true);
        }else if (index == 0){
            if(tutorialIndex > 0){
                prevButton.setEnabled(true);
            }else{
                prevButton.setEnabled(false);
            }
        }else{
            loadTutorialLast(tutorialIndex-1, true);
            return;
        }
        Tutorial2 tutorial = tutorials[tutorialIndex];
        int sc1 = tutorial.getSlideCount()-1;
        if(index < sc1){
            nextButton.setEnabled(true);
        }else if (index == sc1){
            if(tutorialIndex < tutorials.length-1){
                nextButton.setEnabled(true);
            }else{
                nextButton.setEnabled(false);
            }
        }else{
            loadTutorialFirst(tutorialIndex+1, true);
            return;
        }
        this.slideIndex = index;
        if(setCB) slideCB.setSelectedIndex(index);
        loadSlide(tutorial.getSlide(index));
    }
    public Dimension getMainPanelSize(){
        
        Dimension realSize = mainPanel.getSize();
        Insets border = mainPanel.getBorder().getBorderInsets(mainPanel);
        Dimension size = new Dimension(
                realSize.width - (border.left + border.right),
                realSize.height - (border.top + border.bottom)
        );
        return size;
    }
    
    /*@Override
    public void dispose(){
        main.tutorialFrame = null;
        super.dispose();
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tutorialCB = new matrices.main.type.ComboBox<>();
        slideCB = new matrices.main.type.ComboBox<>();
        mainSP = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tutorial");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tutorialCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tutorialCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(tutorialCB, gridBagConstraints);

        slideCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        slideCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slideCBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(slideCB, gridBagConstraints);

        mainSP.setAlignmentY(0.0F);
        mainSP.setDoubleBuffered(true);
        mainSP.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainSPComponentResized(evt);
            }
        });

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainPanelComponentResized(evt);
            }
        });
        mainPanel.setLayout(new java.awt.GridBagLayout());
        mainSP.setViewportView(mainPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainSP, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        closeButton.setText("Close");
        closeButton.setDoubleBuffered(true);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel2.add(closeButton, gridBagConstraints);

        prevButton.setText("Prev");
        prevButton.setDoubleBuffered(true);
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel2.add(prevButton, gridBagConstraints);

        nextButton.setText("Next");
        nextButton.setDoubleBuffered(true);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel2.add(nextButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO addTo your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO addTo your handling code here:
        loadSlide(slideIndex-1, true);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO addTo your handling code here:
        loadSlide(slideIndex+1, true);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void tutorialCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialCBActionPerformed
        // TODO addTo your handling code here:
        loadTutorialFirst(tutorialCB.getSelectedIndex(), false);
        
    }//GEN-LAST:event_tutorialCBActionPerformed

    private void slideCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slideCBActionPerformed
        // TODO addTo your handling code here:
        loadSlide(slideCB.getSelectedIndex(), false);
    }//GEN-LAST:event_slideCBActionPerformed

    private void mainPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainPanelComponentResized
        // TODO addTo your handling code here:
        /*Dimension size = getMainPanelSize();
        for(TutorialSection ts : sections){
            ts.onResize(size);
        }
        mainPanel.revalidate();*/
    }//GEN-LAST:event_mainPanelComponentResized

    private void mainSPComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_mainSPComponentResized
        // TODO add your handling code here:
        int width = mainSP.getViewport().getSize().width;
        mainPanel.setMaximumSize(new Dimension(width, mainPanel.getMaximumSize().height));
        mainPanel.setSize(new Dimension(width, mainPanel.getPreferredSize().height));
        Dimension size = getMainPanelSize();
        for(TutorialSection ts : sections){
            ts.onResize(size);
        }
        mainPanel.revalidate();
    }//GEN-LAST:event_mainSPComponentResized

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
            java.util.logging.Logger.getLogger(MatrixTutorial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainSP;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private matrices.main.type.ComboBox<String> slideCB;
    private matrices.main.type.ComboBox<String> tutorialCB;
    // End of variables declaration//GEN-END:variables
}
