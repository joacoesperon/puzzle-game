//Panel de la interfaz gráfica que muestra la partida en curso, la barra de tiempo
// y se encarga de registrar la partida en "resultados.dat"
//autor: Joaquin Esperon,


package paneles;

import java.lang.Math;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import partida.Partida;
import partida.PartidaFileOutAdd;

public class PanelPartida extends JPanel {

    //Panel que muestra las subimágenes del puzzle.
    private PanelSubImagenes panelImagenes;
    //Objeto que representa la partida en curso.
    private Partida partida;
    //Ventana principal de la aplicación.
    private final JFrame ventana;
    //Barra de progreso que muestra el tiempo transcurrido de la partida. 
    private JProgressBar barraTemporal;
    //Temporizador que actualiza la barra de progreso.
    private Timer cronometro;
    //Indica si la partida ha finalizado o no.
    private boolean finalizado;
    //Panel que contiene las diferentes visualizaciones de la interfaz principal.
    private final JPanel panelVisualizaciones;
    
    
    public PanelPartida(JFrame v,JPanel panelVisual) {
        super();
        // ASIGNACIÓN ADMINISTRADOR DE LAYOUT BorderLayout
        this.setLayout(new BorderLayout());
        this.ventana = v;
        //para que habilite el boton NUEVA PARTIDA al comienzo del programa
        this.finalizado = true;
        this.panelVisualizaciones = panelVisual;
    }
    
    //se resetea antes de iniciar una partida
    public void resetear(){
        this.removeAll();
        this.revalidate();
        this.repaint();
        panelImagenes = null;
        partida = null;
        barraTemporal = null;
    }
    
    //Inicia una nueva partida con la imagen proporcionada,divisiónes
    //Crea el panel de subimágenes y establece la partida en curso.
    public void iniciarPartida(BufferedImage image,String nombre, int divVer, int divHor) {
        instanciarBarra(divVer,divHor);
        panelImagenes = new PanelSubImagenes(image,this, divVer, divHor);
        partida = new Partida(nombre, divVer*divHor);
        this.add(panelImagenes,BorderLayout.CENTER);
        this.add(barraTemporal,BorderLayout.SOUTH);
        this.finalizado = false;
    }
    
    public void instanciarBarra(int divVer, int divHor){
        barraTemporal=new JProgressBar();    
        //ASIGNACIÓN VALORES MÍNIMO Y MÁXIMO A LA JProgressBar barraTemporal
        barraTemporal.setMinimum(0);
        barraTemporal.setMaximum(divVer*divHor*2*10); //ejemplo 3*3*2 = 18 seg * 10(porque la frecuencia de actualizacion es cada 100msg 
        //ASIGNACIÓN VALOR INICIAL A LA JProgressBar barraTemporal
        barraTemporal.setValue(0);
        //ACTIVACIÓN VISUALIZACIÓN VALOR EN LA JProgressBar barraTemporal
        barraTemporal.setStringPainted(true);
        //DIMENSIONAMIENTO JProgressBar barraTemporal
        barraTemporal.setPreferredSize(new Dimension(500,40));
        //ASIGNACIÓN COLORES DE FONDO Y TRAZADO JProgressBar barraTemporal
        barraTemporal.setForeground(Color.RED);
        barraTemporal.setBackground(Color.YELLOW);
    }
    
    public void iniciarCronometro() {
        cronometro = new Timer(100, new GestorEventosCronometro()); 
        cronometro.start();
    }
    
    private class GestorEventosCronometro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout panel = (CardLayout)(panelVisualizaciones.getLayout());
            if(e.getSource()==cronometro){
                
                int valor = barraTemporal.getValue();
                //VERIFICACIÓN SI EL VALOR ACTUAL DE LA JProgressBar barraTemporal
                    //HA LLEGADO AL VALOR MÁXIMO ESTIPULADO
                    if(valor<barraTemporal.getMaximum())
                    {
                        //ASIGNAR A JProgressBar barraTemporal SU VALOR INCREMENTADO
                        //EN UNA UNIDAD
                        barraTemporal.setValue(++valor);
                        partida.setPuntosConseguidos( (partida.getPuntosConseguidos()-0.04));
                    }
                    else
                    {
                        //DETENER EL TEMPORIZADOR cronometro
                        cronometro.stop();
                        //asignar 0 putos a la partida
                        partida.setPuntosConseguidos(0);
                        //mensaje
                        mensaje("NO LO HAS CONSEGUIDO - EL TIEMPO HA TERMINADO");
                        //muestro boton
                        panel.show(panelVisualizaciones,"panelImagenSolucion");
                    }
            }
            if (panelImagenes.getCorrecto()) {
                //si los puntos son menor o igual a 0 se asigna 1 como puntuacion minima por ganar
                if((int)partida.getPuntosConseguidos()<=0){
                    partida.setPuntosConseguidos(1);
                }
                //detener el cronometro
                cronometro.stop();
                mensaje("¡¡¡ ENHORABUENA !!! LO HAS CONSEGUIDO \nHAS OBTENIDO " + (int)partida.getPuntosConseguidos() + " PUNTOS");
                //muestro boton
                panel.show(panelVisualizaciones,"panelImagenSolucion");
            }
        }
    }
    
    public void mensaje(String msj) {
        JOptionPane.showMessageDialog(ventana, msj);
    }
    
    public void setFinalizado(){
        finalizado = true;
    }
    
    public boolean getFinalizado(){
        return finalizado;
    }
    
    //escribe la partida en "resultados.dat"
    public void registrarPartida(){
        PartidaFileOutAdd partidaFileOutAdd = new PartidaFileOutAdd("resultados.dat");
        partidaFileOutAdd.escritura(partida);
        partidaFileOutAdd.cierre();
    }
}