/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.button;

import matrices.math.Matrix;
import matrices.tutorial.type.button.Button;

/**
 *
 * @author MojoMacW7
 */
public class AddMatrixButton extends Button{
    
    Matrix matrix;
    
    public AddMatrixButton(){
        super(null);
    }
    
    public AddMatrixButton(String label, Matrix matrix){
        super(label);
        this.matrix = matrix;
    }
    public void setMatrix(Matrix matrix){
        this.matrix = matrix;
    }
    public void onClick(){
        frame.main.addMatrixIfNotExists(matrix);
        frame.main.toFront();
    }
}
