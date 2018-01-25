
package bean_coucheTransversale;

import java.util.ArrayList;
import model.GestionVehicules;

public abstract class Vehicule implements GestionVehicules {
	
	private int id;
	private String marque;
	private double prix;
	private ArrayList<Vehicule> listedeVoiture; // indiquer ici la liste des véhicules permettra de facilement ajouter les nouveaux véhicules à la liste.
	private ArrayList<Exemplaire> Mesexemplaires; // contiendra la liste d'exemplaires qui existent concernant ce véhicule précis
	
	



	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Vehicule() {
		super();
		
	}
	
	
	
	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Vehicule(int id, String marque, double prix) {
		super();
		this.id = id;
		this.marque = marque;
		this.prix = prix;
	}
	
	
	
	@Override
	public static void ajouterVehicule() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public String getMarque() {
		return marque;
	}

	public double getPrix() {
		return prix;
	}

	public ArrayList<Vehicule> getListedeVoiture() {
		return listedeVoiture;
	}

	public ArrayList<Exemplaire> getMesexemplaires() {
		return Mesexemplaires;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setListedeVoiture(ArrayList<Vehicule> listedeVoiture) {
		this.listedeVoiture = listedeVoiture;
	}

	public void setMesexemplaires(ArrayList<Exemplaire> mesexemplaires) {
		Mesexemplaires = mesexemplaires;
	}

	

	






}






