/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MojoMacW7
 */
public class MatrixTableModel extends DefaultTableModel {
    public static final int CELL_EDIT_NONE = 0;
    public static final int CELL_EDIT_EXCEPT_FIRST = 1;
    public static final int CELL_EDIT_ALL = 2;
    private int cellEditMode = CELL_EDIT_NONE;
    
    
    public MatrixTableModel(Double[][] data, String[] columns, int cellEditMode){
        super(data, columns);
        this.cellEditMode = cellEditMode;
    }
    
    public MatrixTableModel(Double[][] data, String[] columns){
        this(data, columns, CELL_EDIT_NONE);
    }
    
    public void clearTable(){
        int rc = getRowCount();
        setRowCount(0);
        setRowCount(rc);
    }
    public void addRow(){
        setRowCount(getRowCount()+1);
    }
    public void addColumn(){
        setColumnCount(getColumnCount()+1);
    }
    public void addColumn(Double[] values){
        addColumn();
        int rc = getRowCount();
        int j = getColumnCount()-1;
        for(int i = 0; i < rc; ++i){
            setValueAt(values[i], i, j);
        }
    }
    
    public void insertRow(int row){
        insertRow(row, new Double[getColumnCount()]);
    }
    
    public void insertColumn(int col){
        insertColumn(col, new Double[getRowCount()]);
    }
    
    public void insertColumn(int col, Double[] values){
        addColumn();
        int cc = getColumnCount();
        if(col < cc-1){
            int rc = getRowCount();
            for(int i = 0; i < rc; ++i){
                for(int j = cc-1; j > col; --j){
                    this.setValueAt(this.getValueAt(i, j-1), i, j);
                }
            }
            for(int i = 0; i < rc; ++i){
                this.setValueAt(values[i], i, col);
            }
        }
    }
    public void removeRow(){
        setRowCount(getRowCount()-1);
    }
    public void removeColumn(){
        setColumnCount(getColumnCount()-1);
    }
    public void removeColumn(int col){
        int cc = getColumnCount();
        if(col < cc-1){
            int rc = getRowCount();
            for(int i = 0; i < rc; ++i){
                for(int j = col+1; j < cc; ++j){
                    this.setValueAt(this.getValueAt(i, j), i, j-1);
                }
            }
        }
        removeColumn();
    }
    
    public int getCellEditMode(){
        return cellEditMode;
    }
    public void setCellEditMode(int cellEditMode){
        this.cellEditMode = cellEditMode;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }
    @Override
    public boolean isCellEditable(int row, int col){
        switch(cellEditMode){
            case CELL_EDIT_NONE:{
                return false;
            }
            case CELL_EDIT_EXCEPT_FIRST:{
                return row>0 && col > 0;
            }
            case CELL_EDIT_ALL:{
                return true;
            }
            default:{
                return false;
            }
        }
    }
}
