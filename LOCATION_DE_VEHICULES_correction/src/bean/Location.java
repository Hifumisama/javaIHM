package bean;

import java.io.Serializable;
import java.util.Objects;

import dao.Locations;

/**
 * @author adminonedy Represente la location d'un v�hicule bien pr�cis
 */
public class Location implements Serializable {

	/**
	 * Represente l'identifiant unique de la la location.
	 */
	private int numero;
	/**
	 * Represente la liste des locations de la soci�t�.
	 */
	private final Locations locations = Locations.getDefaultLocations();
	/**
	 * Represente la date de d�but de la location
	 */
	private Date debut;
	/**
	 * Represente la date de fin de la location
	 */
	private Date fin;
	/**
	 * Represente la date de remise du v�hicule lou�.
	 */
	private Date dateRemise;
	/**
	 * Represente l'ensemble des prix ou taxe appliqu� � la location
	 */
	private Devis devis;
	/**
	 * Represente le client associ� � la location
	 */
	private Emprunteur emprunteur;
	/**
	 * Represente l'exemplaire associ� � la location
	 */
	private Exemplaire exemplaire;
	/**
	 * Represente un bool�en indiquant si la location est en cours. Vaux true si oui
	 * et false sinon.
	 */
	private boolean locationEnCours = true;
	/**
	 * Represente un bool�en indiquant si le client a souscrit a une assurance. Vaux
	 * true si oui et false sinon.
	 */
	private boolean assurance = false;
	/**
	 * Represente la quantit� de carburant consomm� pendant la location.
	 */
	private Carburant carburant;
	/**
	 * Represente les degats subid par l'exemplaire lou�.
	 */
	private Degat degat;

	/**
	 * Cr�e une nouvelle location avec des donn�es vide et l'ajoute automatiquement
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
	 * Cr�e une nouvelle location avec les donn�es sp�cifi� en param�tre et l'ajoute
	 * automatiquement dans la liste principale des locations et cr�e
	 * automatiquement le d�vis associ� � la location
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
			throw new Exception("Impossible de cr�er une location avec un v�hicule en cours de location!");
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
			throw new Exception("Impossible de cr�er une location avec un v�hicule en cours de location!");
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
