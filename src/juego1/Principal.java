package juego1;

import java.awt.*;





import java.net.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Principal{
	protected static FileReader fr,login;
	protected static FileWriter fw;
	protected static BufferedWriter bw;
	protected static BufferedReader log;
	protected static String campo;
	protected static char[][] camp;
	protected static final int DIM_TABLON_JUEGO_X = 22;//11
	protected static final int DIM_TABLON_JUEGO_Y = 46;//23
	protected static final Color COLOR_ROJO = new Color(197,9,9);
	protected static final Color COLOR_AZUL = new Color(25,25,224);
	protected static final Color COLOR_CYAN = Color.CYAN;
	protected static final Color COLOR_GRIS = new Color(21,86,120);
	protected static final Color COLOR_DEFECTO = new Color(66,7,79);
	protected static final Color COLOR_FONDO_JUEGO = new Color(66,7,79);
	protected static Color colorElegidoPrimario = COLOR_ROJO ; 
	protected static Color colorElegidoSecundario = COLOR_DEFECTO;//se iniciara por defecto
	protected static final int cordX = 40, cordY = 40;
	protected static AudioStream audio;
	protected static String jugs;
	protected static Ventana vent;
	protected static String[][] jugadors;
    protected static int numJugadores;
   
    
	public static void main(String[] args) {
  
		vent = new Ventana();
		//**read archives**//
		try {
			fr = new FileReader("src/lab1.txt");		
			login = new FileReader("src/log.txt");		
			BufferedReader br = new BufferedReader(fr);		
			BufferedReader log = new BufferedReader(login);		
			int i = 0;	
			camp = new char[DIM_TABLON_JUEGO_X][DIM_TABLON_JUEGO_Y];			
			while((campo = br.readLine()) != null) {			
				//System.out.println(""+ campo);		
				char[] linia= campo.toCharArray();		
				for(int j = 0; j < linia.length; j ++) {		
					camp[i][j] = linia[j];
				}	
				i++;
			}
			System.out.println();System.out.println();
			for(int k = 0; k < DIM_TABLON_JUEGO_X; k ++) {
				for(int h = 0; h < DIM_TABLON_JUEGO_Y; h ++) {
					//System.out.print(camp[k][h]);
				}
				//System.out.println();
			}
			
			/**logeando**/	
			int numLineas = 5;//detecting players number
			String x = "";
			Principal.numJugadores = numLineas;	
			jugadors = new String[Principal.numJugadores][5];//inicio el array con el numero de jugadores que estan	
			System.out.println(x);
			jugs = "";	
			/**asd ads as dasd asd 
               asd asd asd a sd
              as dasd asd asd asd 
              as daasd asd s s 
              sd asd asd asd asd 
              asd asd asd as dasd*/	
			i = 0;
			while((jugs = log.readLine()) != null) {//no entra al bucle por algo	
				String[] j = jugs.split(" ");				
				System.out.println(i);			
				System.out.println(jugs);//da error				
				jugadors[i][0] = j[0].trim();			
				//Principal.jugadors[0][0] = "5";				
				//System.out.println("hola" + jugadors[0][0]);				
				jugadors[i][1] = j[1].trim();			
				jugadors[i][2] = j[2].trim();			
				jugadors[i][3] = j[3].trim();			
				jugadors[i][4] = j[4].trim();			
				i++;
			}		
			System.out.println(jugadors[2][4]);
			/**audio**/
			InputStream in = new FileInputStream("src/img/SAW.wav");			
			audio = new AudioStream(in);		
			/**escritura archivos**/		
			//fw = new FileWriter("src/log.txt");		
			//bw = new BufferedWriter(fw);		
			//bw.write("Hola");		
		}catch(IOException e) {		
			System.err.println("No se puede sacar el archivo");		
			System.out.println(e.getMessage() +  e.getLocalizedMessage());
		}	
	}  
}//fin listener teclado	
//fin principal
class Ventana extends JFrame{
	/**DIMENSIONES DE LA PANTALLA**/
	private Toolkit dimensiones = Toolkit.getDefaultToolkit();	
	protected Toolkit config = Toolkit.getDefaultToolkit();	
	Dimension dimension = dimensiones.getScreenSize();
	private int PantallaAlto = (int)dimension.getHeight(), PantallaAncho = (int)dimension.getWidth();
	public int PantallaDimAlto = 1080, PantallaDimAncho = 1920;	
	//private JPanel contador, menu1,jugar;
	private static String presionaParaComenzar = "Pulsa en la pantalla para empezar!";	
	private static boolean comenzar = true;	
	private static int posJugX,posJugY;//player position
	private int posDibX, posDibY;//posicion del recorrido para dibujar	
	private int posIniPX= 1;	
	private int posIniPY =5;	
	protected int posPanRegX = 20 , posPanRegY = 140, dimAlReg = 290, dimAnReg = 324;//dimensiones del registro // 20 140 290  324	    
	ImageIcon iconoCursor1 = new ImageIcon("scr/img/cursor1.png");//imagen del cursor 
	protected ImageIcon imgCuenta;	
	protected Cursor cursorporDefecto, cursorPD2;//cursor en la pagina por defecto	
	private Botones botones;	
	private JButton salir, iniciar, record , menuPpal,guardar, volver, volver1, volver2,ajustes, ajustes1, volver5, nivelTres; //botones del menu	
	private JButton rojo, cyan, azul, gris, registrarse, borrar, conectarse, menuReg, idiomas;	
	private Menu menu;	
	private Ajustes Ajustes;
	private Juego juego; 	
	private Contador Contador;	
	private Estadisticas estadisticas;	
	private EstTabla estTabla;	
	private Registrar registrar;	
	private AjustesGeneral AjustesGeneral;	
	protected JPanel pan;	
	private Image icono, laberinto , Rojo, Gris, Azul, Cyan,iconoJuego;
	public Image skinDiablo1, cursorXD, cursorInvisible;	
    protected static Icon iconoRegistrar, luna;	
	private JTextArea usuario, usuario2;//usuario en la pagina ppal para conectarse	
	private JPasswordField contra, repContra, contraEntrar;//contraseñas para entrar desde la pag ppal
	private String usu = "Introduce tu usuario";	
	protected String nombreDelUsuario = "";	
	private String contrasenya = "y la contraseña";	
	private boolean menuRegistro, tirarMisil, arriba = false, abajo = false;
	protected boolean iniCrono = false;	
	protected score scor;	
	private Contador1 hilo;  	
	private Thread hiloPajaro;//declaro el hilo del pajaro	
	private BienvenidaJug hilo2;	
	private panBienvenida pan1;	
	private boolean runThread = false;
	private Color colorRojo = new Color(169,50,38);	  
	private Color colorMorado = new Color(89,10,144);	
	private int ContadorMin = 0, ContadorSeg = 0, ContadorHora; 	
	private int x1 = 50, y1 = - 100;//posicion inicial del panel 	
	protected static String mensajito = "";
	
	
	/**Player class**/
	public static class Jugador{

