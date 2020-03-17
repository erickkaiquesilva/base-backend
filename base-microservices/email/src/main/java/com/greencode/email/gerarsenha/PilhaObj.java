/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.greencode.email.gerarsenha;

public class PilhaObj<T> {

    private T[] vetor;
    private int topo;

    public PilhaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.topo = -1;
    }

    public boolean isEmpty() {
        if (topo == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (topo == vetor.length - 1) {
            return true;
        }
        return false;
    }

    public void push(T info) {
        if (isFull()) {
            System.out.println("Lista cheia");
        } else {
            vetor[++topo] = info;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            return vetor[topo--];
        }
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return vetor[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Lista vazia");
        } else {
            for (int i = 0; i < topo + 1; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public T[] getVetor() {
        return this.vetor;
    }

}
