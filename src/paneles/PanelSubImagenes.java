//Panel que contiene las subimágenes de la partida en curso y se reliza la gestion
// de eventos para que se puedan cambiar de lugar
//autor: Joaquin Esperon
package paneles;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.swing.*;
import java.util.Random;
import static javax.swing.Spring.scale;
import practica.SubImagen;

public class PanelSubImagenes extends JPanel {

    //matriz de SubImagen
    private SubImagen[][] subImagenes;
    //subdivisiones horizontales(filas)
    private final int filas;
    //subdivisiones verticales(columnas)
    private final int columnas;
    //imagen principal
    private final BufferedImage imagen;
    //variable se utiliza para el gestor de eventos
    private SubImagen subImagenSeleccionada;
    //se utiliza para iniciar el cronometro en el primer movimiento
    private final PanelPartida panelPartida;
    //boolean para indicar si se realizo el 1er movimiento
    private boolean primerMovimiento;
    //boolean para indicar el ordenCorrecto
    private boolean correcto;

    public PanelSubImagenes(BufferedImage image,PanelPartida p, int f, int c) {
    super(new GridLayout(f, c, 0, 0));
    this.filas = f;
    this.columnas = c;
    this.panelPartida = p;
    this.primerMovimiento = false;    
    this.imagen = image;
    this.subImagenes = new SubImagen[filas][columnas];
    int anchoSubImagen = imagen.getWidth() / columnas;
    int altoSubImagen = imagen.getHeight() / filas;
    int numeroImagen = 0; // Variable para asignar el número de imagen

    // Agregar las SubImagenes a la matriz con su número correspondiente
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int x = j * anchoSubImagen;
            int y = i * altoSubImagen;
            BufferedImage subImagen = imagen.getSubimage(x, y, anchoSubImagen, altoSubImagen);
            subImagenes[i][j] = new SubImagen(subImagen, numeroImagen++);
            subImagenes[i][j].addActionListener(new GestorEventos());
        }
    }
    // Mezclar las subimágenes
    mezclarSubImagenes();
    // Añadir las subimágenes al panel en orden aleatorio
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            this.add(subImagenes[i][j]);
        }
    }
}

    private void mezclarSubImagenes() {
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int filaAleatoria = random.nextInt(filas);
                int columnaAleatoria = random.nextInt(columnas);

                // Intercambiar las posiciones de las subimágenes
                SubImagen subImagenTemporal = subImagenes[i][j];
                subImagenes[i][j] = subImagenes[filaAleatoria][columnaAleatoria];
                subImagenes[filaAleatoria][columnaAleatoria] = subImagenTemporal;
            }
        }
    }

    private boolean verificarOrdenCorrecto(){
        boolean ret = true;

        int numeroImagen = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (subImagenes[i][j].getNumeroImagen() != numeroImagen) {
                    ret = false;
                }
                numeroImagen++;
            }
        }
        return ret;
    }

    private class GestorEventos implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        SubImagen subImagen = (SubImagen) e.getSource();
        

        if (!primerMovimiento) {
            // Iniciar el cronómetro solo en el primer movimiento
            panelPartida.iniciarCronometro();
            primerMovimiento = true;
        }

        if (subImagenSeleccionada == null) {
            // Si no hay subimagen previamente seleccionada, guardar la actual
            subImagenSeleccionada = subImagen;
            subImagenSeleccionada.setBorder(BorderFactory.createLineBorder(Color.RED,5));
        } else {
            // Intercambiar las posiciones de las subimágenes
            intercambiarSubImagenes(subImagenSeleccionada, subImagen);
            subImagenSeleccionada.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));

            // Reiniciar la subimagen seleccionada
            subImagenSeleccionada = null;
            correcto = verificarOrdenCorrecto();
        }
    }
}

    private void intercambiarSubImagenes(SubImagen subImagen1, SubImagen subImagen2) {
        if(subImagen1.equals(subImagen2)){
            return;
        }

        // Obtener las coordenadas de las subimágenes en la matriz
        int fila1 = -1, columna1 = -1, fila2 = -1, columna2 = -1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (subImagenes[i][j] == subImagen1) {
                    fila1 = i;
                    columna1 = j;
                } else if (subImagenes[i][j] == subImagen2) {
                    fila2 = i;
                    columna2 = j;
                }
            }
        }

        // Intercambiar las posiciones de las subimágenes en la matriz
        SubImagen subImagenTemporal = subImagenes[fila1][columna1];
        subImagenes[fila1][columna1] = subImagenes[fila2][columna2];
        subImagenes[fila2][columna2] = subImagenTemporal;

        // Actualizar la disposición de las subimágenes en el panel
        removeAll();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.add(subImagenes[i][j]);
            }
        }

        revalidate();
        repaint();
    }

    public boolean getCorrecto(){
        return correcto;
    }
    
}


