import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class hola extends hola2 implements Serializable{
	String hola;

	public hola(int num, int m, String j) {
		super(num, m); this.hola = j;
	}
	
	public String getHola() {
		return this.getHola2() + " " +this.hola;
	}
	
	public static void escr (){
		
		hola[] ho = new hola[2];
		ho[0] = new hola(1,1,"holamundo");
		ho[1] = new hola(2,2,"holamundo2");
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("hola3.txt",false));
			os.writeChars("probando");
			os.writeObject(ho);
			os.close();
			System.out.println("ol");
			
			PrintWriter pw = new PrintWriter(new FileOutputStream("hola.txt",true));
			pw.println("pirula");
			pw.close();
			
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("hola3.txt"));
			System.out.println(is.readLine());
			
			/*try {
				//hola[] l = (hola[])is.readObject();
				//System.out.println(l);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void j () {
		escr();
		/*File fs= new File("hola.txt");
		 if (fs.exists()) System.out.println("El fichero existe!");
		 else System.err.println("El fichero NO existe!");
		 System.out.println("getName(): " + fs.getName());
		 System.out.println("getParent(): " + fs.getParent());
		 System.out.println("length(): " + fs.length());
		 System.out.println("dir(): " + fs.isDirectory());
		 System.out.println(fs.exists());*/
	}
	public static void main(String[] str) throws ExcepcionOpcionFueraDeRango {
	
		hola h = new hola(1,2,"h");
		
		int[] d = new int[5];
		int hg = d.length;
		String hola = "hola";
		hg = hola.length();
		System.out.println(h.getHola());
		//throw new ExcepcionOpcionFueraDeRango("hh");
		j();
	}
}

class hola2{
	int num;
	int m;
	public hola2(int num, int m) {
		this.num = num;
		this.m = m;
	}
	public String getHola2() {
		return ""+this.num + " "+ this.m;
	}
}
class ExcepcionOpcionFueraDeRango extends Exception {
	 public ExcepcionOpcionFueraDeRango(String msg){ super(msg); }
	} 