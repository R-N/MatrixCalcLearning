/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import matrices.Util;
import matrices.main.type.MatrixTableModel;
import matrices.main.type.renderer.MatrixTableRenderer;

/**
 *
 * @author MojoMacW7
 */
public class MatrixOBETableRenderer extends MatrixTableRenderer {
    
    private boolean active = false;
    public void setActive(boolean active){
        this.active = active;
    }
    public boolean isActive(){
        return active;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(!active){
            c.setBackground(Color.WHITE);
            return c;
        }
        MatrixTableModel model = (MatrixTableModel)table.getModel();
        double x = Util.parseDouble((Double)value);
        if(row == column){
            if (x == 1){
                c.setBackground(new Color(102,255,102));
            }else if (x == 0){
                c.setBackground(Color.ORANGE);
            }else{
                c.setBackground(new Color(0,204,0));
            }
        }else{
            if(x == 0){
                c.setBackground(Color.WHITE);
            }else{
                c.setBackground(new Color(153,153,153));
            }
        }
        return c;
    }
}
