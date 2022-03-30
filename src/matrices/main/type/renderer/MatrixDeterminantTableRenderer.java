/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type.renderer;
import java.awt.Color;
import matrices.Util;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import matrices.main.type.renderer.MatrixTableRenderer;

/**
 *
 * @author MojoMacW7
 */
public class MatrixDeterminantTableRenderer extends MatrixTableRenderer{
    boolean row = true;
    int index = 0;
    public void setRow(boolean row){
        this.row = row;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public boolean rowOrColumnUsed(int row, int column){
        if(this.row && row == index){
            return true;
        }else if (!this.row && column == index){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(rowOrColumnUsed(row, column)){
            c.setBackground(Color.YELLOW);
        }else{
            c.setBackground(Color.WHITE);
        }
        return c;
    }
}
