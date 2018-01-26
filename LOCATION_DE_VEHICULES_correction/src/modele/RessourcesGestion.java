package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import bean.Auto;
import bean.Exemplaire;
import bean.Moto;
import bean.Vehicule;
import dao.Flotte;
import dao.Vehicules;

/**
 * Represente la classe effectuant la logique m�tier de manipulations des
 * exemplaires de v�hicules de la soci�t�
 * 
 * @author adminonedy
 *
 */
public class RessourcesGestion {

	/**
	 * L'unique instance de la classe encapsulant la liste des exemplaires de
	 * v�hicules de la soci�t�
	 */
	private static final Flotte exemplaires = Flotte.getDefaultFlotte();

	/**
	 * @return la liste des cexemplaires de v�hicules de la soci�t�
	 */
	public static ArrayList<Exemplaire> getExemplaires() {
		return exemplaires.getExemplaires();
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public static void serialiser() {
		exemplaires.serialiser();
	}

	/**
	 * Prix de location par defaut des mod�le de moto
	 */
	public static final int DEFAULT_PRIX_MOTO = 150;
	/**
	 * Prix de location par defaut des mod�le d'automobile standard
	 */
	public static final int DEFAULT_PRIX_AUTO_STANDARD = 300;
	/**
	 * Prix de location par defaut des mod�le d'automobile de luxe
	 */
	public static final int DEFAULT_PRIX_AUTO_DELUXE = 500;

	/**
	 * Ajoute un nouvel exemplaire dans la liste des exemplaires de v�hicules de la
	 * soci�t�
	 * 
	 * @param vehicule
	 * @param kilometre
	 * @return
	 */
	public static Exemplaire ajouterExemplaire(Vehicule vehicule, int kilometre) {
		return new Exemplaire(kilometre, vehicule);
	}

	/**
	 * Supprime un exemplaire s'il n'est pas en cours de locations dans la liste des
	 * exemplaires de v�hicules de la soci�t�
	 * 
	 * @param exemplaire
	 */
	public static void supprimerExemplaire(Exemplaire exemplaire) {
		if (exemplaire.getLocation() != null) {
			LocationsGestion.supprimerLocation(exemplaire.getLocation());
		}
		actualiser();
	}

	/**
	 * Red�finit les identifiants de chaque exemplaire dans la liste par ordre
	 * croissant et par pas de 1
	 */
	public static void actualiser() {
		int size = exemplaires.getExemplaires().size();
		for (int i = 0; i < size; i++) {
			exemplaires.getExemplaires().get(i).setNumero(i + 1);
		}
	}

	/**
	 * Trie la principale liste d'exemplaires par ordre lexicographique des marques
	 */
	public static void trieParMarque() {
		Collections.sort(exemplaires.getExemplaires());
	}

	/**
	 * Trie la liste principale d'exemplaire par ordre croissant du kilom�trage
	 */
	public static void trieParKilometrage() {
		Collections.sort(exemplaires.getExemplaires(), new Comparator<Exemplaire>() {
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
	 * Trie la liste principale d'exemplaire par ordre croissant du prix de location
	 */
	public static void trieParPrix() {
		Collections.sort(exemplaires.getExemplaires(), new Comparator<Exemplaire>() {
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
	 * Recherche la liste des exemplaires en fonction d'une marque de v�hicule en
	 * particulier
	 * 
	 * @param marque
	 * @return
	 */
	public static ArrayList<Exemplaire> rechercheParMarque(String marque) {
		ArrayList<Exemplaire> temp = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			if (exemplaire.getVehicule().getMarque() == marque) {
				temp.add(exemplaire);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste des exemplaires en fonction d'un kilom�trage pr�cis
	 * 
	 * @param kilometre
	 * @return
	 */
	public static ArrayList<Exemplaire> rechercheParKilometrage(int kilometre) {
		ArrayList<Exemplaire> temp = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			if (exemplaire.getKilometre() == kilometre) {
				temp.add(exemplaire);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste d'exemplaires de motos de la soci�t�
	 * 
	 * @return
	 */
	public static ArrayList<Exemplaire> listeMoto() {
		ArrayList<Exemplaire> temp = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			if (exemplaire.getVehicule() instanceof Moto) {
				temp.add(exemplaire);
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste d'exemplaires d'automobiles standards de la soci�t�
	 * 
	 * @return
	 */
	public static ArrayList<Exemplaire> listeVoitureStandar() {
		ArrayList<Exemplaire> temp = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			if (exemplaire.getVehicule() instanceof Auto) {
				if (!((Auto) exemplaire.getVehicule()).isDeLuxe()) {
					temp.add(exemplaire);
				}
			}
		}
		return temp;
	}

	/**
	 * Recherche la liste d'exemplaires d'automobiles de luxe de la soci�t�
	 * 
	 * @return
	 */
	public static ArrayList<Exemplaire> listeVoitureDeluxe() {
		ArrayList<Exemplaire> temp = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			if (exemplaire.getVehicule() instanceof Auto) {
				if (((Auto) exemplaire.getVehicule()).isDeLuxe()) {
					temp.add(exemplaire);
				}
			}
		}
		return temp;
	}

	/**
	 * Cr�er une chaine de cart�re format�e d'une liste d'exemplaires particuli�re
	 * 
	 * @param exemplaires
	 * @return
	 */
	public static String resultatRecherche(ArrayList<Exemplaire> exemplaires) {
		StringBuilder st = new StringBuilder("Exemplaires: \n");
		for (Exemplaire exemplaire : exemplaires) {
			st.append(exemplaire.toString() + "\n");
		}
		return st.toString();
	}

	/**
	 * Enregistre en m�moire une liste d'exemplaires dont les informations sont
	 * stock�es dans un fichier de donn�e pass� en param�tre
	 * 
	 * @param path
	 */
	public static void transfertFlotte(String path) {
		Vehicules vehicules = Vehicules.getDefaultVehicules();
		Scanner sc1 = null, sc2 = null; // D�claration de deux objets Scanners, un pour parcourir le fichier et l'autre
		// pour parcourir chaque ligne
		try {
			sc1 = new Scanner(new File(path));
			int etage = 0; // Represente la ligne en cours de parcour
			int type = 1; // Represente une ligne d�finissants le type et le nombre d'�l�ments, ie: "20
							// MOTOS" ou "20 AUTOS DE LUXE"
			int nbreExemplaire = 0; // Represnte le nombre d'�l�ments defini dans type
			String element = ""; // Represente le type d'�l�ments list�s
			while (sc1.hasNext()) {
				etage++;
				sc2 = new Scanner(sc1.nextLine());
				if (etage == type) { // Si nous sommes dans une ligne de d�finition, on recupere le nombre et le type
										// d'�lements list�
					int colone = 0;
					String s1 = "";
					String s2 = "";
					while (sc2.hasNext()) {
						colone++;
						if (colone == 1) {
							nbreExemplaire = sc2.nextInt();
							type = type + 1 + nbreExemplaire; // On r�cup�re la prochaine ligne de d�finition par le
																// nombre d'�l�m�nt, sachant qu'il n'y a pas de ligne
																// vide
						} else if (colone == 2) {
							s1 = sc2.next();
						} else if (colone == 3)
							s2 = sc2.next();
						else
							sc2.next();
					}
					if (s1.compareTo("MOTOS") == 0)
						element = s1;
					else if (s1.compareTo("AUTOS") == 0) {
						if (s2.compareTo("STANDARDS") == 0)
							element = s2;
						else
							element = "DELUXE";
					}
				} else if ((nbreExemplaire != 0) && (etage != type)) {
					// Si nous sommes dans une ligne de listage, on cr�e l'�l�ment en cour, en
					// testant � chaque fois si il correspond � une nouvelle marque de v�hicule ou
					// un nouvel exemplaire
					if (element.compareTo("MOTOS") == 0) {
						String marque = sc2.next();
						int cylindree = sc2.nextInt();
						Vehicule moto = null;
						for (Vehicule vehicule : vehicules) {
							if (vehicule instanceof Moto) {
								if ((vehicule.getMarque().compareTo(marque) == 0)
										&& (((Moto) vehicule).getCylindree() == cylindree)) {
									moto = (Moto) vehicule;
									break;
								}
							}
						}
						if (moto == null) {
							moto = new Moto(vehicules.getVehicules().size() + 1, marque, DEFAULT_PRIX_MOTO, cylindree);
						}
						int numero = sc2.nextInt();
						int kilometre = sc2.nextInt();
						Exemplaire exemplaire = null;
						for (Exemplaire exemplaire2 : exemplaires) {
							if (exemplaire2.getVehicule() instanceof Moto) {
								if ((exemplaire2.getVehicule() == moto) && (exemplaire2.getKilometre() == kilometre)) {
									exemplaire = exemplaire2;
									break;
								}
							}
						}
						if (exemplaire == null) {
							exemplaire = new Exemplaire(kilometre, moto);
						}
						nbreExemplaire--;
					} else if (element.compareTo("STANDARDS") == 0) {
						String marque = sc2.next();
						String modele = sc2.next();
						Boolean deLuxe = false;
						Vehicule auto = null;
						for (Vehicule vehicule : vehicules) {
							if (vehicule instanceof Auto) {
								if ((vehicule.getMarque().compareTo(marque) == 0)
										&& (((Auto) vehicule).getModele().compareTo(modele) == 0)
										&& (!((Auto) vehicule).isDeLuxe())) {
									auto = (Auto) vehicule;
									break;
								}
							}
						}
						if (auto == null) {
							auto = new Auto(marque, DEFAULT_PRIX_AUTO_STANDARD, modele, deLuxe);
						}
						int numero = sc2.nextInt();
						int kilometre = sc2.nextInt();
						Exemplaire exemplaire = null;
						for (Exemplaire exemplaire2 : exemplaires) {
							if (exemplaire2.getVehicule() instanceof Auto) {
								if ((exemplaire2.getVehicule() == auto) && (exemplaire2.getKilometre() == kilometre)) {
									exemplaire = exemplaire2;
									break;
								}
							}
						}
						if (exemplaire == null) {
							exemplaire = new Exemplaire(kilometre, auto);
						}
						nbreExemplaire--;

					} else if (element.compareTo("DELUXE") == 0) {
						String marque = sc2.next();
						String modele = sc2.next();
						Boolean deLuxe = true;
						Vehicule auto = null;
						for (Vehicule vehicule : vehicules) {
							if (vehicule instanceof Auto) {
								if ((vehicule.getMarque().compareTo(marque) == 0)
										&& (((Auto) vehicule).getModele().compareTo(modele) == 0)
										&& (((Auto) vehicule).isDeLuxe())) {
									auto = (Auto) vehicule;
									break;
								}
							}
						}
						if (auto == null) {
							auto = new Auto(marque, DEFAULT_PRIX_AUTO_DELUXE, modele, deLuxe);
						}
						int numero = sc2.nextInt();
						int kilometre = sc2.nextInt();
						Exemplaire exemplaire = null;
						for (Exemplaire exemplaire2 : exemplaires) {
							if (exemplaire2.getVehicule() instanceof Auto) {
								if ((exemplaire2.getVehicule() == auto) && (exemplaire2.getKilometre() == kilometre)) {
									exemplaire = exemplaire2;
									break;
								}
							}
						}
						if (exemplaire == null) {
							exemplaire = new Exemplaire(kilometre, auto);
						}
						nbreExemplaire--;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
