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
public class FormButton extends Button{
    Matrix matrix;
    int form;
    public FormButton(String label, Matrix matrix, int form){
        super(label);
        this.matrix = matrix;
        this.form = form;
    }
    public void onClick(){
        frame.main.showForm(matrix, form);
    }
}