  	  private String nomJug, contraJug;
  	  private int score;
  	  private Jugador[] jugadores;
  	  private Jugador jugador;
  	  public int posXJug, posYJug;
  	  private static final int CANTIDAD_JUGADORES = 10;
  	  
  	  	  public Jugador() {jugadores = new Jugador[CANTIDAD_JUGADORES];}	  
	      public Jugador(String nomJ, String contraJ){  
	    	  nomJug = nomJ; 
	    	  contraJug = contraJ;
	      }	 
	      public Jugador(String nom, String contra, int x, int y, int score) {	    	  
	    	  this.nomJug = nom;    	  
	    	  this.contraJug = contra;	    	  
	    	  this.posXJug =x;	    	  
	    	  this.posYJug = y;	    	  
	    	  this.score = score;
	      }
	      public void setJugador(int x, int y, int score) {	    	  
            this.posXJug = x;          
            this.posYJug = y;        
            this.score = score;	    	  
	      } 
	     public static boolean compruebaJug(String nom, String contra) {  
	    	  for(int i = 0; i < Principal.numJugadores; i++) {
	    		  if(Principal.jugadors[i][0].equals(nom.trim()) && Principal.jugadors[i][1].equals(contra.trim())) {  
	    			  return true; 
	    		  }		  
	    	  }
	    	  return false;//si no existe jugador
	      }     
  }
	/**ACTION LISTENER**/
	private class Botones2 extends AbstractAction{	
		public Botones2(String nombre, Icon icono, Color color, int valor) {		
            putValue(Action.NAME, nombre);			
			putValue(Action.SMALL_ICON, icono);		
			putValue(Action.SHORT_DESCRIPTION, "Estas a punto de " + nombre);			
			putValue("AccionBoton", color);			
			putValue("accion", valor);			
		}		
		public void actionPerformed(ActionEvent e) {
			/**PRUEBA SOCKETS**/
			/**if(e.getSource().equals(registrarse)) {
				
				
			try {
				
			     Socket mail = new Socket(Inet4Address.getLocalHost().getHostAddress(), 6666); //creo mi socket//192.168.0.154
			     
			     System.out.println("aqui");
			     
			     DataOutputStream salida = new DataOutputStream(mail.getOutputStream());
			     System.out.println("aqui 1");
			     
			     salida.writeUTF(" hooola ");
			     
			     salida.close();
			     
			}catch(UnknownHostException s) {
			         
				  System.out.println("omg");
				
			}catch(IOException o){
				System.out.println("omg2");
			}
			}
			*/
			/**fin prueba**/
			int valor = (int)getValue("accion");		
			if(valor == 0) {
				System.out.println("boton uno funciona y valor " );			
				//pide la primera ocontra
				try{
					char[] texto  = contra.getPassword();			
				    char[] textRep = repContra.getPassword();							
				    String contra1 ="";  String repcontra = "";			
				    for(int i = 0; i < texto.length; i++) {
					   contra1 += texto[i];
				    }
				
				   for(int l = 0; l < textRep.length; l++) {
					   repcontra += texto[l];
			      	}
				   String text2 = usuario.getText();			
				   System.out.println("  usuario: " + text2 + " y contra : " + contra1 + " y " + repcontra);			   
				   //**COMPUEBA SI LAS CONTRASEÑAS COINCIDEN**//			   
				   if(contra1.equals(repcontra)) {				   
					   System.out.println("Las contraseñas coinciden");				   
					   boolean x =  Jugador.compruebaJug(text2, contra1);//Jugador.compruebaJug(text2, contra1);					   
					   System.out.println(Principal.jugadors[1][0] + "  " + Principal.jugadors[1][1]);
					   if(x == true){					   
						   System.out.println("El jugador existe");					   
					   }else { System.out.println("El jugador no existe");					   
					          usuario.setBackground(colorRojo);				        
				              contra.setBackground(colorRojo);				        
				              repContra.setBackground(colorRojo);}
				   }else {					   
					   System.out.print("Las contraseñas no coinciden");					   
				   }			
				}catch(Exception l){				
					System.err.println("La has cagado pro");				
				}			
			}else if(valor == 1) {				
				System.out.println("Reiniciando contraseña");			
				contra.setText(contrasenya);				
				repContra.setText(contrasenya);				
				usuario.setText(usu);				
				repaint();	
			}else if(valor ==3) {
				/**CONECCION AL PERFIL**/
				//pide la primera ocontra
				try{
				       char[] textRep = contraEntrar.getPassword();							
				       String usuario1 = usuario2.getText();  String contraxD = "";			       
				       for(int i =0; i < textRep.length; i++) {contraxD += textRep[i];}		
				       //System.out.println("  usuario: " + usuario1 + " y contra : " + usuario1 + " y " + contraxD);				       
				       nombreDelUsuario = usuario1;			   
				       //**COMPRUEBA SI EXISTE EL JUGADOR**//
					   boolean x =  Jugador.compruebaJug(usuario1,contraxD );//Jugador.compruebaJug(text2, contra1);				   
					   System.out.println(Principal.jugadors[1][0] + "  " + Principal.jugadors[1][1]);
					   if(x == true){						   
						   System.out.println("El jugador existe");					   						   
						   mensajito = usuario1;
						   if(y1 == -100) {
                            	  BienvenidaJug saludo = new BienvenidaJug();			   
       						      saludo.start();
                              }					   
					   }else { System.out.println("El jugador no existe");
					        usuario2.setBackground(colorRojo);					        
					        contraEntrar.setBackground(colorRojo);
					   }			
				}catch(Exception l){				
					System.err.println("La has cagado pro");				
				}
			}else if(valor == 4) {				
					if(registrar.isVisible()) {					
						registrar.setVisible(false);						
			       }else if(menuRegistro == false){registrar.setVisible(true);}				
					Principal.vent.repaint();				
					System.out.println("entra");
			}else if (valor == 5) {			
                    menu.setVisible(true);				
				    juego.setVisible(false);				
				    estadisticas.setVisible(false);				
			     	estTabla.setVisible(false);				
				    Contador.setVisible(false);			
				    Ajustes.setVisible(false);			    
				    AjustesGeneral.setVisible(false);				    
			}else if (valor == 6) {
				    menu.setVisible(false);
				    juego.setVisible(false);				
				    estadisticas.setVisible(false);				
			     	estTabla.setVisible(false);				
				    Contador.setVisible(false);				
				    Ajustes.setVisible(false);				    
				    AjustesGeneral.setVisible(false);			    
                    //Teclado2 t = new Teclado2()				    
				    //nivel2.addKeyListener(t);				    			    
			}
		}
		
	}
	
