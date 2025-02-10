//se encarga de la lectura de partidas desde un archivo. 
//autores: Joaquin Esperon
package partida;

import java.io.*;

public class PartidaFileIn {
   
    private ObjectInputStream ois;

    public PartidaFileIn(String name){
        try{
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(name)));
        }catch(IOException e){
            System.out.println("Error: " + e.toString());
        }
    }
    
    public Partida lectura(){
        Partida p=new Partida();
        try{
            p = (Partida) ois.readObject();
        }catch(EOFException e){
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
        return p;
    }
    
    public void listado(){
        Partida p = new Partida();
        try{
            for(p=(Partida) ois.readObject();!p.esCentinela();){
            System.out.println(p);
            p=(Partida) ois.readObject();
            }
        }catch(IOException | ClassNotFoundException ex){
            System.out.println("ERROR: " + ex.toString());
        } 
    }
    
    public void close(){
        try{
            if(ois!=null){
            ois.close();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
    
}

