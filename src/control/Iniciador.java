package control;

import javax.print.attribute.standard.NumberOfDocuments;
import java.lang.Math;
import modelo.Tablero;
import utiles.Utiles;

public class Iniciador implements Iniciable {
	private byte minas;
	private Densidad densidad;
	public static final int cien = 100;
	
	public byte getMinas() {
		return minas;
	}

	public void setMinas(byte minas) {
		assert minas>0;
		this.minas = minas;
	}

	public Densidad getDensidad() {
		return densidad;
	}

	public void setDensidad(Densidad densidad) {
		assert densidad!=null;
		this.densidad = densidad;
	}

	private byte filas, columnas;
	private Tablero tablero;

	@Override
	public void establecerDimensionTablero() {
		int valorDensidad=this.densidad.getValor();
		//Calculo las dimensiones dividiendo 100 entre la densidad y multiplicando por el número de minas.
		//El resultado lo divido entre 10 y le hago un mathfloor para que me quede un resultado acabado en 0 
		//y no me salga un tablero con dimensiones anormales
		int dimensiones = (int)Math.floor((minas*(cien/valorDensidad))/10);
		this.columnas=((byte)10);
		this.filas=((byte) ((byte)dimensiones/columnas));
	}

	@Override
	public void crearTablero() {
		assert this.filas > 0 && this.columnas > 0 : "numero no valido de filas/columnas";
		this.tablero=new Tablero(this.filas, this.columnas);
	}

	@Override
	public void colocarMinas() {
		assert this.tablero != null && minas > 0 : "fallo al definir el tablero y/o el numero de minas";
		int x,y;
		for (int i = 0; i < minas; i++) {
			x=Utiles.random(filas, 0);
			y=Utiles.random(columnas, 0);
			boolean retorno = this.tablero.colocarMina(x, y);
			if (retorno=false) {
				i--;
			}
		}	
	}

	public void iniciarJuego(Byte minas, Densidad densidad) {
		setMinas(minas);
		setDensidad(densidad);
		establecerDimensionTablero();
		crearTablero();
		colocarMinas();
		this.tablero.calcularMinasAlrededor();
	}
}
