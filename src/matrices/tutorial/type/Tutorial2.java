/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type;

import matrices.tutorial.type.TutorialSlide2;
import matrices.math.Matrix;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 *
 * @author MojoMacW7
 */
public class Tutorial2 implements TutorialResourceProvider{
    private String title;
    private Matrix[] matrices;
    private ImageIcon[] images;
    private TutorialSlide2[] slides;
    
    public static String imagePath = "/matrices/tutorial/resource/";
    public static ImageIcon getImage(String name){
        try{
            ImageIcon ret = new ImageIcon(Tutorial2.class.getResource(imagePath + name));
            ret.getImage();
            return ret;
        }catch (Exception ex){
            System.out.println("IMAGE NOT FOUND : " + name);
            throw ex;
        }
    }
    public ImageIcon getImage(int id){
        return images[id];
    }
    public Matrix getMatrix(int id){
        return matrices[id];
    }
    
    public Tutorial2(String title, TutorialSlide2[] slides){
        this(title, null, null, slides);
    }
    public Tutorial2(String title, Matrix[] matrices, ImageIcon[] images, TutorialSlide2[] slides){
        this.matrices = matrices;
        this.images = images;
        this.title = title;
        this.slides = slides;
        for(int i = 0; i < slides.length; ++i){
            slides[i].setProvider(this);
        }
    }
    public void clean(){
        for(int i = 0; i < slides.length; ++i){
            slides[i].clean();
        }
    }

    public String getTitle(){
        return title;
    }
    public TutorialSlide2 getSlide(int index){
        return slides[index];
    }
    public int getSlideCount(){
        return slides.length;
    }

    public String[] getSlideTitles(){
        String[] ret = new String[slides.length];
        for(int i = 0; i < slides.length; ++i){
            ret[i] = String.format("%d. %s", i+1, slides[i].getTitle());
        }
        return ret;
    }

    public DefaultComboBoxModel getComboBoxModel(){
        return new DefaultComboBoxModel(getSlideTitles());
    }
}
