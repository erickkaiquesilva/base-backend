package com.greencode.demo.exportararquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.greencode.demo.model.Produto;
import com.greencode.demo.model.Transacao;

public class GravaArquivo {
	
	public static void gravaRegistro (String nomeArq, String registro) {
		BufferedWriter saida = null;
		try {
			saida = new BufferedWriter(new FileWriter(nomeArq, true));
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		try {
				saida.append(registro + "\n");
			    saida.close();

		} catch (IOException e) {
			System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
		}
	}
	
	
	public void gravaArquivo(List<Transacao> transacoes, List<Produto> produtos) {
	
		String nomeArq = "ArquivoNotasFiscais";
		String header = "";
		String corpo = "";
		String trailer = "";
		int contRegDados = 0;
		int total = 0;
		
		// Monta o registro header
		Date dataDeHoje = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		
		header += "01000000001";
		header += formatter.format(dataDeHoje);
		header += "01";
		
		// Grava o registro header
		gravaRegistro(nomeArq, header);
		
		// Monta o corpo 
		
		for(int i = 0; i < transacoes.size(); i++) {
			corpo = "03";
			corpo += String.format("%-10s", produtos.get(i).getNome());
			corpo += String.format("%010d", produtos.get(i).getPreco());
			corpo += String.format("%-19s", transacoes.get(i).getData_hora());
			corpo += String.format("%-2s", "01");
			corpo += String.format("%-11d", transacoes.get(i).getUsuario().getCpf());
			contRegDados++;
			total += produtos.get(i).getPreco();
			gravaRegistro(nomeArq,corpo);
			
		}		
		// monta o trailer
		trailer += "09";
		trailer += String.format("%020d",total);
		trailer += String.format("%010d", contRegDados);
		gravaRegistro(nomeArq,trailer);
	
	}
}
