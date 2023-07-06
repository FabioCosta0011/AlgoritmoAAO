package Algoritmo;

import java.util.ArrayList;
import java.util.List;

public class CaixeiroViajante {
    private int[][] grafo;
    private int numVertices;
    private List<Integer> melhorCaminho;
    private int menorDistancia;
    private int numComparacoes;

    public CaixeiroViajante(int[][] grafo) {
        this.grafo = grafo;
        this.numVertices = grafo.length;
        this.melhorCaminho = new ArrayList<>();
        this.menorDistancia = Integer.MAX_VALUE;
        this.numComparacoes = 0;
    }

    public void encontrarMenorCaminho() {
        List<Integer> caminhoAtual = new ArrayList<>();
        caminhoAtual.add(0); // Começar no vértice 0
        boolean[] visitado = new boolean[numVertices];
        visitado[0] = true;

        encontrarMenorCaminhoAux(caminhoAtual, visitado, 0, 1);

        // Adicionar o vértice de origem novamente para fechar o ciclo
        melhorCaminho.add(0);

        System.out.println("Melhor caminho: " + melhorCaminho);
        System.out.println("Menor distância: " + menorDistancia);
        System.out.println("Número de comparações: " + numComparacoes);
    }

    private void encontrarMenorCaminhoAux(List<Integer> caminhoAtual, boolean[] visitado, int distanciaAtual,
            int nivel) {
        if (nivel == numVertices) {
            int distanciaTotal = distanciaAtual + grafo[caminhoAtual.get(nivel - 1)][0];
            if (distanciaTotal < menorDistancia) {
                menorDistancia = distanciaTotal;
                melhorCaminho = new ArrayList<>(caminhoAtual);
            }
            numComparacoes++;
            return;
        }

        for (int i = 1; i < numVertices; i++) {
            if (!visitado[i]) {
                caminhoAtual.add(i);
                visitado[i] = true;
                int novaDistancia = distanciaAtual + grafo[caminhoAtual.get(nivel - 1)][i];

                encontrarMenorCaminhoAux(caminhoAtual, visitado, novaDistancia, nivel + 1);

                caminhoAtual.remove(caminhoAtual.size() - 1);
                visitado[i] = false;
            }
            numComparacoes++;
        }
    }

    public static void main(String[] args) {
        int[][] grafo1 = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };

        CaixeiroViajante caixeiro1 = new CaixeiroViajante(grafo1);
        System.out.println("\n--Grafo1--");
        caixeiro1.encontrarMenorCaminho();


        int[][] grafo2 = {
                { 0, 2, 9, 10, 6, 5 },
                { 2, 0, 4, 8, 3, 7 },
                { 9, 4, 0, 6, 9, 10 },
                { 10, 8, 6, 0, 1, 3 },
                { 6, 3, 9, 1, 0, 2 },
                { 5, 7, 10, 3, 2, 0 }
        };

        CaixeiroViajante caixeiro2 = new CaixeiroViajante(grafo2);
        System.out.println("\n--Grafo2--");
        caixeiro2.encontrarMenorCaminho();

        
        int[][] grafo3 = {
                { 0, 3, 7, 2, 5, 9, 4, 8 },
                { 3, 0, 5, 8, 1, 4, 7, 2 },
                { 7, 5, 0, 1, 6, 2, 8, 4 },
                { 2, 8, 1, 0, 3, 6, 9, 5 },
                { 5, 1, 6, 3, 0, 2, 4, 7 },
                { 9, 4, 2, 6, 2, 0, 5, 3 },
                { 4, 7, 8, 9, 4, 5, 0, 1 },
                { 8, 2, 4, 5, 7, 3, 1, 0 }
        };

        CaixeiroViajante caixeiro3 = new CaixeiroViajante(grafo3);
        System.out.println("\n--Grafo3--");
        caixeiro3.encontrarMenorCaminho();
        System.out.println("");

    }
}
