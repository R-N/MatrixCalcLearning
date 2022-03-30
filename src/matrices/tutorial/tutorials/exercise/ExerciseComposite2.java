/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import matrices.Util;
import matrices.math.*;
import matrices.math.function.Function.*;
import matrices.math.function.*;
import matrices.tutorial.type.section.HorizontalLayoutSection;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseComposite2 {
    
    public static final int FINAL_MATRIX = 0;
    public static final int FINAL_ORDO = 1;
    public static final int FINAL_DETERMINANT = 2;
    
    int lowOrdo = 2;
    int highOrdo = 4;
    
    int minDegree = 2;
    int maxDegree = 4;
    
    int maxMatrixCount = 4;
    
    int maxCoef = 5;
    
    HashSet<Matrix> matrixSet = new HashSet<Matrix>();
    
    
    List<Operator> operatorPool = new LinkedList<Operator>();
    public static final Operator[] operators = new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY};
    
    public static final int BRACKET_MOD = -2;
    public static final int BRACKET_DIVIDE = -1;
    public static final int BRACKET_ADD = 0;
    public static final int BRACKET_SUBTRACT = 1;
    public static final int BRACKET_MULTIPLY = 2;
    public static final int COEFFICIENT = 3;
    public static final int FUNCTION_ADJ = 4;
    public static final int FUNCTION_INV = 5;
    public static final int FUNCTION_T = 6;
    public static final int FUNCTION_POW = 7;
    
    public static final int NO_KEEP = 0;
    public static final int KEEP_ROW = 1;
    public static final int KEEP_COL = 1 << 1;
    public static final int KEEP_DIM = KEEP_ROW | KEEP_COL;
    
    public static final Integer[] brackets = new Integer[]{BRACKET_ADD, BRACKET_SUBTRACT, BRACKET_MULTIPLY};
    public static final Integer[] bracketsKeepDim = new Integer[]{BRACKET_ADD, BRACKET_SUBTRACT};
    //brackets keep row/col all
    public static final Integer[] functions = new Integer[]{FUNCTION_ADJ, FUNCTION_INV, FUNCTION_T, FUNCTION_POW};
    public static final Integer[] functionsKeepDim = new Integer[]{FUNCTION_ADJ, FUNCTION_INV, FUNCTION_POW};
    
    public static final Integer[] functionsSingular = new Integer[]{FUNCTION_ADJ, FUNCTION_T, FUNCTION_POW};
    public static final Integer[] functionsSingularKeepDim = new Integer[]{FUNCTION_ADJ, FUNCTION_POW};
    
    public static final Integer[] variationsSingle = new Integer[]{BRACKET_ADD, BRACKET_SUBTRACT, BRACKET_MULTIPLY, BRACKET_DIVIDE, BRACKET_MOD, FUNCTION_POW};
    
    public List<Integer> poolBracket = new LinkedList<Integer>();
    public List<Integer> poolFunction = new LinkedList<Integer>();
    public List<Integer> poolFunctionSingular = new LinkedList<Integer>();
    public List<Integer> poolBracketKeepDim = new LinkedList<Integer>();
    public List<Integer> poolFunctionKeepDim = new LinkedList<Integer>();
    public List<Integer> poolFunctionSingularKeepDim = new LinkedList<Integer>();
    public List<Integer> poolSingle = new LinkedList<Integer>();
    
    public void refreshPool(){
        if(operatorPool.size() == 0) Util.extend(operatorPool, operators);
        if(poolBracket.size() == 0) Util.extend(poolBracket, brackets);
        if(poolFunction.size() == 0) Util.extend(poolFunction, functions);
        if(poolFunctionSingular.size() == 0) Util.extend(poolFunctionSingular, functionsSingular);
        if(poolBracketKeepDim.size() == 0) Util.extend(poolBracketKeepDim, bracketsKeepDim);
        if(poolFunctionKeepDim.size() == 0) Util.extend(poolFunctionKeepDim, functionsKeepDim);
        if(poolFunctionSingularKeepDim.size() == 0) Util.extend(poolFunctionSingularKeepDim, functionsSingularKeepDim);
        if(poolSingle.size() == 0) Util.extend(poolSingle, variationsSingle);
    }
    
    public void removeFromPool(Integer i){
        poolBracket.remove(i);
        poolBracketKeepDim.remove(i);
       poolFunction.remove(i);
       poolFunctionSingular.remove(i);
       poolFunctionKeepDim.remove(i);
       poolFunctionSingularKeepDim.remove(i);
    }
    
    //dont forget FUNCTION_T and COEFFICIENT
    
    
    
    int matrixCount = 0;
    
    public Matrix generateMatrixRand(){
        return generateMatrixRowCol(Util.randInt(lowOrdo, highOrdo), Util.randInt(lowOrdo, highOrdo));
    }
    
    public Matrix generateMatrixCol(int columnCount){
        return generateMatrixRowCol(Util.randInt(lowOrdo, highOrdo), columnCount);
    }
    public Matrix generateMatrixRow(int rowCount){
        return generateMatrixRowCol(rowCount, Util.randInt(lowOrdo, highOrdo));
    }
    
    List<Matrix> matrices = new LinkedList<Matrix>();
    
    public Matrix generateMatrixRowCol(int rowCount, int columnCount){
        Matrix ret = MatrixRandom.generateMatrix(matrixCount++, rowCount, columnCount);
        matrices.add(ret);
        return ret;
    }
    
    public Matrix generateMatrixAddSub(Matrix m){
        return generateMatrixRowCol(m.rowCount, m.columnCount);
    }
    public Matrix generateMatrixMulLhs(Matrix m){
        return generateMatrixCol(m.rowCount);
    }
    public Matrix generateMatrixMulRhs(Matrix m){
        return generateMatrixRow(m.columnCount);
    }
    public Matrix generateMatrixMulLhsKeepDim(Matrix m){
        return generateMatrixRowCol(m.rowCount, m.rowCount);
    }
    public Matrix generateMatrixMulRhsKeepDim(Matrix m){
        return generateMatrixRowCol(m.columnCount, m.columnCount);
    }
    public Matrix generateMatrixMulSquarify(Matrix m){
        return generateMatrixRowCol(m.columnCount, m.rowCount);
    }
    public Matrix generateMatrixAddInvertiblify(Matrix m){
        Matrix m2 = MatrixRandom.generateInvertibleMatrix(m);
        Matrix m3 = Matrix.subtract(m2, m);
        Matrix m4;
        do{
            m4 = Matrix.add(m3, MatrixRandom.generateSquareMatrix(matrixCount+1, m3.rowCount));
            //System.out.println("A");
        }while(!Matrix.add(m, m4).hasInverse());
        matrixCount = matrixCount+1;
        return m4;
    }
    
    public ValueType generateSingle(int degree){
        if(matrixSet.size() > 0 && Util.randInt(0, 4) == 0){
            for(Matrix m : matrixSet){
                if(m.hasDeterminant()){
                    Function fun = new DeterminantMatrix();
                    ValueType v = m;
                    if(Util.randInt(0, 9) == 0){
                        v = generateVariation(degree+1, v, KEEP_DIM);
                    }
                    fun.setArg(0, v);
                    return fun;
                }
            }
        }
        
        ValueType ret = new Single((Util.randInt(0, 1) == 0? 1: -1)*0.5*Util.randInt(1, 2*maxCoef));
        if(Util.randInt(0, 4) == 0){
            ret = generateVariation(degree+1, ret, NO_KEEP);
        }
        return ret;
    }
    
    
    public ValueType squarify(Matrix m){
        if(m.isSquareMatrix()){
            return m;
        }
        Matrix mul = generateMatrixMulSquarify(m);
        PrioritizedExpression ret;
        if(Util.randInt(0, 1) == 0){
            ret = PrioritizedExpression.merge(m, Operator.MULTIPLY, mul);
        }else{
            ret = PrioritizedExpression.merge(mul, Operator.MULTIPLY, m);
        }
        ret.getValue();
        return ret;
    }
    
    public PrioritizedExpression squarify(PrioritizedExpression exp){
        Matrix m = (Matrix)(exp.getValue());
        if(m.isSquareMatrix()){
            return exp;
        }
        if(exp.getPriority() < Operator.MULTIPLY.getPriority()){
            exp.setBracket(true);
        }
        Matrix mul = generateMatrixMulSquarify(m);
        PrioritizedExpression ret;
        if(Util.randInt(0, 1) == 0){
            ret = PrioritizedExpression.merge(exp, Operator.MULTIPLY, mul);
        }else{
            ret = PrioritizedExpression.merge(mul, Operator.MULTIPLY, exp);
        }
        ret.getValue();
        return ret;
    }
    
    public ValueType invertiblify(Matrix m){
        if(m.hasInverse()){
            return m;
        }
        if(!m.isSquareMatrix()){
            PrioritizedExpression exp = (PrioritizedExpression)squarify(m);
            return invertiblify(exp);
        }
        Matrix m4 = generateMatrixAddInvertiblify(m);
        int rand = Util.randInt(0, 3);
        
        PrioritizedExpression ret = null;
        if(rand == 0){
            ret = PrioritizedExpression.merge(m, Operator.ADD, m4);
        }else if(rand == 1){
            ret = PrioritizedExpression.merge(m4, Operator.ADD, m);
        }else if (rand == 2){
            Matrix m5 = Matrix.multiply(-1, m4, m4.name);
            ret = PrioritizedExpression.merge(m, Operator.SUBTRACT, m5);
        }else if (rand == 3){
            Matrix m1 = Matrix.multiply(-1, m, m.name);
            ret = PrioritizedExpression.merge(m4, Operator.SUBTRACT, m1);
        }
        ret.getValue();
        return ret;
    }
    public PrioritizedExpression invertiblify(PrioritizedExpression exp){
        Matrix m = (Matrix)(exp.getValue());
        if(m.hasInverse()){
            return exp;
        }
        if(!m.isSquareMatrix()){
            exp = squarify(exp);
            m = (Matrix)(exp.getValue());
        }
        Matrix m4 = generateMatrixAddInvertiblify(m);
        
        if(exp.getPriority() != 1){
            exp.setBracket(true);
        }
        int rand = Util.randInt(0, 2);
        PrioritizedExpression ret = null;
        if(rand == 0){
            ret = PrioritizedExpression.merge(exp, Operator.ADD, m4);
        }else if(rand == 1){
            ret = PrioritizedExpression.merge(m4, Operator.ADD, exp);
        }else if (rand == 2){
            Matrix m5 = Matrix.multiply(-1, m4, m4.name);
            ret = PrioritizedExpression.merge(exp, Operator.SUBTRACT, m5);
        }
        ret.getValue();
        return ret;
    }
    
    public static final Operator[] singleOperators = new Operator[]{Operator.MOD, Operator.DIVIDE, Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY};
    
    public ValueType generateVariationSingle(int degree, ValueType v){
        refreshPool();
        int rand = poolSingle.remove(Util.randInt(0, poolSingle.size()-1));
        ValueType b;
        if(rand == FUNCTION_POW){
            b = new Single(Util.randInt(-2, 2));
            Function fun = new Power();
            fun.setArg(0, v);
            fun.setArg(1, b);
            fun.getValue();
            return fun;
        }else{
            ValueType lhs, rhs;
            b = generateSingle(degree);
            if(Util.randInt(0, 1)==0){
                lhs = v; 
                rhs = b;
            }else{
                lhs = b;
                rhs = v;
            }
            PrioritizedExpression ret = PrioritizedExpression.merge(lhs, singleOperators[rand-BRACKET_MOD], rhs);
            ret.setBracket(true);
            ret.getValue();
            return ret;
        }
    }
    public PrioritizedExpression generateVariationMatrixBracket(int degree, ValueType v, int keep){
        Matrix m = (Matrix)(v.getValue());
        int size;
        PrioritizedExpression exp = null;
        int c = matrixSet.size();
        List<Integer> pool;
        if(keep == KEEP_DIM) pool = poolBracketKeepDim;
        else pool = poolBracket;
        mainLoop:
        do{
            size = pool.size();
            Integer rand;
            try{
                rand = pool.remove(Util.randInt(0, --size));
            }catch(java.lang.IllegalArgumentException ex){
                System.out.println("size: " + size);
                System.out.println("keep: " + keep);
                throw ex;
            }
            removeFromPool(rand);
            if(rand == BRACKET_ADD || rand == BRACKET_SUBTRACT){
                for(Matrix mi : matrixSet){
                    if(mi == m && Util.randInt(1,c) > 1){
                        continue;
                    }
                    if(Matrix.canAddOrSubtract(mi, m)){
                        ValueType a = m, b = mi;
                        if(Util.randInt(0, 4) == 0) a = generateVariation(degree+1, a, KEEP_DIM);
                        if(Util.randInt(0, 4) == 0) b = generateVariation(degree+1, b, KEEP_DIM);
                        Operator op = rand == BRACKET_ADD ? Operator.ADD : Operator.SUBTRACT;
                        if(Util.randInt(0, 1) == 0){
                            exp = PrioritizedExpression.merge(a, op, b);
                        }else{
                            exp = PrioritizedExpression.merge(b, op, a);
                        }
                        keepCheck(v, exp, keep);
                        break mainLoop;
                    }
                }
            }else{
                for(Matrix mi : matrixSet){
                    if(mi == m && Util.randInt(1,c) > 1){
                        continue;
                    }
                    if(keep == NO_KEEP){
                        if(Matrix.canMultiply(mi, m)){
                            if(Matrix.canMultiply(m, mi)){
                                ValueType a = m, b = mi;
                                if(Util.randInt(0, 1) == 0){
                                    exp = PrioritizedExpression.merge(a, Operator.MULTIPLY, b);
                                }else{
                                    exp = PrioritizedExpression.merge(b, Operator.MULTIPLY, a);
                                }
                            }else{
                                exp = PrioritizedExpression.merge(mi, Operator.MULTIPLY, m);
                            }
                            break;
                        }else if (Matrix.canMultiply(m, mi)){
                            exp = PrioritizedExpression.merge(m, Operator.MULTIPLY, mi);
                            break;
                        }
                    }else if (keep == KEEP_ROW){
                        //m left
                        if(Matrix.canMultiply(m, mi)){
                            exp = PrioritizedExpression.merge(m, Operator.MULTIPLY, mi);
                            break;
                        }
                    }else if (keep == KEEP_COL){
                        //m right
                        if(Matrix.canMultiply(mi, m)){
                            exp = PrioritizedExpression.merge(mi, Operator.MULTIPLY, m);
                            break;
                        }
                    }
                }
                if(exp != null){
                    keepCheck(v, exp, keep);
                    if(Util.randInt(0, 9) == 0) exp.setValue(0, generateVariation(degree+1, exp.getValue(0), KEEP_COL | keep));
                    if(Util.randInt(0, 9) == 0) exp.setValue(1, generateVariation(degree+1, exp.getValue(1), KEEP_ROW | keep));
                    keepCheck(v, exp, keep);
                }
            }
        }while(size > 0);
        if(exp != null){
            exp.setBracket(true);
            return exp;
        }
        return null;
    }
    public boolean keepCheck(ValueType v, ValueType v1, int keep){
        Matrix m = (Matrix)(v.getValue());
        Matrix m1 = (Matrix)(v1.getValue());
        boolean ret = true;
        if(keep == KEEP_ROW) ret = m.rowCount == m1.rowCount;
        else if (keep == KEEP_COL) ret = m.columnCount == m1.columnCount;
        else if (keep == KEEP_DIM) ret = (m.rowCount == m1.rowCount && m.columnCount == m1.columnCount);
        if(!ret) throw new UnsupportedOperationException("UNKEPT. keep: " + keep + " type: " + v1.getClass().getName());
        return ret;
    }
    public ValueType generateVariationMatrix(int degree, ValueType v, int keep){
        refreshPool();
        Matrix m = (Matrix)(v.getValue());
        int max = 2;
        if(!m.isSquareMatrix() && keep != NO_KEEP) max = 1;
        int rand0 = Util.randInt(0, max);
        if(rand0 == 0){
            PrioritizedExpression exp = generateVariationMatrixBracket(degree, v, keep);
            if(exp != null){
                keepCheck(v, exp, keep);
                return exp;
            }
            else rand0 = Util.randInt(1, max);
        }
        if(rand0 == 1){
            ValueType single = generateSingle(degree);
            if(Util.randInt(0, 4) == 0) v = generateVariation(degree+1, v, keep);
            if(v instanceof PrioritizedExpression){
                PrioritizedExpression exp0 = (PrioritizedExpression)v;
                if(exp0.canBeTakenApart()) exp0.setBracket(true);
            }
            PrioritizedExpression exp = PrioritizedExpression.merge(single, Operator.MULTIPLY, v);
            exp.setBracket(true);
                keepCheck(v, exp, keep);
            return exp;
        }else{
            Integer rand = null;
            if(keep==KEEP_DIM){
                if(m.hasInverse()){
                    rand = poolFunctionKeepDim.remove(Util.randInt(0, poolFunctionKeepDim.size()-1));
                }else if (m.isSquareMatrix()){
                    rand = poolFunctionSingularKeepDim.remove(Util.randInt(0, poolFunctionSingularKeepDim.size()-1));
                }
            }else{
                if(m.hasInverse()){
                    rand = poolFunction.remove(Util.randInt(0, poolFunction.size()-1));
                }else if (m.isSquareMatrix()){
                    rand = poolFunctionSingular.remove(Util.randInt(0, poolFunctionSingular.size()-1));
                }else{
                    rand = FUNCTION_T;
                }
            }
            Function fun = null;
            if(rand == FUNCTION_ADJ){
                fun = new AdjointMatrix();
            }else if (rand == FUNCTION_INV){
                fun = new InverseMatrix();
            }else if (rand == FUNCTION_T){
                fun = new TransposeMatrix();
            }else if (rand == FUNCTION_POW){
                fun = new Power();
                fun.setArg(1, new Single(Util.randInt(-2, 2)));
            }
            if(Util.randInt(0, 9) == 0) v = generateVariation(degree+1, v, KEEP_DIM);
            fun.setArg(0, v);
            keepCheck(v, fun, keep);
            return fun;
        }
        
    }
    public ValueType generateVariation(int degree, ValueType v, int keep){
        if(degree >= maxDegree) return v;
        ValueType retVal = v.getValue();
        if(retVal instanceof Single){
            return generateVariationSingle(degree, v);
        }else if (retVal instanceof Matrix){
            return generateVariationMatrix(degree, v, keep);
        }
        throw new UnsupportedOperationException("woy");
    }
    public PrioritizedExpression generate(){
        Matrix m = generateMatrixRand();
        return generate(m);
    }
    public PrioritizedExpression generate(Matrix head){
        ValueType v = generateVariation(1, head, KEEP_DIM);
        PrioritizedExpression exp = null;
        Matrix prev = head;
        int max = Util.randInt(1, maxMatrixCount-1);
        for(int i = 0; i < max; ++i){
            Operator op = operators[Util.randInt(0, operators.length-1)];
            Matrix m;
            ValueType vi;
            if(op.getPriority() == 0){
                m = generateMatrixAddSub(prev);
                vi = generateVariation(1, m, KEEP_DIM);
            }else{
                m = generateMatrixMulRhs(prev);
                vi = generateVariation(1, m, KEEP_DIM);
            }
            if(i == 0 && v instanceof PrioritizedExpression){
                PrioritizedExpression expi = (PrioritizedExpression)v;
                if(expi.getPriority() < op.getPriority()){
                    expi.setBracket(true);
                }
            }
            if(vi instanceof PrioritizedExpression){
                PrioritizedExpression expi = (PrioritizedExpression)vi;
                if(expi.getPriority() < op.getPriority()){
                    expi.setBracket(true);
                }
            }
            m = (Matrix)vi.getValue();
            PrioritizedExpression v1;
            try{
                v1 = PrioritizedExpression.merge(v, op, vi);
            }catch(UnsupportedOperationException ex){
                exp.setBracket(true);
                v1 = PrioritizedExpression.merge(v, op, vi);
            }
            
            exp = v1;
            v = v1;
            if(v1.canBeTakenApart()){
                prev = (Matrix)(exp.getValue(0).getValue());
            }else{
                prev = (Matrix)(v1.getValue());
            }
        }
        
        return exp;
    }
    
    public PrioritizedExpression generateSquare(){
        --maxMatrixCount;
        Matrix m = MatrixRandom.generateInvertibleMatrix(matrixCount++, Util.randInt(2, 4));
        matrices.add(m);
        PrioritizedExpression exp = generate(m);
        Matrix ret = (Matrix)(exp.getValue());
        if(!ret.isSquareMatrix()) exp = squarify(exp);
        return exp;
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
        ValueType exp = generate();
        Matrix m = (Matrix)(exp.getValue());
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
        ValueType exp = generate();
        Matrix m = (Matrix)(exp.getValue());
        m.name = "M";
        ExerciseOrdo ret = new ExerciseOrdo("Random Composite - Ordo");
        MatrixRandom.Ordo ordo = new MatrixRandom.Ordo();
        int[] ans = new int[]{m.rowCount, m.columnCount};
        ordo.answer = ans;
        ordo.entries = MatrixRandom.generateEntries(new HashSet<Integer>(), 1, 4, ans);
        ret.childs = generateChilds(matrices, exp.toString());
        ret.init2(ordo);
        return ret;
    }
    public ExerciseDeterminant generateDeterminant(){
        ValueType exp = generateSquare();
        Matrix m = (Matrix)(exp.getValue());
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
