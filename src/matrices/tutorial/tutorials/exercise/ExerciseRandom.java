/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials.exercise;

import java.util.Arrays;
import java.util.HashSet;
import matrices.Util;
import static matrices.tutorial.tutorials.exercise.ExerciseMatrix.problemFormat;
import matrices.tutorial.type.Option;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ExerciseRandom extends Problem{
    
    public ExerciseRandom(String title){
        super(title);
    }
    
    TutorialSection[] childs = new TutorialSection[0];
    final Option unsolveable = new Option("Tidak dapat diselesaikan");
     final Option solveableButNotListed = new Option("Dapat diselesaikan tapi tidak ada di daftar pilihan jawaban");
    public void init(String prob, HashSet<Double> randoms, Double answer){
        
        int i = 1;
        
        while(randoms.size() < 4){
            HashSet<Double> randoms2 = new HashSet<Double>(randoms);
            for(Double d : randoms){
                randoms2.add(d + i);
            }
            for(Double d : randoms){
                randoms2.add(d - i);
            }
            for(Double d : randoms){
                randoms2.add(d * (i+1));
            }
            for(Double d : randoms){
                randoms2.add(d / (i+1));
            }
            randoms = randoms2;
            ++i;
        }
        
        Option[] options = new Option[6];
        
        Option ans;
        
        i = 2;
        options[0] = unsolveable;
        options[1] = solveableButNotListed;
        if(answer != null){
            randoms.remove(answer);
            if(Util.randInt(0, 4) == 0){
                ans = solveableButNotListed;
            }else{
                ans = new Option(Util.toString(answer));
                options[2] = ans;
                i=3;
            }
        }else{
            ans = unsolveable;
        }
        int size = randoms.size();
        int[] items = new int[]{0,1,2,3};
        if(size > 6){
            int size1 = size-1;
            items[0] = Util.randInt(0, size1);
            items[1] = Util.randInt(0, size1, items[0]);
            items[2] = Util.randInt(0, size1, new int[]{items[0], items[1]});
            items[3] = Util.randInt(0, size1, new int[]{items[0], items[1], items[2]});
        }
        int j = 0;
        outerLoop:
        while(i < 6){
            for(Double d : randoms){
                for(int k = 0; k < items.length; ++k){
                    if(j == items[k]){
                        options[i++] = new Option(Util.toString(d));
                        if(i >= 6) break outerLoop;
                        break;
                    }
                }
                ++j;
            }
        }
        
        super.init(
                TutorialSection.join(childs, new TutorialSection[]{new TextSection(prob)}), 
                options, 
                ans
        );
    }
}
