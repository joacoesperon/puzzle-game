//Clase serializable que representa una partida del juego. 
//autor: Joaquin Esperon
package partida;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Partida implements java.io.Serializable{
    //nombre del jugador.
    private String nombreJugador;
    //almacena la fecha y hora en que se jugó la partida. 
    private LocalDateTime fechaHoraPartida;
    //puntos obtenidos
    private double puntosConseguidos;
    
    public static final Partida CENTINELA = new Partida("",Integer.MAX_VALUE);
    
    public Partida() {
        this.fechaHoraPartida = LocalDateTime.now();
    }

    public Partida(String nombreJugador, int puntosConseguidos) {
        this.nombreJugador = nombreJugador;
        this.fechaHoraPartida = LocalDateTime.now();
        this.puntosConseguidos = puntosConseguidos;
    }

    // Getters y setters para los atributos
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public LocalDateTime getFechaHoraPartida() {
        return fechaHoraPartida;
    }

    public void setFechaHoraPartida(LocalDateTime fechaHoraPartida) {
        this.fechaHoraPartida = fechaHoraPartida;
    }

    public double getPuntosConseguidos() {
        return puntosConseguidos;
    }

    public void setPuntosConseguidos(double puntosConseguidos) {
        this.puntosConseguidos = puntosConseguidos;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yy");
        String fechaHoraPartidaString = fechaHoraPartida.format(formatter);
        return "JUGADOR: " + nombreJugador + "      - FECHA: " + fechaHoraPartidaString + " - PUNTOS: " + (int)puntosConseguidos + " puntos.";
    }
    
    public boolean esCentinela() {
        return (this.puntosConseguidos == Integer.MAX_VALUE);
    }

}
