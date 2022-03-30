/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Wolf
 https://stackoverflow.com/users/834010/wolf
 https://stackoverflow.com/questions/10634417/imageIcon-resize-to-fit-on-jpanel
 */
public class ImageLabel extends JLabel{
    private ImageIcon imageIcon;
    
    public static final int FIT_BOTH = 0;
    public static final int FIT_WIDTH = 1;

    private int mode = FIT_BOTH;
    public ImageLabel(){
        this(FIT_BOTH);
    }
    
    public ImageLabel(int mode){
        super("");
        setDoubleBuffered(true);
        setMode(mode);
    }
    
    public void setMode(int mode){
        this.mode = mode;
    }

    @Override
    public void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon instanceof ImageIcon)
        {
            imageIcon = ((ImageIcon) icon);
            fitWidth();
        }
    }
    
    public void setWidth(int width){
        int h = (int)(((double)width)/imageIcon.getIconWidth() * imageIcon.getIconHeight());
        Dimension size = new Dimension(width, h);
        //setMaximumSize(size);
        setPreferredSize(size);
        setSize(size);
    }
    
    public void fitWidth(){
        setWidth(getWidth());
    }

    @Override
    public void paint(Graphics g){
        switch(mode){
            case FIT_BOTH:{
                g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                break;
            }
            case FIT_WIDTH:{
                int w = getWidth();
                int h = w/imageIcon.getIconWidth() * imageIcon.getIconHeight();
                setSize(w, h);
                g.drawImage(imageIcon.getImage(), 0, 0, w, h, null);
                break;
            }
        }
    }
    
}
