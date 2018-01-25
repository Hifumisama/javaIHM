package bean_coucheTransversale;

import java.util.ArrayList;

public class Emprunteur {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private ArrayList<Location> locations = null;  // ou bien une arrayList pour qu'un emprunteur puisse louer plusieurs véhicules à la fois
	private boolean locationEnCours = false;
	
	private int factureTotal = 0;
		
	private ArrayList<Emprunteur> Emprunteurs; // la liste de tous les emprunteurs :
	
	
	// La liste de toutes les locations
	
	
		
	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Emprunteur() {
		super();
	}


	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Emprunteur(int id, String nom, String prenom, String adresse, int factureTotal, boolean locationEnCours) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.factureTotal = factureTotal;
		this.locationEnCours = locationEnCours;
		Emprunteurs.add(this);
		
	}
	
	
	public String ToString() {
		return "Je suis l'emprunteur" +this.prenom+this.nom+ " et j'habite à l'adresse " +adresse+ ". Actuellement j'ai " + locationEnCours;
	}
	
	public void louer() {
		
	}
	
	public void ramener() {
		
	}
	
	
}