	private class Botones extends AbstractAction implements ActionListener{	
		public Botones() {}		
		public void actionPerformed(ActionEvent e) {			
			Object botonPulsado = e.getSource();			
			if(botonPulsado.equals(iniciar)) {				
				comenzar = true;				
				menu.setVisible(false);
				
				juego.setVisible(true);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(true);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(false);
				
				posIniPX = 1;
				
				posIniPY = 5;
				
				AudioPlayer.player.start(Principal.audio);
				
			}else if(botonPulsado.equals(record)){
				
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(true);
				
				estadisticas.add(estTabla);
				
				estTabla.setVisible(true);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(false);
				
			}else if(botonPulsado.equals(salir)) {
			
				System.exit(0);
			}else if(botonPulsado.equals(menuPpal) || botonPulsado.equals(volver)){
				
                menu.setVisible(true);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(false);
				
				runThread = false; //desactivo el contador
				
				AudioPlayer.player.stop(Principal.audio);
				
			}else if(botonPulsado.equals(volver1)){
				
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(true);
				
			} if(botonPulsado.equals(guardar)) {
				/**TODO guadado de la partida actual**/
				System.out.println("Juego guardado");
				
				/*hilo = new Contador1(Contador);
				
				hilo.start();
				
				runThread = true;
				/**TODO**/
				//Juego joc = new Juego();
				
				int x = 10, y= 20; 
				
				//dibCuadro(x + 10, y + 10);
				
			}else if(botonPulsado.equals(ajustes)) {
				
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(true);
				
			}else if(botonPulsado.equals(rojo)) {
				Principal.colorElegidoPrimario = Principal.COLOR_ROJO;
				Principal.colorElegidoSecundario = Principal.COLOR_DEFECTO;
				Ajustes.repaint();
			}else if(botonPulsado.equals(azul)) {
				Principal.colorElegidoPrimario = Principal.COLOR_AZUL;
				Principal.colorElegidoSecundario = Principal.COLOR_DEFECTO;
				Ajustes.repaint();
			}else if(botonPulsado.equals(cyan)) {
				Principal.colorElegidoPrimario = Principal.COLOR_CYAN;
				Principal.colorElegidoSecundario = Principal.COLOR_DEFECTO;
				Ajustes.repaint();
			}else if(botonPulsado.equals(gris)) {
				Principal.colorElegidoPrimario = Principal.COLOR_CYAN;//Principal.COLOR_CYAN
				Principal.colorElegidoSecundario = Principal.COLOR_GRIS;
				Ajustes.repaint();
			}else if (botonPulsado.equals(registrarse)) {
				
				
				System.out.println("registrando");
			}else if(botonPulsado.equals(borrar)) {
			    
			    System.out.println("hola");
			}else if(botonPulsado.equals(ajustes1)) {
				
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(true);
				
				AjustesGeneral.setVisible(false);
				
				
				
			}else if(botonPulsado.equals(volver5)) {
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(true);;
				
			}else if(botonPulsado.equals(nivelTres)) {
				
                menu.setVisible(false);
				
				juego.setVisible(false);
				
				estadisticas.setVisible(false);
				
				estTabla.setVisible(false);
				
				Contador.setVisible(false);
				
				Ajustes.setVisible(false);
				
				AjustesGeneral.setVisible(false);
			}	
		}
	}	
	//mensaje de bienvenida cuando entra un jugador 
    protected class BienvenidaJug extends Thread{
    	  //panel de bienvenida
    	
    	  public void run(){
    		  try {
    			  for(int i =0; i < 100; i ++) {
    				  pan1 = new panBienvenida();
    				  
    				  pan1.setBounds(x1,y1,400,50);
    				  
    				  pan1.setVisible(true);
    				  
    				  menu.add(pan1);
    				  
    				  menu.repaint();
    				  
    			      Thread.sleep(14);
    			      
    			      menu.remove(pan1);
    			      
    			      System.out.print(i + "  ");
    			      
    			      y1++;
    			  }
    			      pan1 = new panBienvenida();
    			      
    			      pan1.setBounds(x1,y1,400,50);
    			      
    			      pan1.setVisible(true);
				  
				      menu.add(pan1);
				  
				      menu.repaint();
				      
    			     Thread.sleep(4000);
    			     
    			  for(int i =0; i < 100; i ++) {
    				  menu.remove(pan1);
    				  
       				  pan1 = new panBienvenida();
       				  
       				  pan1.setBounds(x1,y1,400,50);
       				  
       				  pan1.setVisible(true);
       				  
       				  menu.add(pan1);
       				  
       				  menu.repaint();
       				  
       			      Thread.sleep(5);
       			      
       			      menu.remove(pan1);
       			      System.out.print(i + "  ");
       			      
       			      y1--;
       			  }
    			  
    		  }catch(Exception s) {
    			  
    		  }
    		  
    	  }
    	
    }
    //panel bienvenida
    class panBienvenida extends JPanel{
		  
		  public panBienvenida() {
			  
			  setLayout(null);
			  
			  setBounds(x1, y1,400, 50);
			  
			  setBackground(colorMorado);
			  
			  setVisible(true);
			  
		  }
		  
		  public void paintComponent(Graphics g1) {
			  
			  super.paintComponent(g1);
			  
			  Graphics2D g = (Graphics2D) g1;
			  
			  g.setColor(new Color(241,196,15));
			  
			  g.drawRect(0, 0, 400, 50);
			  
			  g.setFont(new Font("Impact", Font.BOLD , 30));
			  
			  g.drawImage(skinDiablo1, 5, 5, 40, 40, null);
			  
			  g.drawString("Bienvenido " + nombreDelUsuario + "  !", 60, 35);
			  
		  }
	}
	//HILO DEL CONTADOR
	 class Contador1 extends Thread{
			
			JPanel panel;
			
