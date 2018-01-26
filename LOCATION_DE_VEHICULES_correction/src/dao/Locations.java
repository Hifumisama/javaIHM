package dao;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Location;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represente une classe singleton encapsulant la liste de toutes les locations
 * de la société
 * 
 * @author adminonedy
 *
 */
public final class Locations implements Serializable, Iterable<Location> {

	/**
	 * Represente le nom du fichier ou l'unique instance est sérialisé
	 */
	public static final String FILE_LOCATIONS = "app/Locations.ser";

	/**
	 * Initialisation statique au chargement de la classe d'une unique instance
	 */
	private static final Locations singleton = new Locations();

	/**
	 * Liste des locations
	 */
	private ArrayList<Location> locations;

	/**
	 * Constructeur privé afin qu'un code client n'instancie pas plusieurs fois. Ce
	 * constructeur charge l'unique instance sérialisé si le fichier existe,ou crèe
	 * une nouvelle liste si le fichier n'existe pas
	 */
	private Locations() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(FILE_LOCATIONS))));
			ArrayList<Location> temp = (ArrayList<Location>) ois.readObject();
			locations = temp;
			ois.close();
		} catch (Exception e) {
			locations = new ArrayList<Location>();
		}
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public void serialiser() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_LOCATIONS))));
			oos.writeObject(locations);
			oos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * @return l'unique instance de la classe
	 */
	public static Locations getDefaultLocations() {
		return singleton;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("Locations: \n");
		st.append(String.format("|%-17s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| |%-13s| \n",
				"Exemplaire", "Debut", "fin", "Remise", "Penalite", "Carburant", "Reparation", "Prix", "facture"));
		for (Location location : locations) {
			st.append(location.toString() + "\n");
		}
		return st.toString();
	}

	@Override
	public Iterator<Location> iterator() {
		return locations.iterator();
	}

}
