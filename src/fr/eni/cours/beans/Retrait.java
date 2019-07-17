package fr.eni.cours.beans;

import java.io.Serializable;

/**
 * 
 * @author jbodet2019
 * 
 */
public class Retrait implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Attributs
	private int noVente;
	private String rue;
	private String codePostal;
	private String ville;
	private boolean retraitVendeur;
	private boolean retraitAcheteur;
	
	// Constructeur
	public Retrait() {
	}
	
	
	public Retrait(int noVente, String rue, String codePostal, String ville) {
		setNoVente(noVente);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
	
	}
	
	
	public Retrait(int noVente, String rue, String codePostal, String ville, boolean retraitVendeur,
			boolean retraitAcheteur) {
		setNoVente(noVente);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setRetraitVendeur(retraitVendeur);
		setRetraitAcheteur(retraitAcheteur);
	}

	
	
	// Setters et qetters
	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}
	
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	public boolean isRetraitVendeur() {
		return retraitVendeur;
	}
	public void setRetraitVendeur(boolean retraitVendeur) {
		this.retraitVendeur = retraitVendeur;
	}
	
	
	public boolean isRetraitAcheteur() {
		return retraitAcheteur;
	}
	public void setRetraitAcheteur(boolean retraitAcheteur) {
		this.retraitAcheteur = retraitAcheteur;
	}
	
	//toString
		@Override
		public String toString() {
			return "Retrait [noVente=" + getNoVente() + ", rue=" + getRue() + ", codePostal=" + getCodePostal() + ", ville=" + getVille()
					+ ", retraitVendeur=" + isRetraitVendeur() + ", retraitAcheteur=" + isRetraitAcheteur() + "]";
		}
	
}