			public Contador1(JPanel pan) {
				panel = pan;
				
			}
			public void run() {
				
				int x = 0;
			
				//x < 60
				while(runThread) {
				    try{
					    Thread.sleep(1000);

					    scor = new score();
					
					    scor.setLayout(null);

					    scor.setBackground(new Color(103,4,85));
					   
					    scor.setBounds(6,6,620,51);
					   
					    scor.setVisible(true);
					    
					    Contador.add(scor);
					    
					    Contador.repaint();
					    
					    Contador.remove(scor);
					    
					    x++;
					    
					    System.out.print("Esta "+ x);

				     }catch(Exception e) {
					
					    System.out.println("No funciona el thread");
					    
				  }}
			}
			
		}

	
	/**PROPIEDADES DE LA VENTANA**/
	public Ventana() {
		
		try {
			
			iconoJuego = ImageIO.read(new File("src/img/portada.png"));//icono nuevo del juego
			
		    //icono = ImageIO.read(new File("src/suerte.png"));//icono antiguo del juego
		    
		    imgCuenta = new ImageIcon("src/img/cursores/cuenta.png");//icono antiguo del juego
		    
		    laberinto = ImageIO.read(new File("src/img/laberinto.png"));
		    
		    Rojo = ImageIO.read(new File("src/img/Rojo.png"));
		    
		    Azul = ImageIO.read(new File("src/img/Azul.png"));
		    
		    Cyan = ImageIO.read(new File("src/img/Cyan.png"));
		    
		    Gris = ImageIO.read(new File("src/img/Gris.png"));
		    
		    skinDiablo1 = ImageIO.read(new File("src/img/diablo1.png"));
		    
		    cursorXD = ImageIO.read(new File("src/img/cursor1.png"));
		    
		    //cursorXD = ImageIO.read(new File("src/img/cursores/flechaRoja.png"));
		    
		    iconoRegistrar = new ImageIcon("src/img/love.png");
		    
		    luna = new ImageIcon("src/img/luna.png");
		    
		    cursorInvisible = ImageIO.read(new File("src/img/cursores/0.png"));
		    
		    cursorporDefecto = config.createCustomCursor(cursorXD, new Point(0,0), "xd");
		    
		    cursorPD2 = config.createCustomCursor(cursorInvisible, new Point(0,0), "");
		    
		}catch(IOException e) {
			System.err.println("No se encontro la imagen");
		}
		
		System.out.println(PantallaDimAlto +"   "+ PantallaDimAncho);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setIconImage(iconoJuego);
		
		setResizable(false);
		
		setTitle("Juego - Beta Version");
		
		setLayout(null);
		
		setBounds(PantallaAncho/4,PantallaAlto/4,PantallaDimAncho/2,PantallaDimAlto/2);//ancho 480 y alto 270
		System.out.println(PantallaDimAlto/4);
		
		setCursor(cursorporDefecto);
		
		menu = new Menu();
		
		juego = new Juego();
		
		estadisticas = new Estadisticas();
		
		estTabla = new EstTabla();
		
		Contador = new Contador();
		
		Ajustes = new Ajustes();
		
		AjustesGeneral = new AjustesGeneral();
		
		
		menu.setVisible(true);
		
		juego.setVisible(false);
		
		estadisticas.setVisible(false);
		
		estTabla.setVisible(false);
		
		Contador.setVisible(false);
		
		Ajustes.setVisible(false);
		
		AjustesGeneral.setVisible(false);

		add(menu);
		
		add(estadisticas);
		
		add(estTabla);
		
		add(Contador);
		
		add(juego);
		
		add(Ajustes);
		
		add(AjustesGeneral);
		
		setVisible(true);
		
		
	}
	/*private boolean registrado() {
		
		while
		
	}*/
	public class Registrar extends JPanel {
		  
		  public Registrar() {
			  
			  setLayout(null);
			  
			  Raton raton = new Raton();
			  //setBackground(new Color(89,10,144));
			  
			  setBackground(new Color(79,4,127));
			  
              usuario = new JTextArea();
			  
			  usuario.setText(usu);
			  
			  usuario.addMouseListener(raton);

			  contra = new JPasswordField();
			  
			  contra.setText(contrasenya);
			  
			  contra.addMouseListener(raton);
			  
			  repContra = new JPasswordField();
			  
			  repContra.setText(contrasenya);
			  
			  repContra.addMouseListener(raton);
			  
			  usuario2 = new JTextArea();
			  
			  usuario2.setText(usu);
			  
			  usuario2.addMouseListener(raton);
			  
			  contraEntrar = new JPasswordField();
			  
			  contraEntrar.addMouseListener(raton);
			  
			  contraEntrar.setText(contrasenya);
			  
			  //setVisible(true);
			  
		  }
		  //fade in and fade out
		  
		  public void paintComponent(Graphics g3) {
			  
			  //repaint();
			  
			  super.paintComponent(g3);
			  
			  Graphics2D g = (Graphics2D) g3;
			  
			  Font jug = new Font("Impact", Font.BOLD, 35);
			  
			  Font jug2 = new Font("Impact", Font.ITALIC, 20);
				
			  //.setBounds(325, 150 -x, 355, 60);
				
			  g.setFont(jug);
			  
			  g.setColor(new Color(31,195,14));
				
			  //.setForeground(new Color(31,195,14));
			  
			  g.drawString("Registrarse:", 25, 40);
			  
			  g.setFont(jug2);
			  
			  g.drawString("Nombre: ", 15, 70);
			  
			  
			  usuario.setFont(jug2);
			  
			  usuario.setBounds(100, 50, 180, 25);
			  
			  usuario.setForeground(new Color(31,195,14));
			  
			  usuario.setBackground(colorMorado);
			  
			  usuario.setTabSize(16);
			  
			  usuario.setVisible(true);
			
			  add(usuario);
			  
			  g.drawString("Contraseña: ", 15, 100);
			  
			  contra.setFont(jug2);
			  
			  contra.setBounds(120, 80, 160, 25);
			  
			  contra.setForeground(new Color(31,195,14));
			  
			  contra.setBackground(colorMorado);
			  
			  contra.setBorder(null);
			  
			  contra.setVisible(true);
			  
			  add(contra);
			  
			  g.drawString("Repitela: ", 15, 130);
			  
              repContra.setFont(jug2);
			  
              repContra.setBounds(120, 110, 160, 25);
			  
              repContra.setForeground(new Color(31,195,14));
			  
              repContra.setBackground(colorMorado);
			  
              repContra.setBorder(null);
			  
              repContra.setVisible(true);
              
              add(repContra);
			  
			  registrarse = new JButton(new Botones2("Registrarse",null,null,0));
			  
			  registrarse.setFont(jug2);
			  
			  registrarse.setBounds(145, 140, 135, 25);
			  
			  registrarse.setForeground(new Color(31,195,14));
			  
			  registrarse.setBackground(colorMorado);
			  
              //registrarse.addActionListener(botones);
			  
			  registrarse.setVisible(true);
			  
			  add(registrarse);
			  
			  borrar = new JButton(new Botones2("Reiniciar",null,null,1));
			  
			  borrar.setFont(jug2);
			  
			  borrar.setBounds(15, 140, 130, 25);
			  
			  borrar.setForeground(new Color(31,195,14));
			  
			  borrar.setBackground(new Color(89,10,144));
			  
			 // borrar.addActionListener(botones);
			  
			  borrar.setVisible(true);
			  
			  add(borrar);
			  
			  g.setFont(jug);
			  
			  g.drawString("Conectarse:", 25, 200);
			  
			  g.setFont(jug2);
			  
			  g.drawString("Nombre: ", 15, 230);
			  
              usuario2.setFont(jug2);
			  
              usuario2.setBounds(100, 210, 180, 25);
			  
              usuario2.setForeground(new Color(31,195,14));
			  
              usuario2.setBackground(colorMorado);
			  
              usuario2.setBorder(null);
			  
              usuario2.setVisible(true);
			  
			  add(usuario2);
			  
              g.drawString("Contraseña: ", 15, 260);
			  
			  contraEntrar.setFont(jug2);
			  
			  contraEntrar.setBounds(120, 240, 160, 25);
			  
			  contraEntrar.setForeground(new Color(31,195,14));
			  
			  contraEntrar.setBackground(colorMorado);
			  
			  contraEntrar.setBorder(null);
			  
			  contraEntrar.setVisible(true);
			  
			  add(contraEntrar);
			  
			  conectarse = new JButton(new Botones2("Conectarse",Ventana.iconoRegistrar,null,3));
              
			  conectarse.setFont(jug2);
			  
              conectarse.setBounds(15, 270, 265, 25);
			  
              conectarse.setForeground(new Color(31,195,14));
			  
              conectarse.setBackground(colorMorado);
			  
			 // borrar.addActionListener(botones);
			  
              conectarse.setVisible(true);
			  
			  add(conectarse);
			  
		  }
		  
