//Representa un botón personalizado capaz de mostrar una porción de imagen. 
//autor: Joaquin Esperon
package practica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class SubImagen extends JButton {

    //porcion de imagen
    private BufferedImage imagen;
    //numero de imagen
    private int numeroImagen;

    public SubImagen(BufferedImage imagen,int n) {
        super();
        this.imagen = imagen;
        this.numeroImagen = n;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public int getNumeroImagen() {
        return numeroImagen;
    }

    public void setNumeroImagen(int numeroImagen) {
        this.numeroImagen = numeroImagen;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen en el componente
        if (imagen != null) {
            int ancho = getWidth();
            int alto = getHeight();
            g.drawImage(imagen, 0, 0, ancho, alto, null);
        }
    }
}