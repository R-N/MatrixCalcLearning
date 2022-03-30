/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import matrices.Util;

/**
 *
 * @author 
 * https://stackoverflow.com/questions/4611363/how-to-select-all-text-in-jtable-cell-when-editing
 */
public class MatrixCellEditor extends DefaultCellEditor{
    Double value;
    JTextField field;
    public MatrixCellEditor(JTextField field){
        super(field);
        this.field = field;
        field.addFocusListener(
                new FocusAdapter(){
                @Override
                    public void focusGained(FocusEvent e){
                        field.selectAll();
                    }
                }
        );
    }
    String signs = "\\+\\-\\*\\/\\:\\%x";
    Pattern p = Pattern.compile("^ *([^" + signs + "]+) *([" + signs + "]) *([^ ]+)");
    @Override
    public boolean stopCellEditing() {
        String s = (String)super.getCellEditorValue();
        try {
            if ("".equals(s)) {
                return super.stopCellEditing();
            }
            value = Double.parseDouble(s);
        }
        catch (Exception e) {
            try{
                Matcher match = p.matcher(s);
                if(!match.matches()) throw new Exception();
                double lhs = Double.parseDouble(match.group(1).replace(" ", ""));
                double rhs = Double.parseDouble(match.group(3).replace(" ", ""));
                char op = match.group(2).replace(" ", "").charAt(0);
                
                Double val = null;
                switch(op){
                    case '+':
                        val = lhs+rhs;
                        break;
                    case '-':
                        val = lhs-rhs;
                        break;
                    case '*':
                    case 'x':
                        val = lhs*rhs;
                        break;
                    case '/':
                    case ':':
                        val = lhs/rhs;
                        break;
                    case '%':
                        val = lhs%rhs;
                        break;
                }
                if(val == null) throw new Exception();
                value = val;
            }catch(Exception e2){
                ((JComponent)getComponent()).setBorder(new LineBorder(Color.red));
                return false;
            }
        }
        return super.stopCellEditing();
    }
    public MatrixCellEditor(){
        this(new JTextField());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        
        if (c instanceof JTextComponent) {
            JTextComponent jtc = (JTextComponent) c;
            jtc.setBorder(new LineBorder(Color.black));
            if(value instanceof Double){
                Double val = (Double)value;
                jtc.setText(Util.toString(val));
            }
            jtc.selectAll();
        }
        return c;
    }
    
    @Override
    public Object getCellEditorValue(){
        return value;
    }
}
