/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.section;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.JPanel;
import matrices.math.Matrix;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.MatrixSection;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.button.AddMatrixButton;
import matrices.tutorial.type.button.Button;

/**
 *
 * @author MojoMacW7
 */
public class MatrixSection2 implements TutorialSection{
    MatrixSection activePanel = null;
    MatrixTutorial2 frame = null;
    public Matrix matrix = null;
    GridBagConstraints gridBagConstraints;
    ArrayList<Button> buttons = new ArrayList<Button>(4);
    AddMatrixButton addButton = null;
    
    public MatrixSection2(Matrix m){
        this(m, true);
    }
    
    public MatrixSection2(Matrix m, boolean addButton){
        this.matrix = m;
        if(addButton){
            this.addButton = new AddMatrixButton("Add", m);
            buttons.add(this.addButton);
        }
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        //gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        //gridBagConstraints.weightx = 1.0;
    }
    public GridBagConstraints getConstraints(){
        return gridBagConstraints;
    }
    public void onResize(Dimension size){
        for(Button b : buttons){
            b.onResize(size);
        }
    }
    public void init(Dimension size){
        activePanel.loadMatrix(matrix);
        if(addButton != null) addButton.setMatrix(matrix);
        for(Button b : buttons){
            b.init(size);
        }
        activePanel.fitTable();
    }
    public void addTo(MatrixTutorial2 frame){
        activePanel = new MatrixSection(matrix);
        this.frame=frame;
        for(Button b : buttons){
            b.setFrame(frame);
            activePanel.addButton(b);
        }
        frame.addSection(activePanel, gridBagConstraints);
        frame.addSection(this);
    }
    public void addTo(MatrixTutorial2 frame, JPanel panel){
        activePanel = new MatrixSection(matrix);
        this.frame=frame;
        for(Button b : buttons){
            b.setFrame(frame);
            activePanel.addButton(b);
        }
        panel.add(activePanel, gridBagConstraints);
    }
    public void addButton(Button button){
        if(activePanel != null) activePanel.addButton(button);
        buttons.add(button);
    }
    public void setProvider(TutorialResourceProvider provider){
    }

    @Override
    public Dimension getPreferredSize() {
        return activePanel.getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return activePanel.getMinimumSize();
    }

    @Override
    public Dimension getSize() {
        return activePanel.getSize();
    }

    @Override
    public void revalidate() {
        activePanel.revalidate();
    }

    @Override
    public void validate() {
        activePanel.validate();
    }

    @Override
    public void clean() {
        activePanel.removeAll();
        activePanel = null;
        for(Button b : buttons){
            b.clean();
        }
    }
}
