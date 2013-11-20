package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Runnable {

	private ServerSocket servS;
	private ArrayList conexiones;
	private Socket sock;
	private String direccion;
	private int puerto;

	private Thread hilo;
	private boolean pause;
	private boolean control;
	public Servidor() {


		puerto = 4001;
		conexiones = new ArrayList<Conexion>();
		this.hilo = new Thread(this);
		this.control = true;
	}


	public void run() {


		while(control){
			try{
				hilo.sleep(50);
			}catch(InterruptedException e) {
				return;
			}
			System.out.println("Esperando conexiones");
			try {
				sock = servS.accept();
				conexiones.add(new Conexion(sock));
				System.out.println("Nueva conexion aceptada");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void stop(){
		control  = false;

	}
	public void start(){
		hilo.start();
	}

	public void iniciarServidor() {

		if(servS == null){
			try {
				servS = new ServerSocket(puerto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
