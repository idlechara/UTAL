package Laboratorio5;

import junit.framework.TestCase;

public class TestBanda extends TestCase {
	
	Banda testBanda;
	Juego testJuego;
	Pelicula testPelicula;
	Solista testSolista;
	Usuario testUsuario1;
	Usuario testUsuario2;
	Usuario testUsuario3;
	Usuario testUsuario4;
	
	public TestBanda(String name) {
		super(name);
		this.testBanda = new Banda(MusicalGenre.CLASSICAL, 1999, "Felsic Mirage");
		this.testJuego = new Juego("Split Theory", 1990, Genre.SHOOTER, "Nekotsuki", "FedEx", GameMode.ONLINE);
		this.testPelicula = new Pelicula("Scattered Destiny", 2012, MovieGenre.ANIMACION, "Nayuta", "HeartCage", 12000);
		this.testSolista = new Solista(MusicalGenre.MISC, 1980, "Kynku", "Erik", "5 diciembre 1990");
		this.testUsuario1 = new Usuario("El", "Ayudante", "de progra", "estoyAburrido@ocio.net", "asdf123", "9 enero de 1890", this.testBanda);
		this.testUsuario2 = new Usuario("El", "Ayudante", "de progra", "estoyAburrido@ocio.net", "asdf123", "9 enero de 1890", this.testSolista);
		this.testUsuario3 = new Usuario("El", "Ayudante", "de progra", "estoyAburrido@ocio.net", "asdf123", "9 enero de 1890", this.testJuego);
		this.testUsuario4 = new Usuario("El", "Ayudante", "de progra", "estoyAburrido@ocio.net", "asdf123", "9 enero de 1890", this.testPelicula);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.testBanda = null;
		this.testJuego = null;
		this.testPelicula = null;
		this.testSolista = null;
		this.testUsuario1 = null;
		this.testUsuario2 = null;
		this.testUsuario3 = null;
		this.testUsuario4 = null;
	}
	
	

}
