package ui;

import bean.*;
import modele.*;

public class test {

	public static void main(String[] args) throws Exception {

		RessourcesGestion.transfertFlotte("data/flotte.txt");
		EmprunteursGestion.transfertAdherents("data/adherents.txt");
		RessourcesGestion.trieParMarque();
		EmprunteursGestion.trieParNom();
		MarquesGestion.trieParMarque();

		System.out.println("Liste de moto. " + RessourcesGestion.resultatRecherche(RessourcesGestion.listeMoto()));
		System.out.println("\n\n");
		System.out.println(
				"Liste voiture de luxe. " + RessourcesGestion.resultatRecherche(RessourcesGestion.listeVoitureDeluxe()));
		System.out.println("\n\n");
		System.out.println(
				"Liste voiture standard. " + RessourcesGestion.resultatRecherche(RessourcesGestion.listeVoitureStandar()));
		System.out.println("\n\n");
		System.out.println(MarquesGestion.resultatRecherche(MarquesGestion.getVehicules()));
		System.out.println("\n\n");
		System.out.println(EmprunteursGestion.resultatRecherche(EmprunteursGestion.getEmprunteurs()));

		Auto toyota = MarquesGestion.ajouterAuto("Toyota", 300, "Corola", false);
		Auto peugeuot = MarquesGestion.ajouterAuto("Peugeuot", 350, "407", false);
		Auto range = MarquesGestion.ajouterAuto("Range", 400, "Rover", true);

		Exemplaire ex1 = RessourcesGestion.ajouterExemplaire(toyota, 12000);
		Exemplaire ex2 = RessourcesGestion.ajouterExemplaire(toyota, 62550);
		Exemplaire ex3 = RessourcesGestion.ajouterExemplaire(peugeuot, 12000);
		Exemplaire ex4 = RessourcesGestion.ajouterExemplaire(range, 62550);
		
		Emprunteur em1 = EmprunteursGestion.ajouterEmprunteur("Pierre", "Jean", "Paris", "Gabriel peri", 75412, 14);
		Emprunteur em2 = EmprunteursGestion.ajouterEmprunteur("Moussa", "Sissoko", "Lyon", "foret", 65412, 45);
		
		Location l1 = LocationsGestion.louer(em1, ex1, false, 26, 12, 2015, 12, 2, 2016);
		Location l2 = LocationsGestion.louer(em1, ex2, false, 26, 12, 2015, 12, 2, 2016);
		
		Location l3 = LocationsGestion.louer(em2, ex3, true, 26, 12, 2015, 12, 2, 2016);
		Location l4 = LocationsGestion.louer(em2, ex4, true, 26, 12, 2015, 12, 2, 2016);
		
		LocationsGestion.ramener(l2, 23, 3, 2016, Carburant.TROIS_QUART, Degat.MINEUR);
		LocationsGestion.ramener(l4, 23, 3, 2016, Carburant.VIDE, Degat.MAJEUR);
		
		// System.out.println(LocationsGestion.genererFacturePdf(em1));
		// System.out.println(LocationsGestion.genererFacturePdf(em2));
		System.out.println(LocationsGestion.genererFactureTxt(em1));
		System.out.println(LocationsGestion.genererFactureTxt(em2));
		
		MarquesGestion.serialiser();
		RessourcesGestion.serialiser();
		EmprunteursGestion.serialiser();
		LocationsGestion.serialiser();

	}


}
