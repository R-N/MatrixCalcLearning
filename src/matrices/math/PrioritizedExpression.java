/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.math;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author MojoMacW7
 */
public class PrioritizedExpression extends IndirectValueType{
    boolean isBracket = false;
    public void setBracket(boolean isBracket){
        this.isBracket = isBracket;
    }
    public boolean isBracket(){
        return isBracket;
    }
    public static boolean isBracket(ValueType exp){
        if(exp instanceof PrioritizedExpression){
            return ((PrioritizedExpression)exp).isBracket();
        }
        return false;
    }
    ValueType[] values;
    Operator[] operators;
    
    public void setValue(int index, ValueType value){
        values[index] = value;
        getValue();
    }
    
    public ValueType getValue(int index){
        return values[index];
    }
    
    public Operator getOperator(int index){
        return operators[index];
    }
    
    public int getValueCount(){
        return values.length;
    }
    
    public int getOperatorCount(){
        return operators.length;
    }
    
    public int getPriority(){
        return operators[0].priority;
    }
    
    public boolean canBeTakenApart(){
        return !isBracket && getPriority() == 0;
    }
    
    public PrioritizedExpression(ValueType[] values, Operator[] operators){
        this(values, operators, false);
    }
    
    public PrioritizedExpression(ValueType[] values, Operator[] operators, boolean isBracket){
        this.values = values;
        this.operators = operators;
        if(operators.length == 0){
            throw new UnsupportedOperationException("Not supported yet.");
        }else if (values.length != operators.length + 1){
                throw new UnsupportedOperationException("Mismatching length. Values.length = " + values.length + ", Operators.length = " + operators.length);
        }else{
            for(int i = 0; i < values.length; ++i){
                if(values[i] == null){
                    throw new UnsupportedOperationException("VALUES[" + i + "] NULL");
                }
            }
        }
        this.isBracket = isBracket;
        scanPriority();
    }
    
    public void scanPriority(){
        List<Integer> priorities = new LinkedList<Integer>();
        int maxPriority = operators[0].priority;
        int minPriority = maxPriority;
        int priorityKinds = 0;
        for(int i = 1; i < operators.length; ++i){
            int p = operators[i].priority;
            int add = 0;
            if(p < minPriority){
                add = minPriority;
                minPriority = p;
            }
            if(p > maxPriority){
                add = p;
                maxPriority = p;
            }
            if(add>0){
                priorities.add(add);
                priorityKinds++;
            }
        }
        if(maxPriority == minPriority){
            getValue();
            return;
        }
        if(priorityKinds == 0){
            getValue();
            return;
        }
        for(int j = 0; j < priorityKinds; ++j){
            int p = priorities.get(priorityKinds-1-j);
            List<ValueType> nonPriorityValues = new LinkedList<ValueType>();
            List<Operator> nonPriorityOperators = new LinkedList<Operator>();
            List<ValueType> priorityValues = new LinkedList<ValueType>();
            List<Operator> priorityOperators = new LinkedList<Operator>();
            
            ValueType[] tempValues;
            Operator[] tempOperators;
            boolean prevPriority = false;
            for(int i = 0; i < operators.length; ++i){
                if(operators[i].priority == p){
                    if(prevPriority){
                        priorityValues.add(values[i]);
                        priorityOperators.add(operators[i]);
                    }else{
                        prevPriority = true;
                        priorityValues.add(values[i]);
                        priorityOperators.add(operators[i]);
                    }
                }else{
                    if(prevPriority){
                        priorityValues.add(values[i]);
                        tempValues = new ValueType[priorityValues.size()];
                        tempOperators = new Operator[priorityOperators.size()];
                        nonPriorityValues.add(
                                new PrioritizedExpression(
                                        priorityValues.toArray(tempValues), 
                                        priorityOperators.toArray(tempOperators)
                                )
                        );
                        priorityValues = new LinkedList<ValueType>();
                        priorityOperators = new LinkedList<Operator>();
                        nonPriorityOperators.add(operators[i]);
                        prevPriority = false;
                    }else{
                        nonPriorityValues.add(values[i]);
                        nonPriorityOperators.add(operators[i]);
                    }
                }
            }
            if(prevPriority){
                priorityValues.add(values[values.length-1]);
                tempValues = new ValueType[priorityValues.size()];
                tempOperators = new Operator[priorityOperators.size()];
                prevPriority = false;
                nonPriorityValues.add(
                        new PrioritizedExpression(
                                priorityValues.toArray(tempValues), 
                                priorityOperators.toArray(tempOperators)
                        )
                );
            }else{
                nonPriorityValues.add(values[values.length-1]);
            }
            tempValues = new ValueType[nonPriorityValues.size()];
            tempOperators = new Operator[nonPriorityOperators.size()];
            values = nonPriorityValues.toArray(tempValues);
            operators = nonPriorityOperators.toArray(tempOperators);

            if(operators.length == 0){
                throw new UnsupportedOperationException("Not supported yet.");
            }else if (values.length != operators.length + 1){
                throw new UnsupportedOperationException("Mismatching length. Values.length = " + values.length + ", Operators.length = " + operators.length);
            }else{
                for(int i = 0; i < values.length; ++i){
                    if(values[i] == null){
                        throw new UnsupportedOperationException("VALUES[" + i + "] NULL");
                    }
                }
            }
            
            getValue();
        }
        getValue();
    }

