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
public abstract class IndirectValueType extends ValueType{
    @Override
    public abstract DirectValueType getValue();
    
    
    public DirectValueType add (ValueType rhs){
        return getValue().add(rhs);
    }
    public DirectValueType subtract (ValueType rhs){
        return getValue().subtract(rhs);
    }
    public DirectValueType multiply (ValueType rhs){
        return getValue().multiply(rhs);
    }
    public DirectValueType divide (ValueType rhs){
        return getValue().divide(rhs);
    }
    public DirectValueType mod (ValueType rhs){
        return getValue().mod(rhs);
    }
}
