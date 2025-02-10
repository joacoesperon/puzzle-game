package practica;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import paneles.*;

public class Principal {
    
    
    private Container panelContenidos;
    private JFrame ventana;
    private JMenuBar barraMenu;
    private JToolBar iconosMenu;
    private JPanel panelBotones;
    private JPanel panelVisualizaciones;
    private PanelPartida panelPartida;
    private PanelImagenSolucion panelImagenSolucion;
    private PanelHistorial panelHistorial;
    private File directorio= new File("imagenes");
    //imagen para cada partida
    private BufferedImage imagen;
    //para buscar el historial
    private String nombreBusqueda;
    //para cada partida
    private String nombreJugador;
    //divsiones horizontales
    private int divHor;
    //divsiones verticales
    private int divVer;

    public static void main(String[] args) {
        new Principal().metodoPrincipal();        
    }

    private void metodoPrincipal() {       
        //declaración contenedor JFrame 
        ventana=new JFrame("PRÁCTICA - PROGRAMACION II - 2022-2023 - UIB");
        //asignación a panelContenidos del panel de contenidos del contenedor JFrame
        panelContenidos=ventana.getContentPane();  
        panelContenidos.setLayout(new BorderLayout());
        
        ////////////////////////////////////////////////////////////////////////////////
        //                   JMenuBar barraMenu Y COMPONENTES ASOCIADAS               //
        ////////////////////////////////////////////////////////////////////////////////              
        crearJMenuBar();

        ////////////////////////////////////////////////////////////////////////////////
        //                      JToolBar iconosMenu y COMPONENTES                     //
        ////////////////////////////////////////////////////////////////////////////////
        crearJtoolBar();
        
        ////////////////////////////////////////////////////////////////////////////////
        //                      PANEL panelBotones y COMPONENTES                      //
        ////////////////////////////////////////////////////////////////////////////////
        crearPanelBotones();
        
        ////////////////////////////////////////////////////////////////////////////////
        //                      PANEL panelVisualizaciones Y COMPONENTES              //
        ////////////////////////////////////////////////////////////////////////////////
        crearPanelVisualizaciones();
        
        ////////////////////////////////////////////////////////////////////////////////
        //                      SEPARADORES JSplitPane                                //
        ////////////////////////////////////////////////////////////////////////////////
        JSplitPane separador1= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        separador1.setTopComponent(iconosMenu);
        JSplitPane separador2= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelBotones,panelVisualizaciones);
        JSplitPane separador3= new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JLabel(),new JLabel());
        
        //añadir componentes al panelContenidos
        ventana.setJMenuBar(barraMenu);
        panelContenidos.add(separador1,BorderLayout.NORTH);
        panelContenidos.add(separador2,BorderLayout.CENTER);
        panelContenidos.add(separador3,BorderLayout.SOUTH);

        ////////////////////////////////////////////////////////////////////////////////
        //                  ÚLTIMAS INTERVENCIONES CONTENEDOR JFrame                  //
        ////////////////////////////////////////////////////////////////////////////////  
        //DIMENSIONAMIENTO DEL CONTENEDOR JFrame ventana 
        ventana.setSize(650, 550);
        //CENTRADO DEL CONTENEDOR ventana EN EL CENTRO DE LA PANTALLA
        ventana.setLocationRelativeTo(null);
        //ACTIVACIÓN DEL CIERRE INTERACTIVO VENTANA DE WINDOWS EN EL CONTENEDOR 
        //JFrame ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //VISUALIZACIÓN CONTENEDOR JFrame ventana
        ventana.setVisible(true);
    }

    private void crearJMenuBar(){
        
        ////////DECLARACIÓN COMPONENTE JMenuBar barraMenu
        barraMenu=new JMenuBar();
        //asignacion fondo negro
        barraMenu.setBackground(Color.BLACK);

        ////////DECLARACIÓN DE LA COMPONENTE JMenu menu ASOCIADA A barraMenu
        JMenu menu=new JMenu("MENU");
        //asignacion color de trazado blanco
        menu.setForeground(Color.WHITE);
        ////////INLCUSIÓN menu EN barraMenu
        barraMenu.add(menu);
        
        ////////COMPONENTES JMenuItem asociadas a la componente JMenu menu
        JMenuItem[] botonesMenu= new JMenuItem[5];
        String[] nombresBotonesMenu = {"NUEVA PARTIDA","CLASIFICACION GENERAL","HISTORIAL","CAMBIAR DIRECTORIO DE IMAGENES","SALIR"};
        for(int i=0;i<botonesMenu.length;i++){
             //instancio el boton con su nombre
            botonesMenu[i] = new JMenuItem(nombresBotonesMenu[i]);
            //set nombre para el manipulador de eventos
            botonesMenu[i].setName(nombresBotonesMenu[i]);
             //asigno color de trazado
            botonesMenu[i].setForeground(Color.WHITE);
            //asigno color de fondo
            botonesMenu[i].setBackground(Color.BLACK);
            //manipulador de evento asociado a la componente JButton botonesNotas[i]
            botonesMenu[i].addActionListener(new ManipuladorEventos());
            //añado las componentes a panelBotonesNotas
            menu.add(botonesMenu[i]);
        }
    }
    
    private void crearJtoolBar(){
        ////////DECLARACIÓN COMPONENTE JToolBar iconosMenu
        iconosMenu=new JToolBar();
        //asignacion fondo negro
        iconosMenu.setBackground(Color.BLACK);
        iconosMenu.setFloatable(false);
           
        ////////COMPONENTES JButton CON ICONOS  asociadas a la componente JToolBar iconosMenu
        JButton[] botonesIcono= new JButton[5];
        // Carga la imagen para el icono del botón
        String[] nombresIconos = {"NUEVA PARTIDA","CLASIFICACION GENERAL","HISTORIAL","CAMBIAR DIRECTORIO DE IMAGENES","SALIR"};
        String dir = "iconos\\";
        String[] iconos = {dir+"iconoNuevaPartida.jpg",dir+"iconoHistorialGeneral.jpg",dir+"iconoHistorialSelectivo.jpg",dir+"iconoCambiarDIrectorio.jpg",dir+"iconoSalir.jpg"};
        for(int i=0;i<botonesIcono.length;i++){
            //ImageIcon con el icono correpsondiente
            ImageIcon icono = new ImageIcon(iconos[i]);
             //instancio el boton con su nombre
            botonesIcono[i] = new JButton(icono);
            //asigno nombre
            botonesIcono[i].setName(nombresIconos[i]);
            //fondo negro
            botonesIcono[i].setBackground(Color.BLACK);
            //manipulador de evento asociado a la componente JButton botonesNotas[i]
            botonesIcono[i].addActionListener(new ManipuladorEventosIconos());
            //añado las componentes a panelBotonesNotas
            iconosMenu.add(botonesIcono[i]);
        }
    }

    private void crearPanelBotones(){
        /////////DECLARACIÓN CONTENEDOR JPanel panelBotones
        panelBotones = new JPanel();     
        //asignación administrador GridLayout al contenedor con 4 filas y 1 columna
        panelBotones.setLayout(new GridLayout( 4, 1 ));

        //DECLARACION botonesNotas
        JButton[] botonesPanel= new JButton[4];
        String[] nombresBotonesPanel = {"NUEVA PARTIDA","CLASIFICACION GENERAL","HISTORIAL","SALIR"};
        for(int i=0;i<botonesPanel.length;i++){
             //instancio el boton con su nombre
            botonesPanel[i] = new JButton(nombresBotonesPanel[i]);
            //set nombre para el manipulador de eventos
            botonesPanel[i].setName(nombresBotonesPanel[i]);
            //asignación tipografia a la componente JButton accion2
            botonesPanel[i].setFont(new Font("arial", 0, 10));
            //asignacion fondo negro
            botonesPanel[i].setBackground(Color.BLACK);
            //asignacion color de trazado negro
            botonesPanel[i].setForeground(Color.WHITE);
            //manipulador de evento asociado a la componente JButton botonesNotas[i]
            botonesPanel[i].addActionListener(new ManipuladorEventos());
            //añado las componentes a panelBotonesNotas
            panelBotones.add(botonesPanel[i]);
        }   
    }

    private void crearPanelVisualizaciones(){
        ////////DECLARACIÓN CONTENEDOR JPanel panelVisualizaciones
        panelVisualizaciones=new JPanel();
        //asignación administrador de layout al contenedor JPanel panelVisual
        panelVisualizaciones.setLayout(new CardLayout());
         
        ////////////////////////////////////////////////////////////////////////////////
        //////////    DECLARACIÓN CONTENEDOR JPanel imagenUIB         ||||/////////////
        PanelStandby panelStandby=new PanelStandby();
        //INTRODUCCIÓN panelStandby EN panelVisualizaciones ASOCIADO AL LITERAL "panelStandby"
        panelVisualizaciones.add(panelStandby,"panelStandby");
        
        ////////////////////////////////////////////////////////////////////////////////
        //////////    DECLARACIÓN CONTENEDOR JPanel panelPartida         ||||/////////////
        panelPartida =new PanelPartida(ventana,panelVisualizaciones);
        //INTRODUCCIÓN interface2 EN panelInterfaces ASOCIADO AL LITERAL "PANEL 2"
        panelVisualizaciones.add(panelPartida,"panelPartida");
        
        ////////////////////////////////////////////////////////////////////////////////
        //////////    DECLARACIÓN CONTENEDOR JPanel panelPartida         ||||/////////////
        panelImagenSolucion =new PanelImagenSolucion(panelVisualizaciones,panelPartida);
        //INTRODUCCIÓN interface2 EN panelInterfaces ASOCIADO AL LITERAL "PANEL 2"
        panelVisualizaciones.add(panelImagenSolucion,"panelImagenSolucion");
        
        ////////////////////////////////////////////////////////////////////////////////
        //////////    DECLARACIÓN CONTENEDOR JPanel panelPartida         ||||/////////////
        panelHistorial =new PanelHistorial();
        //INTRODUCCIÓN interface2 EN panelInterfaces ASOCIADO AL LITERAL "PANEL 2"
        panelVisualizaciones.add(panelHistorial,"panelHistorial");
    }
    
    // Gestor de eventos para los botones y elementos del menú
