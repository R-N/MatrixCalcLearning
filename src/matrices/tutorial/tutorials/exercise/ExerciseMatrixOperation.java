/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import matrices.Util;
import matrices.math.Matrix;
import matrices.tutorial.tutorials.exercise.MatrixRandom.Ordo;
import matrices.tutorial.tutorials.exercise.MatrixRandom;
import matrices.tutorial.type.Option;
import matrices.tutorial.type.section.HorizontalLayoutSection;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseMatrixOperation extends ExerciseMatrix{
    
     int low = 2;
     int high = 4;
    
    static String problem = "M = (%sA) %s (%sB)";
    
    public ExerciseMatrixOperation(){
        super("Operation");
        init();
    }
    public ExerciseMatrixOperation(int operator){
        super("Operation");
        init(operator);
    }
    public ExerciseMatrixOperation(boolean hasAnswer){
        super("Operation");
        init(hasAnswer);
    }
    public ExerciseMatrixOperation(int operator, boolean hasAnswer){
        super("Operation");
        init(operator, hasAnswer);
    }
    
    public void init(){
        Ordo ordo = MatrixRandom.generateOrdo(low, high);
        init(ordo);
    }
    public void init(int operator){
        Ordo ordo = MatrixRandom.generateOrdo(operator, low, high);
        init(ordo);
    }
    public void init(boolean hasAnswer){
        Ordo ordo = MatrixRandom.generateOrdo(hasAnswer, low, high);
        init(ordo);
    }
    public void init(int operator, boolean hasAnswer){
        Ordo ordo = MatrixRandom.generateOrdo(operator, hasAnswer, low, high);
        init(ordo);
    }
    public void init (Ordo ordo){
        Matrix a = MatrixRandom.generateMatrix("A", ordo.lhsRow, ordo.lhsCol);
        Matrix b = MatrixRandom.generateMatrix("B", ordo.rhsRow, ordo.rhsCol);
        Matrix a2 = a;
        Matrix b2 = b;
        double lhsCoef = 1;
        double rhsCoef = 1;
        if(Util.randInt(0, 1) == 0){
            if(Util.randInt(0, 1) == 0) lhsCoef = Util.randInt(-5, 5);
            if(Util.randInt(0, 3) == 0) lhsCoef = lhsCoef / 2;
        }
        if(Util.randInt(0, 1) == 0){
            if(Util.randInt(0, 1) == 0) rhsCoef = Util.randInt(-5, 5);
            if(Util.randInt(0, 3) == 0) rhsCoef = rhsCoef / 2;
        }
        if(ordo.answer == null){
            int max = Util.max(Util.max(Util.max(ordo.lhsRow, ordo.lhsCol), ordo.rhsRow), ordo.rhsCol);
            a2 = MatrixRandom.fitOne(a, max, max);
            b2 = MatrixRandom.fitOne(b, max, max);
            hasAnswer = false;
        }
        a2 = Matrix.multiply(lhsCoef, a2);
        b2 = Matrix.multiply(rhsCoef, b2);
        String operator = "ERROR";
        if(ordo.operator == 0){
            matrix = Matrix.add(a2, b2);
            operator = "+";
        }else if (ordo.operator == 1){
            matrix = Matrix.subtract(a2, b2);
            operator = "-";
        }else if (ordo.operator == 2){
            matrix = Matrix.multiply(a2, b2);
            operator = "*";
        }
        
        matrix.name = "M";
        
        childs = new TutorialSection[]{
            new HorizontalLayoutSection(
                    new TutorialSection[]{
                        new MatrixSection2(a),
                        new MatrixSection2(b)
                    }
            ),
            new TextSection(String.format(problem, Util.toString(lhsCoef), operator, Util.toString(rhsCoef)))
        };
        super.init();
    }
}
