package dao;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Emprunteur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represente une classe singleton encapsulant la liste de tous les emprunteurs
 * de la société
 * 
 * @author adminonedy
 *
 */
public final class Emprunteurs implements Serializable, Iterable<Emprunteur> {

	/**
	 * Represente le nom du fichier ou l'unique instance est sérialisé
	 */
	public static final String FILE_EMPRUNTEURS = "app/Emprunteurs.ser";

	/**
	 * Initialisation statique au chargement de la classe d'une unique instance
	 */
	private static final Emprunteurs singleton = new Emprunteurs();

	/**
	 * Liste d'emprunteurs
	 */
	private ArrayList<Emprunteur> emprunteurs;

	/**
	 * Constructeur privé afin qu'un code client n'instancie pas plusieurs fois. Ce
	 * constructeur charge l'unique instance sérialisé si le fichier existe,ou crèe
	 * une nouvelle liste si le fichier n'existe pas
	 */
	private Emprunteurs() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(FILE_EMPRUNTEURS))));
			ArrayList<Emprunteur> temp = (ArrayList<Emprunteur>) ois.readObject();
			emprunteurs = temp;
			ois.close();
		} catch (Exception e) {
			emprunteurs = new ArrayList<Emprunteur>();
		}
	}

	/**
	 * Enregistre dans un fichier l'unique instance
	 */
	public void serialiser() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(FILE_EMPRUNTEURS))));
			oos.writeObject(emprunteurs);
			oos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * @return l'unique instance de la classe
	 */
	public static Emprunteurs getDefaultEmprunteurs() {
		return singleton;
	}

	public ArrayList<Emprunteur> getEmprunteurs() {
		return emprunteurs;
	}

	@Override
	public String toString() {
		StringBuilder st = new StringBuilder("Emprunteurs: \n");
		for (Emprunteur emprunteur : emprunteurs) {
			st.append(emprunteur.toString() + "\n");
		}
		return st.toString();
	}

	@Override
	public Iterator<Emprunteur> iterator() {
		return emprunteurs.iterator();
	}

}
