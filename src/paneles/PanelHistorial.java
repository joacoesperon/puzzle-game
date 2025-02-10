//Esta clase se encarga de mostrar el historial de partidas en un panel de la 
//interfaz gráfica
//autor: Joaquin Esperon

package paneles;

import java.awt.*;
import javax.swing.*;
import partida.*;

public class PanelHistorial extends JPanel {

    private JTextArea areaVisualizacionResultados;

    // Constructor donde creamos el JTextArea y lo añadimos al panel dentro de un JScrollPane
    public PanelHistorial() {
        super(new BorderLayout());
        areaVisualizacionResultados = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaVisualizacionResultados);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }
   
    //Lee los datos de las partidas desde el archivo "resultados.dat"
    //y los muestra en areaVisualizacionResultados
    public void mostrarListadoPartidas() {
        areaVisualizacionResultados.setText("                               HISTORIAL       \n");
    
        PartidaFileIn partidaFileIn = new PartidaFileIn("resultados.dat");
        Partida partida = partidaFileIn.lectura();
        
        while (partida != null) {
            areaVisualizacionResultados.append(partida + "\n");
            partida = partidaFileIn.lectura();
        }
    
        partidaFileIn.close();
    }
    
    //Lee los datos de las partidas desde el archivo "resultados.dat"
    //y muestra los del jugador buscado en areaVisualizacionResultados
    public void mostrarPartidasPorJugador(String nombreJugador) {
        areaVisualizacionResultados.setText("                               HISTORIAL       \n");
    
        PartidaFileIn partidaFileIn = new PartidaFileIn("resultados.dat");
        Partida partida = partidaFileIn.lectura();
        
        while (partida != null) {
            if (nombreJugador.equals(partida.getNombreJugador())) {
                areaVisualizacionResultados.append(partida.toString() + "\n");
            }
            partida = partidaFileIn.lectura();
        }
    
    partidaFileIn.close();
}
}
