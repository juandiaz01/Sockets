package logic;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Cliente implements Runnable {

	private Socket sock;
	private DataOutputStream DOutputStream;
	private DataInputStream DInputStream;
	private String direccion;
	private int puerto;
	private int opcion;

	private Thread hilo;
	private boolean pause;
	private boolean control;

	public Cliente() {
		try {
			sock = new Socket("localhost", 4001);//(direcion,puerto)
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Error en el host");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error en el puerto");
		}
		try {
			DInputStream = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al crear el canal de entrada");
		}
		try {
			DOutputStream = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al crear el canal de salida");
		}
		opcion = 0;
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


	public void run() {
		while(control){
			switch (opcion) {
			case 1:
				try {
					System.out.println("se recibio "+DInputStream.readUTF());
					DOutputStream.writeInt(1);
					DOutputStream.writeUTF(" hola");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				break;
			case 2:

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
			//System.out.println("Hola");
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

}
