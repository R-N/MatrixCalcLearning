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
public class CramerButton extends Button{
    Matrix mainMatrix;
    boolean row;
    int num;
    Matrix subMatrix;
    int column;
    public CramerButton(String label, Matrix mainMatrix, boolean row, int num, Matrix subMatrix, int column){
        super(label);
        this.mainMatrix = mainMatrix;
        this.row = row;
        this.num = num;
        this.subMatrix = subMatrix;
        this.column = column;
    }
    public void onClick(){
        frame.main.showCramer(mainMatrix, row, num, subMatrix, column);
    }
}
