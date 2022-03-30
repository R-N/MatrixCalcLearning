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
public class MatrixOBEHistory1 implements MatrixOBEHistory{
    private class Node{
        public MatrixOBEHistory1 parent = null;
        public MatrixArrayList data = null;
        public Node prev = null;
        public Node next = null;
        public int main = -1;

        public Node(MatrixOBEHistory1 parent, MatrixArrayList ml, int main){
            this.parent = parent;
            this.data = ml;
            this.main = main;
        }
    }
    public Node now;
    public MatrixOBEHistory1(MatrixArrayList ml, int mainMatrix){
        now = new Node(this, ml, mainMatrix);
    }
    public void addMatrix(Matrix m){
        step();
        now.data.add(m);
    }
    public boolean setMatrix(int index, Matrix m){
        if(0 <= index && index < getMatrixCount()){
            step();
            assert now.data.set(index, m);
            return true;
        }else{
            return false;
        }
    }
    public boolean removeMatrixAt(int index){
        if(0 <= index && index < getMatrixCount()){
            step();
            assert now.data.removeAt(index);
            return true;
        }else{
            return false;
        }
    }
    public MatrixArrayList getData(){
        return now.data;
    }
    public int getMainMatrixIndex(){
        return now.main;
    }
    public boolean hasMainMatrix(){
        return now.main >= 0;
    }
    public int getMatrixCount(){
        return now.data.count();
    }
    public Matrix getMainMatrix(){
        return now.data.get(now.main);
    }
    public Matrix getMatrix(int index){
        return now.data.get(index);
    }
    public void step(){
        setAll(now.data.clone(), now.main);
    }
    public void setMainMatrix(int newMainMatrix){
        setAll(now.data.clone(), newMainMatrix);
    }
    public void setData(MatrixArrayList newData){
        setAll(newData, now.main);
    }
    public void setAll(MatrixArrayList newData, int newMainMatrix){
        if(now.next != null){
            now.next.prev = null;
            now.next = null;
        }
        Node newPrev = new Node(this, now.data, now.main);
        newPrev.prev = now.prev;
        newPrev.next = now;
        if(now.prev != null) now.prev.next = newPrev;
        now.prev = newPrev;
        now.data = newData;
        now.main = newMainMatrix;
    }
    
    public boolean undo(){
        if(!canUndo()){
            return false;
        }
        now = now.prev;
        return true;
    }
    
    public boolean redo(){
        if(!canRedo()){
            return false;
        }
        now = now.next;
        return true;
    }
    
    public boolean canRedo(){
        return now.next != null;
    }
    
    public boolean canUndo(){
        return now.prev != null;
    }
    
    public String[] getMatrixNameArray(){
        return now.data.getNameArray();
    }
}
