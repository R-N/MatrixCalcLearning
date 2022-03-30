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
public class OperationButton extends Button{
    public double lhsCoef;
    public Matrix lhsMatrix;
    public int operator;
    public double rhsCoef;
    public Matrix rhsMatrix;
    public OperationButton(String label){
        super(label);
    }
    public OperationButton(String label, double lhsCoef, Matrix lhsMatrix, int operator, double rhsCoef, Matrix rhsMatrix){
        super(label);
        this.lhsCoef = lhsCoef;
        this.lhsMatrix = lhsMatrix;
        this.operator = operator;
        this.rhsCoef = rhsCoef;
        this.rhsMatrix = rhsMatrix;
    }
    public void onClick(){
        frame.main.showOperation(lhsCoef, lhsMatrix, operator, rhsCoef, rhsMatrix);
    }
}
