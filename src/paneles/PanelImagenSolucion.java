//Panel de la interfaz gráfica que muestra la imagen de solución de cada partida.
//autor: Joaquin Esperon

package paneles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PanelImagenSolucion extends JPanel{
    
    //Etiqueta que muestra la imagen de solución.
    private JLabel imagenSolucion;
    //Botón "CONTINUAR" para finalizar la partida.
    private JButton  botonContinuar;
    //Panel que contiene las diferentes visualizaciones de la interfaz principal.
    private final JPanel panelVisualizaciones;
    //Panel de la partida actual para finalizarla y registrarla
    private final PanelPartida panelPartida;
    
    //asigno los paneles y pongo el boton en el sur del panel
    public PanelImagenSolucion(JPanel panelVisu,PanelPartida panelPar){
        super(new BorderLayout());
        panelVisualizaciones = panelVisu;
        panelPartida = panelPar;
        instanciarBoton();
        add(botonContinuar, BorderLayout.SOUTH);
    }
    
    //Establece la imagen de solución en el panel. Redimensiona la imagen al 
    //tamaño del panel y la muestra en la etiqueta imagenSolucion.
    public void setImagen(BufferedImage image) {
        if (imagenSolucion != null) {
            remove(imagenSolucion);
        }
        
        // Redimensionar la imagen al tamaño del panel
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        
        Image img = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
        imagenSolucion = new JLabel(new ImageIcon(img));
        add(imagenSolucion, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void instanciarBoton(){
        botonContinuar = new JButton("CONTINUAR");
        //asignacion fondo negro
        botonContinuar.setBackground(Color.BLACK);
        //asignacion color de trazado negro
        botonContinuar.setForeground(Color.WHITE);
        //asignación del gestor de eventos gestorEventosAccion al botón
        botonContinuar.addActionListener(new GestorEventosBoton());
    }
    
    //si se presiona el boton se pasa al panelStandby finaliza la partida y se registra
    private class GestorEventosBoton implements ActionListener {
        @Override
	public void actionPerformed(ActionEvent evento) {
            CardLayout panel = (CardLayout)(panelVisualizaciones.getLayout());
	    switch (evento.getActionCommand()) {
                case "CONTINUAR" -> {
                    panel.show(panelVisualizaciones, "panelStandby");
                    panelPartida.setFinalizado();
                    panelPartida.registrarPartida();
                }
            } 
        }
   };
}