private class ManipuladorEventos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent evento) {
        CardLayout panel = (CardLayout) (panelVisualizaciones.getLayout());

        switch (evento.getActionCommand()) {
            case "NUEVA PARTIDA" -> {
                if (panelPartida.getFinalizado()) {
                    if (nuevaPartida()) {
                        cargarImagen();
                        panelImagenSolucion.setImagen(imagen);
                        panelPartida.resetear();
                        panelPartida.iniciarPartida(imagen, nombreJugador, divHor, divVer);
                        panel.show(panelVisualizaciones, "panelPartida");
                    }
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "CLASIFICACION GENERAL" -> {
                if (panelPartida.getFinalizado()) {
                    panelHistorial.mostrarListadoPartidas();
                    panel.show(panelVisualizaciones, "panelHistorial");
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "HISTORIAL" -> {
                if (panelPartida.getFinalizado()) {
                    if (nuevaBusqueda()) {
                        panelHistorial.mostrarPartidasPorJugador(nombreBusqueda);
                        panel.show(panelVisualizaciones, "panelHistorial");
                    }
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "CAMBIAR DIRECTORIO DE IMAGENES" -> {
                if (panelPartida.getFinalizado()) {
                    seleccionarDirectorio();
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "SALIR" -> System.exit(0);
        }
    }
}

// Gestor de eventos para los botones con icono
private class ManipuladorEventosIconos implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        JButton boton = (JButton) evento.getSource();
        String nombreBoton = boton.getName();
        
        CardLayout panel = (CardLayout) (panelVisualizaciones.getLayout());

        switch (nombreBoton) {
            case "NUEVA PARTIDA" -> {
                if (panelPartida.getFinalizado()) {
                    if (nuevaPartida()) {
                        cargarImagen();
                        panelImagenSolucion.setImagen(imagen);
                        panelPartida.resetear();
                        panelPartida.iniciarPartida(imagen, nombreJugador, divHor, divVer);
                        panel.show(panelVisualizaciones, "panelPartida");
                    }
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "CLASIFICACION GENERAL" -> {
                if (panelPartida.getFinalizado()) {
                    panelHistorial.mostrarListadoPartidas();
                    panel.show(panelVisualizaciones, "panelHistorial");
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "HISTORIAL" -> {
                if (panelPartida.getFinalizado()) {
                    if (nuevaBusqueda()) {
                        panelHistorial.mostrarPartidasPorJugador(nombreBusqueda);
                        panel.show(panelVisualizaciones, "panelHistorial");
                    }
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
            }
            case "CAMBIAR DIRECTORIO DE IMAGENES" -> {
                if (panelPartida.getFinalizado()) {
                    seleccionarDirectorio();
                } else {
                    mensaje("ANTES DEBES TERMINAR LA PARTIDA EN CURSO");
                }
                break;
            }
            case "SALIR" -> System.exit(0);
        }
    }
}
    
    //se pide el nombre del jugador, devuelve false si no se pueden obtener
    private boolean nuevaBusqueda(){
        //declaración array de componentes String con los literales de los conceptos a introducir
        String [] datos={"NOMBRE JUGADOR"};
        //asignación a datos los datos introducidos a través del objeto lecturaDatos
        datos=new lecturaDatos(ventana,datos).getDatosTexto();
        if (datos!=null) {
            nombreBusqueda=datos[0];
            return true;
        }
        else {
            mensaje("¡¡¡ NO HAS INTRODUCIDO LOS DATOS CORRECTAMENTE\n"+
                                  "    PARA BUSCAR UN JUGADOR !!!");
            return false;
        }
    }
    
    private boolean nuevaPartida(){
        //declaración array de componentes String con los literales de los conceptos a introducir
        String [] datos={"NOMBRE JUGADOR","NÚMERO DE SUBDIVISIONES HORIZONTAL","NÚMERO DE SUBDIVISIONES VERTICAL"};
        //asignación a datos los datos introducidos a través del objeto lecturaDatos
        datos=new lecturaDatos(ventana,datos).getDatosTexto();
        if (datos!=null) {
            try {
                nombreJugador=datos[0];
                divHor=Integer.parseInt(datos[1]); 
                divVer=Integer.parseInt(datos[2]); 
            } catch (NumberFormatException error) {
                mensaje("¡¡¡ NO HAS INTRODUCIDO LOS DATOS CORRECTAMENTE\n"+
                                  "    PARA INICIAR UNA PARTIDA !!!");
                return false;
            }
            //minimo de divisiones 2*2 maximo 10*10
            if (!((divHor>=2)&&(divHor<=10)&&(divVer>=2)&&(divVer<=10))) {
                mensaje("¡¡¡ NO HAS INTRODUCIDO LOS DATOS CORRECTAMENTE\n"+
                                  "    PARA INICIAR UNA PARTIDA !!!");
                return false;
            }
            return true;
        }
        else {
            mensaje("¡¡¡ NO HAS INTRODUCIDO LOS DATOS CORRECTAMENTE\n"+
                                  "    PARA INICIAR UNA PARTIDA !!!");
            return false;
        }
    }
    
    //muestra un dialogo con un mensaje
    private void mensaje(String msj) {
        JOptionPane.showMessageDialog(ventana, msj);         
    }
    
    //para cambiar el directorio
    public void seleccionarDirectorio() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar Directorio");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = chooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            directorio = chooser.getSelectedFile();
            System.out.println("Directorio seleccionado: " + directorio.getAbsolutePath());
            
            // Obtener la lista de archivos dentro del directorio
            File[] imagenes = directorio.listFiles(file -> {
                final var name = file.getName().toLowerCase();
            
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
            }); 
        
            if(imagenes.length==0){
                //se vuelve a asignar el predeterminado
                directorio = new File("imagenes");
                mensaje("No hay imagenes en el directorio. \nSeleccionar otro directorio");
                System.out.println("Directorio predeterminado asignado ");
                seleccionarDirectorio();  
            } 
        }else {
            System.out.println("Selección de directorio cancelada por el usuario.");
        }
    }
    //le carga a imagen una imagen del directorio
    private void cargarImagen() {
        
        // Obtener la lista de archivos dentro del directorio
        File[] imagenes = directorio.listFiles();    
        
            // Generar un índice aleatorio dentro del rango de archivos
            Random random = new Random();
            int indiceAleatorio = random.nextInt(imagenes.length);
            // Obtener el archivo de imagen seleccionado
            File archivoSeleccionado = imagenes[indiceAleatorio];
        
            try {
            
            // Leer el archivo de imagen seleccionado y asignarlo a la variable "imagen"
            imagen = ImageIO.read(archivoSeleccionado);
            
            // Procesar la imagen según tus necesidades
            } catch (IOException e) {
            // Manejar la excepción de lectura de imagen
            System.out.println("ERROR: " + e.getMessage());
            }
    }
}
    