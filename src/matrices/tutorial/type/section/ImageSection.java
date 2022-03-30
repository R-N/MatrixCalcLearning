/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.section;

import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import matrices.Util;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.ImageLabel;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class ImageSection implements TutorialSection{

    public static class ImageSectionLabel extends ImageLabel{
        public ImageSectionLabel(ImageIcon image, double widthMul){
            super();
            setDoubleBuffered(true);
            this.image = image;
            this.widthMul = widthMul;
            setIcon(image);
        }
        public double widthMul;
        public ImageIcon image;
        public void clean(){
            image.getImage().flush();
            image = null;
        }
        
        @Override
        public void setWidth(int width){
            width = Util.max((int)(width*widthMul), (int)(image.getIconWidth() * 0.5));
            super.setWidth(width);
            //revalidate();
        }
    }
    
    static String resourcePath = "/matrices/tutorial/resource/";
    GridBagConstraints gridBagConstraints;
    double widthMul = 1;
    
    ImageSectionLabel activeLabel = null;
    String imageName;
    
    public ImageSection(String name){
        this(name, 100.0);
    }
    public ImageSection(String name, double widthPercentage){
        super();
        this.imageName = name;
        this.widthMul = widthPercentage*0.01;
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        //gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
    }
    public void onResize(Dimension size){
        activeLabel.setWidth(size.width);
    }
    public void addTo(MatrixTutorial2 frame){
        activeLabel = new ImageSectionLabel(Tutorial2.getImage(imageName), widthMul);
        frame.addSection(activeLabel, gridBagConstraints);
        frame.addSection(this);
    }
    public void addTo(MatrixTutorial2 frame, JPanel panel){
        activeLabel = new ImageSectionLabel(Tutorial2.getImage(imageName), widthMul);
        panel.add(activeLabel, gridBagConstraints);
    }
    
    public void init(Dimension size){
        onResize(size);
    }
    public GridBagConstraints getConstraints(){
        return gridBagConstraints;
    }
    public void setProvider(TutorialResourceProvider provider){
    }
    @Override
    public Dimension getPreferredSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getMinimumSize() {
        return activeLabel.getMinimumSize();
    }

    @Override
    public Dimension getSize() {
        return activeLabel.getSize();
    }

    @Override
    public void revalidate() {
         activeLabel.revalidate();
    }

    @Override
    public void validate() {
         activeLabel.validate();
    }

    @Override
    public void clean() {
        activeLabel.clean();
        activeLabel = null;
    }
}
