/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type.renderer;
import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import matrices.Util;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

/**
 *
 * @author MojoMacW7
 */
public class MatrixTableRenderer extends DefaultTableCellRenderer{
    public MatrixTableRenderer(){
        super();
        setHorizontalAlignment(JLabel.RIGHT);
    }
    public void setValue(Object value) {
        setText((value == null) ? "" : Util.toString((Double)value));
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setForeground(Color.BLACK);
        c.setBackground(Color.WHITE);
        if(isSelected){
            c.setBorder(new LineBorder(Color.BLUE, 2));
        }else{
            c.setBorder(null);
        }
        return c;
    }
}
