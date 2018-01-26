package bean;

/**
 * @author adminonedy Represente un modèle abstrait d'une marque d'automobile
 */
public class Auto extends Vehicule {

	/**
	 * Represente le modèle de véhicule
	 */
	private String modele;
	/**
	 * vaux true, si le modèle est de luxe et false si non
	 */
	private boolean deLuxe;

	/**
	 * Crée un modèle de marque d'automobile avec des données vide et l'ajoute
	 * automatiquement dans la liste principale des marques
	 */
	public Auto() {
		super();
		modele = "";
		deLuxe = false;
	}

	/**
	 * Crée un modèle de marque d'automobile avec les données spécifié en paramètre
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
