/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import matrices.Util;
import matrices.main.form.MatrixForm;
import matrices.math.Matrix;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseMatrixForm extends ExerciseMatrix{
    String[] func = new String[]{
        "transpose",
        "magnitude",
        "minor",
        "cofactor",
        "adjoint",
        "inverse"
    };
    public ExerciseMatrixForm(){
        super("Form");
        init();
    }
    public ExerciseMatrixForm(int size){
        super("Form");
        init(size);
    }
    public ExerciseMatrixForm(int size, int form){
        super("Form");
        init(size, form);
    }
    
    int low = 2;
    int high = 4;
    
    String problem = "M = %s(%s)";
    public void init(){
        int size = Util.randInt(low, high);
        init(size);
    }
    public void init(int size){
        int form = Util.randInt(0, 4);
        if(form > 0) ++form;
        init(size, form);
    }
    public void init(int size, int form){
        
        Matrix a;
        Matrix a2;
        hasAnswer = true;
        if(form < 2){
            a = MatrixRandom.generateMatrix("A", size, Util.randInt(low, high));
            a2 = a;
        }else{
            hasAnswer = Util.randInt(0, 4) == 0;
            if(hasAnswer){
                if (form <5){
                    a = MatrixRandom.generateSquareMatrix("A", size);
                }else{
                    a = MatrixRandom.generateInvertibleMatrix("A", size);
                }
                a2=a;
            }else{
                if (form <5){
                    int col = Util.randInt(low, high, size);
                    a = MatrixRandom.generateMatrix("A", size, col);
                    int max = Util.max(size, col);
                    a2 = MatrixRandom.fitOne(a, max, max);
                }else{
                    a = MatrixRandom.generateSingularMatrix("A", size);
                    a2 = MatrixRandom.generateInvertibleMatrix(a);
                }
            }
        }
        Matrix m = null;
        
        switch(form){
            case MatrixForm.FORM_TRANSPOSE:{
                m = a2.getTransposeMatrix();
                break;
            }
            case MatrixForm.FORM_MINOR:{
                m = a2.getMinorMatrix();
                break;
            }
            case MatrixForm.FORM_COFACTOR:{
                m = a2.getCofactorMatrix();
                break;
            }
            case MatrixForm.FORM_ADJOINT:{
                m = a2.getAdjointMatrix();
                break;
            }
            case MatrixForm.FORM_INVERSE:{
                m = a2.getInverseMatrix();
                break;
            }
        }
        m.name = "M";
        matrix = m;
        
        String prob = String.format(problem, func[form], a.name);
        
        childs = new TutorialSection[]{
            new MatrixSection2(a),
            new TextSection(prob)
        };
        super.init();
    }
}
