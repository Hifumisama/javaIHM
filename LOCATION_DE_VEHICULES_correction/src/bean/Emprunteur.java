package bean;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Objects;

import dao.Emprunteurs;

/**
 * @author adminonedy Represente un client de la societe de location
 */
public class Emprunteur implements Comparable<Emprunteur>, Serializable {

	/**
	 * Represente une identifiant unique du client
	 */
	private int id;
	/**
	 * Represente le nom du client
	 */
	private String nom;
	/**
	 * Represente le prénom du client
	 */
	private String prenom;
	/**
	 * Represente l'adresse du client
	 */
	private Adresse adresse;
	/**
	 * Represente le prix ttc de toutes les locations effectuées par le client
	 */
	private int factureTotal = 0;
	/**
	 * Represente le prix de base de toutes les locations effectuées par le client
	 */
	private int prixLocation = 0;
	/**
	 * Represente un booléen indiquant si le client à au moins une location en
	 * cours. Vaux true si le client a au moins une location en cours et false
	 * sinon.
	 */
	private boolean locationEnCours = false;
	/**
	 * Represente la liste des clients du magasin
	 */
	private final Emprunteurs emprunteurs = Emprunteurs.getDefaultEmprunteurs();
	/**
	 * Represente la liste des locations du client en question(1 véhicule par
	 * location)
	 */
	private final ArrayList<Location> locations;

	/**
	 * Crée un nouveau client avec des données vide et l'ajoute automatiquement dans
	 * la liste principale des clients
	 */
	public Emprunteur() {
		this.id = emprunteurs.getEmprunteurs().size() + 1;
		this.nom = "";
		this.prenom = "";
		this.adresse = new Adresse();
		locations = new ArrayList<Location>();
		emprunteurs.getEmprunteurs().add(this);
	}

	/**
	 * Crée un nouveau client avec les données spécifié en paramètre et l'ajoute
	 * automatiquement dans la liste principale des clients
	 * 
	 * @param nom
	 * @param prenom
	 * @param adresse
	 */
	public Emprunteur(String nom, String prenom, Adresse adresse) {
		Objects.requireNonNull(adresse);
		this.id = emprunteurs.getEmprunteurs().size() + 1;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		locations = new ArrayList<Location>();
		emprunteurs.getEmprunteurs().add(this);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getFactureTotal() {
		return factureTotal;
	}

	public void setFactureTotal(int factureTotal) {
		this.factureTotal = factureTotal;
	}

	public int getPrixLocation() {
		return prixLocation;
	}

	public void setPrixLocation(int prixLocation) {
		this.prixLocation = prixLocation;
	}

	public boolean isLocationEnCours() {
		return locationEnCours;
	}

	public void setLocationEnCours(boolean locationEnCours) {
		this.locationEnCours = locationEnCours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	@Override
	public int compareTo(Emprunteur emprunteur) {
		int temp = this.nom.compareTo(emprunteur.nom);
		if (temp != 0) {
			return temp;
		} else {
			return this.prenom.compareTo(emprunteur.prenom);
		}
	}

	@Override
	public String toString() {
		return "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse;
	}

}
