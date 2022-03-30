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

/**
 *
 * @author MojoMacW7
 */
public class MatrixCramerTableRenderer extends MatrixDeterminantTableRenderer{
    int column = -1;
    public void setColumn(int column){
        this.column = column;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(this.column >= 0 && this.column == column){
            if(rowOrColumnUsed(row, column)){
                c.setBackground(Color.GREEN);
            }else{
                c.setBackground(Color.CYAN);
            }
        }
        return c;
    }
}
