/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.math;

import matrices.Util;

/**
 *
 * @author MojoMacW7
 */
public class Single extends DirectValueType{
    public double value;
    
    public Single(double value){
        if(value == Double.NaN || value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY){
            throw new UnsupportedOperationException("Invalid value: " + value);
        }
        this.value = value;
    }
    
    public double getDouble(){
        return value;
    }
    
    public Single add(Single rhs){
        return new Single(value+rhs.value);
    }
    
    public Single subtract(Single rhs){
        return new Single(value-rhs.value);
    }
    
    public Single multiply(Single rhs){
        return new Single(value*rhs.value);
    }
    
    public Single divide(Single rhs){
        return new Single(value/rhs.value);
    }
    
    public Single mod(Single rhs){
        return new Single(value%rhs.value);
    }
    
    public Matrix multiply(Matrix rhs){
        return Matrix.multiply(value, rhs);
    }

    @Override
    public DirectValueType add(DirectValueType rhs) {
        if(rhs instanceof Single){
            return add((Single)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet. type: " + rhs.getClass().getName());
    }

    @Override
    public DirectValueType subtract(DirectValueType rhs) {
        if(rhs instanceof Single){
            return subtract((Single)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet. type: " + rhs.getClass().getName());
    }

    @Override
    public DirectValueType multiply(DirectValueType rhs) {
        if(rhs instanceof Single){
            return multiply((Single)rhs);
        }else if (rhs instanceof Matrix){
            return Matrix.multiply(value, (Matrix)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DirectValueType divide(DirectValueType rhs) {
        if(rhs instanceof Single){
            return divide((Single)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet. type: " + rhs.getClass().getName());
    }

    @Override
    public DirectValueType mod(DirectValueType rhs) {
        if(rhs instanceof Single){
            return mod((Single)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet. type: " + rhs.getClass().getName());
    }

    public String toString(boolean bracket){
        return Util.toString(value);
    }
}
