package modelo;

public interface AccionesTablero {
	/**
	 * por cada sitio, calcula el numero de minas que tiene alrededor
	 */
	void calcularMinasAlrededor();
	void desvelarContigua(Coordenada lugar);
}
