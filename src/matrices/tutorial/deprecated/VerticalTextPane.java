/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.deprecated;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author MojoMacW7
 */
public class VerticalTextPane extends JTextPane {
    JScrollPane parent;
    int sbWidth;
    public VerticalTextPane(JScrollPane parent, int scrollbarWidth){
        this.parent = parent;
        this.sbWidth = scrollbarWidth;
        parent.add(this);
        setVisible(true);
    }
    
    public int getScrollbarWidth(){
        return sbWidth;
    }
    
    /*@Override 
    public int getWidth(){
        return parent.getWidth()-getScrollbarWidth();
    }*/
    
        
}
