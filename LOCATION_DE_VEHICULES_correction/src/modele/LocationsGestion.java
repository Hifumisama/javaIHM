package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
// import com.itextpdf.text.BaseColor;
// import com.itextpdf.text.Document;
// import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.Element;
// import com.itextpdf.text.Font;
// import com.itextpdf.text.PageSize;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.Font.FontFamily;
// import com.itextpdf.text.pdf.PdfWriter;

import bean.*;
import dao.Locations;

/**
 * Represente la classe effectuant la logique métier de manipulation des
 * locations de la société
 * 
 * @author adminonedy
 *
 */
public class LocationsGestion {

	/**
	 * L'unique instance de la classe encapsulant la liste des locations de la
	 * société
	 */
	private static final Locations locations = Locations.getDefaultLocations();

	/**
	 * @return la liste des locations de la société
	 */
	public static ArrayList<Location> getLocations() {
		return locations.getLocations();
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public static void serialiser() {
		locations.serialiser();
	}

	/**
	 * Represente l'action de louer et ajoute une nouvelle location dans la liste
	 * des locations de la société
	 * 
	 * @param emprunteur
	 * @param exemplaire
	 * @param assurance
	 * @param jourDebut
	 * @param moisDebut
	 * @param anneeDebut
	 * @param jourFin
	 * @param moisFin
	 * @param anneeFin
	 * @return
	 * @throws Exception
	 */
	public static Location louer(Emprunteur emprunteur, Exemplaire exemplaire, boolean assurance, int jourDebut,
			int moisDebut, int anneeDebut, int jourFin, int moisFin, int anneeFin) throws Exception {
		Location location = new Location(exemplaire, emprunteur, new Date(jourDebut, moisDebut, anneeDebut),
				new Date(jourFin, moisFin, anneeFin), assurance);
		bilan(emprunteur);
		return location;
	}

	/**
	 * Represente l'action de ramener et attribut les valeurs concérné par
	 * l'opération dans la location donc il est question
	 * 
	 * @param location
	 * @param jourRemise
	 * @param moisRemise
	 * @param anneeRemise
	 * @param carburant
	 * @param degat
	 */
	public static void ramener(Location location, int jourRemise, int moisRemise, int anneeRemise, Carburant carburant,
			Degat degat) {
		Date remise = new Date(jourRemise, moisRemise, anneeRemise);
		if (location.isLocationEnCours() == true) {
			location.setDateRemise(remise);
			location.setCarburant(carburant);
			location.setDegat(degat);
			location.getDevis().setPenaliteRetard(
					(int) (location.getDevis().getPrix() * Date.differenceJour(remise, location.getFin()) * 3 / 2));
			location.getDevis().setConsoCarburant(carburant.ordinal() * 100 / 4);
			if (carburant == Carburant.VIDE) {
				location.getDevis().setConsoCarburant(100);
			}
			if (!location.isAssurance()) {
				location.getDevis().setReparation(degat.ordinal() * 500);
			} else {
				location.getDevis().setReparation(300);
			}
			location.getDevis()
					.setFacturePartiel(location.getDevis().getPrix() + location.getDevis().getPenaliteRetard()
							+ location.getDevis().getConsoCarburant() + location.getDevis().getReparation());
			location.setLocationEnCours(false);
			location.getExemplaire().setLocation(null);
		}
		bilan(location.getEmprunteur());
	}

	/**
	 * Supprime une location dans la liste des locations de la société
	 * 
	 * @param location
	 */
	public static void supprimerLocation(Location location) {
		locations.getLocations().remove(location);
		location.getEmprunteur().getLocations().remove(location);
		location.getExemplaire().setLocation(null);
		actualiser();
	}

	/**
	 * Redéfinit les identifiants de chaque location dans la liste par ordre
	 * croissant et par pas de 1
	 */
	public static void actualiser() {
		int size = locations.getLocations().size();
		for (int i = 0; i < size; i++) {
			locations.getLocations().get(i).setNumero(i + 1);
		}
	}

	/**
	 * Vérifie letat de toutes les locations du clients passé en paramètre et met à
	 * jour le prix total des locations, la facture ttc, et l'etat global des
	 * locations
	 * 
	 * @param emprunteur
	 */
	public static void bilan(Emprunteur emprunteur) {
		int factureTotal = 0;
		int prixLocation = 0;
		boolean locationEnCours = false;
		for (Location location : emprunteur.getLocations()) {
			factureTotal += location.getDevis().getFacturePartiel();
			prixLocation += location.getDevis().getPrix();
			if (location.isLocationEnCours()) {
				locationEnCours = true;
			}
		}
		emprunteur.setFactureTotal(factureTotal);
		emprunteur.setPrixLocation(prixLocation);
		emprunteur.setLocationEnCours(locationEnCours);
	}

	/**
	 * Créer une chaine de cartère formatée representant la facture du client passé
	 * en paramètre et contenant, le devis de chaque location, et le devis total ttc
	 * de toutes les locations du dit client
	 * 
	 * @param emprunteur
	 * @return
	 */
	public static String facture(Emprunteur emprunteur) {
		StringBuilder st = new StringBuilder((String.format(
				"|%-27s| |%-11s| |%-11s| |%-11s| |%-10s| |%-10s| |%-10s| |%-10s| |%-10s| \n", "Exemplaire", "Debut",
				"fin", "Remise", "Penalite", "Carburant", "Reparation", "Prix", "facture")));
		for (Location location : emprunteur.getLocations()) {
			st.append(location.toString() + "\n");
		}
		bilan(emprunteur);
		st.append(String.format("%109s   %-10s   %-10d", "Total", emprunteur.getPrixLocation(),
				emprunteur.getFactureTotal()));
		return st.toString();
	}

	/**
	 * Génère la facture du client passsé en paramètre, dans un fichier .txt situé à
	 * la racine du programme
	 * 
	 * @param emprunteur
	 * @return
	 * @throws IOException
	 */
	public static String genererFactureTxt(Emprunteur emprunteur) throws IOException {
		StringBuilder st = new StringBuilder("Facture: " + emprunteur.toString() + "\n");
		st.append(facture(emprunteur));
		File f = new File("facture/facture_" + emprunteur.getNom() + ".txt");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		pw.println(st.toString());
		pw.close();
		return "La facture sous format txt de Mr " + emprunteur.getNom()
				+ " a été générée dans le dossier <<facture>> à la racine de l'application";
	}

	/**
	 * Génère la facture du client passsé en paramètre, dans un fichier .pdf situé à
	 * la racine du programme
	 * 
	 * @param emprunteur
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	
	/**
	 * public static String genererFacturePdf(Emprunteur emprunteur) throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter.getInstance(document, new FileOutputStream("facture/facture_" + emprunteur.getNom() + ".pdf"));
		document.open();
		Paragraph paragraph = new Paragraph();
		Paragraph titre = new Paragraph("Facture: " + emprunteur.toString() + "\n",
				new Font(Font.FontFamily.COURIER, 12, Font.BOLD, BaseColor.BLUE));
		titre.setAlignment(Element.ALIGN_CENTER);
		Paragraph contenu = new Paragraph(facture(emprunteur),
				new Font(FontFamily.COURIER, 9, Font.BOLD, BaseColor.DARK_GRAY));
		contenu.setAlignment(Element.ALIGN_CENTER);
		paragraph.add(titre);
		paragraph.add(contenu);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		document.close();
		return "La facture sous format pdf de Mr " + emprunteur.getNom()
				+ " a été générée dans le dossier <<facture>> à la racine de l'application";
	}
	 * 
	 */
	

	/**
	 * Trie la principale liste des locations par ordre croissant des dates de debut
	 */
	public static void trieLocationsParDebut() {
		Collections.sort(locations.getLocations(), new Comparator<Location>() {
			@Override
			public int compare(Location location1, Location location2) {
				return Date.compare(location1.getDebut(), location2.getDebut());
			}
		});
	}

	/**
	 * Trie la principale liste des locations par ordre croissant des dates de fin
	 */
	public static void trieLocationsParFin() {
		Collections.sort(locations.getLocations(), new Comparator<Location>() {
			@Override
			public int compare(Location location1, Location location2) {
				return Date.compare(location1.getFin(), location2.getFin());
			}
		});
	}

	/**
	 * Recherche la liste des locations en fonction d'une date de debut
	 * 
	 * @param debut
	 * @return
	 */
	public static ArrayList<Location> RechercheLocationsParDebut(Date debut) {
		ArrayList<Location> temp = new ArrayList<Location>();
		for (Location location : locations) {
			if (Date.compare(location.getDebut(), debut) == 0) {
				temp.add(location);
			}
		}
		return temp;
	}

	/**
	 * Créer une chaine de cartère formatée d'une liste de location particulière
	 * 
	 * @param list
	 * @return
	 */
	public static String resultatRecherche(ArrayList<Location> locations) {
		StringBuilder st = new StringBuilder("Locations trouvés: \n");
		st.append(String.format("|%-17s| |%-17s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| \n",
				"Emprunteur", "Exemplaire", "Debut", "fin", "Remise", "Penalite", "Carburant", "Reparation", "Prix",
				"facture"));
		for (Location location : locations) {
			st.append(String.format("|%-17s| %s",
					location.getEmprunteur().getPrenom() + location.getEmprunteur().getNom(),
					location.toString() + "\n"));
		}
		return st.toString();
	}

}
