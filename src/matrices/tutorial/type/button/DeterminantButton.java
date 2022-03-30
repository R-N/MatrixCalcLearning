/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.button;
import matrices.tutorial.type.button.Button;
import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public class DeterminantButton extends Button{
    Matrix matrix;
    boolean row;
    int num;
    public DeterminantButton(String label, Matrix matrix, boolean row, int num){
        super(label);
        this.matrix = matrix;
        this.row = row;
        this.num = num;
    }
    public void onClick(){
        frame.main.showDeterminant(matrix, row, num);
    }
}
