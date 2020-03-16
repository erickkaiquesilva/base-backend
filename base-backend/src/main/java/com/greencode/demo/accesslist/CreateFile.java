package com.greencode.demo.accesslist;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.TimeZone;

public class CreateFile {
	private FileWriter arquivo = null;
	private Formatter saida = null;
	Date data = new Date();
	DateFormat formatarDate =  new SimpleDateFormat("yyyyMMdd");
	DateFormat dateWithHour =  new SimpleDateFormat("yyyyMMdd-hh:mm:ss");
	String atualDate = formatarDate.format(data);
	String atualTime = dateWithHour.format(data);

	public void gravar(AccessList lista) {
		try {
			arquivo = new FileWriter("/var/log/greencode/access-log-" + atualDate + ".log",true);
			saida = new Formatter(arquivo);
		} catch (Exception erro) {
			System.out.println("Erro ao gerar o arquivo - " + atualDate);
			System.exit(1);
		}
		
		try {
			for (int i = 0; i < lista.getTamanho(); i++) {
				saida.format("%s - acesso efetuado às : %s \n", lista.getElemento(i), atualTime);
			}
		} catch (FormatterClosedException erro) {
			System.out.println("ERRO AO GRAVAR O ARQUIVO - : " + atualDate);
		}
		
		try {
			saida.close();
			arquivo.close();
		} catch(IOException erro) {
			System.out.println("EERO AO FECHAR O ARQUIVO - " + atualDate);
			System.exit(1);
		}
	}

}