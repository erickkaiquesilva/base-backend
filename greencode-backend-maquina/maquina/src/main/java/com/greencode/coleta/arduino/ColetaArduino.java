package com.greencode.coleta.arduino;

import com.fazecast.jSerialComm.SerialPort;
import com.greencode.maquina.gui.Reciclando;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColetaArduino {

    private SerialPort comPort = SerialPort.getCommPort("/dev/ttyACM0");
    //private SerialPort comPort = SerialPort.getCommPort("COM3");
    public boolean CapturaDados(boolean wait) {

        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = comPort.getInputStream();
        try {
            
            if (in.read() < 20 && !wait) {
               return true;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public SerialPort getComPort() {
        return comPort;
    }
}
