/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.deprecated;

import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public class MatrixArrayList {
    int count = 0;
    private int limit;
    Matrix[] matrices;
    
    public MatrixArrayList(int limit){
        this.limit = limit;
        matrices = new Matrix[limit];
    }
    public MatrixArrayList(MatrixArrayList ml){
        this(ml.limit);
        int c = ml.count;
        for(int i = 0; i < c; ++i){
            matrices[i] = ml.matrices[i].clone();
        }
        this.count = c;
    }
    
    public int getLimit(){
        return limit;
    }
    private void resize(int limit){
        Matrix[] matrixTemp = new Matrix[limit];
        
        for(int i = 0; i < count; ++i){
            matrixTemp[i] = this.matrices[i];
        }
        this.matrices = matrixTemp;
        
        this.limit = limit;
    }
    
    public void add(Matrix m){
        if(count == limit){
            resize(limit*2);
        }
        matrices[count] = m;
        ++count;
    }
    public boolean insert(int index, Matrix m){
        if (index < 0 || index > count){
            return false;
        }
        if(index == count){
            add(m);
            return true;
        }
        if(count == limit){
            resize(limit*2);
        }
        for(int i = count; i > index; ++i){
            matrices[i] = matrices[i-1];
        }
        matrices[index] = m;
        ++count;
        return true;
    }
    public Matrix get(int index){
        return matrices[index];
    }
    
    
    public boolean set(int index, Matrix m){
        if (index < 0 || index >= count){
            return false;
        }
        matrices[index] = m;
        return true;
    }
    public boolean removeAt(int index){
        if (index < 0 || index >= count){
            return false;
        }
        matrices[index] = null;
        for(int i = index+1; i < count; ++i){
            matrices[i-1] = matrices[i];
        }
        --count;
        return true;
        
    }
    
    public int count(){
        return count;
    }
    
    public String[] getNameArray(){
        String[] ret = new String[count];
        for(int i = 0; i < count; ++i){
            ret[i] = matrices[i].name;
        }
        return ret;
    }
    
    public MatrixArrayList clone(){
        return new MatrixArrayList(this);
    }
}
