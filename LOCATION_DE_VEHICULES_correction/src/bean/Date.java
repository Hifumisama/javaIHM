package bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author adminonedy Represente une date
 */
public class Date implements Serializable {

	/**
	 * Represente le jour de la date
	 */
	private int jour;
	/**
	 * Represente le mois de la date
	 */
	private int mois;
	/**
	 * Represente l'année de la date
	 */
	private int annee;

	/**
	 * Crée une nouvelle date avec les données spécifié en paramètre
	 * 
	 * @param date
	 */
	public Date(java.util.Date date) {
		this.jour = date.getDate();
		this.mois = date.getMonth() + 1;
		this.annee = date.getYear() + 1900;
	}

	/**
	 * Crée une nouvelle date avec les données spécifié en paramètre
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 */
	public Date(int jour, int mois, int annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * Calcul le nombre de jours entre deux dates
	 * 
	 * @param a
	 *            Represente la date supérieur
	 * @param b
	 *            Represente la date inférieur
	 * @return la différence de jour entre a et b
	 */
	public static long differenceJour(Date a, Date b) {

		Calendar time1 = Calendar.getInstance();
		time1.set(Calendar.YEAR, a.annee);
		time1.set(Calendar.MONTH, a.mois);
		time1.set(Calendar.DAY_OF_MONTH, a.jour);
		time1.set(Calendar.HOUR_OF_DAY, 0);
		time1.set(Calendar.MINUTE, 0);
		time1.set(Calendar.SECOND, 0);
		time1.set(Calendar.MILLISECOND, 0);

		Calendar time2 = Calendar.getInstance();
		time2.set(Calendar.YEAR, b.annee);
		time2.set(Calendar.MONTH, b.mois);
		time2.set(Calendar.DAY_OF_MONTH, b.jour);
		time2.set(Calendar.HOUR_OF_DAY, 0);
		time2.set(Calendar.MINUTE, 0);
		time2.set(Calendar.SECOND, 0);
		time2.set(Calendar.MILLISECOND, 0);

		long diff = time1.getTimeInMillis() - time2.getTimeInMillis();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public static int compare(Date date1, Date date2) {

		if ((date1 == null) && (date2 != null)) {
			return -1;
		} else if ((date1 != null) && (date2 == null)) {
			return 1;
		} else if ((date1 == null) && (date2 == null)) {
			return 0;
		}

		if (date1.annee == date2.annee) {
			if (date1.mois == date2.mois) {
				if (date1.jour == date2.jour) {
					return 0;
				} else if (date1.jour > date2.jour) {
					return 1;
				} else {
					return -1;
				}
			} else if (date1.mois > date2.mois) {
				return 1;
			} else {
				return -1;
			}
		} else if (date1.annee > date2.annee) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return jour + "/" + mois + "/" + annee;
	}

}
