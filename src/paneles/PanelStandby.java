//Panel de la interfaz gráfica que muestra el logo de la UIB.  
//autor: Joaquin Esperon
package paneles;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelStandby extends JPanel{
    
    public PanelStandby(){
        super();
        //ASIGNACIÓN ADMINISTRADOR DE LAYOUT BorderLayout
        this.setLayout(new BorderLayout());
        
        //////////DECLARACIÓN COMPONENTE JLabel imagenUIB
        JLabel imagen=new JLabel(new ImageIcon(new ImageIcon("iconos\\UIB.jpg").getImage().getScaledInstance(520, 420, Image.SCALE_SMOOTH)));
        //INTRODUCCIÓN imagenUIB EN interface1 EN LA ZONA CENTRAL
        this.add(imagen,BorderLayout.CENTER);  
    }
}
