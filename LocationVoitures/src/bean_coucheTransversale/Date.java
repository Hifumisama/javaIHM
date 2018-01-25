package bean_coucheTransversale;

public class Date {

	private int jour;
	private int mois;
	private int annee;

	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Date() {
		super();
	}

	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Date(int jour, int mois, int annee) {
		super();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	public int getJour() {
		return jour;
	}

	public int getMois() {
		return mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

}
