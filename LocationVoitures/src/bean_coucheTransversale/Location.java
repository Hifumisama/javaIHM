package bean_coucheTransversale;

import java.util.ArrayList;
import dao.Locations;
import model.GestionLocations;
import dao.Autos;

public class Location implements GestionLocations {

	private int idLocation; // 
		
	/**
	 *Represente la liste de toutes les locations  
	 */

	private Date dateDebutLocation; 
	private Date dateFinLocation; // date de remise prévue du vehicule
	private Date dateRemise; // date de remise reelle du vehicule
	
	/**
	 *Represente l'exemplaire associé à la location 
	 */
	private Exemplaire exemplaire;

	private Devis devis;
	private int dureePrevue;
	private ArrayList<Vehicule> vehicules;
	private ArrayList<Location> locations;
	
	/**
	 *Represente le client associé à la location 
	 */
	private Emprunteur emprunteur;

	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Location() {
		super();
		}
	
	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Location(
			int numeroLocation, 
			ArrayList<Location> locations,
			Date dateDebutLocation,
			Date dateFinLocation,
			Date dateRemise, 
			Devis devis, 
			Exemplaire exemplaire, 
			Emprunteur emprunteur, 
			ArrayList<Vehicule> vehicules
			) {
		super();
		this.idLocation = numeroLocation;
		this.locations = locations;
		this.dateDebutLocation = dateDebutLocation;
		this.dateFinLocation = dateFinLocation;
		this.dateRemise = dateRemise;
		this.devis = devis;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.vehicules = vehicules;
		locations.add(this);
	}
	
	
	
	
	@Override
	public void dureeEffective() {}
	
	@Override
	public void calculPrixLocation() {}
	
	public int getNumeroLocation() {
		return idLocation;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public Date getDateDebutLocation() {
		return dateDebutLocation;
	}

	public Date getDateFinLocation() {
		return dateFinLocation;
	}

	public Date getDateRemise() {
		return dateRemise;
	}

	public Devis getDevis() {
		return devis;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setNumeroLocation(int numeroLocation) {
		this.idLocation = numeroLocation;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public void setDateDebutLocation(Date dateDebutLocation) {
		this.dateDebutLocation = dateDebutLocation;
	}

	public void setDateFinLocation(Date dateFinLocation) {
		this.dateFinLocation = dateFinLocation;
	}

	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}

	// ajouter directement chaque nouvelle location à la liste des locations
	
}
