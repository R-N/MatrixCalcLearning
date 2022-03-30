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
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseDeterminant extends ExerciseRandom{
    int low = 2;
    int high = 4;
    Matrix matrix;
    public boolean showMatrix = true;
    public ExerciseDeterminant(){
        super("Determinant");
        init();
    }
    public ExerciseDeterminant(int size){
        super("Determinant");
        init(size);
    }
    public ExerciseDeterminant(String title){
        super(title);
    }
    
    static final String problemFormat = "|%s| = ...";
    boolean hasAnswer = true;
    
    public void init(){
        int size = Util.randInt(low, high);
        init(size);
    }
    public void init(int size){
        if(Util.randInt(0, 4) == 0){
            hasAnswer = false;
            if(Util.randInt(0, 2) == 0){
                matrix = MatrixRandom.generateSingularMatrix("M", size);
            }else{
                int col = Util.randInt(low, high, size);
                matrix = MatrixRandom.generateMatrix("M", size, col);
            }
        }else{
            matrix = MatrixRandom.generateInvertibleMatrix("M", size);
        }
        init2();
    }
    
    public void init2(){
        
        HashSet<Double> distinctDets = new HashSet<Double>();
        
        Double ans0 = null;
        if(hasAnswer && matrix.hasDeterminant()){
            ans0 = matrix.getDeterminant();
            distinctDets.add(ans0);
        }else{
            int max = Util.max(matrix.rowCount, matrix.columnCount);
            Matrix m2 = MatrixRandom.fitOne(matrix, max, max);
            distinctDets.add(m2.getDeterminant());
        }
        if(matrix.name == null || matrix.name.isEmpty()) matrix.name = "M";
        if(showMatrix){
            childs = new TutorialSection[]{
                new MatrixSection2(matrix)
            };
        }
        
        String prob = String.format(problemFormat, matrix.name);
        
        
        super.init(
                prob, 
                distinctDets, 
                ans0
        );
    }
}
