package bean_coucheTransversale;

public class Adresse {
	private int numeroDeRue;
	private String rue;
	private int cp;
	private String ville;



	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Adresse() {

	}

	
	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Adresse (int numero, String rue, int CP, String ville) {
	this.numeroDeRue = numero;
	this.rue = rue;
	this.cp = CP;
	this.ville = ville;	
	}
	
	
	
	public int getNumero() {
		return numeroDeRue;
	}

	public String getRue() {
		return rue;
	}

	public int getCP() {
		return cp;
	}

	public String getVille() {
		return ville;
	}

	public void setNumero(int numero) {
		numeroDeRue = numero;
	}

	public void setRue(String rue) {
		rue = rue;
	}

	public void setCP(int cP) {
		cp = cP;
	}

	public void setVille(String ville) {
		ville = ville;
	}
	
}
