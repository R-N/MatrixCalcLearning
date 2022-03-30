/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.math;

/**
 *
 * @author MojoMacW7
 */
public abstract class MathType {
    public abstract String toString(boolean bracket);
    public String toString(){
        return toString(false);
    }
}
