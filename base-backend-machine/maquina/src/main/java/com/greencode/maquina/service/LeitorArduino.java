package com.greencode.maquina.service;

import java.io.InputStream;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.greencode.maquina.model.Reciclavel;

public class LeitorArduino {
	
	private List<Reciclavel> reciclados; 
	private boolean ler;
	
	public LeitorArduino() {
		
	}
	
	public void lerPortaUsb(Reciclavel reciclavel) {
		SerialPort comPort = SerialPort.getCommPort("/dev/ttyACM0"); //linux
		//SerialPort comPort = SerialPort.getCommPort("COM3"); //windowns
		
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		InputStream in = comPort.getInputStream();
		try {
			while(ler) {
				char dado = ' ';
				dado = (char) in.read();
				
				if(dado == ' ') {
					reciclados.add(reciclavel);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		comPort.closePort();
	}

	public boolean isLer() {
		return ler;
	}

	public void setLer(boolean ler) {
		this.ler = ler;
	}
	
	
	
}