		  private class Raton extends MouseAdapter{
				 
				public void mouseClicked(MouseEvent e) {
					
					if(e.getSource().equals(contra)) {
						
						contra.setText("");
						
						contra.repaint();
						//esta mal, hay que arreglarlo
					}else if (e.getSource().equals(repContra)) {
						
						repContra.setText("");
						
						repContra.repaint();
					}else if(e.getSource().equals(contraEntrar)) {
						
						contraEntrar.setText("");
						
						contraEntrar.repaint();
					}else if(e.getSource().equals(usuario)) {
						
						usuario.setText("");
						
						usuario.repaint();
					}else if(e.getSource().equals(usuario2)) {
						
						usuario2.setText("");
						
						//registrar.setVisible(false);
					}
						
			    }
				public void mouseExited(MouseEvent e) {
					
					if(e.getSource().equals(contra)) {
						
					}
				}
		 
		  }
		  
		  
	}
	
	
	/**CLASE MENU QUE CONTENDRA EL INICIO**/
	private class Menu extends JPanel{
		
		public Menu(){
			setCursor(cursorporDefecto);
			//System.out.print(Principal.jugadors[0][0]);
			
			setLayout(null);
			
			setBounds(0,0,PantallaDimAncho/2,PantallaDimAlto/2);
			
		    setBackground(new Color(89,10,144));
		    
		    int x = 10;
		    
		    Botones botons = new Botones();
			
			iniciar = new JButton("Jugar");
			
			iniciar.addActionListener(botons);
			
			iniciar.setBackground(new Color(79,4,127));
			
			Font jug = new Font("Impact", Font.BOLD, 50);
			
			iniciar.setBounds(325, 150 -x, 355, 60);
			
			iniciar.setFont(jug);
			
			iniciar.setForeground(new Color(31,195,14));
			
			iniciar.setVisible(true);
			
		
			
			record = new JButton("Estadisticas");
			
		    record.addActionListener(botons);
			
	        record.setBackground(new Color(79,4,127));
			
			record.setBounds(325, 235 - x, 355, 60);
			
			record.setFont(jug);
			
			record.setForeground(new Color(31,195,14));
			
			record.setVisible(true);
			
			
			
			salir = new JButton("Salir");
			
            salir.addActionListener(botons);
			
			salir.setBackground(new Color(79,4,127));
			
			salir.setBounds(325,415 - x,355,60);//325,415 - x,355,60
			
			salir.setFont(jug);
			
			salir.setForeground(new Color(31,195,14));
			
			salir.setVisible(true);
			
			
			
			ajustes = new JButton("Ajustes");
			
			ajustes.addActionListener(botons);
				
			ajustes.setBackground(new Color(79,4,127));
				
			ajustes.setBounds(325,325 - x,355,60);
				
			ajustes.setFont(jug);
				
			ajustes.setForeground(new Color(31,195,14));
				
			ajustes.setVisible(true);
			
			
			Rata raton = new Rata();
			
			registrar = new Registrar();
			
			//registrar.setCursor(Cursor.getPredefinedCursor(Cursor));
			
			registrar.addMouseListener(raton);
			
			registrar.setBounds(posPanRegX,posPanRegY, dimAlReg,dimAnReg);//290  324  20 140
			
			registrar.setVisible(false);//true
			
			registrar.setFocusable(true);
			
			
			menuReg = new JButton(new Botones2("", imgCuenta, null, 4));
			
			menuReg.setBounds(700,405,60,60);
			
			//menuReg.setIcon();//imgCuenta 
			
			menuReg.setVisible(true);
			
			
			
			add(iniciar);
			
			add(record);
			
			add(salir);
			
			add(ajustes);
			
			add(registrar);
			
			add(menuReg);
			
		}
		
		/**listener**/
		
		
		/**Graphics 2D menu**/
		public void paintComponent(Graphics g1){
			super.paintComponent(g1);
			Graphics2D g = (Graphics2D) g1;
			
			Font fuente = new Font("Impact", Font.BOLD , 60);//50
			
			g.drawImage(laberinto,0,0, PantallaDimAncho/ 2,PantallaDimAlto / 2,null);
			
			g.setFont(fuente);
			
			//g.setColor(new Color);
			
			g.setColor(new Color(72,179,25));
			
			g.drawString("EL LABERINTO", 330, 100);//330
			
            g.setColor(new Color(18,123,36));
            
            Font fuente1 = new Font("Impact", Font.BOLD , 65);//55
            
			g.drawString("EL LABERINTO", 328, 100);//328
			
		}//fin paint component
		
		/**mouse listener del menu**/
        private class Rata extends MouseAdapter{
        	
        	  public Rata() {}
        	  
        	  public void mouseEntered(MouseEvent e) {
        		  
        		  //System.out.println("Estas dentro del panel");
        		  
        		 // posPanRegX -= 15;//efecto del aumento
        		  
        		  //posPanRegY += 15;
        		 
        		  //repaint();
        	  }
        	
        	  public void mouseExited(MouseEvent e) {
        		  
        		  //System.out.println("Estas fuera del panel");
        		  
                  //posPanRegX += 15;//efecto del aumento
        		  
        		 // posPanRegY -= 15;
        		  
        		  //repaint();
        		  
        		  
        	  }
        }
       
		
	}//fin menu
	private class AjustesGeneral extends JPanel{
		public AjustesGeneral() {
			
			int x = 10;
			
			setLayout(null);
			   
			setBackground(new Color(89,10,144));
			   
		    setBounds(0,0,PantallaDimAncho/2,PantallaDimAlto/2);
		    
		    volver2 = new JButton(new Botones2("Volver",null, null, 5));
			   
		    volver2.setBounds(325, 415 - x, 355 , 60);
			   
		    volver2.setBackground(new Color(79,4,127));
				
		    Font jug = new Font("Impact", Font.BOLD, 40);
				
		    volver2.setFont(jug);
				
		    volver2.setForeground(new Color(31,195,14));
			   
		    //volver1.addActionListener(botones);
			   
			volver2.setVisible(true);
			
			ajustes1 = new JButton("Ajustes de fondo");
			
			ajustes1.setBounds(325, 150 - x, 355 , 60);
			
			ajustes1.setBackground(new Color(79,4,127));
				
		    ajustes1.setFont(jug);
				
		    ajustes1.setForeground(new Color(31,195,14));
			   
		    ajustes1.addActionListener(botones);
			   
			ajustes1.setVisible(true);
			
			idiomas = new JButton(new Botones2("Idiomas", null, null, 6));
			
            idiomas.setBounds(325, 235 - x, 355 , 60);
			
            idiomas.setBackground(new Color(79,4,127));
				
            idiomas.setFont(jug);
				
            idiomas.setForeground(new Color(31,195,14));
            
            idiomas.setVisible(true);
           

			add(volver2);
			
			add(ajustes1);
			
			add(idiomas);
		}
		
