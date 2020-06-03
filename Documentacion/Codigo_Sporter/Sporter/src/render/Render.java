// Autor; Jose Luis Gonzalez Ramirez

package render;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
// Clase que permite incluir botones en una table dinamicamente.

public class Render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
    	
    	setHorizontalAlignment(SwingConstants.CENTER); // Daniel : para centrar valores de las celdas
        
        if(value instanceof JButton){
            JButton btn = (JButton)value; 
            if(isSelected){
                btn.setForeground(table.getSelectionForeground());
      btn.setBackground(table.getSelectionBackground());
            }else{
                btn.setForeground(table.getForeground());
      btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }
        
        
        if(value instanceof JCheckBox){
            JCheckBox ch = (JCheckBox)value;
            return ch;
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); 
    }

    
}

