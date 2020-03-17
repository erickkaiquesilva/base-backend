package com.greencode.email.gerarsenha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gustavo
 */
public class GerarSenha {

	List<Character> pilha = new ArrayList<Character>(10);
	private char[] alfabeto = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private Random random = new Random();

	public List<Character> getPilha() {
		return pilha;
	}

	public void setPilha(List<Character> pilha) {
		this.pilha = pilha;
	}

	private List<Character> montaSenha() {
		for (int i = 0; i < pilha.size(); i++) {
			if (pilha.get(i) % 2 == 0) {
				if(random.nextBoolean()) {
					pilha.set(i, alfabeto[random.nextInt(25)]);
				}else {
					pilha.set(i, Character.toUpperCase(alfabeto[random.nextInt(25)]));
				}
			}
		}
		return pilha;
	}

	public List<Character> inverteSenha(List<Character> frase) {
		List<Character> invertido = new ArrayList<Character>();
		PilhaObj<Character> pil = new PilhaObj<Character>(pilha.size());
		for (int i = 0; i < pilha.size(); i++) {
			pil.push(pilha.get(i));
		}
		
		for(int i = 0; i < pilha.size(); i++) {
			invertido.add(pil.pop());
		}
		
		return invertido;
	}

	public String exibeSenha(boolean bool) {
		if (pilha.isEmpty()) {
			System.out.println("Pilha vazia");
		} else {
			List<Character> senha = new ArrayList<Character>();;
			String novaSenha = "";
			if (!bool) {
					senha = montaSenha();
					for(Character c : senha) {
						novaSenha += Character.toString(c);
					}
				
			} else {
					senha = inverteSenha(montaSenha());
					for(Character c : senha) {
						novaSenha += Character.toString(c);
					}
			}
			return novaSenha;
		}
		return "Não foi possivel exibir a senha";
	}

	public int fatorial(int num) {

		if (num <= 1) {

			return 1;

		} else {

			return fatorial(num - 1) * num;

		}

	}

}
