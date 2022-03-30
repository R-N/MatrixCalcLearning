/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.button;
import matrices.tutorial.type.button.Button;
import matrices.math.Matrix;
import matrices.tutorial.tutorials.exercise.Problem;

/**
 *
 * @author MojoMacW7
 */
public class ProblemCheckButton extends Button{
    Problem parent;
    public ProblemCheckButton(String label, Problem parent){
        super(label);
        this.parent = parent;
    }
    public void onClick(){
        parent.check();
    }
}
