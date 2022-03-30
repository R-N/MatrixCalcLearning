/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;
import matrices.math.Matrix;
import java.util.ArrayList;
/**
 *
 * @author MojoMacW7
 */
public class MatrixList2 extends ArrayList<Matrix> {
    public MatrixList2(){
        super();
    }
    public MatrixList2(int limit){
        super(limit);
    }
    public boolean addIfNotExists(Matrix m){
        if(indexOf(m) >= 0){
            return false;
        }else{
            add(m);
            return true;
        }
    }
    public MatrixList2 clone(){
        MatrixList2 ret = new MatrixList2(size());
        for(Matrix m : this){
            ret.add(m.clone());
        }
        return ret;
    }
    public String[] getNameArray(){
        String[] ret = new String[size()];
        int i = -1;
        for(Matrix m : this){
            ret[++i] = m.name;
        }
        return ret;
    }
}
