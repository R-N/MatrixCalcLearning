/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import matrices.Util;
import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public class MatrixRandom {
    
    public static class Ordo{
        public int lhsRow;
        public int lhsCol;
        public int rhsRow;
        public int rhsCol;
        public int operator;
        public int[] answer;
        public int[][] entries;
    }
    public static class MatrixResult{
        public Matrix optionSource;
        public boolean hasAnswer;
    }
    public static class Operation extends MatrixResult{
        public Matrix lhs;
        public Matrix rhs;
        public double lhsCoef;
        public double rhsCoef;
        public int operator;
    }
    public static class Form extends MatrixResult{
        public Matrix matrix;
        public int form;
    }
    public static class DeterminantResult{
        public double result;
        public boolean hasAnswer;
    }
    public static class Cramer{
        public Matrix main;
        public Matrix sub;
        public int column;
    }
    
    public static Ordo generateOrdo(int low, int high){
        int operator = Util.randInt(0, 2);
        boolean hasAnswer = Util.randInt(0, 4) == 0;
        return generateOrdo(operator, hasAnswer, low, high);
    }
    public static Ordo generateOrdo(int operator, int low, int high){
        boolean hasAnswer = Util.randInt(0, 4) == 0;
        return generateOrdo(operator, hasAnswer, low, high);
    }
    public static Ordo generateOrdo(boolean hasAnswer, int low, int high){
        int operator = Util.randInt(0, 2);
        return generateOrdo(operator, hasAnswer, low, high);
    }
    
    public static void fillPool(HashSet<Integer> pool0, int req, int low, int high){
        if(pool0.size() < req){
            List<Integer> temp = new ArrayList<Integer>(high-low+1);
            for(int i = low; i <= high; ++i){
                if(!pool0.contains(i)){
                    temp.add(i);
                }
            }
            
            while(pool0.size() < req && temp.size() > 0){
                pool0.add(temp.remove(Util.randInt(0, temp.size()-1)));
            }
            int i = 1;
            while(pool0.size() < req){
                pool0.add(high+(i++));
            }
        }
    }
    
    public static List<int[]> generatePermutation2(int size){
        List<int[]> pool2 = new LinkedList<int[]>();
        for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                if(i != j){
                    pool2.add(new int[]{i, j});
                }
            }
        }
        return pool2;
    }
    
    public static int[][] generateEntries(HashSet<Integer> pool0, int low, int high, int[] ans){
        if(ans != null){
            pool0.add(ans[0]);
            pool0.add(ans[1]);
        }
        fillPool(pool0, 4, low, high);
        
        int in = 0;
        int[] pool = new int[pool0.size()];
        for(Integer i : pool0){
            pool[in++] = i;
        }
        
        List<int[]> pool2 = generatePermutation2(pool0.size());
        
        int[][] entries = new int[4][];
        
        for(int i = 0; i < 4; ++i){
            int[] e0 = pool2.remove(Util.randInt(0, pool2.size()-1));
            int[] e1 = new int[]{pool[e0[0]], pool[e0[1]]};
            if(ans!=null && e1[0] == ans[0] && e1[1] == ans[1]){
                --i;
                continue;
            }
            entries[i] = e1;
        }
        //if(ans != null) entries[0] = ans;
        return entries;
    }
    
    public static Ordo generateOrdo(int operator, boolean hasAnswer, int low, int high){
        
        int[] ans = null;
        
        int lhsRow, lhsCol, rhsRow, rhsCol;
        if(operator < 2){
            lhsRow = Util.randInt(low, high);
            lhsCol = Util.randInt(low, high, lhsRow);
            rhsRow = lhsRow;
            rhsCol = lhsCol;
            if (Util.randInt(0,4) == 0){
                int c = Util.randInt(0, 3);
                if(c == 1){
                    rhsRow = Util.randInt(low, high, lhsRow);
                }else if (c == 2){
                    rhsRow = Util.randInt(low, high, lhsRow);
                    rhsCol = rhsRow;
                }else if(c == 3){
                    rhsCol = Util.randInt(low, high, lhsCol);
                }else{
                    rhsRow = Util.randInt(low, high, lhsRow);
                    rhsCol = Util.randInt(low, high, lhsCol);
                }
                
            }else{
                ans = new int[]{lhsRow, lhsCol};
            }
        }else{
            lhsRow = Util.randInt(low, high);
            lhsCol = Util.randInt(low, high, lhsRow);
            rhsRow = lhsCol;
            rhsCol = Util.randInt(low, high, new int[]{lhsRow, lhsCol});
            
            if (Util.randInt(0, 4) == 0){
                int c = Util.randInt(0, 2);
                if(c == 0){
                    rhsRow = lhsRow;
                }else if (c == 1){
                    rhsRow = rhsCol;
                }else{
                    rhsRow = Util.randInt(low, high);
                }
                
            }else{
                ans = new int[]{lhsRow, rhsCol};
            }
        }
        HashSet<Integer> pool0 = new HashSet<Integer>();
        pool0.add(lhsRow);
        pool0.add(lhsCol);
        pool0.add(rhsRow);
        pool0.add(rhsCol);
        
        int[][] entries = generateEntries(pool0, low, high, ans);
        
        Ordo ret = new Ordo();
        ret.lhsRow = lhsRow;
        ret.lhsCol = lhsCol;
        ret.rhsRow = rhsRow;
        ret.rhsCol = rhsCol;
        ret.operator = operator;
        ret.answer = ans;
        ret.entries = entries;
        return ret;
    }
    
    public static double[][] generateMatrixData(int rowCount, int columnCount){
        double[][] data = new double[rowCount][columnCount];
        int max = Util.max(5, Util.max(rowCount, columnCount));
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                data[i][j] = Util.randInt(-max, max);
            }
        }
        return data;
    }
    
    public static Matrix generateMatrix(int index, int rowCount, int columnCount){
        return generateMatrix(Util.indexToLetterUpper(index), rowCount, columnCount);
    }
    public static Matrix generateMatrix(String name, int rowCount, int columnCount){
        double[][] data = generateMatrixData(rowCount, columnCount);
        return new Matrix(data, name);
    }
    public static Matrix generateSquareMatrix(int index, int size){
        return generateSquareMatrix(Util.indexToLetterUpper(index), size);
    }
    public static Matrix generateSquareMatrix(String name, int size){
        return generateMatrix(name, size, size);
    }
    public static Matrix generateInvertibleMatrix(int index, int size){
        return generateInvertibleMatrix(Util.indexToLetterUpper(index), size);
    }
    public static Matrix generateInvertibleMatrix(String name, int size){
        double[][] data = generateMatrixData(size, size);
        return generateInvertibleMatrix(data, name);
        
    }
    public static Matrix generateInvertibleMatrix(double[][] data, String name){
        int size = data.length;
        Matrix id = Matrix.getIdentityMatrix(size);
        //Matrix source = new Matrix(data, name);
        int size1 = size-1;
        int max = Util.max(size, 5);
            //System.out.println("2");
        do{
            Matrix trial = new Matrix(data, name);
            if(trial.hasInverse()){
                Matrix inv = trial.getInverseMatrix();
                Matrix trial2 = Matrix.multiply(trial, trial);
                if(id.equals(Matrix.multiply(trial, inv))
                        && id.equals(Matrix.multiply(inv, trial))
                        //&& trial.equals(Matrix.multiply(trial2, inv))
                        //&& trial.equals(Matrix.multiply(inv, trial2))
                        && trial.equals(inv.getInverseMatrix())){
                    return trial;
                }
            }
            int row = Util.randInt(0, size1);
            int col = Util.randInt(0, size1);
            data[row][col] = Util.randInt(-max, max, (int)data[row][col]);
        }while(true);
    }
    public static Matrix generateInvertibleMatrix(Matrix m){
        double[][] data = m.toPrimitiveDoubleArray();
        return generateInvertibleMatrix(data, m.name);
    }
    
    public static Matrix generateSingularMatrix(int index, int size){
        return generateSingularMatrix(Util.indexToLetterUpper(index), size);
    }
    public static Matrix generateSingularMatrix(String name, int size){
        double[][] data = generateMatrixData(size, size);
        return generateSingularMatrix(data, name);
    }
    //https://math.stackexchange.com/questions/197390/generating-a-random-singular-matrices-using-matlab
    public static Matrix generateSingularMatrix(double[][] data, String name){
        int size = data.length;
        int size1 = size-1;
        boolean row = Util.randInt(0, 1) == 0;
        int num = Util.randInt(0, size1);
        
        if(row){
            for(int i = 0; i < size; ++i){
                double sum = 0;
                for(int j = 0; j < size; ++j){
                    if(j != num){
                        sum += data[j][i];
                    }
                }
                data[num][i] = sum;
            }
        }else{
            for(int i = 0; i < size; ++i){
                double sum = 0;
                for(int j = 0; j < size; ++j){
                    if(j != num){
                        sum += data[i][j];
                    }
                }
                data[i][num] = sum;
            }
        }
        return new Matrix(data, name);
    }
    public static Matrix generateSingularMatrix(Matrix m){
        double[][] data = m.toPrimitiveDoubleArray();
        return generateSingularMatrix(data, m.name);
    }
    public static Matrix fitOne(Matrix m, int row, int col){
        double[][] data = new double[row][col];
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                if(i < m.rowCount && j < m.columnCount){
                    data[i][j] = m.getElementAt(i, j);
                }else{
                    data[i][j] = 1;
                }
            }
        }
        return new Matrix(data, m.name);
    }
}
