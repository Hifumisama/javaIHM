package bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author adminonedy Represente l'ensemble des prix ou taxe appliqué à la
 *         location d'un véhicule bien précis
 */
public class Devis implements Serializable {

	/**
	 * Represente la location lié au devis, et contenant la reference vers le
	 * véhicule et vers le client concerné
	 */
	private final Location location;
	/**
	 * Represente le prix de base de la location et est égale au prix de location
	 * journalier du véhicule multplié par le nombre de jour de location
	 */
	private int prix;
	/**
	 * Represente le prix ttc de la location d'un véhicule précis Ce prix évolue
	 * avec l'état de la location
	 */
	private int facturePartiel;
	/**
	 * Represente une taxe appliqué lorsqu'un véhicule est retourné en retard. Cette
	 * taxe vaut 150% du prix de la location pour chaque jour de reatard
	 */
	private int penaliteRetard = 0;
	/**
	 * Represente le prix du carburant consommé pendant la location. Le prix du
	 * plein vaux 100, le prix de la consommation vaut dont la fraction consommé
	 * multiplié par 100
	 */
	private int consoCarburant = 0;
	/**
	 * Represente le prix des reparations associé aux degats d'un véhicule loués. Si
	 * une assurance est souscrit, ce prix est fixé à 300, si non ce prix 500
	 * multiplié par la fraction de degat comis.
	 */
	private int reparation = 0;

	/**
	 * Crée un nouveau devis avec les données spécifié en paramètre
	 * 
	 * @param location
	 * @param prix
	 * @param debut
	 * @param fin
	 */
	public Devis(Location location, int prix, Date debut, Date fin) {
		Objects.requireNonNull(location);
		Objects.requireNonNull(debut);
		Objects.requireNonNull(fin);
		this.location = location;
		this.prix = (int) (prix * Date.differenceJour(fin, debut));
		this.facturePartiel = this.prix;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getFacturePartiel() {
		return facturePartiel;
	}

	public void setFacturePartiel(int facturePartiel) {
		this.facturePartiel = facturePartiel;
	}

	public int getPenaliteRetard() {
		return penaliteRetard;
	}

	public void setPenaliteRetard(int penaliteRetard) {
		this.penaliteRetard = penaliteRetard;
	}

	public int getConsoCarburant() {
		return consoCarburant;
	}

	public void setConsoCarburant(int consoCarburant) {
		this.consoCarburant = consoCarburant;
	}

	public int getReparation() {
		return reparation;
	}

	public void setReparation(int reparation) {
		this.reparation = reparation;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return String.format("|%-10d| |%-10d| |%-10d| |%-10d| |%-10d|", penaliteRetard, consoCarburant, reparation,
				prix, facturePartiel);
	}

}
