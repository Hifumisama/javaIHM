package dao;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Vehicule;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represente une classe singleton encapsulant la liste de tous les modèle de
 * marque présents dans les exemplaires de la société
 * 
 * @author adminonedy
 *
 */
public final class Vehicules implements Serializable, Iterable<Vehicule> {

	/**
	 * Represente le nom du fichier ou l'unique instance sérialisé
	 */
	public static final String FILE_VEHICULESS = "app/Vehicules.ser";

	/**
	 * Initialisation statique au chargement de la classe d'une unique instance
	 */
	private static final Vehicules singleton = new Vehicules();

	/**
	 * Liste de véhicules
	 */
	private ArrayList<Vehicule> vehicules;

	/**
	 * Constructeur privé afin qu'un code client n'instancie pas plusieurs fois. Ce
	 * constructeur charge l'unique instance sérialisé si le fichier existe,ou crèe
	 * une nouvelle liste si le fichier n'existe pas
	 */
	private Vehicules() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(FILE_VEHICULESS))));
			ArrayList<Vehicule> temp = (ArrayList<Vehicule>) ois.readObject();
			vehicules = temp;
			ois.close();
		} catch (Exception e) {
			vehicules = new ArrayList<Vehicule>();
		}
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public void serialiser() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_VEHICULESS))));
			oos.writeObject(vehicules);
			oos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * @return l'unique instance de la classe
	 */
	public static Vehicules getDefaultVehicules() {
		return singleton;
	}

	public ArrayList<Vehicule> getVehicules() {
		return vehicules;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("Véhicules: \n");
		for (Vehicule vehicule : vehicules) {
			st.append(vehicule.toString() + "\n");
		}
		return st.toString();
	}

	@Override
	public Iterator<Vehicule> iterator() {
		return this.vehicules.iterator();
	}

}
