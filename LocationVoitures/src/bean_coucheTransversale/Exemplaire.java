package bean_coucheTransversale;

import java.util.ArrayList;

import dao.Flotte;

public class Exemplaire {

	private int id;
	private Vehicule vehicule;
	private double kilometrage;
	private double prix; // le prix d'un exemplaire a pour formule le prix d'un véhicule - (prix *( km / 50000) * (10/100)
	private Flotte listeExemplaires; // liste des exemplaires presents dans une flotte
	private boolean locationEnCours = false;
	
	
	
	
	
	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Exemplaire() {
		super();
		}
	
	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Exemplaire(int id, Vehicule vehicule, double kilometrage, double prix) {
		super();
		this.id = id;
		this.vehicule = vehicule;
		this.kilometrage = kilometrage;
		this.prix =  vehicule.getPrix() / 50000 *10/100 ;
		}
	
	
	public int getId() {
		return id;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}
	public double getKilometrage() {
		return kilometrage;
	}
	public double getPrix() {
		return prix;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	public void setKilometrage(double kilometrage) {
		this.kilometrage = kilometrage;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
}
