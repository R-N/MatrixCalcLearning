/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.type.section;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import matrices.tutorial.form.MatrixTutorial2;
import matrices.tutorial.type.TutorialResourceProvider;
/**
 *
 * @author MojoMacW7
 */
public class HorizontalLayoutSection implements TutorialSection{
    TutorialSection[] childs = null;
    protected JPanel activePanel = null;
    double sumWeight = 0;
    GridBagConstraints gridBagConstraints = null;
    protected HorizontalLayoutSection(){
    }
    public HorizontalLayoutSection(TutorialSection[] childs){
        this(childs, java.awt.GridBagConstraints.NORTH);
    }
    public HorizontalLayoutSection(TutorialSection[] childs, int anchor){
        init(childs, anchor);
    }
    
    public JPanel createPanel(){
        activePanel = new JPanel();
        activePanel.setDoubleBuffered(true);
        activePanel.setLayout(new java.awt.GridBagLayout());
        return activePanel;
    }
    
    public void init(TutorialSection[] childs, int anchor){
        this.childs = childs;
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        //gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        //gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        sumWeight = 0;
        for(int i = 0; i < childs.length; ++i){
            GridBagConstraints c = childs[i].getConstraints();
            c.fill = java.awt.GridBagConstraints.NONE;
            c.gridx = i;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            
            if(i > 0) c.insets.left = c.insets.left+10;
            
            if(childs[i] instanceof TextSection) sumWeight += c.weightx;
        }
    }
    
    public GridBagConstraints getConstraints(){
        return gridBagConstraints;
    }
    
    public void init(Dimension size){
        for(int i = 0; i < childs.length; ++i){
            if(childs[i] instanceof TextSection){
                TextSection t = (TextSection)childs[i];
                t.revalidate();
                int prefw = (int)(t.getPreferredSize().width / t.widthMul);
                if(prefw > 0){
                    GridBagConstraints c = t.getConstraints();
                    java.awt.Insets in = c.insets;
                    int maxw = size.width;
                    int minw = (int)(t.getMinimumSize().width / t.widthMul);

                    int w = prefw;
                    if(w < minw) w = minw;
                    if(w > maxw) w = maxw;
                    childs[i].init(new Dimension(w, size.height));
                }
            }else{
                childs[i].init(size);
            }
        }
    }
    
    public void onResize(Dimension size){
        if(sumWeight > 0){
            for(int i = 0; i < childs.length; ++i){
                if(childs[i] instanceof TextSection){
                    TextSection t = (TextSection)childs[i];
                    t.revalidate();
                    int prefw = (int)(t.getPreferredSize().width / t.widthMul);
                    if(prefw > 0){
                        GridBagConstraints c = t.getConstraints();
                        java.awt.Insets in = c.insets;
                        int maxw = size.width;
                        int minw = (int)(t.getMinimumSize().width / t.widthMul);

                        int w = prefw;
                        if(w < minw) w = minw;
                        if(w > maxw) w = maxw;
                        childs[i].onResize(new Dimension(w, size.height));
                    }
                }else{
                    childs[i].onResize(size);
                }
            }
        }
    }
    
    public void addTo(MatrixTutorial2 frame, JPanel panel){
        JPanel p = createPanel();
        for(int i = 0; i < childs.length; ++i){
            childs[i].addTo(frame, p);
        }
        panel.add(p, gridBagConstraints);
    }
    
    public void addTo(MatrixTutorial2 frame){
        JPanel p = createPanel();
        for(int i = 0; i < childs.length; ++i){
            childs[i].addTo(frame, p);
        }
        frame.addSection(p, gridBagConstraints);
        frame.addSection(this);
        
    }
    public void setProvider(TutorialResourceProvider provider){
        for(int i = 0; i < childs.length; ++i){
            childs[i].setProvider(provider);
        }
    }
    @Override
    public void clean(){
        activePanel.removeAll();
        activePanel = null;
        for(int i = 0; i < childs.length; ++i){
            childs[i].clean();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return activePanel.getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return activePanel.getMinimumSize();
    }

    @Override
    public Dimension getSize() {
        return activePanel.getSize();
    }

    @Override
    public void revalidate() {
        activePanel.revalidate();
    }

    @Override
    public void validate() {
        activePanel.validate();
    }
}
