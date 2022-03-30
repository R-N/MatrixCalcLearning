/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.deprecated;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import matrices.main.form.MainFrame;
import matrices.tutorial.deprecated.HTMLTutorialSlide.Component;
import matrices.tutorial.type.ImageLabel;
import matrices.main.type.MatrixFrame;
/**
 *
 * @author MojoMacW7
 */
public class MatrixTutorial extends JFrame {
    
    
    
    
    ImageLabel imageLabel;
    String resourcePath = "/matrices/tutorial/resource/";
    Tutorial[] tutorials = new Tutorial[]{
        new Tutorial(
            "Test A",
            new ITutorialSlide[]{
                new TutorialSlide(
                        "A01",
                        new ImageIcon(this.getClass().getResource(resourcePath + "01.png")),
                        "TEXT A01"
                ),
                new TutorialSlide(
                        "A02",
                        new ImageIcon(this.getClass().getResource(resourcePath + "02.PNG")),
                        "TEXT A02"
                ),
                new TutorialSlide(
                        "A03",
                        new ImageIcon(this.getClass().getResource(resourcePath + "03.PNG")),
                        "TEXT A03"
                )
            }
        ),
        new Tutorial(
            "Test B",
            new ITutorialSlide[]{
                new TutorialSlide(
                        "B01",
                        new ImageIcon(this.getClass().getResource(resourcePath + "02.PNG")),
                        "TEXT B01"
                ),
                new TutorialSlide(
                        "B02",
                        new ImageIcon(this.getClass().getResource(resourcePath + "01.png")),
                        "TEXT B02"
                ),
                new HTMLTutorialSlide(
                        "B03",
                        new Component[]{
                            HTMLTutorialSlide.fromText("Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text Top text "),
                            HTMLTutorialSlide.fromImage(resourcePath + "01.png"),
                            HTMLTutorialSlide.fromImage(resourcePath + "02.PNG"),
                            HTMLTutorialSlide.fromImage(resourcePath + "03.PNG"),
                            HTMLTutorialSlide.fromText("Bottom text", HTMLTutorialSlide.ALIGNMENT_CENTER)
                        }
                )
            }
        )
    };
    int tutorialIndex = 0;
    int slideIndex = 0;
    VerticalTextPane htmlTP;
    /**
     * Creates new form MatrixTutorial
     */
    public MatrixTutorial(MainFrame root, JFrame parent) {
        initComponents();
        imageLabel = new ImageLabel();
        imageHolderPanel.add(imageLabel);
        String[] tutorialTitles = new String[tutorials.length];
        for(int i = 0; i < tutorials.length; ++i){
            tutorialTitles[i] = String.format("%d. %s", i+1, tutorials[i].getTitle());
        }
        tutorialCB.setModel(new DefaultComboBoxModel(tutorialTitles));
        //htmlTP = new VerticalTextPane(htmlSlidePanel, 3);
        htmlTP1.setContentType("text/html");
        htmlSlidePanel.setVisible(false);
        normalSlidePanel.setVisible(false);
        loadTutorialFirst(0, true);
        setVisible(true);
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
    
    public void loadSlide(TutorialSlide slide){
        
        this.imageLabel.setIcon(slide.getImage());
        if(slide.getText() == null || slide.getText().isEmpty()){
            this.textSP.setVisible(false);
        }else{
            this.textArea.setText(slide.getText());
            this.textSP.setVisible(true);
        }
        this.htmlSlidePanel.setVisible(false);
        this.normalSlidePanel.setVisible(true);
    }
    
    public void loadSlide(HTMLTutorialSlide slide){
        this.htmlTP1.setText(slide.getHTML());
        this.normalSlidePanel.setVisible(false);
        this.htmlSlidePanel.setVisible(true);
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
        Tutorial tutorial = tutorials[tutorialIndex];
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
        tutorial.getSlide(index).load(this);
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

        tutorialCB = new javax.swing.JComboBox<>();
        slideCB = new javax.swing.JComboBox<>();
        normalSlidePanel = new javax.swing.JPanel();
        imageHolderPanel = new javax.swing.JPanel();
        textSP = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        htmlSlidePanel = new javax.swing.JScrollPane();
        htmlTP1 = new javax.swing.JTextPane();
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

        normalSlidePanel.setLayout(new java.awt.GridBagLayout());

        imageHolderPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        normalSlidePanel.add(imageHolderPanel, gridBagConstraints);

        textSP.setMinimumSize(new java.awt.Dimension(20, 80));
        textSP.setPreferredSize(new java.awt.Dimension(223, 123));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        textSP.setViewportView(textArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        normalSlidePanel.add(textSP, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(normalSlidePanel, gridBagConstraints);

        htmlSlidePanel.setHorizontalScrollBar(null);

        htmlTP1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        htmlTP1.setText("asdafasfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdafasfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdafasfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf");
        htmlSlidePanel.setViewportView(htmlTP1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(htmlSlidePanel, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel2.add(closeButton, gridBagConstraints);

        prevButton.setText("Prev");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        jPanel2.add(prevButton, gridBagConstraints);

        nextButton.setText("Next");
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
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
        loadSlide(slideIndex-1, true);
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        loadSlide(slideIndex+1, true);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void tutorialCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialCBActionPerformed
        // TODO add your handling code here:
        loadTutorialFirst(tutorialCB.getSelectedIndex(), false);
        
    }//GEN-LAST:event_tutorialCBActionPerformed

    private void slideCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slideCBActionPerformed
        // TODO add your handling code here:
        loadSlide(slideCB.getSelectedIndex(), false);
    }//GEN-LAST:event_slideCBActionPerformed

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
            java.util.logging.Logger.getLogger(MatrixTutorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatrixTutorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane htmlSlidePanel;
    private javax.swing.JTextPane htmlTP1;
    private javax.swing.JPanel imageHolderPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel normalSlidePanel;
    private javax.swing.JButton prevButton;
    private javax.swing.JComboBox<String> slideCB;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textSP;
    private javax.swing.JComboBox<String> tutorialCB;
    // End of variables declaration//GEN-END:variables
}
