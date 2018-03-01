package control;

public class PrincipalDePrueba {
public static void main(String[] args) {
	PrincipalDePrueba instancia = new PrincipalDePrueba();
	//Los 9 son bombas.
	//8 es agua
	//Números del 1 al 2 son las bombas alrededor
	int coordenadax=1,coordenaday=4;
	int[][][] matriz = {{{1,0},{1,0},{8,0},{1,0},{9,0},{1,0}}
					   ,{{9,0},{1,0},{8,0},{1,0},{1,0},{1,0}}
					   ,{{1,0},{1,0},{8,0},{8,0},{8,0},{8,0}}
					   ,{{8,0},{8,0},{8,0},{1,0},{1,0},{1,0}}
					   ,{{8,0},{8,0},{8,0},{2,0},{9,0},{2,0}}
					   ,{{8,0},{8,0},{8,0},{2,0},{9,0},{2,0}}};
//	int[][][] matriz2 = {{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}
//	   ,{{8,0},{8,0},{8,0},{8,0},{8,0},{8,0}}};
	instancia.representarMatriz(matriz);
	instancia.mostrarAgua(matriz,coordenadax,coordenaday);
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
public void mostrarAgua(int[][][] matriz,int coordenadax, int coordenaday) {
	if (matriz[coordenadax][coordenaday][0]==9) {
		System.out.println("Game Over");
		desvelarTodoElTablero(matriz);
	}else {
		if (matriz[coordenadax][coordenaday][0]==8) {
			
		}else {
			desvelarCasilla(matriz, coordenadax, coordenaday);
		}
	}
	representarMatriz(matriz);
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