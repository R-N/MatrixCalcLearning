/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import matrices.Util;
import matrices.tutorial.type.Option;
import matrices.tutorial.type.TutorialSlide2;
import matrices.tutorial.type.button.ProblemCheckButton;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class Problem extends TutorialSlide2{
    ButtonGroup buttonGroup = new ButtonGroup();
    Option[] options;
    Option answer = null;
    TutorialSection[] childs;
    ProblemCheckButton button = new ProblemCheckButton("Check", this);
    public Problem(String title){
        super(title, new TutorialSection[0]);
    }
    public void init(TutorialSection[] childs, Option[] options, Option answer){
        this.childs = childs;
        for(int i = 0; i < options.length; ++i){
            options[i].init(buttonGroup, i);
        }
        this.options = options;
        this.answer = answer;
        
        shuffleOptions();
    }
    
    public void shuffleOptions(){
        Util.shuffle(options);
        this.sections = TutorialSection.join(childs, options, button);
    }
    
    public boolean isCorrect(){
        return answer.isSelected();
    }
    
    public boolean check(){
        boolean selected = false;
        for(int i = 0; i < options.length; ++i){
            if(options[i].isSelected()){
                selected = true;
                break;
            }
        }
        if(!selected && !Util.askConfirmation(null, "You have not chosen any answer. Continue anyway?")){
            return false;
        }
        for(int i = 0; i < options.length; ++i){
            if(options[i] == answer){
                if(options[i].isSelected()){
                    options[i].setBackground(Color.GREEN);
                }else{
                    options[i].setBackground(null);
                }
                options[i].setBorder(new LineBorder(Color.GREEN));
            }else if(options[i].isSelected()){
                options[i].setBackground(Color.RED);
                //options[i].setBorder(new LineBorder(Color.RED));
            }else{
                options[i].setBackground(null);
                options[i].setBorder(null);
            }
        }
        button.setEnabled(false);
        return true;
    }
    
}
