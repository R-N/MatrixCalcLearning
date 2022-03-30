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
public class HTMLTutorialSlide implements ITutorialSlide{
    public static final int ALIGNMENT_LEFT = 0;
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_RIGHT = 2;
    public static final int ALIGNMENT_JUSTIFY = 3;
    public static final String[] ALIGNMENTS = {"left", "center", "right", "justify"};
    public static final int COMPONENTTYPE_TEXT = 0;
    public static final int COMPONENTTYPE_IMAGE = 1;
    String title;
    public static class Component{
        String content;
        int alignment;
        int type;
        
        public Component(int type, String content, int alignment){
            this.type = type;
            this.content = content;
            this.alignment = alignment;
        }
        
        public String getHTML(){
            String ret = String.format(
                    "<div align=\"%s\">%s</div>",
                    ALIGNMENTS[alignment],
                    content
            );
            return ret;
        }
    }
    public static Component fromText(String text){
        return fromText(text, ALIGNMENT_LEFT);
    }
    public static Component fromText(String text, int alignment){
        return fromText(text, "verdana", 8, "black", alignment);
    }
    public static Component fromText(String text, String font, int size, String color, int alignment){
        return new Component(
                COMPONENTTYPE_TEXT,
                String.format(
                        "<font font=\"%s\" size=\"%d\" color=\"%s\">%s</font>",
                        font,
                        size,
                        color,
                        text.replace("\n", "<br>")
                ),
                alignment
        );
    }
    public static Component fromImage(String path){
        return fromImage(path, ALIGNMENT_CENTER);
    }
    public static Component fromImage(String path, int alignment){
        String ret = String.format(
                "<img src=\"%s\" />", 
                HTMLTutorialSlide.class.getResource(path).toString()
        );
        return new Component(
                COMPONENTTYPE_IMAGE,
                ret,
                alignment
        );
                
    }
    public Component[] components;
    public HTMLTutorialSlide(String title, Component[] components){
        this.title = title;
        this.components = components;
    }
    public String getTitle(){
        return title;
    }
    public String getHTML(){
        String ret= "<!DOCTYPE html><html>\n<head>\n<style>\nimg {\nmax-width:100%;\n}\n</style></head><body>";
        for(int i = 0; i < components.length; ++i){
            ret = ret + components[i].getHTML();
        }
        ret = ret + "</body></html>";
        return ret;
    }
    public void load(MatrixTutorial frame){
        frame.loadSlide((HTMLTutorialSlide)this);
    }
}
