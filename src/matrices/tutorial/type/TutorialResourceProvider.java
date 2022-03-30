/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type;

import javax.swing.ImageIcon;
import matrices.math.Matrix;

/**
 *
 * @author MojoMacW7
 */
public interface TutorialResourceProvider {
    public static final int PROVIDER_TUTORIAL = 0;
    public static final int PROVIDER_SLIDE = 1;
    public Matrix getMatrix(int id);
    public ImageIcon getImage(int id);
}
