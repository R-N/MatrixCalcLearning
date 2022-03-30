/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.deprecated;

import matrices.Util;
import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public interface MatrixOBEHistory {
    public void addMatrix(Matrix m);
    public boolean setMatrix(int index, Matrix m);
    public boolean removeMatrixAt(int index);
    public MatrixArrayList getData();
    public int getMainMatrixIndex();
    public boolean hasMainMatrix();
    public int getMatrixCount();
    public Matrix getMainMatrix();
    public Matrix getMatrix(int index);
    public void setMainMatrix(int newMainMatrix);
    public void setData(MatrixArrayList newData);
    public void setAll(MatrixArrayList newData, int newMainMatrix);
    
    public boolean undo();
    
    public boolean redo();
    
    public boolean canRedo();
    
    public boolean canUndo();
    
    public String[] getMatrixNameArray();
}
