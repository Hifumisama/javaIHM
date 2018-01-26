package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bean.Auto;
import bean.Exemplaire;
import bean.Moto;
import bean.Vehicule;
import dao.Vehicules;

/**
 * Represente la classe effectuant la logique métier de manipulation des modèles
 * de véhicules de la société
 * 
 * @author adminonedy
 *
 */
public class MarquesGestion {

	/**
	 * L'unique instance de la classe encapsulant la liste des modèles de vehicules
	 * de la société
	 */
	private static final Vehicules vehicules = Vehicules.getDefaultVehicules();

	/**
	 * @return la liste des modèles de vehicules de la société
	 */
	public static ArrayList<Vehicule> getVehicules() {
		return vehicules.getVehicules();
	}
	
	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public static void serialiser() {
		vehicules.serialiser();
	}

	/**
	 * Ajoute un nouveau modèle d'automobiles dans la liste des marques de la
	 * société
	 * 
	 * @param marque
	 * @param prix
	 * @param modele
	 * @param deLuxe
	 * @return
	 */
	public static Auto ajouterAuto(String marque, int prix, String modele, boolean deLuxe) {
		return new Auto(marque, prix, modele, deLuxe);
	}

	/**
	 * Ajoute un nouveau modèle de moto dans la liste des marques de la société
	 * 
	 * @param marque
	 * @param prix
	 * @param cylindree
	 * @return
	 */
	public static Moto ajouterMoto(String marque, int prix, int cylindree) {
		return new Moto(vehicules.getVehicules().size() + 1, marque, prix, cylindree);
	}

	/**
	 * Supprime un modèle de véhicule dans la liste des marques de la société
	 * 
	 * @param vehicule
	 */
	public static void supprimerVehicule(Vehicule vehicule) {
		ArrayList<Exemplaire> exemplaires = vehicule.getExemplaires();
		for (Exemplaire exemplaire : exemplaires) {
			RessourcesGestion.supprimerExemplaire(exemplaire);
		}
		vehicule.getExemplaires().clear();
		actualiser();
	}

	/**
	 * Redéfinit les identifiants de chaque modèle dans la liste par ordre croissant
	 * et par pas de 1
	 */
	public static void actualiser() {
		int size = vehicules.getVehicules().size();
		for (int i = 0; i < size; i++) {
			vehicules.getVehicules().get(i).setNumero(i + 1);
		}
	}

	/**
	 * Trie la principale liste des modèles par ordre lexicographique des marques
	 */
	public static void trieParMarque() {
		Collections.sort(vehicules.getVehicules());
	}

	/**
	 * Trie la principale liste des modèles par ordre croissant des prix de location
	 */
	public void trieParPrix() {
		Collections.sort(vehicules.getVehicules(), new Comparator<Vehicule>() {
			@Override
			public int compare(Vehicule vehicule1, Vehicule vehicule2) {
				if (vehicule1.getPrix() == vehicule2.getPrix())
					return 0;
				else if (vehicule1.getPrix() > vehicule2.getPrix())
					return 1;
				else
					return -1;
			}
		});
	}

	/**
	 * Trie la liste d'exemplaire du modèle passé en paramètre par ordre croissant
	 * du kilométrage
	 * 
	 * @param vehicule
	 */
	public void trieExemplaireParKilometrage(Vehicule vehicule) {
		Collections.sort(vehicule.getExemplaires(), new Comparator<Exemplaire>() {
			@Override
			public int compare(Exemplaire exemplaire1, Exemplaire exemplaire2) {
				if (exemplaire1.getKilometre() == exemplaire2.getKilometre())
					return 0;
				else if (exemplaire1.getKilometre() > exemplaire2.getKilometre())
					return 1;
				else
					return -1;
			}
		});
	}

	/**
	 * Trie la liste d'exemplaire du modèle passé en paramètre par ordre croissant
	 * du prix de location
	 * 
	 * @param vehicule
	 */
	public void trieExemplaireParPrix(Vehicule vehicule) {
		Collections.sort(vehicule.getExemplaires(), new Comparator<Exemplaire>() {
			@Override
			public int compare(Exemplaire exemplaire1, Exemplaire exemplaire2) {
				if (exemplaire1.getPrix() == exemplaire2.getPrix())
					return 0;
				else if (exemplaire1.getPrix() > exemplaire2.getPrix())
					return 1;
				else
					return -1;
			}
		});
	}

	/**
	 * Recherche la liste des modèles de motos de la société
	 * 
	 * @return
	 */
	public static ArrayList<Moto> listeMoto() {
		ArrayList<Moto> temp = new ArrayList<Moto>();
		for (Vehicule vehicule : vehicules) {
			if (vehicule instanceof Moto) {
				temp.add((Moto) vehicule);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste des modèles d'automobiles de la société
	 * 
	 * @return
	 */
	public static ArrayList<Auto> listeVoiture() {
		ArrayList<Auto> temp = new ArrayList<Auto>();
		for (Vehicule vehicule : vehicules) {
			if (vehicule instanceof Auto) {
				temp.add((Auto) vehicule);
			}
		}
		return temp;
	}

	/**
	 * Créer une chaine de cartère formatée d'une liste de modèle particulié
	 * 
	 * @param vehicules
	 * @return
	 */
	public static String resultatRecherche(ArrayList<Vehicule> vehicules) {
		StringBuilder st = new StringBuilder("Marque: \n");
		for (Vehicule vehicule : vehicules) {
			st.append(vehicule.toString() + "\n");
		}
		return st.toString();
	}

}
