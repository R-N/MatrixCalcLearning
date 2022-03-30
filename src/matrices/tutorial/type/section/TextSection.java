/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.section;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import matrices.tutorial.deprecated.WrapEditorKit;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.TutorialSlide2;
import matrices.main.type.WrapTextPane;
/**
 *
 * @author MojoMacW7
 */

public class TextSection implements TutorialSection{
    
    WrapTextPane activePane = null;
    
    public GridBagConstraints gridBagConstraints;
    public double widthMul;
    public String text;
    public TextSection(String text){
        this(text, 100);
    }
    public TextSection(String text, double widthPercentage){
        this.text = text;
        this.widthMul = widthPercentage/100;
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
    }
    
    public WrapTextPane createPane(){
        activePane = new WrapTextPane(text);
        activePane.setBackground(null);
        return activePane;
    }
    
    /* @Override
     public boolean getScrollableTracksViewportWidth() {
          return true;
     }*/
     
    
    public void onResize(Dimension size){
        int width = (int)(size.width*widthMul);
        if(width != activePane.getWidth()){
            activePane.setSize(new Dimension(width, 20));
        }
    }
    public GridBagConstraints getConstraints(){
        return gridBagConstraints;
    }
    
    public void init(Dimension size){
        onResize(size);
    }
    public void addTo(MatrixTutorial2 frame, JPanel panel){
        activePane = createPane();
        panel.add(activePane, gridBagConstraints);
    }
    public void addTo(MatrixTutorial2 frame){
        activePane = createPane();
        frame.addSection(activePane, gridBagConstraints);
        frame.addSection(this);
    }
    public void setProvider(TutorialResourceProvider provider){
    }

    @Override
    public Dimension getPreferredSize() {
        return activePane.getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return activePane.getMinimumSize();
    }

    @Override
    public Dimension getSize() {
        return activePane.getSize();
    }

    @Override
    public void revalidate() {
        activePane.revalidate();
    }

    @Override
    public void validate() {
        activePane.validate();
    }

    @Override
    public void clean() {
        activePane = null;
    }
}
