package backtracking;

import java.util.Scanner;

public class NRainhasBacktracking {

	public static void main(String[] args) {
		long tempoInicial = System.currentTimeMillis();

		Scanner scanner = new Scanner(System.in);
		System.out.println("---------- Insira o tamanho do tabuleiro ----------");
		int tamanhoTabuleiro = scanner.nextInt();
		System.out.println("");

		inserirRainha(tamanhoTabuleiro);

		long tempoFinal = System.currentTimeMillis() - tempoInicial;
		System.out.println("O algoritmo foi executado em " + tempoFinal / 1000 + " segundos");

	}

	private static void inserirRainha(int tamanhoTabuleiro) {

		// Se a tabuleiro é 1x1 ou 2x2 ou 3x3, então não temos solução possível.
		// No tabuleiro de 1x1 ou 2x2, rainhas inseridas na primeira linha, em qualquer
		// posição, atacará a rainha
		// colocada em qualquer posição da linha 2
		// No tabuleiro de 3x3, rainhas colocadas na primeira e segunda linha para todas
		// as combinações
		// de posições atacará a rainha colocada em qualquer posição na linha 3

		if (tamanhoTabuleiro < 4) {
			System.out.println("Sem solução para tabuleiros menores que 4x4.");
		} else {
			int[][] tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];
			inserirTodasRainhas(tabuleiro, 0);
			imprimirTabuleiro(tabuleiro);
		}
	}

	private static boolean inserirTodasRainhas(int tabuleiro[][], int linha) {
		if (linha >= tabuleiro.length) {
			return true;
		}

		boolean todasRainhasInseridas = false;
		for (int j = 0; j < tabuleiro.length; j++) {

			if (ehSeguro(tabuleiro, linha, j)) {
				tabuleiro[linha][j] = 1;
				todasRainhasInseridas = inserirTodasRainhas(tabuleiro, linha + 1);
			}
			if (todasRainhasInseridas) {
				break;
			} else {
				tabuleiro[linha][j] = 0;
			}
		}
		return todasRainhasInseridas;
	}

	private static boolean ehSeguro(int tabuleiro[][], int linha, int coluna) {

		// Faz a checagem na diagonal esquerda
		for (int i = linha - 1, j = coluna - 1; i >= 0 && j >= 0; i--, j--) {
			if (tabuleiro[i][j] == 1) {
				return false;
			}
		}

		// Faz a checagem na diagonal direita
		for (int i = linha - 1, j = coluna + 1; i >= 0 && j < tabuleiro.length; i--, j++) {
			if (tabuleiro[i][j] == 1) {
				return false;
			}
		}

		// Faz a checagem na mesma coluna
		for (int i = linha - 1; i >= 0; i--) {
			if (tabuleiro[i][coluna] == 1) {
				return false;
			}
		}

		return true;
	}

	private static void imprimirTabuleiro(int[][] tabuleiro) {
		for (int linha = 0; linha < tabuleiro.length; linha++) {
			for (int col = 0; col < tabuleiro.length; col++) {
				if (tabuleiro[linha][col] == 1) {
					System.out.print("X ");
				} else {
					System.out.print("_ ");
				}
			}
			System.out.println();
		}
		System.out.println("");
	}

}
