/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.deprecated;

import javax.swing.ImageIcon;
import matrices.tutorial.deprecated.MatrixTutorial;

/**
 *
 * @author MojoMacW7
 */
public class TutorialSlide implements ITutorialSlide{
    private String title;
    private ImageIcon image;
    private String text;

    public TutorialSlide(String title, ImageIcon image, String text){
        this.title = title;
        this.image = image;
        this.text = text;
    }

    public String getTitle(){
        return title;
    }

    public void load(MatrixTutorial frame){
        frame.loadSlide((TutorialSlide)this);
    }
    
    public String getText(){
        return text;
    }
    
    public ImageIcon getImage(){
        return image;
    }
}
