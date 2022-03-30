/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.math.function;

import matrices.math.DirectValueType;
import matrices.math.IndirectValueType;
import matrices.math.Matrix;
import matrices.math.Single;
import matrices.math.ValueType;
import java.util.Arrays;
import matrices.math.PrioritizedExpression;

/**
 *
 * @author MojoMacW7
 */
public abstract class Function extends IndirectValueType{
    ValueType[] args;
    
    public void setArgs(ValueType[] args){
        this.args = args;
    }
    public void setArg(int index, ValueType arg){
        args[index] = arg;
    }
    public int getArgCount(){
        return args.length;
    }
    
    public ValueType getArg(int index){
        return args[index];
    }
    
    public String getFirstArgString(boolean bracket){
        if(args[0] instanceof Function || (args[0] instanceof PrioritizedExpression && !PrioritizedExpression.isBracket(args[0]))){
            return String.format("(%s)", args[0].toString(bracket));
        }else{
            return args[0].toString(bracket);
        }
    }
    
    
    public static class TransposeMatrix extends Function{
        public TransposeMatrix(){
            args = new ValueType[1];
        }
        @Override
        public DirectValueType getValue() {
            Matrix val = (Matrix)(args[0].getValue());
            return val.getTransposeMatrix();
        }
        public String toString(boolean bracket){
            return String.format("%s<sup>t</sup>", getFirstArgString(bracket));
        }
    };
    
    public static class AdjointMatrix extends Function{
        public AdjointMatrix(){
            args = new ValueType[1];
        }
        @Override
        public DirectValueType getValue() {
            Matrix val = (Matrix)(args[0].getValue());
            if(val.hasDeterminant()){
                return val.getAdjointMatrix();
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public String toString(boolean bracket){
            return String.format("adj(%s)", getFirstArgString(bracket));
        }
    };
    public static class DeterminantMatrix extends Function{
        public DeterminantMatrix(){
            args = new ValueType[1];
        }
        @Override
        public DirectValueType getValue() {
            Matrix val = (Matrix)(args[0].getValue());
            if(val.hasDeterminant()){
                return new Single(val.getDeterminant());
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public String toString(boolean bracket){
            return String.format("|%s|", getFirstArgString(bracket));
        }
    };
    public static class InverseMatrix extends Function{
        public InverseMatrix(){
            args = new ValueType[1];
        }
        @Override
        public DirectValueType getValue() {
            Matrix val = (Matrix)(args[0].getValue());
            if(val.hasInverse()){
                return val.getInverseMatrix();
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public String toString(boolean bracket){
            return String.format("%s<sup>-1</sup>", getFirstArgString(bracket));
        }
    };
    public static class Power extends Function{
        public Power(){
            args = new ValueType[2];
        }
        @Override
        public DirectValueType getValue() {
            ValueType val = args[0].getValue();
            double pow = ((Single)(args[1].getValue())).getDouble();
            if(val instanceof Single){
                return new Single(
                        Math.pow(
                                ((Single)val).getDouble(), 
                                pow
                        )
                );
            }else if(val instanceof Matrix && ((Matrix)val).isSquareMatrix() && Math.round(pow) == pow){
                Matrix m = (Matrix)val;
                Matrix ret = m.clone();
                for(int i = 0; i < pow; ++i){
                    ret = Matrix.multiply(ret, m);
                }
                return ret;
            }
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        public String toString(boolean bracket){
            return String.format("%s<sup>%s</sup>", getFirstArgString(bracket), args[1].toString(bracket));
        }
    };
}
