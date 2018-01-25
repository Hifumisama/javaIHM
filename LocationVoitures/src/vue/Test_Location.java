package vue;



import bean_coucheTransversale.Adresse;
import bean_coucheTransversale.Emprunteur;
import bean_coucheTransversale.Exemplaire;
import bean_coucheTransversale.Moto;



public class Test_Location {

	public static void main(String[] args) {
		
		
		Emprunteur empr1 = new Emprunteur(1, "VAL", "Adrien", "26 rue de la Paix 65888 LILLE", 1, false);
		Emprunteur empr2 = new Emprunteur(1, "VAL", "Adrien", "8 PLACE 98777 VIENNE", 1, true);
		Emprunteur empr3 = new Emprunteur(1, "VAL", "Adrien", "96 rue de la joie 65888 LYON", 1, true);
		Emprunteur empr4 = new Emprunteur(1, "VAL", "Adrien", "2 rue de la Paix 65888 RENNES", 1, false);
		Emprunteur empr5 = new Emprunteur(1, "VAL", "Adrien", "2 rue de la Paix 65888 VALENCE", 1, true);
		
		
		
		Exemplaire exemp = new Exemplaire();
		
		Moto v  = new Moto(2, 150, "Pierre", 70);
		
		System.out.println("Le conducteur " + v.getConducteur() + " a loué un véhicule");
	}

}
