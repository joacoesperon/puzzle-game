package partida;

/*
Redefinicion de la clase ObjectOutputStream para que no escriba una cabecera 
al principio del Stream y posibilite la adicion de objetos en ficheros
*/
//autor: Joaquin Esperon

import java.io.*;

public class AdicionObjectOutputStream extends ObjectOutputStream {
    //constructor que recibe OutputStream
    // @param out
    // @throws java.io.IOException
    public AdicionObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }
    
    //CONSTRUCTOR
    // @throws java.io.IOException
    protected AdicionObjectOutputStream() throws IOException, SecurityException {
        super(); //ejecuta el constructor de la clase madre ObjectOutputStream
    }
    
    //Redefinicion del metodo de escribir la cabecera para que no haga nada.
    // @throws java.io.IOException
    @Override
    protected void writeStreamHeader() throws IOException{
        
    } 
}
