/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials;

import matrices.Util;
import matrices.main.form.MatrixForm;
import matrices.main.form.MatrixOperation;
import matrices.tutorial.tutorials.exercise.*;
import matrices.math.Matrix;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialSlide2;
import matrices.tutorial.type.MatrixSection;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class Exercise {
    
    public static Tutorial2[] tutorials = new Tutorial2[]{
    };
    
    public static Tutorial2[] getExercise(){
        return new Tutorial2[]{
            new Tutorial2("Random Operation", 
                    Util.shuffle(
                            new TutorialSlide2[]{
                            new ExerciseOrdo(),
                            new ExerciseOrdo(),
                            new ExerciseOrdo(),
                            new ExerciseOrdo(),
                            new ExerciseOrdo(),
                            new ExerciseMatrixOperation(),
                            new ExerciseMatrixOperation(),
                            new ExerciseMatrixOperation(),
                            new ExerciseMatrixOperation(),
                            new ExerciseMatrixOperation(),
                        }
                    )
            ),
            new Tutorial2("Random Determinant", 
                    Util.shuffle(
                        new TutorialSlide2[]{
                            new ExerciseDeterminant(),
                            new ExerciseDeterminant(),
                            new ExerciseDeterminant(),
                            new ExerciseDeterminant(),
                            new ExerciseDeterminant(),
                        }
                    )
            ),
            new Tutorial2("Random Form", 
                    Util.shuffle(
                        new TutorialSlide2[]{
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                            new ExerciseMatrixForm(),
                        }
                    )
            ),
            new Tutorial2("Random Form", 
                    Util.shuffle(
                        new TutorialSlide2[]{
                            new ExerciseLinearEquation(),
                            new ExerciseLinearEquation(),
                            new ExerciseLinearEquation(),
                            new ExerciseLinearEquation(),
                            new ExerciseLinearEquation(),
                        }
                    )
            ),
            new Tutorial2("All", 
                    Util.shuffle(
                        new TutorialSlide2[]{
                            new ExerciseOrdo(Util.randInt(0, 1)),
                            new ExerciseOrdo(MatrixOperation.OPERATOR_MULTIPLY),
                            new ExerciseMatrixOperation(Util.randInt(0, 1)),
                            new ExerciseMatrixOperation(MatrixOperation.OPERATOR_MULTIPLY),
                            new ExerciseDeterminant(2),
                            new ExerciseDeterminant(3),
                            new ExerciseDeterminant(4),
                            new ExerciseMatrixForm(2, MatrixForm.FORM_INVERSE),
                            new ExerciseMatrixForm(3, MatrixForm.FORM_INVERSE),
                            new ExerciseMatrixForm(4, MatrixForm.FORM_INVERSE),
                            new ExerciseLinearEquation(2),
                            new ExerciseLinearEquation(3),
                            new ExerciseLinearEquation(4),
                            new ExerciseComposite().generateMatrix(),
                            new ExerciseComposite().generateDeterminant(),
                            new ExerciseComposite().generateOrdo(),
                            new ExerciseComposite2().generateMatrix(),
                            new ExerciseComposite2().generateDeterminant(),
                            new ExerciseComposite2().generateOrdo(),
                            new ExerciseComposite().generateProblem(),
                            new ExerciseComposite().generateProblem(),
                            new ExerciseComposite().generateProblem(),
                            new ExerciseComposite2().generateProblem(),
                            new ExerciseComposite2().generateProblem(),
                            new ExerciseComposite2().generateProblem(),
                        }
                    )
            ),
        };
    }
}
