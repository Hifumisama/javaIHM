package bean;

/**
 * @author adminonedy Represente un mod�le abstrait d'une marque de moto
 */
public class Moto extends Vehicule {

	/**
	 * Represente la cylindr�e du mod�le de moto
	 */
	private int cylindree;

	/**
	 * Cr�e un mod�le de marque de moto avec des donn�es vide et l'ajoute
	 * automatiquement dans la liste principale des marques
	 */
	public Moto() {
		super();
		cylindree = 0;
	}

	/**
	 * Cr�e un mod�le de marque de moto avec les donn�es sp�cifi� en param�tre et
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
