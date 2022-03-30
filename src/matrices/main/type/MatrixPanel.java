/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;

import javax.swing.JPanel;
import matrices.main.form.MainFrame;

/**
 *
 * @author MojoMacW7
 */
public abstract class MatrixPanel extends JPanel{
    public MainFrame root;
    private MatrixPanel(){}
    
    public MatrixPanel(MainFrame root){
        this.root = root;
    }
}
