package bean;

import java.io.Serializable;
import java.util.ArrayList;

import dao.Vehicules;

/**
 * @author adminonedy
 *Represente un modèle abstrait d'une marque de véhicule quelconque
 */
public abstract class Vehicule implements Comparable<Vehicule>, Serializable {

	/**
	 * Represente un identifiant unique du modèle
	 */
	private int numero;
	/**
	 * Represente la marque du modèle
	 */
	protected String marque;
	/**
	 * Represente le prix du modèle
	 */
	protected int prix;
	/**
	 * Represente la liste de tous les modèles de lasociété
	 */
	protected final Vehicules vehicules = Vehicules.getDefaultVehicules();
	/**
	 * Represente la liste d'exemplaires du modèle existant dans la flotte
	 */
	protected final ArrayList<Exemplaire> exemplaires;

	/**
	 * Crée une marque avec des données vide et l'ajoute automatiquement dans la liste principale des marques
	 */
	public Vehicule() {
		this.numero = vehicules.getVehicules().size() + 1;
		marque = "";
		prix = 0;
		vehicules.getVehicules().add(this);
		exemplaires = new ArrayList<Exemplaire>();
	}

	/**
	 * Crée une marque avec les données spécifié en paramètre et l'ajoute
	 * automatiquement dans la liste principale des marques
	 * 
	 * @param marque
	 * @param prix
	 */
	public Vehicule(String marque, int prix) {
		this.numero = vehicules.getVehicules().size() + 1;;
		this.marque = marque;
		this.prix = prix;
		vehicules.getVehicules().add(this);
		exemplaires = new ArrayList<Exemplaire>();
	}

	public ArrayList<Exemplaire> getExemplaires() {
		return exemplaires;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public int compareTo(Vehicule vehicule) {
		return marque.compareTo(vehicule.marque);
	}

}
