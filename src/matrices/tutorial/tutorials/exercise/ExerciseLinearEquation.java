/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import java.util.HashSet;
import matrices.Util;
import matrices.math.Matrix;
import matrices.tutorial.type.Option;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseLinearEquation extends ExerciseRandom{
    int low = 2;
    int high = 4;
    final Option solveableButNotListed = new Option("Dapat diselesaikan tapi tidak ada di daftar pilihan jawaban");
    
    public ExerciseLinearEquation(){
        super("Linear Equation");
        init();
    }
    public ExerciseLinearEquation(int size){
        super("Linear Equation");
        init(size);
    }
    
    public void init(){
        int size = Util.randInt(low, high);
        init(size);
    }
    public void init(int size){
        Matrix k = MatrixRandom.generateInvertibleMatrix("K", size);
        Matrix h = MatrixRandom.generateMatrix("H", size, 1);
        String[] eq = new String[size];
        for(int i = 0; i < size; ++i){
            String s = Util.toString(k.getElementAt(i, 0)) + "x";
            for(int j = 1; j < size; ++j){
                double e = k.getElementAt(i, j);
                if(e == 0.0 || e == -0.0){
                    continue;
                }else if(e < 0){
                    s = String.format("%s - %s%s", s, Util.toStringCoef(-e), Util.indexToLetterXLower(j));
                }else{
                    s = String.format("%s + %s%s", s, Util.toStringCoef(e), Util.indexToLetterXLower(j));
                }
            }
            s = String.format("%s = %s", s, Util.toString(h.getElementAt(i, 0)));
            eq[i] = s;
        }
        Matrix v = Matrix.multiply(k.getInverseMatrix(), h);
        Double ans = v.getMagnitudeAt(0);
        HashSet<Double> randoms = new HashSet<Double>();
        randoms.add(ans);
        for(int i = 0; i < size; ++i){
            randoms.add(ans - v.getElementAt(i, 0));
            randoms.add(ans + v.getElementAt(i, 0));
        }
        String prob = eq[0];
        for(int i = 1; i < size; ++i){
            prob = String.format("%s\n%s", prob, eq[i]);
        }
        String s2 = "x";
        for(int i = 1; i < size; ++i){
            s2 = String.format("%s + %s", s2, Util.indexToLetterXLower(i));
        }
        prob = String.format("%s\n\n%s = ...", prob, s2);
        super.init(prob, randoms, ans);
    }
}