		/**Graphics 2D ajustes general**/
		public void paintComponent(Graphics g1){
			super.paintComponent(g1);
			Graphics2D g = (Graphics2D) g1;
			
			Font fuente = new Font("Impact", Font.BOLD , 60);//50
			
			g.drawImage(laberinto,0,0, PantallaDimAncho/ 2,PantallaDimAlto / 2,null);
			
			g.setFont(fuente);
			
			//g.setColor(new Color);
			
			g.setColor(new Color(72,179,25));
			
			g.drawString("EL LABERINTO", 330, 100);//330
			
            g.setColor(new Color(18,123,36));
            
            Font fuente1 = new Font("Impact", Font.BOLD , 65);//55
            
			g.drawString("EL LABERINTO", 328, 100);//328
			
		}//fin paint component
		
		
	}
	
	
     //inicio ajustes
	private class Ajustes extends JPanel{
		  public Ajustes() {
			
			   setLayout(null);
			   
			   setBackground(new Color(89,10,144));
			   
			   setBounds(0,0,PantallaDimAncho/2,PantallaDimAlto/2);
			   
			   volver1 = new JButton("Volver");
			   
			   volver1.setBounds(PantallaDimAncho / 4 - 161, PantallaDimAlto / 4 - 40, 320,50);
			   
			   volver1.setBackground(new Color(79,4,127));
				
			   Font jug = new Font("Impact", Font.BOLD, 40);
				
			   volver1.setFont(jug);
				
			   volver1.setForeground(new Color(31,195,14));
			   
			   volver1.addActionListener(botones);
			   
			   volver1.setVisible(true);
			   
			   Font cl = new Font("Impact", Font.PLAIN, 30);
			   
			   rojo = new JButton("Fondo rojo");
			   
			   rojo.addActionListener(botones);
			   
			   rojo.setBackground(new Color(79,4,127));
			   
			   rojo.setBounds(PantallaDimAncho / 2 - 310, PantallaDimAlto / 4 -40, 250, 50);
			   
			   rojo.setFont(cl);
			   
			   rojo.setForeground(new Color(31,195,14));
			   
			   rojo.setVisible(true);
			   
			   
			   azul = new JButton("Fondo azul");
               
			   azul.addActionListener(botones);
			   
			   azul.setBackground(new Color(79,4,127));
			   
			   azul.setBounds(60, PantallaDimAlto / 4 -40, 250, 50);
			   
			   azul.setFont(cl);
			   
			   azul.setForeground(new Color(31,195,14));
			   
			   azul.setVisible(true);
			   
			   
			   gris = new JButton("Fondo cyan-gris");
        
			   gris.addActionListener(botones);
			   
			   gris.setBackground(new Color(79,4,127));
			   
			   gris.setBounds(PantallaDimAncho / 2 - 310, 430, 250, 50);
			   
			   gris.setFont(cl);
			   
			   gris.setForeground(new Color(31,195,14));
			   
			   gris.setVisible(true);
			   
			   
			   cyan = new JButton("Fondo cyan");

			   cyan.addActionListener(botones);
			   
			   cyan.setBackground(new Color(79,4,127));
			   
			   cyan.setBounds(60, 430, 250, 50);
			   
			   cyan.setFont(cl);
			   
			   cyan.setForeground(new Color(31,195,14));
			   
			   gris.setVisible(true);
			   
			   
			   
			   
			   add(volver1);
			   
			   add(rojo);
			   
			   add(cyan);
			   
			   add(azul);
			   
			   add(gris);
			   
			   setVisible(false);
		  }
		  
		  public void paintComponent(Graphics g5) {
			  
			  super.paintComponent(g5);
			  
			  Graphics2D g = (Graphics2D) g5;
			  
			  g.drawImage(laberinto,0,0, PantallaDimAncho/ 2,PantallaDimAlto / 2,null);
			  
			  g.setColor(Principal.colorElegidoSecundario);
			  
			  g.fillRect(PantallaDimAncho / 4 - 210, 10, 430, 60);
			  
			  g.setFont(new Font("Impact", Font.BOLD, 50));
			  
			  g.setColor(new Color(18,123,36));
			  
			  g.setColor(Principal.colorElegidoPrimario);
			  
			  g.drawString("Ajustes del Fondo", PantallaDimAncho / 4 - 200, 60);	
			  
			  g.drawImage(Azul, 60,80,250,150, null);
			  
			  g.drawImage(Rojo, PantallaDimAncho / 2 - 310,80,250,150, null);
			  
			  g.drawImage(Cyan, 60,280,250,150, null);
			  
			  g.drawImage(Gris, PantallaDimAncho / 2 - 310,280,250,150, null);
		  }
	}
	protected class Contador extends JPanel{
		
		  public Contador() {
			  
			    setLayout(null);
			    
			    setBounds(0,PantallaDimAlto/2-98,PantallaDimAncho,98);
			    
			    setBackground(new Color(89,10,144));
			    
			    guardar = new JButton("Guardar");
			    
			    volver = new JButton("Salir");
			    
			    guardar.addActionListener(botones);
			    
			    volver.addActionListener(botones);
			    
			    guardar.setBounds(PantallaDimAncho/2- 165, 17, 150 , 31);
			    
			    volver.setBounds(PantallaDimAncho/2- (165 + 160), 17, 150 , 31);
			    
                guardar.setBackground(new Color(103,4,85));
               
		        volver.setBackground(new Color(103,4,85));
              
		        guardar.setFont(new Font("Impact", Font.BOLD, 25));
		        
		        guardar.setForeground(new Color(31,195,14));
		        
                volver.setFont(new Font("Impact", Font.BOLD, 25));
		        
		        volver.setForeground(Color.RED);
			    
			    guardar.setVisible(true);
			    
			    volver.setVisible(true);
			    
			    score scor = new score();
			    
			    scor.setVisible(true);
			    
			    add(guardar);
			    
			    add(volver);
			    
			    add(scor);
		  }
		  	
		  
		
	}//fin del contador 
	
	public class score extends JPanel{
		  
