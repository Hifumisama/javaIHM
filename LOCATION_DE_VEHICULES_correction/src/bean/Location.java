package bean;

import java.io.Serializable;
import java.util.Objects;

import dao.Locations;

/**
 * @author adminonedy Represente la location d'un véhicule bien précis
 */
public class Location implements Serializable {

	/**
	 * Represente l'identifiant unique de la la location.
	 */
	private int numero;
	/**
	 * Represente la liste des locations de la société.
	 */
	private final Locations locations = Locations.getDefaultLocations();
	/**
	 * Represente la date de début de la location
	 */
	private Date debut;
	/**
	 * Represente la date de fin de la location
	 */
	private Date fin;
	/**
	 * Represente la date de remise du véhicule loué.
	 */
	private Date dateRemise;
	/**
	 * Represente l'ensemble des prix ou taxe appliqué à la location
	 */
	private Devis devis;
	/**
	 * Represente le client associé à la location
	 */
	private Emprunteur emprunteur;
	/**
	 * Represente l'exemplaire associé à la location
	 */
	private Exemplaire exemplaire;
	/**
	 * Represente un booléen indiquant si la location est en cours. Vaux true si oui
	 * et false sinon.
	 */
	private boolean locationEnCours = true;
	/**
	 * Represente un booléen indiquant si le client a souscrit a une assurance. Vaux
	 * true si oui et false sinon.
	 */
	private boolean assurance = false;
	/**
	 * Represente la quantité de carburant consommé pendant la location.
	 */
	private Carburant carburant;
	/**
	 * Represente les degats subid par l'exemplaire loué.
	 */
	private Degat degat;

	/**
	 * Crée une nouvelle location avec des données vide et l'ajoute automatiquement
	 * dans la liste principale des locations
	 */
	public Location() {
		numero = locations.getLocations().size() + 1;
		exemplaire = null;
		emprunteur = null;
		debut = null;
		fin = null;
		dateRemise = null;
		devis = null;
		carburant = null;
		degat = null;
		locations.getLocations().add(this);
	}

	/**
	 * Crée une nouvelle location avec les données spécifié en paramètre et l'ajoute
	 * automatiquement dans la liste principale des locations et crée
	 * automatiquement le dévis associé à la location
	 * 
	 * @param exemplaire
	 * @param emprunteur
	 * @param debut
	 * @param fin
	 * @param assurance
	 * @throws Exception
	 */
	public Location(Exemplaire exemplaire, Emprunteur emprunteur, Date debut, Date fin, boolean assurance)
			throws Exception {
		Objects.requireNonNull(exemplaire);
		Objects.requireNonNull(emprunteur);
		Objects.requireNonNull(debut);
		Objects.requireNonNull(fin);
		if (exemplaire.getLocation() != null) {
			throw new Exception("Impossible de créer une location avec un véhicule en cours de location!");
		}
		this.numero = locations.getLocations().size() + 1;
		this.exemplaire = exemplaire;
		this.exemplaire.setLocation(this);
		this.emprunteur = emprunteur;
		this.emprunteur.getLocations().add(this);
		this.debut = debut;
		this.fin = fin;
		this.assurance = assurance;
		devis = new Devis(this, exemplaire.getPrix(), debut, fin);
		locations.getLocations().add(this);
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
		this.emprunteur.getLocations().add(this);
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) throws Exception {
		if (exemplaire.getLocation() != null) {
			throw new Exception("Impossible de créer une location avec un véhicule en cours de location!");
		}
		this.exemplaire = exemplaire;
		this.exemplaire.setLocation(this);
	}

	public boolean isLocationEnCours() {
		return locationEnCours;
	}

	public void setLocationEnCours(boolean locationEnCours) {
		this.locationEnCours = locationEnCours;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Devis getDevis() {
		return devis;
	}

	public void setDevis() {
		devis = new Devis(this, exemplaire.getPrix(), debut, fin);
	}

	public boolean isAssurance() {
		return assurance;
	}

	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	public Date getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	public Carburant getCarburant() {
		return carburant;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}

	public Degat getDegat() {
		return degat;
	}

	public void setDegat(Degat degat) {
		this.degat = degat;
	}

	@Override
	public String toString() {
		return String.format("|%-27s| |%-11s| |%-11s| |%-11s| %s", exemplaire, debut, fin, dateRemise, devis);
	}

}
