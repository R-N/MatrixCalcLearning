/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author MojoMacW7
 */
public class Util {
    public static double parseDouble(Double d){
        return parseDouble(d, 0);
    }
    public static double parseDouble(Double d, int defaultValue){
        if(d == null){
            return defaultValue;
        }else{
            return d;
        }
    }
    public static double parseDouble(String s, int defaultValue) throws NumberFormatException{
        if (s == null){
            return defaultValue;
        }
        s = s.trim();
        if(s.isEmpty()){
            return defaultValue;
        }
        return Double.parseDouble(s);
    }
    public static double parseDouble(String s) throws NumberFormatException{
        return parseDouble(s, 0);
    }
    public static double parseCoefficient(String s) throws NumberFormatException{
        if (s == null){
            return 1;
        }
        s = s.trim();
        if(s.isEmpty()){
            return 1;
        }
        if(s.equals("-")){
            return -1;
        }
        return Double.parseDouble(s);
    }
    public static double parseInt(String s, int defaultValue) throws NumberFormatException{
        if (s == null){
            return defaultValue;
        }
        s = s.trim();
        if(s.isEmpty()){
            return defaultValue;
        }
        return Integer.parseInt(s);
    }
    public static double parseInt(String s) throws NumberFormatException{
        return parseInt(s, 0);
    }
    
    public static Dimension max(Dimension a, Dimension b){
        return new Dimension(
                Util.max(a.width, b.width),
                Util.max(a.height, b.height)
        );
    }
                
    
    public static int max(int a, int b){
        return (a > b) ? a : b;
    }
    
    public static int min(int a, int b){
        return (a < b) ? a : b;
    }
    
    public static void showError(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccess(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showMessage(Component parent, String title, String message){
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
    }
    
    public static boolean askConfirmation(Component parent, String question){
        return JOptionPane.showConfirmDialog(parent, question, "Are you sure?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
    }
    
    public static String toStringCoef(double x){
        if(x == 1){
            return "";
        }else{
            return toString(x);
        }
    }
    
    public static String toString(double x){
        String s = String.format("%.2f", Math.round(x*100)*0.01);
        int last = s.length()-1;
        for(int i = last; i >= 0;--i){
            char c = s.charAt(i);
            if(c == '0'){
                continue;
            }else{
                if(c == '.' || c == ','){
                    s = s.substring(0, i);
                }else{
                    s = s.substring(0, i+1);
                }
                break;
            }
        }
        return s;
    }
    static Random rand = new Random();
    public static int indexOf(int[] arr, int e){
        for(int i = 0; i < arr.length; ++i){
            if(arr[i] == e) return i;
        }
        return -1;
    }
    public static int indexOf(int[][] arr, int[] e){
        outerLoop:
        for(int i = 0; i < arr.length; ++i){
            int[] ei = arr[i];
            if(ei == e){
                return i;
            }else if (ei == null || e == null){
                continue;
            }else if (ei.length != e.length){
                continue;
            }else{
                for(int j = 0; j < e.length; ++j){
                    if (ei[j] != e[j]) continue outerLoop;
                }
                return i;
            }
        }
        return -1;
    }
    public static int randInt(int min, int max){
        if(min == max) return min;
        int randomNum = (rand.nextInt(max-min+1)) + min;
        return randomNum;
    }
    public static int rotaryClamp(int x, int min, int max){
        int delta = max-min;
        while(x < min) x += delta;
        while(x > max) x-= delta;
        return x;
    }
    public static int randInt(int min, int max, int not){
        int randomNum = randInt(min, max);
        
        if(randomNum == not){
            randomNum = rotaryClamp(randomNum + randInt(1, max-min-1), min, max);
        }
        
        return randomNum;
    }
    public static int randInt(int min, int max, int[] not){
        List<Integer> pool = new ArrayList<Integer>(max-min+1);
        outerLoop:
        for(int i = min; i <= max; ++i){
            for(int j = 0; j < not.length; ++j){
                if (i == not[j]) continue outerLoop;
            }
            pool.add(i);
        }
        return pool.get(randInt(0, pool.size()-1));
    }
    //https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array/1520212#1520212
    public static <T> T[] shuffle (T[] ar) {
        for (int i = ar.length - 1; i > 0; i--){
            int index = rand.nextInt(i + 1);
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    
    public static String indexToLetterUpper(int index){
        return new String(
                new char[]{
                    (char)('A' + index)
                }
        );
    }
    public static String indexToLetterLower(int index){
        return new String(
                new char[]{
                    (char)('a' + index)
                }
        );
    }
    public static String indexToLetterXLower(int index){
        return new String(
                new char[]{
                    (char)('x' + index)
                }
        );
    }
    public static <T> void extend(List<T> list, T[] arr){
        for(int i = 0; i < arr.length; ++i){
            list.add(arr[i]);
        }
    }
    public static <T> ArrayList<T> toArrayList(T[] arr){
        ArrayList<T> ret = new ArrayList<T>(arr.length);
        extend(ret, arr);
        return ret;
    }
    public static <T> LinkedList<T> toLinkedList(T[] arr){
        LinkedList<T> ret = new LinkedList<T>();
        extend(ret, arr);
        return ret;
    }
    public static double[][] toPrimitive(Double[][] data){
        double[][] data1 = new double[data.length][];
        for(int i = 0; i < data.length; ++i){
            if(data[i] == null) continue;
            data1[i] = new double[data[i].length];
            for(int j = 0; j < data[i].length; ++j){
                data1[i][j] = data[i][j];
            }
        }
        return data1;
    }
    public static <T> T getRandom(HashSet<T> col){
        int j = Util.randInt(0, col.size()-1);
        int i =0;
        for(T t : col){
            if(i == j){
                return t;
            }
            ++i;
        }
        return null;
    }
    
    public static boolean checkFirstRun(){
        File file = new File("firstrun");
        boolean firstRun = false;
        if(!file.exists()){
            firstRun = true;
            try{
                file.createNewFile();
            }catch(IOException ex){
            }
        }
        return firstRun;
    }
    
    public static boolean setLookAndFeel(){
        if(setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")
                || setLookAndFeel(UIManager.getSystemLookAndFeelClassName())){
            return true;
        }
        return false;
    }
    
    public static boolean setLookAndFeel(String laf){
        try{
            UIManager.setLookAndFeel(laf);
            return true;
        }catch(ClassNotFoundException ex){
        }catch(InstantiationException ex){
        }catch(IllegalAccessException ex){
        }catch(UnsupportedLookAndFeelException ex){
        }
        return false;
    }
    
}
