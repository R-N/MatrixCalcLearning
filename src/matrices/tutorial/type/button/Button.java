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
import matrices.tutorial.form.MatrixTutorial2;
import java.awt.GridBagConstraints;
import matrices.math.Matrix;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.section.TutorialSection;
/**
 *
 * @author MojoMacW7
 */
public class Button extends JButton implements TutorialSection{
    MatrixTutorial2 frame;
    GridBagConstraints gridBagConstraints;
    public Button(String label){
        super();
        setDoubleBuffered(true);
        this.setText(label);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onClick();
            }
        });
    }
    public void onClick(){
    }
    
    public void onResize(Dimension size){
    }
    public void addTo(MatrixTutorial2 frame){
        this.frame = frame;
        frame.addSection(this, gridBagConstraints);
        frame.addSection(this);
    }
    public void setFrame(MatrixTutorial2 frame){
        this.frame = frame;
    }
    public void addTo(MatrixTutorial2 frame, JPanel panel){
        this.frame = frame;
        panel.add(this, gridBagConstraints);
    }
    public void init(Dimension size){
    }
    public GridBagConstraints getConstraints(){
        return gridBagConstraints;
    }
    public void setProvider(TutorialResourceProvider provider){
    }
    @Override
    public void clean(){
    }
}
