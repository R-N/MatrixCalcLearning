/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import matrices.Util;
import matrices.tutorial.type.Option;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;
import matrices.tutorial.tutorials.exercise.MatrixRandom;
import matrices.tutorial.tutorials.exercise.MatrixRandom.Ordo;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseOrdo extends Problem{
     int low = 1;
     int high = 10;
    
    static String problem = "M = A<sub>%dx%d</sub> %s B<sub>%dx%d</sub>";
    static String problem2 = "Ordo M adalah...";
    static String option = "%dx%d";
    final Option unsolveable = new Option("Tidak dapat diselesaikan");
    final Option solveableButNotListed = new Option("Dapat diselesaikan tapi tidak ada di daftar pilihan jawaban");
    
    public TutorialSection[] childs = new TutorialSection[0];
    
    public  Option createOption(int lhs, int rhs){
        return new Option(String.format(option, lhs, rhs));
    }
    public ExerciseOrdo(String title){
        super(title);
    }
    public ExerciseOrdo(){
        super("Operation - Ordo");
        init();
    }
    public ExerciseOrdo(int operator){
        super("Operation - Ordo");
        init(operator);
    }
    public void init(){
        Ordo ordo = MatrixRandom.generateOrdo(low, high);
        init(ordo);
    }
    public void init(int operator){
        Ordo ordo = MatrixRandom.generateOrdo(operator, low, high);
        init(ordo);
    }
    public void init(Ordo ordo){
        String op = "ERROR";
        if(ordo.operator == 0) op = "+";
        else if (ordo.operator == 1) op = "-";
        else if (ordo.operator == 2) op = "*";
        String prob = String.format(problem, ordo.lhsRow, ordo.lhsCol, op, ordo.rhsRow, ordo.rhsCol);
        childs = new TutorialSection[]{
            new TextSection(prob)
        };
        
        init2(ordo);
    }
    
    public void init2(Ordo ordo){
        Option[] options = new Option[6];

        options[0] = unsolveable;
        options[1] = solveableButNotListed;
        
        Option ans;
        int i = 2;
        if(ordo.answer != null){
            if(Util.randInt(0, 4) == 0){
                ans = solveableButNotListed;
            }else{
                ans = new Option(String.format(option, ordo.answer[0], ordo.answer[1]));
                options[i] = ans;
                ++i;
            }
        }else{
            ans = unsolveable;
        }
        int j = 0;
        if(ordo == null){
            System.out.println("ORDO NULL");
        }else if (ordo.entries == null){
            System.out.println("ORDO ENTRIES NULL");
        }
        for(; i < 6; ++i){
            if(ordo.entries[j] == null){
                System.out.println("ordo.entries[" + j + "] NULL");
            }
            options[i] = createOption(ordo.entries[j][0], ordo.entries[j][1]);
            ++j;
        }
        super.init(
                TutorialSection.join(
                        childs,
                        new TutorialSection[]{
                            new TextSection(problem2)
                        }
                ),
                options, 
                ans
        );
    }
    
}
