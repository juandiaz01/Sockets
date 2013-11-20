package logic;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Conexion implements Runnable{


	private Socket sock;
	private DataOutputStream DOutputStream;
	private DataInputStream DInputStream;

	private Thread hilo;
	private boolean pause;
	private boolean control;

	public Conexion(Socket nuevaConexion) {
		sock = nuevaConexion;
		try {
			DInputStream = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			System.out.println("Error al crear el canal de entrada");
			e.printStackTrace();
		}
		try {
			DOutputStream = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error al crear el canal de salida");
			e.printStackTrace();
		}
	}


	public void run() {
		while(control){
			try{
				hilo.sleep(50);
			}catch(InterruptedException e) {
				return;
			}
			System.out.println("Hola");

		}
	}
	public void stop(){
		control  = false;

	}
	public void start(){
		hilo.start();
	}
	public void cerrarConexion(){
		try {
			DInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
