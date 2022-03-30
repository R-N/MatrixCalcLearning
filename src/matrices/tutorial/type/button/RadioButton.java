/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.section.TutorialSection;
/**
 *
 * @author MojoMacW7
 */
public class RadioButton extends JRadioButton implements TutorialSection{
    GridBagConstraints gridBagConstraints;
    public RadioButton(){
        this("");
    }
    public RadioButton(String label){
        super();
        setDoubleBuffered(true);
        this.setText(label);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
    }

    @Override
    public void onResize(Dimension size) {
    }

    @Override
    public void addTo(MatrixTutorial2 frame) {
        frame.addSection(this, gridBagConstraints);
        frame.addSection(this);
    }

    @Override
    public void addTo(MatrixTutorial2 frame, JPanel panel) {
        panel.add(this, gridBagConstraints);
    }

    @Override
    public void init(Dimension size) {
        
    }

    @Override
    public GridBagConstraints getConstraints() {
        return gridBagConstraints;
    }

    @Override
    public void setProvider(TutorialResourceProvider provider) {
        
    }
    @Override
    public void clean(){
    }
}
