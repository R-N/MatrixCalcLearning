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
public abstract class ValueType extends MathType{
    public abstract DirectValueType getValue();
    
    
    public abstract DirectValueType add (ValueType rhs);
    public abstract DirectValueType subtract (ValueType rhs);
    public abstract DirectValueType multiply (ValueType rhs);
    public abstract DirectValueType divide (ValueType rhs);
    public abstract DirectValueType mod (ValueType rhs);
    public boolean canBeTakenApart(){
        return false;
    }
}
