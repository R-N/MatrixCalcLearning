/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import matrices.main.form.MainFrame;
import matrices.main.form.MatrixOBE;
import matrices.tutorial.tutorials.*;
import matrices.math.*;
import matrices.tutorial.tutorials.exercise.MatrixRandom;
import matrices.tutorial.tutorials.exercise.ExerciseComposite;
import static matrices.tutorial.tutorials.exercise.ExerciseComposite.INVERSE;
import static matrices.tutorial.tutorials.exercise.ExerciseComposite.MULTIPLY;
import static matrices.tutorial.tutorials.exercise.ExerciseComposite.MULTIPLY_SQUARE;
import static matrices.tutorial.tutorials.exercise.ExerciseComposite.SUBTRACT;
import static matrices.tutorial.tutorials.exercise.ExerciseComposite.TRANSPOSE;
import matrices.tutorial.type.Tutorial2;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void test(){
        Matrix m = MatrixRandom.generateInvertibleMatrix(1, 2);
        ExerciseComposite ec = new ExerciseComposite();
        Matrix ret;
        
        ret = (Matrix)(ec.generateAdd(m).getValue());
        if(!m.equals(ret)){
            System.out.println("ADDITION INEQUAL");
        }
        
        ret = (Matrix)(ec.generateSubtract(m).getValue());
        if(!m.equals(ret)){
            System.out.println("SUBTRACTION INEQUAL");
        }
        ret = (Matrix)(ec.generateMultiplyLeft(m).getValue());
        if(!m.equals(ret)){
            System.out.println("MULTIPLY LEFT INEQUAL");
        }
        
        ret = (Matrix)(ec.generateMultiplyRight(m).getValue());
        if(!m.equals(ret)){
            System.out.println("MULTIPLY RIGHT INEQUAL");
        }
        ret = (Matrix)(ec.generateTranspose(m).getValue());
        if(!m.equals(ret)){
            System.out.println("TRANSPOSE INEQUAL");
        }
        
        ret = (Matrix)(ec.generateInverse(m).getValue());
        if(!m.equals(ret)){
            System.out.println("INVERSE INEQUAL");
        }
        
        ret = (Matrix)(ec.generateMultiplySquare(m).getValue());
        if(!m.equals(ret)){
            System.out.println("MULTIPLY SQUARE INEQUAL");
        }
        
    }
    public static boolean test2(){
        Matrix m = MatrixRandom.generateInvertibleMatrix(1, 3);
        if(!m.getInverseMatrix().getInverseMatrix().equals(m)){
            return true;
        }
        return false;
    }
    public static boolean test3(){
        Matrix m = MatrixRandom.generateInvertibleMatrix(1, 3);
        if(!Matrix.getIdentityMatrix(3).equals(Matrix.multiply(m, m.getInverseMatrix()))){
            return true;
        }
        return false;
    }
    public static boolean test4(){
        Matrix m = MatrixRandom.generateMatrix("M", 4, 3);
        if(!m.equals(m.clone())){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Util.setLookAndFeel();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Guide.init();
                MainFrame mf = new MainFrame();
                mf.setLocationRelativeTo(null);
                mf.setVisible(true);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Tutorial2[] t = Lesson.tutorials;
                    }
                });
                if(Util.checkFirstRun()){
                    mf.showGuide(0,0);
                }else{
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            Tutorial2[] t = Guide.tutorials;
                        }
                    });
                }
            }
        });
    }
    
}
