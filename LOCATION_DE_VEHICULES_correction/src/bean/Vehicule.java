package bean;

import java.io.Serializable;
import java.util.ArrayList;

import dao.Vehicules;

/**
 * @author adminonedy
 *Represente un mod�le abstrait d'une marque de v�hicule quelconque
 */
public abstract class Vehicule implements Comparable<Vehicule>, Serializable {

	/**
	 * Represente un identifiant unique du mod�le
	 */
	private int numero;
	/**
	 * Represente la marque du mod�le
	 */
	protected String marque;
	/**
	 * Represente le prix du mod�le
	 */
	protected int prix;
	/**
	 * Represente la liste de tous les mod�les de lasoci�t�
	 */
	protected final Vehicules vehicules = Vehicules.getDefaultVehicules();
	/**
	 * Represente la liste d'exemplaires du mod�le existant dans la flotte
	 */
	protected final ArrayList<Exemplaire> exemplaires;

	/**
	 * Cr�e une marque avec des donn�es vide et l'ajoute automatiquement dans la liste principale des marques
	 */
	public Vehicule() {
		this.numero = vehicules.getVehicules().size() + 1;
		marque = "";
		prix = 0;
		vehicules.getVehicules().add(this);
		exemplaires = new ArrayList<Exemplaire>();
	}

	/**
	 * Cr�e une marque avec les donn�es sp�cifi� en param�tre et l'ajoute
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
