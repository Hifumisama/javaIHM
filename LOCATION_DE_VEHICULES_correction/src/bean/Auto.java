package bean;

/**
 * @author adminonedy Represente un mod�le abstrait d'une marque d'automobile
 */
public class Auto extends Vehicule {

	/**
	 * Represente le mod�le de v�hicule
	 */
	private String modele;
	/**
	 * vaux true, si le mod�le est de luxe et false si non
	 */
	private boolean deLuxe;

	/**
	 * Cr�e un mod�le de marque d'automobile avec des donn�es vide et l'ajoute
	 * automatiquement dans la liste principale des marques
	 */
	public Auto() {
		super();
		modele = "";
		deLuxe = false;
	}

	/**
	 * Cr�e un mod�le de marque d'automobile avec les donn�es sp�cifi� en param�tre
	 * et l'ajoute automatiquement dans la liste principale des marques
	 * 
	 * @param marque
	 * @param prix
	 * @param modele
	 * @param deLuxe
	 */
	public Auto(String marque, int prix, String modele, boolean deLuxe) {
		super(marque, prix);
		this.modele = modele;
		this.deLuxe = deLuxe;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public boolean isDeLuxe() {
		return deLuxe;
	}

	public void setDeLuxe(boolean deLuxe) {
		this.deLuxe = deLuxe;
	}

	@Override
	public String toString() {
		return marque +"-" + modele;
	}

}
