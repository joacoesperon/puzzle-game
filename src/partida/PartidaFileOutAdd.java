package partida;

/* CLASE PartidaFileOutAdd
lleva a cabo la gestion de ficheros de objetos Partida a nivel de escritura
posibilitando,si el fichero ya existe, añadir objetos en el 
*/
//autor: Joaquin Esperon

import java.io.*;

public class PartidaFileOutAdd {
    
    //ATRIBUTOS
    //declaracion ObjectOutputStream para la escritura en ficheros de objetos Partida
    //cuando se desee crear un fichero nuevo
    private ObjectOutputStream fichero1 = null;
    
    //declaracion AdicionObjectOutputStream para escritura en ficheros a nivel 
    //de objetos cuando se desee añadir objetos en un fichero ya existente
    private AdicionObjectOutputStream fichero2=null;
    
    //CONSTRUCTOR
    public PartidaFileOutAdd(String nombreFichero){
        
        try{
            //declaracion de File que enlace con el fichero pasado por parametro
            File f = new File(nombreFichero);
            if(f.exists()){
                //instanciacion objeto AdicionObjectOutputStream con el fichero dado
                //para posibilitar la adicion de objetos en dicho fichero
                fichero2 =  new AdicionObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nombreFichero, true)));
            }
            else { //osea el fichero no existe, lo creo
                //instaciacion ObjectOutputStream con el fichero dado
                //para posibilitar la escritura de objetos en dicho fichero que 
                //sera creado como nuevo
                fichero1 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nombreFichero)));
            }
        }catch(IOException error){
            System.out.println("Error " + error.toString());
        }
    }
    
    //METODOS FUNCIONALES
    //metodo para escribir un objeto Partida en el fichero enlazado
     public void escritura(Partida Partida){
        
        try{ 
            //verificar si el fichero es de nueva creacion o ya existente,donde
            //se esta añadiendo un objeto Partida nuevo
            //escritura objeto Partida dado
            if(fichero2==null){
                //escritura objeto Partida dado
                fichero1.writeObject(Partida);
            }
            else{
                //escritura objeto Partida dado
                fichero2.writeObject(Partida);
            }
        }catch(IOException error){
            System.out.println("ERROR: " + error.toString());
        }
    }
    
    //METODO cierre lleva a cabo el cierre del enlace con el fichero
    public void cierre(){
        try{
            //verificar si el fichero es de nueva creacion o ya existente,
            //donde se esta añadiendo un objeto Partida nuevo
            if(fichero2==null){
                fichero1.close();
            }
            else{
                fichero2.close();
            }
        }catch(IOException error){
            System.out.println("ERROR: " + error.getMessage());
        }
    }
}
