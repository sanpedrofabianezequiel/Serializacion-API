package Serializacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Serializacion {

	public static void main(String[] args) {
		//implements Serializable  en las clases que queremos Serializar
		//Luego en el MAIN ponemos la siguiente logica
		//ObjectoOutputStram var=new objectOutputStream(new fileinputStram("url"))
		//var.writeObject(Objeto que queremos serealizar o tomar y enviar a un archivo previamente creado);
		//var.close() close conexion
		Administrador jefe=new Administrador("Ezequiel", 50000, 2020, 04, 15);
			//Setiamos un incentivo al Jefe
		jefe.setIncentivo(5000);
		
		Empleado[] empleados=new Empleado[3];	//Creo un Array size=3 del tipo Empleado
												//Luego le asigno el NEW de cada uno por POLIMORFISMO
		empleados[0]=jefe;	//polimorfismo//Herencia mas precisamente en este ejemplo
		empleados[1]=new Empleado("Ana", 25000, 2020, 10, 1);
		empleados[2]=new Empleado("Luis", 18000, 2012, 9, 15);
		
		//-------------------------OUT DATA
		try {
			ObjectOutputStream dataOut= new ObjectOutputStream(new FileOutputStream("C:\\Users\\Ezequiel\\Desktop\\Programas\\Java\\dataObjeto.txt"));
			dataOut.writeObject(empleados);//PARAMETROS= EL OBJETO QUE QUEREMOS ALMACENAR
			dataOut.close();
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		//------------------------INPUT DATA
		
		try {
			ObjectInputStream dataIn=new ObjectInputStream(new FileInputStream("C:\\Users\\Ezequiel\\Desktop\\Programas\\Java\\dataObjeto.txt"));
			
				//var.readObject() devuelve un tipo Object, en este caso necesitamos un casteo al tipo que lo queremos
				//CASTEAMOS con el TIPO y si es ARRAY
			Empleado[] empleadosIn= (Empleado[]) dataIn.readObject();
			dataIn.close();
			
			//Necesitamos un forEch para leerlo
			for (Empleado var : empleadosIn) {
				System.out.println(var);
			}
		
	
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		
		
		
		
	}

}
class Empleado implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private double sueldo;
	private Date fechaContrato;
	
	
	public Empleado(String nombre, double sueldo, int año, int mes ,int dia) {//Constructor
		this.nombre=nombre;
		this.sueldo=sueldo;
		
		GregorianCalendar miCalendario=new GregorianCalendar(año, mes, dia);
		this.fechaContrato = miCalendario.getTime();
		
	}
	
	public String getNombre() {//Getter y Setter
		return nombre;
	}
	public double getSueldo() {//Getter y Setter
		return sueldo;
	}
	public Date getFechaContrato() {//Getter y Setter
		return fechaContrato;
	}
	
	public void subirSueldo(double porcentaje) {//Metodo Aumentar sueldo
		double aumento=sueldo*porcentaje/100;
		sueldo += aumento;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", sueldo=" + sueldo + ", fechaContrato=" + fechaContrato + "]";
	}
	
}	


class Administrador extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double incentivo;
	
	
	public Administrador(String nombre, double sueldo, int año, int mes, int dia) {//Usamos+Super el contructor de la clase padre,podriamos agregarle parametros si se necesitara
		super(nombre, sueldo, año, mes, dia);

	}
	
	public double getSueldo() {
		double sueldoBase= super.getSueldo(); 	//Obtenemos el sueldo de Base de la clase PADRE y luego lo modificamos
		return sueldoBase +incentivo;
	}
	public void setIncentivo(double incentivo) {
		this.incentivo=incentivo;
	}
	
	public String toString() {
		
		return super.toString() + ".Ademas cuenta con un Incentivo de: " +incentivo;
	}
	
	
}