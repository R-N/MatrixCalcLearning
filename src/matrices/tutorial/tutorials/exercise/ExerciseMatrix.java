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
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseMatrix extends ExerciseRandom{
    Matrix matrix;
    public ExerciseMatrix(String title){
        super(title);
    }
    
    static final String problemFormat = "%s<sub>%d, %d</sub> = ...";
    boolean hasAnswer = true;
    
    public void init(){
        int rc = matrix.rowCount;
        int cc = matrix.columnCount;
        HashSet<Double> distinctElements = new HashSet<Double>();
        for(int i = 0; i < rc; ++i){
            for(int j = 0; j < cc; ++j){
                distinctElements.add(matrix.getElementAt(i, j));
            }
        }
        int row = Util.randInt(1, rc);
        int col = Util.randInt(1, cc);
        Double ans0 = null;
        if (hasAnswer){
            ans0 = matrix.getElementAt(row-1, col-1);
        }
        
        String prob = String.format(problemFormat, matrix.name, row, col);
        
        super.init(
                prob,
                distinctElements, 
                ans0
        );
        
    }
}
