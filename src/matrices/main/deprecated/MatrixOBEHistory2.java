/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.deprecated;

import matrices.main.deprecated.MatrixArrayList;
import matrices.main.deprecated.MatrixOBEHistory;
import matrices.Util;
import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public class MatrixOBEHistory2 implements MatrixOBEHistory{
    private abstract class Change{
        public MatrixOBEHistory2 parent = null;
        public Change prev = null;
        public Change next = null;

        public Change(MatrixOBEHistory2 parent){
            this.parent = parent;
        }
        public abstract void undo();
        public abstract boolean apply();
        
        public void setNext(Change next){
            if(this.next != null){
                this.next.prev = null;
            }
            this.next = next;
            if(next != null){
                if(next.prev != null){
                    next.prev.next = null;
                }
                next.prev = this;
            }
        }
        
        public void setPrev(Change prev){
            if(this.prev != null){
                this.prev.next = null;
            }
            this.prev = prev;
            if(prev != null){
                if(prev.next != null){
                    prev.next.prev = null;
                }
                prev.next = this;
            }
        }
        
    }
    
    public class SetMainMatrix extends Change{
        int from = -1;
        int to;
        public SetMainMatrix(MatrixOBEHistory2 parent, int to){
            super(parent);
            this.to = to;
        }
        public void undo(){
            parent.main = from;
        }
        public boolean apply(){
            this.from = parent.main;
            parent.main = to;
            return true;
        }
    }

    public class SetMatrix extends Change{
        int index;
        Matrix from = null;
        Matrix to;

        public SetMatrix(MatrixOBEHistory2 parent, int index, Matrix to){
            super(parent);
            this.index = index;
            this.to = to;
        }

        public void undo(){
            parent.getData().set(index, from);
        }
        public boolean apply(){
            this.from = parent.getMatrix(index);
            return parent.getData().set(index, to);
        }
    }

    public class AddMatrix extends Change{
        int index;
        Matrix m;
        public AddMatrix (MatrixOBEHistory2 parent, Matrix m){
            super(parent);
            this.m = m;
        }

        public void undo(){
            parent.getData().removeAt(index);
        }
        public boolean apply(){
            this.index = parent.getMatrixCount();
            parent.getData().add(m);
            return true;
        }
    }

    public class RemoveMatrix extends Change{
        int index;
        Matrix m;
        public RemoveMatrix(MatrixOBEHistory2 parent, int index){
            super(parent);
            this.index = index;
        }

        public void undo(){
            parent.getData().insert(index, m);
        }

        public boolean apply(){
            this.m = parent.getMatrix(index);
            return parent.getData().removeAt(index);
        }
    }
    
    public class SetData extends Change{
        MatrixArrayList from;
        MatrixArrayList to;
        public SetData(MatrixOBEHistory2 parent, MatrixArrayList to){
            super(parent);
            this.to = to;
        }
        
        public void undo(){
            parent.data = from;
        }
        
        public boolean apply(){
            this.from = parent.data;
            parent.data = to;
            return true;
        }
    }
    public MatrixArrayList data = null;
    public int main = -1;
    public Change undo;
    public Change redo;
    public MatrixOBEHistory2(MatrixArrayList ml, int mainMatrix){
        this.data = ml;
        this.main = mainMatrix;
    }
    public boolean step(Change c){
        if(!c.apply()){
            return false;
        }
        c.setPrev(undo);
        undo = c;
        redo = null;
        return true;
    }
    public void addMatrix(Matrix m){
        step(new AddMatrix(this, m));
    }
    public boolean setMatrix(int index, Matrix m){
        if(0 <= index && index < getMatrixCount()){
            assert step(new SetMatrix(this, index, m));
            return true;
        }else{
            return false;
        }
    }
    public boolean removeMatrixAt(int index){
        if(0 <= index && index < getMatrixCount()){
            assert step(new RemoveMatrix(this, index));
            return true;
        }else{
            return false;
        }
    }
    public MatrixArrayList getData(){
        return data;
    }
    public int getMainMatrixIndex(){
        return main;
    }
    public void setMainMatrix(int index){
        SetMainMatrix smm = new SetMainMatrix(this, index);
        
    }
    public boolean hasMainMatrix(){
        return main >= 0;
    }
    public int getMatrixCount(){
        return data.count();
    }
    public Matrix getMainMatrix(){
        return data.get(main);
    }
    public Matrix getMatrix(int index){
        return data.get(index);
    }
    public void setData(MatrixArrayList data){
        step(new SetData(this, data));
        
    }
    public void setAll(MatrixArrayList data, int mainMatrix){
        setData(data);
        setMainMatrix(mainMatrix);
    }
    
    public boolean undo(){
        if(!canUndo()){
            return false;
        }
        undo.undo();
        redo = undo;
        undo = redo.prev;
        return true;
    }
    
    public boolean redo(){
        if(!canRedo()){
            return false;
        }
        redo.apply();
        undo = redo;
        redo = undo.next;
        return true;
    }
    
    public boolean canRedo(){
        return redo != null;
    }
    
    public boolean canUndo(){
        return undo != null;
    }
    
    public String[] getMatrixNameArray(){
        return data.getNameArray();
    }
}
