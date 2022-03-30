/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.math;

/**
 *
 * @author MojoMacW7
 */
public abstract class DirectValueType extends ValueType{
    public DirectValueType getValue(){
        return this;
    }
    
    public abstract DirectValueType add(DirectValueType rhs);
    public abstract DirectValueType subtract(DirectValueType rhs);
    public abstract DirectValueType multiply(DirectValueType rhs);
    public abstract DirectValueType divide(DirectValueType rhs);
    public abstract DirectValueType mod(DirectValueType rhs);

    @Override
    public DirectValueType add(ValueType rhs) {
        return add(rhs.getValue());
    }

    @Override
    public DirectValueType subtract(ValueType rhs) {
        return subtract(rhs.getValue());
    }

    @Override
    public DirectValueType multiply(ValueType rhs) {
        return multiply(rhs.getValue());
    }

    @Override
    public DirectValueType divide(ValueType rhs) {
        return divide(rhs.getValue());
    }

    @Override
    public DirectValueType mod(ValueType rhs) {
        return mod(rhs.getValue());
    }
    
    
    
}