    @Override
    public DirectValueType getValue() {
        try{
            DirectValueType ret = values[0].getValue();
            for(int i = 0; i < operators.length; ++i){
                ret = operators[i].operate(ret, values[i+1].getValue());
            }
            return ret;
        }catch(UnsupportedOperationException ex){
            System.err.println("Exp: " + this.toString(true));
            throw ex;
        }
    }
    
    @Override
    public String toString(boolean bracket){
        String ret = values[0].toString(bracket);
        if(operators == null){
            System.out.println("OPERATORS NULL");
        }else if (values == null){
            System.out.println("values NULL");
        }
        for(int i = 0; i < operators.length; ++i){
            if(operators[i] == null){
                System.out.println("operators[" + i + "] NULL");
            }else if(values[i+1] == null){
                System.out.println("values[" + (i+1) + "] NULL");
            }
            ret = ret + operators[i].toString(bracket) + values[i+1].toString(bracket);
        }
        if(isBracket || bracket){
            ret = String.format("(%s)", ret);
        }
        return ret;
    }
    
    public static PrioritizedExpression merge(ValueType lhs, Operator operator, ValueType rhs){
        if(lhs instanceof PrioritizedExpression && !isBracket(lhs)){
            if(rhs instanceof PrioritizedExpression && !isBracket(rhs)){
                return merge((PrioritizedExpression)lhs, operator, (PrioritizedExpression) rhs);
            }
            return merge((PrioritizedExpression)lhs, operator, rhs);
        }else if (rhs instanceof PrioritizedExpression && !isBracket(rhs)){
            return merge(lhs, operator, (PrioritizedExpression) rhs);
        }
        return new PrioritizedExpression(
                new ValueType[]{
                    lhs,
                    rhs
                },
                new Operator[]{
                    operator
                }
        );
    }
    
    public static PrioritizedExpression merge(ValueType lhs, Operator operator, PrioritizedExpression rhs){
        if(rhs.isBracket()){
            return merge(lhs, operator, (ValueType)rhs);
        }
        ValueType[] newValues = new ValueType[1 + rhs.values.length];
        Operator[] newOperators = new Operator[1 + rhs.operators.length];
        
        newValues[0] = lhs;
        newOperators[0] = operator;
        
        for(int i = 0; i < rhs.values.length; ++i){
            newValues[i+1] = rhs.values[i];
        }
        for(int i = 0; i < rhs.operators.length; ++i){
            newOperators[i+1] = rhs.operators[i];
        }
        return new PrioritizedExpression(newValues, newOperators);
    }
    public static PrioritizedExpression merge(PrioritizedExpression lhs, Operator operator, ValueType rhs){
        if(lhs.isBracket()){
            return merge((ValueType)lhs, operator, rhs);
        }
        ValueType[] newValues = new ValueType[lhs.values.length + 1];
        Operator[] newOperators = new Operator[lhs.operators.length + 1];
        
        
        for(int i = 0; i < lhs.values.length; ++i){
            newValues[i] = lhs.values[i];
        }
        for(int i = 0; i < lhs.operators.length; ++i){
            newOperators[i] = lhs.operators[i];
        }
        
        newValues[lhs.values.length] = rhs;
        newOperators[lhs.operators.length] = operator;
        
        return new PrioritizedExpression(newValues, newOperators);
    }
    
    public static PrioritizedExpression merge(PrioritizedExpression lhs, Operator operator, PrioritizedExpression rhs){
        if(lhs.isBracket()){
            if(rhs.isBracket()){
                return merge((ValueType)lhs, operator, (ValueType)rhs);
            }
            return merge((ValueType)lhs, operator, rhs);
        }else if (rhs.isBracket()){
            return merge(lhs, operator, (ValueType) rhs);
        }
        ValueType[] newValues = new ValueType[lhs.values.length + rhs.values.length];
        Operator[] newOperators = new Operator[lhs.operators.length + 1 + rhs.operators.length];
        for(int i = 0; i < lhs.values.length; ++i){
            newValues[i] = lhs.values[i];
        }
        for(int i = 0; i < lhs.operators.length; ++i){
            newOperators[i] = lhs.operators[i];
        }
        newOperators[lhs.operators.length] = operator;
        int j = 0;
        for(int i = lhs.values.length; i < newValues.length; ++i){
            newValues[i] = rhs.values[j];
            ++j;
        }
        j = 0;
        for(int i = lhs.operators.length+1; i < newOperators.length; ++i){
            newOperators[i] = rhs.operators[j];
        }
        return new PrioritizedExpression(newValues, newOperators);
    }
}
