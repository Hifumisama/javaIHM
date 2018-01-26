package bean;

/**
 * @author adminonedy Represente un modèle abstrait d'une marque de moto
 */
public class Moto extends Vehicule {

	/**
	 * Represente la cylindrée du modèle de moto
	 */
	private int cylindree;

	/**
	 * Crée un modèle de marque de moto avec des données vide et l'ajoute
	 * automatiquement dans la liste principale des marques
	 */
	public Moto() {
		super();
		cylindree = 0;
	}

	/**
	 * Crée un modèle de marque de moto avec les données spécifié en paramètre et
	 * l'ajoute automatiquement dans la liste principale des marques
	 * 
	 * @param numero
	 * @param marque
	 * @param prix
	 * @param cylindree
	 */
	public Moto(int numero, String marque, int prix, int cylindree) {
		super(marque, prix);
		this.cylindree = cylindree;
	}

	public int getCylindree() {
		return cylindree;
	}

	public void setCylindree(int cylindree) {
		this.cylindree = cylindree;
	}

	@Override
	public String toString() {
		return marque + "-" + cylindree;
	}

}
