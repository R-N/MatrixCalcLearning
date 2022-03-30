/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.deprecated;

import matrices.tutorial.deprecated.ITutorialSlide;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author MojoMacW7
 */
public class Tutorial{
    private String title;
    private ITutorialSlide[] slides;
    public Tutorial(String title, ITutorialSlide[] slides){
        this.title = title;
        this.slides = slides;
    }

    public String getTitle(){
        return title;
    }
    public ITutorialSlide getSlide(int index){
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
