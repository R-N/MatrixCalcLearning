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
public class OBEButton extends Button{
    Matrix[] matrices;
    int mainMatrix;
    public OBEButton(String label, Matrix[] matrices, int mainMatrix){
        super(label);
        this.matrices = matrices;
        this.mainMatrix = mainMatrix;
    }
    public void onClick(){
        frame.main.showOBE(matrices, mainMatrix);
    }
}
