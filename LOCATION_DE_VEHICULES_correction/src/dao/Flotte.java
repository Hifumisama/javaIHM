package dao;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Exemplaire;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Represente une classe singleton encapsulant la liste de tous les exemplaires
 * de véhicules de la société
 * 
 * @author adminonedy
 *
 */
public final class Flotte implements Serializable, Iterable<Exemplaire> {

	/**
	 * Represente le nom du fichier ou l'unique instance est sérialisé
	 */
	public static final String FILE_FLOTTE = "app/Flotte.ser";

	/**
	 * Initialisation statique au chargement de la classe d'une unique instance
	 */
	private static final Flotte singleton = new Flotte();

	/**
	 * Liste d'exemplaires
	 */
	private ArrayList<Exemplaire> exemplaires;

	/**
	 * Constructeur privé afin qu'un code client n'instancie pas plusieurs fois. Ce
	 * constructeur charge l'unique instance sérialisé si le fichier existe,ou crèe
	 * une nouvelle liste si le fichier n'existe pas
	 */
	private Flotte() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(FILE_FLOTTE))));
			ArrayList<Exemplaire> temp = (ArrayList<Exemplaire>) ois.readObject();
			exemplaires = temp;
			ois.close();
		} catch (Exception e) {
			exemplaires = new ArrayList<Exemplaire>();
		}
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public void serialiser() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_FLOTTE))));
			oos.writeObject(exemplaires);
			oos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * @return l'unique instance de la classe
	 */
	public static Flotte getDefaultFlotte() {
		return singleton;
	}

	public ArrayList<Exemplaire> getExemplaires() {
		return exemplaires;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("Exemplaires: \n");
		for (Exemplaire exemplaire : exemplaires) {
			st.append(exemplaire.toString() + "\n");
		}
		return st.toString();
	}

	@Override
	public Iterator<Exemplaire> iterator() {
		return exemplaires.iterator();
	}

}
