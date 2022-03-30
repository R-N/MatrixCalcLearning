/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;

import matrices.math.Matrix;
import matrices.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MojoMacW7
 */
public class MatrixOBEHistory3 {
    private abstract class Change{
        public MatrixOBEHistory3 parent = null;
        public Change prev = null;
        public Change next = null;

        public Change(MatrixOBEHistory3 parent){
            this.parent = parent;
        }
        public abstract void undo();
        public abstract int apply();
        
        public void setNext(Change next){
            if(this.next != null){
                this.next.prev = null;
            }
            if(next != null){
                if(next.prev != null){
                    next.prev.next = null;
                }
                next.prev = this;
            }
            this.next = next;
        }
        
        public void setPrev(Change prev){
            if(this.prev != null){
                this.prev.next = null;
            }
            if(prev != null){
                if(prev.next != null){
                    prev.next.prev = null;
                }
                prev.next = this;
            }
            this.prev = prev;
        }
        
    }
    
    public class SetMainMatrix extends Change{
        int from = -1;
        int to;
        public SetMainMatrix(MatrixOBEHistory3 parent, int to){
            super(parent);
            this.to = to;
        }
        @Override
        public void undo(){
            parent.main = from;
        }
        @Override
        public int apply(){
            this.from = parent.main;
            parent.main = to;
            return 0;
        }
    }

    public class SetMatrix extends Change{
        int index;
        Matrix from = null;
        Matrix to;

        public SetMatrix(MatrixOBEHistory3 parent, int index, Matrix to){
            super(parent);
            this.index = index;
            this.to = to;
        }

        @Override
        public void undo(){
            parent.getData().set(index, from);
        }
        @Override
        public int apply(){
            this.from = parent.getMatrix(index);
            int ret = parent.getData().set(index, to) != null ? 0 : -1;
            return ret;
        }
    }

    public class AddMatrix extends Change{
        int index;
        Matrix m;
        public AddMatrix (MatrixOBEHistory3 parent, Matrix m){
            super(parent);
            this.m = m;
        }

        @Override
        public void undo(){
            parent.getData().remove(index);
        }
        @Override
        public int apply(){
            this.index = parent.getMatrixCount();
            parent.getData().add(m);
            return this.index;
        }
    }
    public class AddMatrixIfNotExists extends AddMatrix{
        public AddMatrixIfNotExists (MatrixOBEHistory3 parent, Matrix m){
            super(parent, m);
        }

        @Override
        public void undo(){
            if(this.index >= 0) parent.getData().remove(index);
        }
        @Override
        public int apply(){
            if(parent.indexOf(m) < 0){
                this.index = parent.getMatrixCount();
                if(parent.getData().addIfNotExists(m)){
                    return this.index;
                }else{
                    return -1;
                }
            }else{
                this.index = -1;
                return -1;
            }
        }
    }

    public class RemoveMatrix extends Change{
        int index;
        Matrix m;
        public RemoveMatrix(MatrixOBEHistory3 parent, int index){
            super(parent);
            this.index = index;
        }

        @Override
        public void undo(){
            parent.getData().add(index, m);
        }

        @Override
        public int apply(){
            this.m = parent.getMatrix(index);
            return parent.getData().remove(index) != null ? 0 : -1;
        }
    }
    
    public class SetData extends Change{
        MatrixList2 from;
        MatrixList2 to;
        public SetData(MatrixOBEHistory3 parent, MatrixList2 to){
            super(parent);
            this.to = to;
        }
        
        @Override
        public void undo(){
            parent.data = from;
        }
        
        @Override
        public int apply(){
            this.from = parent.data;
            parent.data = to;
            return 0;
        }
    }
    public MatrixList2 data = null;
    public int main = -1;
    public Change undo;
    public Change redo;
    public MatrixOBEHistory3(MatrixList2 ml, int mainMatrix){
        this.data = ml;
        this.main = mainMatrix;
    }
    public int step(Change c){
        int ret = c.apply();
        if(ret < 0){
            return ret;
        }
        c.setPrev(undo);
        undo = c;
        redo = null;
        return ret;
    }
    public int addMatrix(Matrix m){
        return step(new AddMatrix(this, m));
    }
    public boolean setMatrix(int index, Matrix m){
        if(0 <= index && index < getMatrixCount()){
            step(new SetMatrix(this, index, m));
            return true;
        }else{
            return false;
        }
    }
    public boolean removeMatrixAt(int index){
        if(0 <= index && index < getMatrixCount()){
            step(new RemoveMatrix(this, index));
            return true;
        }else{
            return false;
        }
    }
    public int indexOf(Matrix m){
        return data.indexOf(m);
    }
    public int addMatrixIfNotExists(Matrix m){
        int ret = step(new AddMatrixIfNotExists(this, m));
        if(ret < 0) ret = indexOf(m);
        return ret;
    }
    public MatrixList2 getData(){
        return data;
    }
    public int getMainMatrixIndex(){
        return main;
    }
    public void setMainMatrix(int index){
        step(new SetMainMatrix(this, index));
    }
    public boolean hasMainMatrix(){
        return main >= 0;
    }
    public int getMatrixCount(){
        return data.size();
    }
    public Matrix getMainMatrix(){
        return data.get(main);
    }
    public Matrix getMatrix(int index){
        return data.get(index);
    }
    public void setData(MatrixList2 data){
        step(new SetData(this, data));
        
    }
    public void setAll(MatrixList2 data, int mainMatrix){
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
        String[] ret = new String[getMatrixCount()];
        int i = -1;
        for(Matrix m : data){
            ret[++i] = m.name;
        }
        return ret;
    }
}
