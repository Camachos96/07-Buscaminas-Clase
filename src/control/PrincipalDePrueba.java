package control;

import java.util.Scanner;
public class PrincipalDePrueba {
private Scanner leer;


public static void main(String[] args) {
	PrincipalDePrueba instancia = new PrincipalDePrueba();
	//Los 9 son bombas.
	//8 es agua
	//El resto de numeros representan las bombas alrededor
	//En el segundo valor de cada punto, un 0 representa que la casilla está oculta y un 1 que es visible
	int[]coordenadas=instancia.pedirCoordenadas();
	int[][][] matriz = {{{1,0},{1,0},{8,0},{1,0},{9,0},{1,0}}
					   ,{{9,0},{1,0},{8,0},{1,0},{1,0},{1,0}}
					   ,{{1,0},{1,0},{8,0},{8,0},{8,0},{8,0}}
					   ,{{8,0},{8,0},{8,0},{1,0},{1,0},{1,0}}
					   ,{{8,0},{8,0},{8,0},{1,0},{9,0},{1,0}}
					   ,{{8,0},{8,0},{8,0},{1,0},{1,0},{1,0}}};
//	int[][][] matriz2 = {{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}};
	instancia.representarMatriz(matriz);
	instancia.mostrarAgua(matriz,coordenadas[0],coordenadas[1]);
	
}

public int[] pedirCoordenadas() {
	leer = new Scanner(System.in);
	int[] coordenadas = new int[2];
	System.out.println("Introduce primero la coordenada X y luego la coordenada Y");
	for (int i = 0; i < coordenadas.length; i++) {
		coordenadas[i]=leer.nextInt();
	}
	return coordenadas;
}

public void representarMatriz(int[][][] matriz) {
	for (int i = 0; i < matriz.length; i++) {
		for (int j = 0; j < matriz[i].length; j++) {
			if (comprobarVisibilidad(matriz, i, j)) {
				System.out.print(matriz[i][j][0]+" ");
			}else
				System.out.print("0 ");
		}
		System.out.println("");
	}
	System.out.println("-----------------");
}

/** Metodo recursivo que recorre las casillas alrededor de la casilla seleccionada viendo qué contienen y actuando en base a ello
 *  Si contiene un 9 (bomba) es GameOver
 *  Si contiene un 8 (agua) se hace la llamada recursiva
 *  Si contiene cualquier otro número simplemente se desvela
 * @param matriz
 * @param coordenadax
 * @param coordenaday
 */
public void mostrarAgua(int[][][] matriz,int coordenadax, int coordenaday) {
	if (matriz[coordenadax][coordenaday][0]==9) {
		System.out.println("Game Over");
		desvelarTodoElTablero(matriz);
	}else{
		desvelarCasilla(matriz, coordenadax, coordenaday);
		if (comprobarAgua(matriz, coordenadax, coordenaday)) {
			for (int i = coordenadax-1; i <= coordenadax+1; i++) {
				for (int j = coordenaday-1; j <= coordenaday+1; j++) {
					if (comprobarLimites(matriz, i, j)&&!comprobarVisibilidad(matriz, i, j)) {
						mostrarAgua(matriz, i, j);
					}
				}
			}
		}
	}
	representarMatriz(matriz);
}

/**
 * @param matriz
 * @param coordenadax
 * @param coordenaday
 * @return
 */
public boolean comprobarAgua(int[][][] matriz, int coordenadax, int coordenaday) {
	return matriz[coordenadax][coordenaday][0]==8;
}
public void desvelarTodoElTablero(int[][][] matriz) {
	for (int i = 0; i < matriz.length; i++) {
		for (int j = 0; j < matriz[i].length; j++) {
			if (!comprobarVisibilidad(matriz, i, j)) {
				desvelarCasilla(matriz, i, j);
			}
		}
	}
}
/**Comprueba si una casilla está dentro del tablero o sale de sus límites
 * @param matriz
 * @param coordenadax
 * @param coordenaday
 * @return
 */
public boolean comprobarLimites(int[][][] matriz, int coordenadax, int coordenaday) {
	if (comprobarLimiteX(matriz, coordenadax)&&comprobarLimiteY(matriz,coordenaday)) {
		return true;
	};
	return false;
}
private boolean comprobarLimiteX(int[][][] matriz, int coordenadax){
	if (coordenadax>=0&&coordenadax<matriz.length) {
		return true;
	}
	return false;
}
private boolean comprobarLimiteY(int[][][] matriz,int coordenaday){
	if (coordenaday>=0&&coordenaday<matriz.length) {
		return true;
	}
	return false;
}
/**
 * @param matriz 
 * @param coordenadax
 * @param coordenaday
 * @return True -> Desvelada || False -> Oculta
 */
public boolean comprobarVisibilidad(int[][][] matriz, int coordenadax, int coordenaday) {
	if (matriz[coordenadax][coordenaday][1]==1) {
		return true;
	}else
		return false;
}


public void desvelarCasilla(int[][][] matriz, int coordenadax, int coordenaday) {
	matriz[coordenadax][coordenaday][1]=1;
}
}