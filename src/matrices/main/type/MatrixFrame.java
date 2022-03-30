/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;

import javax.swing.JFrame;
import matrices.main.form.MainFrame;

/**
 *
 * @author MojoMacW7
 */
public abstract class MatrixFrame extends JFrame {
    public MainFrame root;
    public MatrixFrame parent;
    private MatrixFrame(){}
    
    /*public MatrixFrame(MainFrame root){
        this(root, root.topmostFrame);
    }*/
    
    public MatrixFrame(MainFrame root, MatrixFrame parent){
        this.root = root;
        this.parent = parent;
        if(root != null) root.topmostFrame = this;
        if(parent != null) parent.setEnabled(false);
    }
    
    @Override
    public void setVisible(boolean visible){
        if(visible){
            pack();
            this.setLocationRelativeTo(parent);
        }
        super.setVisible(visible);
    }
    
    public void superSetVisible(boolean visible){
        super.setVisible(visible);
    }
    
    @Override
    public void dispose(){
        if(this.isEnabled() && parent != null){
            parent.toFront();
            parent.superSetVisible(true);
            parent.setEnabled(true);
        }
        root.topmostFrame = parent;
        super.dispose();
    }
    
    @Override
    public void toFront(){
        super.toFront();
        setFocusable(true);
        repaint();
    }
}
