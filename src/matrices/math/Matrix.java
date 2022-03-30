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
public class Matrix extends DirectValueType{
    public String name = null;
    private final double[][] data;
    public final int rowCount;
    public final int columnCount;
    
    
    public Matrix(Matrix m){
        this(m, m.name);
    }
    public Matrix(Matrix m, String name){
        this(m.data, name);
    }
    
    private Matrix(int rowCount, int columnCount){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.data = new double[rowCount][columnCount];
    }
    
    private Matrix(int rowCount, int columnCount, String name){
        this(rowCount, columnCount);
        this.name = name;
    }
    
    public Matrix(Double[][] data){
        this(data, null);
    }
    public Matrix(Double[][] data, String name){
        this(Util.toPrimitive(data), name);
    }
    
    public Matrix(double[][] data){
        this.rowCount = data.length;
        int cc = 0;
        for(int i = 0; i < rowCount; ++i){
            if(cc < data[i].length){
                cc = data[i].length;
            }
        }
        this.columnCount = cc;
        this.data = new double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            cc = data[i].length;
            for(int j = 0; j < cc; ++j){
                double value = data[i][j];
                if(value == Double.NaN || value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY){
                    throw new UnsupportedOperationException("Invalid value: " + value);
                }
                this.data[i][j] = value;
            }
        }
    }
    public Matrix(double[][] data, String name){
        this(data);
        this.name = name;
    }
    public Matrix clone(){
        return new Matrix(this);
    }
    public Matrix clone(String name){
        return new Matrix(this, name);
    }
    public static Matrix getZeroMatrix(int rowCount, int columnCount){
        return getZeroMatrix(rowCount, columnCount, "0");
    }
    public static Matrix getZeroMatrix(int rowCount, int columnCount, String name){
        return new Matrix(rowCount, columnCount, name);
    }
    
    public static Matrix getIdentityMatrix(int size){
        return getIdentityMatrix(size, "I");
    }
    public static Matrix getIdentityMatrix(int size, String name){
        return getScalarMatrix(size, 1, name);
    }
    public static Matrix getScalarMatrix(int size, double value){
        return getScalarMatrix(size, value, Util.toString(value));
    }
    public static Matrix getScalarMatrix(int size, double value, String name){
        Matrix m = new Matrix(size, size, name);
        for (int i = 0; i < size; ++i){
            m.data[i][i] = value;
        }
        return m;
    }
    
    public static Matrix getUniformMatrix(int rowCount, int columnCount, double value){
        return getUniformMatrix(rowCount, columnCount, value, Util.toString(value));
    }
    
    public static Matrix getUniformMatrix(int rowCount, int columnCount, double value, String name){
        Matrix m = new Matrix(rowCount, columnCount, name);
        for (int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                m.data[i][j] = value;
            }
        }
        return m;
    }
    
    public double getElementAt(int row, int column){
        return data[row][column];
    }
    
    public Matrix switchRows(int row1, int row2){
        if(row1 == row2){
            return new Matrix(this);
        }
        double[][] newData = new double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            int row = i;
            if(row == row1){
                row = row2;
            } else if (row == row2){
                row = row1;
            }
            for(int j = 0; j < columnCount; ++j){
                newData[i][j] = data[row][j];
            }
        }
        return new Matrix(newData, name);
    }
    
    public boolean isSquareMatrix(){
        return rowCount == columnCount;
    }
    
    public boolean hasDeterminant(){
        return isSquareMatrix();
    }
    
    public boolean hasInverse(){
        return hasDeterminant() && getDeterminant() != 0;
    }
    public boolean isIdentity(){
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                if(i == j){
                    if(data[i][j] != 1){
                        return false;
                    }
                }else{
                    if(data[i][j] != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean isZero(){
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                if(data[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean canAddOrSubtract(Matrix lhs, Matrix rhs){
        return lhs.rowCount == rhs.rowCount && lhs.columnCount == rhs.columnCount;
    }
    
    public static boolean canMultiply(Matrix lhs, Matrix rhs){
        return lhs.columnCount == rhs.rowCount;
    }
    
    public double getMagnitudeAt(int column){
        double sum = 0;
        for(int i = 0; i < rowCount; ++i){
            double x = data[i][column];
            sum+= x*x;
        }
        return Math.sqrt(sum);
    }
    
    public static Matrix getMagnitudeMatrix(Matrix m){
        int cc = m.columnCount;
        double[][] data = new double[1][cc];
        for(int i = 0; i < cc; ++i){
            data[0][i] = m.getMagnitudeAt(i);
        }
        return new Matrix(data, String.format("magnitude(%s)", m.name));
    }
    public Matrix getMagnitudeMatrix(){
        return getMagnitudeMatrix(this);
    }
    
    public Matrix multiplyRow(double coefficient, int row){
        if(coefficient == 1){
            return new Matrix(this);
        }
        double[][] newData = new double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                if(i == row){
                    newData[i][j] = coefficient*data[i][j];
                }else{
                    newData[i][j] = data[i][j];
                }
            }
        }
        return new Matrix(newData, name);
    }
    
    public Matrix addRow2ToRow1(double coefficient1, int row1, double coefficient2, int row2){
        double[][] newData = new double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                if(i == row1){
                    newData[row1][j] = coefficient1 * data[row1][j] + coefficient2 * data[row2][j];
                }else{
                    newData[i][j] = data[i][j];
                }
            }
        }
        return new Matrix(newData, name);
    }
    
    public String[][] toStringArray(){
        String[][] ret = new String[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                ret[i][j] = Util.toString(data[i][j]);
            }
        }
        return ret;
    }
    public Double[][] toDoubleArray(){
        Double[][] ret = new Double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                ret[i][j] = data[i][j];
            }
        }
        return ret;
    }
    public double[][] toPrimitiveDoubleArray(){
        double[][] ret = new double[rowCount][columnCount];
        for(int i = 0; i < rowCount; ++i){
            for(int j = 0; j < columnCount; ++j){
                ret[i][j] = data[i][j];
            }
        }
        return ret;
    }
    
    public Matrix getSubMatrixExcluding(int row, int column){
        if(rowCount <= 1 || columnCount <= 1){
            return null;
            //throw new Exception("Matriks satu baris atau satu kolom");
        }
        double[][] newData = new double[rowCount-1][columnCount-1];
        int p=0;
        for(int i = 0; i < rowCount; ++i){
            int q = 0;
            if(i==row){
                continue;
            }
            for(int j = 0; j < columnCount; ++j){
                if (j==column){
                    continue;
                }
                newData[p][q] = data[i][j];
                ++q;
            }
            ++p;
        }
        return new Matrix(newData);
    }
    
    public static int powNeg1(int pow){
        int ret = 1;
        for(int i = 0; i < pow; ++i){
            ret *= -1;
        }
        return ret;
    }
    
    public Double getMinorAt(int row, int col){
        Matrix m = getSubMatrixExcluding(row, col);
        if(m == null){
            return null;
        }
        return m.getDeterminant();
    }
    
    public Double getCofactorAt(int row, int col){
        Double minor = getMinorAt(row, col);
        if(minor == null){
            return null;
        }
        return powNeg1(row+col) * minor;
    }
    
    public static Double getDeterminant(Matrix m){
        if(!m.hasDeterminant()){
            return null;
            //throw new Exception("Matriks tidak persegi");
        }
        int cc = m.columnCount;
        if(cc == 1){
            return m.data[0][0];
        }else if (cc == 2){
            return m.data[0][0] * m.data[1][1] - m.data[0][1] * m.data[1][0];
        }else{
            double det = 0;
            for(int i = 0; i < cc; ++i){
                det += m.data[0][i] * m.getCofactorAt(0, i);
            }
            return det;
        }
    }
    
    public Double getDeterminant(){
        return Matrix.getDeterminant(this);
    }
    
    
    public static Matrix transpose(Matrix m){
        int rc = m.rowCount;
        int cc = m.columnCount;
        double[][] newData = new double[cc][rc];
        for (int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[j][i] = m.data[i][j];
            }
        }
        String name = String.format("Transpose(%s)", m.name);
        Matrix ret = new Matrix(newData, name);
        return ret;
    }
    
    public Matrix getTransposeMatrix(){
        return transpose(this);
    }
    
    public static Matrix getMinorMatrix(Matrix m){
        int rc = m.rowCount;
        int cc = m.columnCount;
        if(rc != cc || rc <= 1){
            return null;
        }
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = m.getMinorAt(i, j);
            }
        }
        String name = String.format("Minor(%s)", m.name);
        Matrix ret = new Matrix(newData, name);
        return ret;
    }
    public Matrix getMinorMatrix() {
        return getMinorMatrix(this);
    }
    
    public static Matrix getCofactorMatrix(Matrix m) {
        int rc = m.rowCount;
        int cc = m.columnCount;
        if(rc != cc || rc <= 1){
            return null;
        }
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = m.getCofactorAt(i, j);
            }
        }
        
        String name = String.format("Cofactor(%s)", m.name);
        Matrix ret = new Matrix(newData, name);
        return ret;
    }
    public Matrix getCofactorMatrix() {
        return getCofactorMatrix(this);
    }
    
    public static Matrix getAdjointMatrix(Matrix m){
        Matrix cof = getCofactorMatrix(m);
        if(cof == null){
            return null;
        }
        Matrix ret = transpose(cof);
        ret.name = String.format("Adjoint(%s)", m.name);
        return ret;
    }
    public Matrix getAdjointMatrix(){
        return getAdjointMatrix(this);
    }
    
    public static Matrix inverse(Matrix m){
        Double det = getDeterminant(m);
        if(det == null || det == 0){
            return null;
        }
        /*try{
            det = m.getDeterminant();
        }catch(Exception ex){
            throw new Exception("Matriks tidak memiliki invers (bukan matriks persegi");
        }
        if (det == 0){
            throw new Exception("Matriks tidak memliki invers (determinan nol)");
        }*/
        Matrix ret = multiply(1/det, getAdjointMatrix(m));
        ret.name = String.format("Inverse(%s)", m.name);
        return ret;
    }
    public Matrix getInverseMatrix(){
        return inverse(this);
    }
    public static Matrix subtract(Matrix lhs, Matrix rhs){
        String name = String.format("(%s-%s)", lhs.name, rhs.name);
        return subtract(lhs, rhs, name);
    }
    
    public static Matrix subtract(Matrix lhs, Matrix rhs, String name){
        if (!canAddOrSubtract(lhs, rhs)){
            return null;
        }
        Matrix ret = add(lhs, multiply(-1, rhs), name);
        return ret;
    }
    public static Matrix multiply(Matrix m, double k){
        String name = String.format("(%s*%s)", m.name, Util.toString(k));
        return multiply(m, k, name);
    }
    
    public static Matrix multiply(Matrix m, double k, String name){
        if(k == 1){
            return m.clone();
        }
        Matrix ret = multiply(k, m, name);
        return ret;
    }
    
    public static Matrix divide(Matrix m, double d){
        String name = String.format("(%s/%s)", m.name, Util.toString(d));
        return divide(m, d, name);
    }
    public static Matrix divide(Matrix m, double d, String name){
        if(d == 0){
            return null;
        }
        if(d == 1){
            return m.clone();
        }
        Matrix ret = multiply(1/d, m, name);
        return ret;
    }
    
    public static Matrix add(Matrix lhs, Matrix rhs){
        String name = String.format("(%s+%s)", lhs.name, rhs.name);
        return add(lhs, rhs, name);
    }
    public static Matrix add(Matrix lhs, Matrix rhs, String name){
        if (!canAddOrSubtract(lhs, rhs)){
            return null;
            //throw new Exception(String.format("Ordo tidak sama. A[%d][%d] + B[%d][%d]", lhs.rowCount, lhs.columnCount, rhs.rowCount, rhs.columnCount));
        }
        int rc = lhs.rowCount;
        int cc = lhs.columnCount;
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = lhs.data[i][j] + rhs.data[i][j];
            }
        }
        return new Matrix(newData, name);
    }
    public static Matrix multiply(Matrix lhs, Matrix rhs){
        String name = String.format("(%s*%s)", lhs.name, rhs.name);
        return multiply(lhs, rhs, name);
    }
    public static Matrix multiply(Matrix lhs, Matrix rhs, String name) {
        if (!canMultiply(lhs, rhs)){
            return null;
            //throw new Exception(String.format("Ordo tidak sesuai. A[%d][%d] * B[%d][%d]. Seharusnya A[p][n] * B[n][q].", lhs.rowCount, lhs.columnCount, rhs.rowCount, rhs.columnCount));
        }
        int rc = lhs.rowCount;
        int cc = rhs.columnCount;
        int c = lhs.columnCount;
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                double x = 0;
                for (int k = 0; k < c; ++k){
                    x += lhs.data[i][k] * rhs.data[k][j];
                    //x += lhs.getElementAt(i, k) * rhs.getElementAt(k, j);
                }
                newData[i][j] = x;
            }
        }
        return new Matrix(newData, name);
    }
    public static Matrix multiply(double k, Matrix m){
        String name = String.format("(%s*%s)", Util.toString(k), m.name);
        return multiply(k, m, name);
    }
    public static Matrix multiply(double k, Matrix m, String name){
        if(k == 1){
            return m.clone();
        }
        int rc = m.rowCount;
        int cc = m.columnCount;
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = m.data[i][j] * k;
            }
        }
        return new Matrix(newData, name);
    }
    public static Matrix mod(Matrix m, double k){
        String name = String.format("(%s%%%s)", m.name, Util.toString(k));
        return mod(m, k, name);
    }
    public static Matrix mod(Matrix m, double k, String name){
        if(k == 1){
            Matrix ret = Matrix.getZeroMatrix(m.rowCount, m.columnCount, name);
            return ret;
        }
        int rc = m.rowCount;
        int cc = m.columnCount;
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = m.data[i][j] % k;
            }
        }
        return new Matrix(newData, name);
    }
    
    public static Matrix add(Matrix m, double c){
        String name = String.format("(%s+%s)", m.name, Util.toString(c));
        return add(m, c, name);
    }
    public static Matrix add(Matrix m, double c, String name){
        if(c == 0){
            Matrix ret = m.clone(name);
            return ret;
        }
        int rc = m.rowCount;
        int cc = m.columnCount;
        double[][] newData = new double[rc][cc];
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                newData[i][j] = m.data[i][j] + c;
            }
        }
        return new Matrix(newData, name);
    }
    
    public static Matrix add(double c, Matrix m){
        String name = String.format("(%s+%s)", m.name, Util.toString(c));
        Matrix ret = add(m, c, name);
        return ret;
    }
    
    public static Matrix subtract(Matrix m, double c){
        String name = String.format("(%s-%s)", m.name, Util.toString(c));
        Matrix ret = add(m, -c, name);
        return ret;
    }

    @Override
    public DirectValueType add(DirectValueType rhs) {
        if(rhs instanceof Matrix && Matrix.canAddOrSubtract(this, (Matrix)rhs)){
            return Matrix.add(this, (Matrix)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet.: " + rhs.getClass().getName()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DirectValueType subtract(DirectValueType rhs) {
        if(rhs instanceof Matrix && Matrix.canAddOrSubtract(this, (Matrix)rhs)){
            return Matrix.subtract(this, (Matrix)rhs);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DirectValueType multiply(DirectValueType rhs) {
        if(rhs instanceof Matrix && Matrix.canMultiply(this, (Matrix)rhs)){
            return Matrix.multiply(this, (Matrix)rhs);
        }else if (rhs instanceof Single){
            return Matrix.multiply(this, ((Single)rhs).getDouble());
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DirectValueType divide(DirectValueType rhs) {
        if(rhs instanceof Single){
            return Matrix.divide(this, ((Single)rhs).getDouble());
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DirectValueType mod(DirectValueType rhs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static boolean equals(Matrix lhs, Matrix rhs, boolean compareName){
        if(lhs == rhs) return true;
        if(lhs.rowCount != rhs.rowCount || lhs.columnCount != rhs.columnCount) return false;
        if((lhs.data == null) != (rhs.data == null)) return false;
        if(compareName){
            if((lhs.name == null) != (rhs.name == null)) return false;
            if(lhs.name != null && !lhs.name.equals(rhs.name)) return false;
        }
        if(lhs.data == null) return true;
        for(int i = 0; i < lhs.rowCount; ++i){
            if((lhs.data[i] == null) != (rhs.data[i] == null)) return false;
            if(lhs.data[i] == null) continue;
            for(int j = 0; j < lhs.columnCount; ++j){
                if(lhs.data[i][j] != rhs.data[i][j]) return false;
            }
        }
        return true;
    }
    public boolean equals(Matrix rhs){
        return equals(this, rhs, false);
    }
    
    @Override
    public boolean equals(Object rhs){
        if(rhs instanceof Matrix){
            return equals(this, (Matrix)rhs, true);
        }else{
            return false;
        }
    }
    
    public String toString(boolean bracket){
        String ret = name;
        if(bracket){
            ret = String.format("%s<%sx%s>", ret, rowCount, columnCount);
        }
        return ret;
    }
}