		   public score() {
			   
			   setLayout(null);
			   
			   setBackground(new Color(103,4,85));
			   
			   setBounds(6,6,620,51);
			   
			   setVisible(true);
			   
		   }  
		   public void paintComponent(Graphics g2) {
				  
			   super.paintComponent(g2);
				  
			   Graphics2D g = (Graphics2D) g2;
				  
			   Font fuente = new Font("Impact", Font.BOLD, 20);
				  
			   g.setFont(fuente);
				  
			   g.setColor(new Color(31,195,14));
				  
			   g.drawString("Puntuacion:", 12, 23);	
			   
			   g.drawString("Record:", 12, 46);//antes 260, 23
			   
			   //paso enteros a string
			   
			   String ContadorMinS = "0" + Integer.toString(ContadorMin);
			   ContadorMinS = ContadorMinS.substring(ContadorMinS.length() - 2);
			   
			   String ContadorSegS = "0" + Integer.toString(ContadorSeg);
			   ContadorSegS = ContadorSegS.substring(ContadorSegS.length() - 2);
			   
			   String ContadorHoraH = "0" + Integer.toString(ContadorHora);
			   ContadorHoraH = ContadorHoraH.substring(ContadorHoraH.length() - 2);
			   
			   g.drawString("Tiempo Transcurrido:   ", 280, 23);
			   
			   g.setColor(new Color(241,196,15));
			   
			   g.drawString(ContadorHoraH + "  :  " + ContadorMinS +  "  :  " +  ContadorSegS, 490, 23);
			   
			   ContadorSeg ++ ; 
			   
			   if(ContadorSeg > 58) {
				   
				   ContadorMin ++;
				   
				   ContadorSeg = 0;
				   
			   }
			   
			   
			   /*if(iniCrono == true) {
				   
				   try {
					   
					   int min= 0, seg = 0;
					   
					   while(iniCrono == true) {
						   
						    Thread.sleep(1000);

					        g.drawString(min + " : " + seg, 310, 23);
					        
					        
					        seg++;
					        if(seg > 58) {
					        	min++;
					        	
					        	seg= 0;
					        }
					   }
				   }catch(Exception e){
					   
					   ;
				   }
				   
			   }*/
		   }
	  }
	//fin de score
	
	protected class Juego extends JPanel {
	
	     public int cordX, cordY;
		 
		 public Juego() {  
			 
			    Raton raton= new Raton();
			    
			    Teclado teclado = new Teclado();
			    
			    addKeyListener(teclado);
			    
			    addMouseListener(raton);
			 
			    setLayout(null);
				
				setBounds(0,0,PantallaDimAncho/2,PantallaDimAlto/2 - 98);
	
			    setBackground(Principal.COLOR_FONDO_JUEGO);//66,7,79
			  
			    System.out.println(PantallaDimAncho/2 + "y " + (PantallaDimAlto/2 - 80));//960 y 460
			    
			    System.out.println("Es focusable??:   " + isFocusable());
				
				setFocusTraversalKeysEnabled(false);
				
				setFocusable(true);
				
			    
		 }		  
		 public void paintComponent(Graphics g2) {
			  
			  super.paintComponent(g2);
			  
			  Graphics2D g = (Graphics2D) g2;
			  
			  Font fuente = new Font("Impact", Font.PLAIN, 25);
			  
			  g.setFont(fuente);
			  
			  g.setColor(new Color(20,106,26));
              
			  int esp = 40, sep = 80; 
			  
			  g.drawString("S", 12, esp + sep);g.drawString("A", 12, 2* esp + sep);g.drawString("L", 12, 3 *esp + sep);
			  g.drawString("I", 12, 4 * esp + sep);g.drawString("D", 12, 5* esp + sep);g.drawString("A", 12, 6* esp + sep);

			  g.setColor(Color.red);
			//960 y 460  ---- x = 20 && y =11
			  //dibujo columnas
			  for(int l = 1; l < 46; l++) {//96<48<24
				  
				  g.drawRect(l*20 + 20, 0, 1, PantallaDimAlto/2-80);

			  }
			  //dibujo filas
			  
			  for(int k = 0; k < 24; k++) {//int k = 0; k < 12; k++
				  
				  g.drawRect(40, k * 20, PantallaDimAlto - (139*2) + 77, 1);//original  g.drawRect(40, k * 40, PantallaDimAlto - (139*2) + 77, 1);
			  }
			  /**RELLENO EL CAMPO**/
			  
			  //g.setColor(Color.CYAN);
			  for(int k = 0; k < Principal.DIM_TABLON_JUEGO_X; k ++) {
				  
					for(int h = 0; h < Principal.DIM_TABLON_JUEGO_Y; h ++) {
											
						char uno = '1', cero = '0';
						
						if(Principal.camp[k][h] == uno) {
							
							g.setColor(Principal.colorElegidoPrimario);//172,5,5      20,126,27  Principal.colorElegidoPrimario
							
							g.fillRect(40 + h* 20 + 3 , k * 20 + 3, 16, 16);//medida original h*40 + 5   32 32
							
							
							
						}else if(Principal.camp[k][h] == '0'){
							g.setColor(Principal.colorElegidoSecundario);//25,25,224   21,86,120
							
                            g.fillRect(40 + h* 20 + 3 , k * 20 + 3, 16, 16);	
                        
						}else if(Principal.camp[k][h] == '2') {
							
							g.setColor(Color.MAGENTA);
							
							g.fillRect(40 + h*20+3, k*20+3, 16, 16);
					
						}else if(Principal.camp[k][h] == '4') {
							g.setColor(Color.ORANGE);
							
							g.fillRect(40 + h*20 + 3, k*20+3, 16, 16);
						}
					}
				}
			    
			    g.setColor(Color.PINK);
			    
			    //g.fillRect(posIniPX*20 + 40 +3, posIniPY * 20 +3, 16, 16);//original : g.fillRect(posIniPX*40 + 40 +5, posIniPY * 40 +5, 32, 32);
			    
			    g.drawImage(skinDiablo1, posIniPX *20 + 43, posIniPY * 20 + 3, 16,16, null);
			    
			    if(comenzar == true) {
			    	g.setFont(new Font("Impact",Font.BOLD, 60));
			    	
			    	g.setColor(new Color(241,91,0));
			    	
			    	g.drawString(Ventana.presionaParaComenzar, 2, PantallaDimAlto/4 -42);
			    	
			    	g.setColor(new Color(217,224,18));
			    	
			    	g.drawString(Ventana.presionaParaComenzar , 0, PantallaDimAlto/4 -40);
			    }else {iniCrono = true;}
			    
		 }
		 
