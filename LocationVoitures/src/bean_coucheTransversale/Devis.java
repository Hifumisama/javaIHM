package bean_coucheTransversale;

import java.util.ArrayList;

import dao.Autos;
import model.GestionLocations;

public class Devis implements GestionLocations {

	// represente la location liée au devis, et contenant la référence vers le
	// véhicule et vers le client concerné
	private Location location;
	private Emprunteur emprunteur;
	private Vehicule vehicule;
	private double prix;
	private double facturePartielle;
	private int penalitesRetard = 0;

	/**
	 * REPRESENTE LE PRIX DU CARBURANT CONSOMME PENDANT LA LOCATION
	 */
	private int consoCarburant = 0;

	private int reparation = 0;

	/**
	 * CONSTRUCTEUR PAR DEFAUT
	 */
	public Devis() {
		super();
	}

	/**
	 * CONSTRUCTEUR AVEC PARAMETRES
	 */
	public Devis(Location location, double prix, double facturePartielle, int penalitesRetard, int consoCarburant,
			int reparation) {
		super();
		this.location = location;
		this.prix = prix;
		this.facturePartielle = facturePartielle;
		this.penalitesRetard = penalitesRetard;
		this.consoCarburant = consoCarburant;
		this.reparation = reparation;
	}

	
	@Override
	public void calculPrixLocation() {
		Auto a = new Auto();
		ArrayList<Auto> listeAutos = new ArrayList<Auto>();
		
		for (Auto : listeAutos) {

			if (a.getPrix() > 35000 && a.isDeluxe()) {
				this.prix = 450;
			} else {
				this.prix = 250;
			}
		}
	}

	public Location getLocation() {
		return location;
	}

	public double getPrix() {
		return prix;
	}

	public double getFacturePartielle() {
		return facturePartielle;
	}

	public int getPenalitesRetard() {
		return penalitesRetard;
	}

	public int getConsoCarburant() {
		return consoCarburant;
	}

	public int getReparation() {
		return reparation;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setFacturePartielle(double facturePartielle) {
		this.facturePartielle = facturePartielle;
	}

	public void setPenalitesRetard(int penalitesRetard) {
		this.penalitesRetard = penalitesRetard;
	}

	public void setConsoCarburant(int consoCarburant) {
		this.consoCarburant = consoCarburant;
	}

	public void setReparation(int reparation) {
		this.reparation = reparation;
	}

}
