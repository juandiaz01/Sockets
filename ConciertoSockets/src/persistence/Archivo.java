package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

	private BufferedReader bReader;
	private File archivo;
	private String ruta;
	private FileReader fReader;
	private ArrayList letraCancion;
	
	public Archivo(String nombre) {
		archivo = new File(" ");
		ruta = archivo.getAbsolutePath()+nombre+".txt";
		letraCancion = new ArrayList<String>();
	}
	

	public void abrirArchivo() {
		try {
			fReader = new FileReader(ruta);
			bReader = new BufferedReader(fReader);
		} catch (Exception e) {
			System.out.println("Error al abrir el archivo");
		}
	}
	public void leerArchivo() {
		String linea = "";
		abrirArchivo();
		
		try {
			while ((linea = bReader.readLine())!=null) {
				letraCancion.add(linea);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void cerrarArchivo() {
		try {
			bReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList getLetraCancion() {
		return letraCancion;
	}
	
	public void setLetraCancion(ArrayList letraCancion) {
		this.letraCancion = letraCancion;
	}
}