		 private class Teclado implements KeyListener{		    
			    public Teclado() {		    	
			    }		 
			    public void keyTyped(KeyEvent e) {		 		  
					  if(e.getKeyCode() == KeyEvent.VK_S) {					    	//System.out.println("Tecla " + e.getKeyChar() + "  presionada y soltada");
					  }else {//System.out.println("Tecla " + e.getKeyChar() + "  presionada y soltada"); 					  
					  }
			    }
				 public void keyPressed(KeyEvent e) {
					  if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {			  
						  if((Principal.camp[posIniPY - 1][posIniPX]) == '0' || (Principal.camp[posIniPY-1][posIniPX]) == '2' && posIniPY > 0) {
							  posIniPY --; juego.repaint(); 
						  }
					  }else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {					  
						  System.out.println(Principal.camp[3][2]);					  
						  if((Principal.camp[posIniPY + 1][posIniPX]) == '0' || (Principal.camp[posIniPY+1][posIniPX]) == '2' && posIniPY > 0) {	 
							  posIniPY ++; juego.repaint(); 
						  }
					  }else  if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
						  if((Principal.camp[posIniPY][posIniPX - 1]) == '0' || (Principal.camp[posIniPY][posIniPX - 1]) == '2' && posIniPX > 0) {
							  posIniPX--; juego.repaint(); 
						  }
					  }else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {  
						  if((Principal.camp[posIniPY][posIniPX + 1]) == '0' || (Principal.camp[posIniPY][posIniPX + 1]) == '2' &&  posIniPX > 0) {	
							  posIniPX ++; juego.repaint(); 
						  }
					  }			
				  }
			     public void keyReleased(KeyEvent e) {
			    	 juego.repaint();
				  } 
			}	 
		   /**DELAY WITH THREAD**/     
	     protected void delay(int cantidad){	    	 
	         try { Thread.sleep(cantidad); } catch (Exception e) { ; }
	     }	     
	     /**La clase raton**/
		 protected class Raton extends MouseAdapter{		 
			public void mouseClicked(MouseEvent e) {			
				if(juego.isVisible()) {
				juego.setFocusable(true);
				
				juego.setFocusCycleRoot(true);
				
				juego.setFocusTraversalKeysEnabled(false);
				
				juego.requestFocusInWindow();
				
				}
				System.out.println("Esta enfocada la ventana? "  +juego.isFocusable());
				
				int cantidadClics = e.getClickCount();
				
				int ratonX = e.getX(), ratonY = e.getY();
				
				int botonRaton = e.getButton();
				
				if(botonRaton == MouseEvent.BUTTON1) {
					
					comenzar = false; juego.setCursor(cursorPD2); juego.repaint();
					
					if(runThread == false) {
						
						hilo = new Contador1(Contador);
						
						hilo.start();
						
						runThread = true;
					}
					
				    System.out.println("click    raton x  " + ratonX + "  y:" + ratonY +  "  con boton izquierdo con "+ cantidadClics + " clicks" );
				}else if(botonRaton == MouseEvent.BUTTON3) {
					
					System.out.println("click    raton x  " + ratonX + "  y:" + ratonY + "  con boton derecho "+ cantidadClics + " clicks" );
				}
			}

			public void mousePressed(MouseEvent e) {
				comenzar = false;juego.repaint();
			}

			public void mouseReleased(MouseEvent e) {
				comenzar = false;juego.repaint();
			}
			 
		 }
		  /**@param reibuja el jugador en la posicion correspondiente 
	      * <b> -1 xNegativo, +1 xPositivo, -2 yNegativo, +2 yPositivo</b>**/

	}//fin juego
	
	
	//fin juego
	
	private class Estadisticas extends JPanel{
		
		 private Image copa;
		
		 public Estadisticas(){
			 
			     //añado cursor
			    setCursor(cursorporDefecto);
			 
			    setLayout(null);
			
			    setBounds(0,0,PantallaDimAncho/2,PantallaDimAlto/2);
			
		        setBackground(new Color(243,156,18));//new Color(66,7,79)new Color(127,4,108)
		        
		        
		        
		        menuPpal = new JButton("Volver al menu principal");
		        
		        botones = new Botones();
		        
		        menuPpal.addActionListener(botones);
		        
		        menuPpal.setBounds(400, 400, 400, 50);
		        
		        menuPpal.setBackground(new Color(103,4,85));
		        
		        menuPpal.setFont(new Font("Impact", Font.BOLD, 30));
		        
		        menuPpal.setForeground(new Color(31,195,14));
		        
		        menuPpal.setVisible(true);
		        
		        add(menuPpal);
		        
		 }//fin constructor
		 
		 public void paintComponent(Graphics g1) {
			 super.paintComponent(g1);
			 
             try {
				 
            	 copa = ImageIO.read(new File("src/img/copa.png"));
            	 
            	 laberinto = ImageIO.read(new File("src/img/laberinto.png"));
            	 
			 }catch(IOException e){
				 
				 System.err.println("No se encontro la imagen");
			 }
			 Graphics2D g = (Graphics2D) g1;
			 
			 Font titulo = new Font("Impact", Font.BOLD, 60);
			 
			 g.setColor(new Color(66,66,66));
			 
			 g.drawImage(laberinto,0,0,PantallaDimAncho/2, PantallaDimAlto/2,null);
			 
			 g.drawImage(copa, 50, 130, 350 , 250, null);
			 
			 g.fillRect(0, 20, PantallaDimAncho, 80);
			 
			 int ancho = 0;
			 
			 for(int i = 0; i < 9 ; i++) {  g.drawImage(laberinto, ancho, 20, PantallaDimAncho / 10, 80, null); ancho += PantallaDimAncho/10;}
			 
			 g.setColor(new Color(255,210,0));
			 
			 g.setFont(titulo);
			 
			 g.drawString("ESTADISTICAS", 300 , 80);
			 
			 g.setColor(new Color(165,2,17));
			 
			 g.setFont(new Font("Impact", Font.BOLD, 59));
			 
			 g.drawString("ESTADISTICAS", 300 , 80);
			 
			 g.setColor(new Color(207,34,34));
			 
			 g.drawRect(400, 120, 400, 280);
		 }
		 
	}//fin estadisticas
	 private class EstTabla extends JPanel{
		 
		 public EstTabla() {
			 
			 setBackground(new Color(0,5,204));
			 
			 setBounds(400,120,400,280);
			 
			 setVisible(true);
			 
		 }
		 
	 }
}

/**CLASE QUE LLAMA AL ARCHIVO**/
class Archivo {
	File file;
	public Archivo() {
		try {
			FileReader fr = new FileReader("src/lab1.txt");
			
		}catch(IOException e) {
			
			System.err.println("No se puede sacar el archivo");
		}
		
	}
	
	
}
class Info extends JPanel{
	
	public Info() {}
	
	public static Info info(String descripcio, int posX, int posY) {
		
		Info panel = new Info();
		
		panel.setLayout(null);
		
		panel.setBounds(posX , posY, 50 , 70);
		
		panel.setVisible(true);
		
		return panel;
	}
}

//clase del idioma


class Idioma{
	
	public Idioma() {

		
	}	
}
//socket

class mail{
	
	public mail() {
	
		
	}
	
}

/**clase servidor**/
class Servidor extends Thread{

	
	Socket servicio = null;
	
	DataInputStream flujoDeEntrada = null;
	
	DataOutputStream flujoDeSalida = null;
	
	public Servidor(Socket servicio, DataInputStream x, DataOutputStream y) {
		
		this.servicio = servicio;
		
		flujoDeEntrada = x;
		
		flujoDeSalida = y;
		
	}
	
	public void run() {
		
		System.out.println("Se acepto la coneccion nueva");
		
		try {
			
			String mensaje = flujoDeEntrada.readUTF();
			
			System.out.println(mensaje);
			
		}catch(Exception e) {}
	}
}


