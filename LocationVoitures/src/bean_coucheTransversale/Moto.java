package bean_coucheTransversale;

public class Moto extends Vehicule {

	private int cylindree;
	private int roues;
	private float poids;
	private String conducteur;
	
	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Moto() {
		
	}
	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Moto(int roues, float poids, String conducteur, int cylindree) {
		super();
		this.cylindree = cylindree;
		this.conducteur = conducteur;
		this.roues = roues;
		this.poids = poids;
	}
	
	
	// METHODES
	

	

	

	 public String ToString() {
		 
		 return "roues =  " + roues 
				 + " poids = " + poids 
				 + " conducteur : " + conducteur 
				 + " cylindree : " + cylindree;
	 }
	 
	 
	 public void rendre() {
		 
	 };
	 
	 
	 
		public void louer() {
			
		};
		
	
	 
	
	// main
	


	
	
	

	 // getters setters

	public int getCylindree() {
		return cylindree;
	}


	public void setCylindree(int cylindree) {
		this.cylindree = cylindree;
	}


	public int getRoues() {
		return roues;
	}


	public void setRoues(int roues) {
		this.roues = roues;
	}


	public float getPoids() {
		return poids;
	}


	public void setPoids(float poids) {
		this.poids = poids;
	}


	public String getConducteur() {
		return conducteur;
	}


	public void setConducteur(String conducteur) {
		this.conducteur = conducteur;
	}
	
}


