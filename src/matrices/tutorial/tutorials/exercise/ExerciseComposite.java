/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import matrices.math.*;
import matrices.math.function.*;
import matrices.math.function.Function.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import matrices.Util;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.tutorials.exercise.MatrixRandom.Ordo;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.TutorialSlide2;
import matrices.tutorial.type.section.HorizontalLayoutSection;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseComposite {
    public static final int FINAL_MATRIX = 0;
    public static final int FINAL_ORDO = 1;
    public static final int FINAL_DETERMINANT = 2;
    
    int lowOrdo = 2;
    int highOrdo = 4;
    
    int minDegree = 2;
    int maxDegree = 4;
    
    int maxMatrixCount = 4;
    
    HashSet<Matrix> matrixSet = new HashSet<Matrix>();
    
    public ExerciseComposite(){}
    public ExerciseComposite(int lowOrdo, int highOrdo, int minDegree, int maxDegree){
        this.lowOrdo = lowOrdo;
        this.highOrdo = highOrdo;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
    }
    
    public PrioritizedExpression generateAdd(Matrix m){
        PrioritizedExpression ret;
        //do{
            Matrix rand;
            //do{
                rand = MatrixRandom.generateMatrix("M", m.rowCount, m.columnCount);
            //    System.out.println("A");
            //}while(!Matrix.canAddOrSubtract(m, rand));
            ret = generateAdd(m, rand);
            //    System.out.println("B");
        //}while(!m.equals((Matrix)ret.getValue()));
        matrixSet.remove(m);
        for(int i = 0; i < ret.getValueCount(); ++i){
            matrixSet.add((Matrix)(ret.getValue(i)));
        }
        return ret;
    }
    public PrioritizedExpression generateAdd(Matrix m, Matrix rand){
        Matrix lhs = Matrix.subtract(m, rand, m.name);
        return new PrioritizedExpression(
                new ValueType[]{
                    lhs,
                    rand
                },
                new Operator[]{
                    Operator.ADD
                }
        );
    }
    public PrioritizedExpression generateSubtract(Matrix m){
        PrioritizedExpression ret;
        //do{
            Matrix rand;
            //do{
                rand = MatrixRandom.generateMatrix("M", m.rowCount, m.columnCount);
            //    System.out.println("C");
            //}while(!Matrix.canAddOrSubtract(m, rand));
            ret = generateSubtract(m, rand);
            //    System.out.println("D");
        //}while(!m.equals((Matrix)ret.getValue()));
        matrixSet.remove(m);
        for(int i = 0; i < ret.getValueCount(); ++i){
            matrixSet.add((Matrix)(ret.getValue(i)));
        }
        return ret;
    }
    public PrioritizedExpression generateSubtract(Matrix m, Matrix rand){
        Matrix lhs = Matrix.add(m, rand, m.name);
        return new PrioritizedExpression(
                new ValueType[]{
                    lhs,
                    rand
                },
                new Operator[]{
                    Operator.SUBTRACT
                }
        );
    }
    public PrioritizedExpression generateMultiplySquare (Matrix m){
        PrioritizedExpression ret =  generateMultiply(m, m);
        matrixSet.remove(m);
        for(int i = 0; i < ret.getValueCount(); ++i){
            matrixSet.add((Matrix)(ret.getValue(i)));
        }
        return ret;
    }
    public PrioritizedExpression generateMultiply(Matrix m){
        PrioritizedExpression ret;
        if(Util.randInt(0, 1) == 0){
            ret = generateMultiplyRight(m);
        }else{
            ret = generateMultiplyLeft(m);
        }
        matrixSet.remove(m);
        for(int i = 0; i < ret.getValueCount(); ++i){
            matrixSet.add((Matrix)(ret.getValue(i)));
        }
        return ret;
    }
    public PrioritizedExpression generateMultiply(Matrix m, Matrix rand){
        if(!m.isSquareMatrix()){
            throw new UnsupportedOperationException("<MHAS TO BE SQUARE");
        }
        if(Util.randInt(0, 1) == 0){
            return generateMultiplyRight(m, rand);
        }else{
            return generateMultiplyLeft(m, rand);
        }
    }
    public PrioritizedExpression generateMultiplyRight(Matrix m){
        PrioritizedExpression ret;
        //do{
            Matrix rand = MatrixRandom.generateInvertibleMatrix("M", m.columnCount);
            ret = generateMultiplyRight(m, rand);
            //    System.out.println("E");
        //}while(!m.equals((Matrix)ret.getValue()));
        return ret;
    }
    public PrioritizedExpression generateMultiplyRight(Matrix m, Matrix rand){
        Matrix inv = rand.getInverseMatrix();
        if(inv == null) throw new UnsupportedOperationException("NO INV");
        Matrix b = Matrix.multiply(m, rand, m.name);
        if(b == null) throw new UnsupportedOperationException("CANT MULTIPLY");
        return new PrioritizedExpression(
                new ValueType[]{
                    b,
                    inv
                },
                new Operator[]{
                    Operator.MULTIPLY
                }
        );
    }
    public PrioritizedExpression generateMultiplyLeft(Matrix m){
        PrioritizedExpression ret;
        //do{
            Matrix rand = MatrixRandom.generateInvertibleMatrix("M", m.rowCount);
            ret = generateMultiplyLeft(m, rand);
        //        System.out.println("F");
        //}while(!m.equals((Matrix)ret.getValue()));
        return ret;
    }
    public PrioritizedExpression generateMultiplyLeft(Matrix m, Matrix rand){
        Matrix inv = rand.getInverseMatrix();
        if(inv == null) throw new UnsupportedOperationException("NO INV");
        Matrix b = Matrix.multiply(rand, m, m.name);
        if(inv == null) throw new UnsupportedOperationException("CANT MULTIPLY");
        return new PrioritizedExpression(
                new ValueType[]{
                    inv,
                    b,
                },
                new Operator[]{
                    Operator.MULTIPLY
                }
        );
    }
    
    public TransposeMatrix generateTranspose(Matrix m){
        TransposeMatrix fun = new TransposeMatrix();
        fun.setArgs(
                new ValueType[]{
                    m.getTransposeMatrix()
                }
        );
        matrixSet.remove(m);
        for(int i = 0; i < fun.getArgCount(); ++i){
            matrixSet.add((Matrix)(fun.getArg(i)));
        }
        return fun;
    }
    public InverseMatrix generateInverse(Matrix m){
        Matrix inv = m.getInverseMatrix();
        if(inv == null) throw new UnsupportedOperationException("NO INVERSE");
        InverseMatrix fun = new InverseMatrix();
        fun.setArgs(
                new ValueType[]{
                    inv
                }
        );
        matrixSet.remove(m);
        for(int i = 0; i < fun.getArgCount(); ++i){
            matrixSet.add((Matrix)(fun.getArg(i)));
        }
        return fun;
    }
    //full feature
    public static final int ADD = 0;
    public static final int SUBTRACT = 1;
    //result cant be taken apart
    public static final int MULTIPLY = 2;
    public static final int TRANSPOSE = 3; 
    //source need to be invertible matrix
    public static final int INVERSE = 4; 
    public static final int MULTIPLY_SQUARE = 5;
    
    public List<Integer> poolFunction = new LinkedList<Integer>();
    public List<Integer> poolOperation = new LinkedList<Integer>();
    public List<Integer> poolOperationSingular = new LinkedList<Integer>();
    
    public static final Integer[] operations = new Integer[]{ADD, SUBTRACT, MULTIPLY};//, MULTIPLY_SQUARE};
    public static final Integer[] operationsSingular = new Integer[]{ADD, SUBTRACT, MULTIPLY};
    public static final Integer[] functions = new Integer[]{TRANSPOSE, INVERSE};
    public void refreshPool(){
        if(poolFunction.size() == 0) Util.extend(poolFunction, functions);
        if(poolOperation.size() == 0) Util.extend(poolOperation, operations);
        if(poolOperationSingular.size() == 0) Util.extend(poolOperationSingular, operationsSingular);
    }
    
    public void removeFromPool(Integer i){
        poolFunction.remove(i);
        poolOperation.remove(i);
        poolOperationSingular.remove(i);
    }
    
    public ValueType generate(int degree, Matrix v){
        Integer rand = null;
        refreshPool();
        if(matrixSet.size() < maxMatrixCount){
            if(degree < minDegree || Util.randInt(0, 1) < 1){
                if(v.hasInverse()){
                    rand = poolOperation.remove(Util.randInt(0, poolOperation.size()-1));
                }else{
                    rand = poolOperationSingular.remove(Util.randInt(0, poolOperationSingular.size()-1));
                }
            }
        }
        if(rand == null){
            if(v.hasInverse()){
                rand = poolFunction.remove(Util.randInt(0, poolFunction.size()-1));
            }else{
                rand = TRANSPOSE;
            }
        }
        removeFromPool(rand);
        return generate(degree, v, rand);
    }
    
    public ValueType generate(int degree, Matrix v, int rand){
        if(matrixSet.size() >= maxMatrixCount) return v;
        matrixSet.add(v);
        ValueType ret;
        switch (rand){
            case ADD:{
                ret = generateAdd(v);
                break;
            }
            case SUBTRACT:{
                ret = generateSubtract(v);
                break;
            }
            case MULTIPLY:{
                ret = generateMultiply(v);
                break;
            }
            case TRANSPOSE:{
                ret = generateTranspose(v);
                break;
            }
            case INVERSE:{
                ret = generateInverse(v);
                break;
            }
            case MULTIPLY_SQUARE:{
                ret = generateMultiplySquare(v);
                break;
            }
            default:{
                throw new UnsupportedOperationException("rand = " + rand);
            }
        }
        if(ret instanceof Matrix){
                throw new UnsupportedOperationException("rand = " + rand);
        }
        return generate(degree+1, ret);
    }
    public ValueType generate(int degree, PrioritizedExpression v){
        ValueType ret = v;
        int c = v.getValueCount();
        
        ValueType v0 = v.getValue(0);
        if(Util.randInt(0, 2) < 2){
            Matrix m = (Matrix)(v0.getValue());
            ValueType v01;
            //System.out.println("A1");
            //do{
                v01 = generate(degree, v0);
            //}while(!m.equals((Matrix)(v01.getValue())));
            //System.out.println("A2");
            v0 = v01;
        }
        for(int i = 1; i < c; ++i){
            Operator op = v.getOperator(i-1);
            ValueType val = v.getValue(i);
            if(Util.randInt(0, 2) < 2){
                Matrix m = (Matrix)(val.getValue());
                ValueType val1;
                //System.out.println("B1");
                //do{
                    val1 = generate(degree, val);
                //}while(!m.equals((Matrix)(val1.getValue())));
                //System.out.println("B2");
            }
            if(val instanceof PrioritizedExpression){
                PrioritizedExpression exp = (PrioritizedExpression)val;
                if(exp.getPriority() < op.getPriority()){
                    exp.setBracket(true);
                }
            }
            PrioritizedExpression v1;
            try{
                v1 = PrioritizedExpression.merge(v0, op, val);
            }catch(UnsupportedOperationException ex){
                ((PrioritizedExpression)v0).setBracket(true);
                v1 = PrioritizedExpression.merge(v0, op, val);
            }
            v0 = v1;
        }
        ret = v0;
        return ret;
    }
    public ValueType generate(int degree, Function v){
        
        ValueType ret = v;
        int c = v.getArgCount();
        
        for(int i = 0; i < c; ++i){
            if(Util.randInt(0, 2) < 2){
                ValueType val = v.getArg(i);
                Matrix m = (Matrix)(val.getValue());
                ValueType val2;
                //System.out.println("C1");
                //do{
                    val2 = generate(degree, val);
                //}while(!m.equals((Matrix)val2.getValue()));
                //System.out.println("C2");
                v.setArg(i, val2);
            }
        }
        ret = v;
        return ret;
    }
    public ValueType generate(int degree, ValueType v){
        if(degree >= maxDegree){// || Util.randInt(minDegree, maxDegree*2) <= degree){
            return v;
        }
        Matrix m = (Matrix)(v.getValue());
        if(v instanceof Matrix){
            ValueType v1;
            //System.out.println("D1");
            //do{
                v1 = generate(degree, m);
            //}while(!m.equals((Matrix)(v1.getValue())));
            //System.out.println("D2");
            if(matrixSet.size() < maxMatrixCount && v1 instanceof Matrix){
                throw new java.lang.UnsupportedOperationException("???");
            }
            return v1;
        }else if (v instanceof PrioritizedExpression){
            ValueType v1;
            //System.out.println("E1");
            //do{
                v1 = generate(degree, (PrioritizedExpression)v);
            //}while(!m.equals((Matrix)(v1.getValue())));
            //System.out.println("E2");
            return v1;
        }else if (v instanceof Function){
            ValueType v1;
            //System.out.println("F1");
            //do{
                v1 = generate(degree, (Function)v);
            //}while(!m.equals((Matrix)(v1.getValue())));
            //System.out.println("F2");
            return v1;
        }else{
            throw new UnsupportedOperationException("wtf is this: " + v.getClass().getName());
        }
            
    }
    
    List<Matrix> matrices = new ArrayList<Matrix>();
    
    public int nameMatrices(int lastIndex, ValueType v){
        if (v instanceof Matrix){
            Matrix m = (Matrix)v;
            m.name = Util.indexToLetterUpper(lastIndex++);
            matrices.add(m);
        }else if (v instanceof PrioritizedExpression){
            PrioritizedExpression exp = (PrioritizedExpression)v;
            int c = exp.getValueCount();
            for(int i = 0; i < c; ++i){
                lastIndex = nameMatrices(lastIndex, exp.getValue(i));
            }
        }else if (v instanceof Function){
            Function f = (Function)v;
            int c = f.getArgCount();
            for(int i = 0; i < c; ++i){
                lastIndex = nameMatrices(lastIndex, f.getArg(i));
            }
        }
        return lastIndex;
    }
    
    public ValueType generate(Matrix m){
        ValueType ret;
        //System.out.println("G1");
        //do{
            ret = generate(0, m);
        //}while(!m.equals((Matrix)(ret.getValue())));
        //System.out.println("G2");
        if(ret instanceof Matrix){
            throw new UnsupportedOperationException("What");
        }
        int count = nameMatrices(0, ret);
        return ret;
    }
    
    public Matrix generateAnyMatrix(){
        Matrix m;
        int rand = Util.randInt(0, 4);
        if (rand < 1){
            m = MatrixRandom.generateMatrix(
                    "M", 
                    Util.randInt(lowOrdo, highOrdo), 
                    Util.randInt(lowOrdo, highOrdo)
            );
        }else{
            m = MatrixRandom.generateSquareMatrix(
                    "M",
                    Util.randInt(lowOrdo, highOrdo)
            );
        }
        return m;
    }
    
    public TutorialSection[] generateChilds(List<Matrix> matrices, String exp){
        int c = matrices.size();
        int div = 2;
        int one = c%div;
        int two = c-one;
        int two2 = two/div;
        TutorialSection[] ret = new TutorialSection[two2 + (one > 0 ? 1 : 0) + 1];
        for(int i = 0; i < two2; ++i){
            TutorialSection[] childs = new TutorialSection[div];
            for(int j = 0; j < div; ++j){
                int k = i*div + j;
                childs[j] = new MatrixSection2(matrices.get(k));
            }
            HorizontalLayoutSection layout = new HorizontalLayoutSection(childs);
            ret[i] = layout;
        }
        if(one > 0){
            TutorialSection[] childLast = new TutorialSection[one];
            for(int j = 0; j < one; ++j){
                int k = two2*div + j;
                childLast[j] = new MatrixSection2(matrices.get(k));
            }
            HorizontalLayoutSection layout = new HorizontalLayoutSection(childLast);
            ret[ret.length-2] = layout;
        }
        ret[ret.length-1] = new TextSection("M = " + exp);
        return ret;
    }
    

    public ExerciseMatrix generateMatrix(){
        Matrix m = generateAnyMatrix();
        ValueType exp = generate(m);
        m = (Matrix)(exp.getValue());
        m.name = "M";
        ExerciseMatrix ret = new ExerciseMatrix("Random Composite - Matrix");
        ret.matrix = m;
        ret.hasAnswer = true;
        ret.childs = generateChilds(matrices, exp.toString());
        ret.init();
        return ret;
    }
    public ExerciseOrdo generateOrdo(){
        highOrdo = 8;
        Matrix m = MatrixRandom.generateSquareMatrix(
                "M",
                Util.randInt(lowOrdo, highOrdo)
        );
        ValueType exp = generate(m);
        m = (Matrix)(exp.getValue());
        m.name = "M";
        ExerciseOrdo ret = new ExerciseOrdo("Random Composite - Ordo");
        Ordo ordo = new Ordo();
        int[] ans = new int[]{m.rowCount, m.columnCount};
        ordo.answer = ans;
        ordo.entries = MatrixRandom.generateEntries(new HashSet<Integer>(), 1, 4, ans);
        ret.childs = generateChilds(matrices, exp.toString());
        ret.init2(ordo);
        return ret;
    }
    public ExerciseDeterminant generateDeterminant(){
        Matrix m = MatrixRandom.generateSquareMatrix(
                "M",
                Util.randInt(lowOrdo, highOrdo)
        );
        ValueType exp = generate(m);
        m = (Matrix)(exp.getValue());
        m.name = "M";
        ExerciseDeterminant ret = new ExerciseDeterminant("Random Composite - Determinant");
        ret.showMatrix = false;
        ret.matrix = m;
        
        ret.hasAnswer = true;
        ret.childs = generateChilds(matrices, exp.toString());
        ret.init2();
        return ret;
    }
    
    public Problem generateProblem(){
        int rand = Util.randInt(0, 3);
        if (rand == 0){
            return generateOrdo();
        }else if (rand == 1){
            return generateDeterminant();
        }else{
            return generateMatrix();
        }
    }
}
