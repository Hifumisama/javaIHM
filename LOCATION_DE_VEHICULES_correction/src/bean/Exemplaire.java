package bean;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.Objects;

import dao.Flotte;

/**
 * @author adminonedy Represente un exemplaire de véhicule palpable
 */
public class Exemplaire implements Comparable<Exemplaire>, Serializable {

	/**
	 * Represente la liste des exemplaires de la société
	 */
	private final Flotte exemplaires = Flotte.getDefaultFlotte();
	/**
	 * Represente l'identifiant unque de l'exemplaire
	 */
	private int numero;
	/**
	 * Represente le kilometrage de l'exemplaire et le maximun est de 180.000 kms
	 */
	private int kilometre;
	/**
	 * Represente le prix de location journalier de l'exemplaire. Ce prix vaut 10%
	 * de réduction sur le prix du modèle par tranche de 50.000 kms
	 */
	private int prix;
	/**
	 * Represente le modèle et la marque de véhicule correspondant à l'exemplaire.
	 */
	private Vehicule vehicule;
	/**
	 * Represente la location associé à l'exemplaire. Elle vaut nulle, si
	 * l'exemplaire n'est pas en cours de location
	 */
	private Location location;

	/**
	 * Crée un nouvel exemplaire avec des données vide et l'ajoute automatiquement
	 * dans la liste principale des exemplaires
	 */
	public Exemplaire() {
		this.numero = exemplaires.getExemplaires().size() + 1;
		this.vehicule = null;
		this.kilometre = 0;
		this.prix = 0; // // de 50 000 kms
		exemplaires.getExemplaires().add(this);
	}

	/**
	 * Crée un nouvel exemplaire avec les données spécifié en paramètre et l'ajoute
	 * automatiquement dans la liste principale des exemplaires
	 * 
	 * @param kilometre
	 * @param vehicule
	 */
	public Exemplaire(int kilometre, Vehicule vehicule) {
		Objects.requireNonNull(vehicule);
		this.numero = exemplaires.getExemplaires().size() + 1;
		this.vehicule = vehicule;
		int temp = kilometre;
		if (temp > 180000) {
			temp = 180000;
		}
		this.kilometre = temp;
		this.prix = vehicule.getPrix() - (vehicule.getPrix() * kilometre / 50000 * 10 / 100); // 10% de réduction par
																								// tranche // // de 50
																								// 000 kms
		exemplaires.getExemplaires().add(this);
		vehicule.getExemplaires().add(this);
	}

	public int getKilometre() {
		return kilometre;
	}

	public void setKilometre(int kilometre) {
		this.kilometre = kilometre;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix() {
		if ((vehicule != null) && (kilometre != 0))
			this.prix = vehicule.getPrix() - (vehicule.getPrix() * kilometre / 50000 * 10 / 100); // 10% de réduction
																									// par
																									// tranche;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		vehicule.getExemplaires().add(this);
		this.vehicule = vehicule;
	}

	public boolean isLocationEnCours() {
		if (location == null)
			return false;
		else
			return true;
	}

	@Override
	public int compareTo(Exemplaire exemplaire) {
		if ((exemplaire.getVehicule() == null) && (vehicule != null)) {
			return vehicule.getMarque().compareTo("");
		} else if ((exemplaire.getVehicule() != null) && (vehicule == null)) {
			return "".compareTo(exemplaire.getVehicule().getMarque());
		} else if ((exemplaire.getVehicule() == null) && (vehicule == null)) {
			return "".compareTo("");
		} else {
			return vehicule.getMarque().compareTo(exemplaire.getVehicule().getMarque());
		}
	}

	@Override
	public String toString() {
		if (vehicule instanceof Auto) {
			return ((Auto)vehicule).getMarque() + "-" + ((Auto)vehicule).getModele() + ", " + kilometre + "Km";
		}
		else {
			return ((Moto)vehicule).getMarque() + "-" + ((Moto)vehicule).getCylindree() + ", " + kilometre + "Km";
		}
	}

}
