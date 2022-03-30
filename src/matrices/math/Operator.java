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
public abstract class Operator extends MathType{
    int priority = 0;
    public int getPriority(){
        return priority;
    }
    public Operator(){}
    public Operator(int priority){
        this.priority = priority;
    }
    public abstract DirectValueType operate(ValueType lhs, ValueType rhs);
    
    public static final Operator ADD = new Operator(){
        public DirectValueType operate(ValueType lhs, ValueType rhs){
            return lhs.add(rhs);
        }
        public String toString(boolean bracket){
            return "+";
        }
    };
    public static final Operator SUBTRACT = new Operator(){
        public DirectValueType operate(ValueType lhs, ValueType rhs){
            return lhs.subtract(rhs);
        }
        public String toString(boolean bracket){
            return "-";
        }
    };
    public static final Operator MULTIPLY = new Operator(1){
        public DirectValueType operate(ValueType lhs, ValueType rhs){
            return lhs.multiply(rhs);
        }
        public String toString(boolean bracket){
            return "*";
        }
    };
    
    public static final Operator DIVIDE = new Operator(1){
        public DirectValueType operate(ValueType lhs, ValueType rhs){
            return lhs.divide(rhs);
        }
        public String toString(boolean bracket){
            return "/";
        }
    };
    public static final Operator MOD = new Operator(){
        public DirectValueType operate(ValueType lhs, ValueType rhs){
            return lhs.mod(rhs);
        }
        public String toString(boolean bracket){
            return "%";
        }
    };
}
