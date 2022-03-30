/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;

import javax.swing.JLabel;
import javax.swing.JPanel;
import matrices.main.type.WrapTextPane;
import matrices.Util;

/**
 *
 * @author MojoMacW7
 */
public class MatrixHistoryEntry {
    WrapTextPane typeLabel;
    JPanel calcPanel;
    WrapTextPane calcTA;
    JLabel valueLabel;
    
    public MatrixHistoryEntry(WrapTextPane typeLabel, JPanel calcPanel, WrapTextPane calcTA, JLabel valueLabel){
        this.typeLabel = typeLabel;
        this.calcPanel = calcPanel;
        this.calcTA = calcTA;
        this.valueLabel = valueLabel;
    }
    
    public void show(String type, String calc, double value){
        typeLabel.setTextCenter(type);
        calcTA.setText(calc);
        valueLabel.setText(Util.toString(value));
        typeLabel.setVisible(true);
        calcPanel.setVisible(true);
        valueLabel.setVisible(true);
    }
    
    public void hide(){
        typeLabel.setVisible(false);
        calcPanel.setVisible(false);
        valueLabel.setVisible(false);
    }
        
}
