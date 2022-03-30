/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.section;
import java.awt.Dimension;
import matrices.tutorial.form.MatrixTutorial2;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.TutorialSlide2;
/**
 *
 * @author MojoMacW7
 */
public interface TutorialSection {
    public void onResize(Dimension size);
    public void addTo(MatrixTutorial2 frame);
    public void addTo(MatrixTutorial2 frame, JPanel panel);
    public void init(Dimension size);
    public GridBagConstraints getConstraints();
    
    public Dimension getPreferredSize();
    public Dimension getMinimumSize();
    public Dimension getSize();
    public void revalidate();
    public void validate();
    
    public void clean();
    
    public void setProvider(TutorialResourceProvider provider);
    
    public static TutorialSection[] join(TutorialSection[] a, TutorialSection[] b){
        TutorialSection[] ret = new TutorialSection[a.length+b.length];
        
        for(int i = 0; i < a.length; ++i){
            ret[i] = a[i];
        }
        int j = a.length-1;
        for(int i = 0; i < b.length; ++i){
            ret[++j] = b[i];
        }
        return ret;
    }
    public static TutorialSection[] join(TutorialSection[] a, TutorialSection[] b, TutorialSection c){
        TutorialSection[] ret = new TutorialSection[a.length+b.length+1];
        
        for(int i = 0; i < a.length; ++i){
            ret[i] = a[i];
        }
        int j = a.length-1;
        for(int i = 0; i < b.length; ++i){
            ret[++j] = b[i];
        }
        ret[ret.length-1] = c;
        return ret;
    }
}
