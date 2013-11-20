package logic;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Conexion implements Runnable{


	private Socket sock;
	private DataOutputStream DOutputStream;
	private DataInputStream DInputStream;
	private int opcion;

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
		opcion = 0;
	}
	public void iniComunicacion() {

		try {
			DOutputStream.writeInt(1);
			DOutputStream.writeUTF(" mensaje");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		while(control){
			try {
				opcion = DInputStream.readInt();
			} catch (IOException e1) {
				//e1.printStackTrace();
				System.out.println("no se recibio la opcion");
			}

			switch (opcion) {
			case 1:
				try {
					System.out.println(DInputStream.readUTF());

				} catch (IOException e1) {
					//e1.printStackTrace();
				}
				break;
			case 2:
				try {
					iniComunicacion();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				break;
			case 3:

				break;

			default:
				break;
			}
			try{
				hilo.sleep(50);
			}catch(InterruptedException e) {
				return;
			}
			System.out.println("Hola");
		}
		while (pause)

			try {
				wait();
			} catch (InterruptedException e) {
				//e.printStackTrace();
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
