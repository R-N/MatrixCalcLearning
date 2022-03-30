/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import javax.swing.border.Border;
import matrices.tutorial.type.button.RadioButton;
import matrices.tutorial.type.section.HorizontalLayoutSection;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class Option extends HorizontalLayoutSection{
    RadioButton radio;
    Object content;
    public Option(String content){
        this(new RadioButton(), content);
    }
    public Option(TutorialSection content){
        this(new RadioButton(), content);
    }
    public Option(RadioButton radio, String content){
        super(
                new TutorialSection[]{radio},
                GridBagConstraints.WEST
        );
        this.radio = radio;
        this.content = content;
    }
    public Option(RadioButton radio, TutorialSection content){
        super(
                new TutorialSection[]{
                    radio,
                    content
                },
                GridBagConstraints.WEST
        );
        this.content = content;
        this.radio = radio;
    }
    
    public void init(ButtonGroup group, int index){
        String letter = "";//((char) ('a' + index)) + ". ";
        if(content instanceof String){
            letter = letter+(String)content;
        }
        radio.setText(letter);
        group.add(radio);
    }
    
    public boolean isSelected(){
        return radio.isSelected();
    }
    
    public void setBackground(Color color){
        activePanel.setBackground(color);
    }
    
    public void setBorder(Border border){
        activePanel.setBorder(border);
    }
}
