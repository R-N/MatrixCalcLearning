/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.main.type;
import java.beans.Transient;
import javax.swing.JComboBox;

/**
 *
 * @author MojoMacW7
 */
public class ComboBox<E> extends JComboBox<E>{
    int selectedIndex = 0;
    public ComboBox(){
        super();
        setDoubleBuffered(true);
    }
    
    @Override
    public void setModel(javax.swing.ComboBoxModel<E> model){
        super.setModel(model);
        selectedIndex = 0;
    }
    
    @Override
    public void setSelectedIndex(int anIndex) {
        int size = dataModel.getSize();

        selectedIndex = anIndex;
        if ( anIndex == -1 ) {
            dSetSelectedItem();
        } else if ( anIndex < -1 || anIndex >= size ) {
            throw new IllegalArgumentException("setSelectedIndex: " + anIndex + " out of bounds");
        } else {
            dSetSelectedItem();
        }
        selectedIndex = anIndex;
    }
    
    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    @Override
    public void setSelectedItem(Object anObject){
        super.setSelectedItem(anObject);
        selectedIndex = super.getSelectedIndex();
    }

    public void dSetSelectedItem() {
        Object oldSelection = selectedItemReminder;
        Object objectToSelect = dataModel.getElementAt(selectedIndex);
        if (oldSelection == null || !oldSelection.equals(objectToSelect)) {

            // Must toggle the state of this flag since this method
            // call may result in ListDataEvents being fired.
            //selectingItem = true;
            dataModel.setSelectedItem(objectToSelect);
            //selectingItem = false;

            if (selectedItemReminder != dataModel.getSelectedItem()) {
                // in case a users implementation of ComboBoxModel
                // doesn't fire a ListDataEvent when the selection
                // changes.
                selectedItemChanged();
            }
        }
        fireActionEvent();
    }
    
}
