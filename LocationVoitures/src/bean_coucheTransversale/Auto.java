package bean_coucheTransversale;

public class Auto extends Vehicule{

	private String modele;
	private boolean deluxe; 
	
	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Auto() {
		super();
	}

	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Auto(String modele, double prix, boolean deluxe) {
		super();
		this.modele = modele;
		this.prix = prix;
		this.deluxe = deluxe;
	}

	public String getModele() {
		return modele;
	}


	public double getPrix() {
		return prix;
	}


	public boolean isDeluxe() {
		return deluxe;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setDeluxe(boolean deluxe) {
		this.deluxe = deluxe;
	}	
	
}
