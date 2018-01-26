package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import bean.Adresse;
import bean.Auto;
import bean.Emprunteur;
import bean.Exemplaire;
import bean.Location;
import dao.Emprunteurs;

/**
 * Represente la classe effectuant la logique métier de manipulations des
 * clients de la société
 * 
 * @author adminonedy
 */
public class EmprunteursGestion {

	/**
	 * L'unique instance de la classe encapsulant la liste des clients de la société
	 */
	private static final Emprunteurs emprunteurs = Emprunteurs.getDefaultEmprunteurs();

	/**
	 * @return la liste des clients de la société
	 */
	public static ArrayList<Emprunteur> getEmprunteurs() {
		return emprunteurs.getEmprunteurs();
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public static void serialiser() {
		emprunteurs.serialiser();
	}

	/**
	 * Ajoute un nouveau client dans la liste des clients de la société
	 * 
	 * @param nom
	 * @param prenom
	 * @param ville
	 * @param rue
	 * @param cp
	 * @param numRue
	 * @return Le nouveau client ajouté
	 */
	public static Emprunteur ajouterEmprunteur(String nom, String prenom, String ville, String rue, int cp,
			int numRue) {
		return new Emprunteur(nom, prenom, new Adresse(ville, rue, cp, numRue));
	}

	/**
	 * Supprime un client dans la liste des clients de la société
	 * 
	 * @param emprunteur
	 */
	public static void supprimerEmprunteur(Emprunteur emprunteur) {
		emprunteurs.getEmprunteurs().remove(emprunteur);
		ArrayList<Location> locations = emprunteur.getLocations();
		for (Location location : locations) {
			LocationsGestion.supprimerLocation(location);
		}
		actualiser();
	}

	/**
	 * Redéfinit les identifiants de chaque client dans la liste par ordre croissant
	 * et par pas de 1
	 */
	public static void actualiser() {
		int size = emprunteurs.getEmprunteurs().size();
		for (int i = 0; i < size; i++) {
			emprunteurs.getEmprunteurs().get(i).setId(i + 1);
		}
	}

	/**
	 * Trie la principale liste des clients par ordre lexicographique de nom
	 */
	public static void trieParNom() {
		Collections.sort(emprunteurs.getEmprunteurs());
	}

	/**
	 * Trie la principale liste des clients par ordre croissant des factures total
	 * de location
	 */
	public static void trieParFactureTotal() {
		Collections.sort(emprunteurs.getEmprunteurs(), new Comparator<Emprunteur>() {
			@Override
			public int compare(Emprunteur emprunteur1, Emprunteur emprunteur2) {
				if (emprunteur1.getFactureTotal() == emprunteur2.getFactureTotal())
					return 0;
				else if (emprunteur1.getFactureTotal() > emprunteur2.getFactureTotal())
					return 1;
				else
					return -1;
			}
		});
	}

	/**
	 * Recherche la liste des clients ayant un nom particulier
	 * 
	 * @param nom
	 * @return la liste de client ayant le nom spécifié
	 */
	public static ArrayList<Emprunteur> rechercheParNom(String nom) {
		ArrayList<Emprunteur> temp = new ArrayList<Emprunteur>();
		for (Emprunteur emprunteur : emprunteurs) {
			if (emprunteur.getNom() == nom) {
				temp.add(emprunteur);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste des clients en fonction de l'etat de la location(En cours
	 * ou non)
	 * 
	 * @param locationEnCours
	 * @return la liste de client ayant l'état spécifié
	 */
	public static ArrayList<Emprunteur> rechercheParLocationEnCours(boolean locationEnCours) {
		ArrayList<Emprunteur> temp = new ArrayList<Emprunteur>();
		for (Emprunteur emprunteur : emprunteurs) {
			LocationsGestion.bilan(emprunteur);
			if (emprunteur.isLocationEnCours() == locationEnCours) {
				temp.add(emprunteur);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste des clients en fonction d'une marque de véhicule loué
	 * 
	 * @param marque
	 * @return la liste de client ayant louer la marque spécifié
	 */
	public static ArrayList<Emprunteur> rechercheParMarque(String marque) {
		ArrayList<Emprunteur> temp = new ArrayList<Emprunteur>();
		for (Emprunteur emprunteur : emprunteurs) {
			ArrayList<Location> temp2 = emprunteur.getLocations();
			for (Location location : temp2) {
				if (location.getExemplaire().getVehicule().getMarque() == marque) {
					temp.add(emprunteur);
					break;
				}
			}
		}
		return temp;
	}

	/**
	 * Créer une chaine de cartère formatée d'une liste de client particulière
	 * 
	 * @param list
	 * @return la chaine de caractères formatée
	 */
	public static String resultatRecherche(ArrayList<Emprunteur> emprunteurs) {
		StringBuilder st = new StringBuilder("Emprunteurs trouvés: \n");
		for (Emprunteur emprunteur : emprunteurs) {
			st.append(emprunteur.toString() + "\n");
		}
		return st.toString();
	}

	/**
	 * Enregistre en mémoire une liste de clients dont les informations sont
	 * stockées dans un fichier de donnée passé en paramètre
	 * 
	 * @param path
	 * 
	 * 
	 */
	public static void transfertAdherents(String path) {
		Scanner sc1 = null, sc2 = null; // Déclaration de deux objets Scanners, un pour parcourir le fichier et l'autre
										// pour parcourir chaque ligne
		try {
			sc1 = new Scanner(new File(path));
			while (sc1.hasNext()) {
				sc2 = new Scanner(sc1.nextLine());
				String prenom = sc2.next();
				String nom = sc2.next();
				String ville = sc2.next();
				String rue = sc2.next();
				int cp = sc2.nextInt();
				int numero = sc2.nextInt();
				Adresse adresse = new Adresse(ville, rue, cp, numero);
				Emprunteur emprunteur = null;
				for (Emprunteur emprunteur2 : emprunteurs) {
					if ((emprunteur2.getNom().equals(nom)) && (emprunteur2.getPrenom().equals(prenom))
							&& (emprunteur2.getAdresse().getVille().equals(ville))
							&& (emprunteur2.getAdresse().getRue().equals(rue))
							&& (emprunteur2.getAdresse().getNumero() == numero)) {
						emprunteur = emprunteur2;
						break;
					}
				}
				if (emprunteur == null) {
					emprunteur = new Emprunteur(nom, prenom, adresse);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
