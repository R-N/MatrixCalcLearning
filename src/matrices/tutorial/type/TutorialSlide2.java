/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type;

import javax.swing.ImageIcon;
import matrices.math.Matrix;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class TutorialSlide2 implements TutorialResourceProvider{
    String title;
    private Matrix[] matrices;
    private ImageIcon[] images;
    public TutorialSection[] sections;
    
    public static String imagePath = "/matrices/tutorial/resource/";
    public static ImageIcon getImage(String name){
        return new ImageIcon(Tutorial2.class.getResource(imagePath + name));
    }
    public ImageIcon getImage(int id){
        return images[id];
    }
    public Matrix getMatrix(int id){
        return matrices[id];
    }
    public TutorialSlide2(String title, TutorialSection[] sections, TutorialSlide2 child){
        this(title, TutorialSection.join(sections, child.sections));
    }
    public TutorialSlide2(String title, TutorialSection[] sections){
        this(title, null, null, sections);
    }
    public TutorialSlide2(String title, Matrix[] matrices, ImageIcon[] images, TutorialSection[] sections){
        this.matrices = matrices;
        this.images = images;
        this.title = title;
        this.sections = sections;
        setProvider(this);
    }
    
    public void setProvider(TutorialResourceProvider provider){
        for(int i = 0; i < sections.length; ++i){
            sections[i].setProvider(provider);
        }
    }
    
    public String getTitle(){
        return title;
    }
    
    public void clean(){
        for(int i = 0; i < sections.length; ++i){
            sections[i].clean();
        }
    }
}
