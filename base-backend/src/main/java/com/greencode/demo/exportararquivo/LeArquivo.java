package com.greencode.demo.exportararquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class LeArquivo {
		
	public static void leArquivo(String nomeArq) {
		BufferedReader entrada = null;
		String registro;
		String tipoRegistro;
		String produto, valor, dataHora, cpf;
		int qtdFalta, contRegistro=0;
		
		// Abre o arquivo
		try {
			entrada = new BufferedReader(new FileReader(nomeArq));
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		// L� os registros do arquivo
		try {
			// L� um registro
			registro = entrada.readLine();
			
			while (registro != null) {
				// Obt�m o tipo do registro
				tipoRegistro = registro.substring(0, 2);
				
				if (tipoRegistro.equals("01")) {
					System.out.println("Header");
					System.out.println("Tipo de arquivo: " + registro.substring(2, 11));
					System.out.println("Data/hora de gera��o do arquivo: " + registro.substring(11,30));
					System.out.println("Vers�o do layout: " + registro.substring(30,32));
				}
				else if (tipoRegistro.equals("09")) {
					System.out.println("\nTrailer");
					int qtdRegistro = Integer.parseInt(registro.substring(22,32));
					if (qtdRegistro == contRegistro) {
						System.out.println("Quantidade de registros gravados compat�vel com quantidade lida");
					}	
					else {	
						System.out.println("Quantidade de registros gravados n�o confere com quantidade lida");
					}
				}
				else if (tipoRegistro.contentEquals("03")) {
					if (contRegistro == 0) {
						System.out.println();
						System.out.printf("%-10s %010s %-19s %-11s\n", "PRODUTO","VALOR","DATA E HORA","CPF");
					}
					
					produto= registro.substring(2,12);
					valor = registro.substring(12,22);
					dataHora = registro.substring(22,41);
					cpf = registro.substring(32,43);
					
					System.out.printf("%-10s %010s %-19s %-11s\n", produto, valor, dataHora, cpf);
					contRegistro++;
				}
				else {
					System.out.println("Tipo de registro inv�lido");
				}
				
				// l� o pr�ximo registro
				registro = entrada.readLine();
			}
			
			// Fecha o arquivo
		    entrada.close();
		} catch (IOException e) {
			System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
		}
			
	}

	public static void main(String[] args) {
		String nomeArq = "ArquivoNotas";
		leArquivo(nomeArq);
	}

}
