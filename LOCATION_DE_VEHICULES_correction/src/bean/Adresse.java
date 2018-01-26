package bean;

import java.io.Serializable;

/**
 * @author adminonedy
 *La classe Adresse represente l'adresse d'une habitation dans notre application
 */
public final class Adresse implements Serializable {

	/**
	 * Represente Le nom d'une ville 
	 */
	private String ville;
	/**
	 * Represente le nom d'une rue
	 */
	private String rue;
	/**
	 * Represente le code postal correspondant � l'adresse
	 */
	private int cp;
	/**
	 * Represente le num�ro de l'habitation dans la rue correspondante
	 */
	private int numero;

	/**
	 * Cr�e une nouvelle adresse avec des donn�es vide
	 */
	public Adresse() {
		this.ville = "";
		this.rue = "";
		this.cp = 0;
		this.numero = 0;
	}
	
	/**
	 * Cr�e une nouvelle adresse avec les donn�es sp�cifi� en param�tre
	 * 
	 * @param ville
	 * @param rue
	 * @param cp
	 * @param numero
	 */
	public Adresse(String ville, String rue, int cp, int numero) {
		this.ville = ville;
		this.rue = rue;
		this.cp = cp;
		this.numero = numero;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return numero + " " + rue + ", " + cp + " " + ville;
	}

}
