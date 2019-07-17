package fr.eni.cours.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Enchere implements Serializable{
	
	/**
	 * 
	 * @author jbodet2019
	 * 
	 */
	
	private int noVente;
	private LocalDateTime dateEnchere;
	private Utilisateur user;
	private int noUtilisateur;
	private Vente vente;
	private boolean archiver;
	private int prixEnchere;
	private int montantEnchere;
	
	
	
	// Constructeur
	public Enchere() {
		
	}

	
	// Constructeur sans le noVente
	public Enchere(LocalDateTime dateEnchere, Utilisateur user, int noUtilisateur, Vente vente, boolean archiver,
			int prixEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.user = user;
		this.noUtilisateur = noUtilisateur;
		this.vente = vente;
		this.archiver = archiver;
		this.prixEnchere = prixEnchere;
		this.montantEnchere = montantEnchere;
	}




	
	//Constructeur utilisé lors des enchères.
	public Enchere(LocalDateTime dateEnchere, int noUtilisateur, int noVente, int montantEnchere) {
		
		this.dateEnchere = dateEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noVente = noVente;
		this.montantEnchere = montantEnchere;
	}


	// Constructeur avec tous les param�tres
	public Enchere(int noVente, LocalDateTime dateEnchere, Utilisateur user, int noUtilisateur, Vente vente,
			boolean archiver, int prixEnchere, int montantEnchere) {
		super();
		this.noVente = noVente;
		this.dateEnchere = dateEnchere;
		this.user = user;
		this.noUtilisateur = noUtilisateur;
		this.vente = vente;
		this.archiver = archiver;
		this.prixEnchere = prixEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	
	
	// Setters et Setters
	

	public int getNoVente() {
		return noVente;
	}


	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}


	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public Vente getVente() {
		return vente;
	}


	public void setVente(Vente vente) {
		this.vente = vente;
	}


	public boolean isArchiver() {
		return archiver;
	}


	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}


	public int getPrixEnchere() {
		return prixEnchere;
	}


	public void setPrixEnchere(int prixEnchere) {
		this.prixEnchere = prixEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	// toString
	@Override
	public String toString() {
		return "Enchere [noVente=" + noVente + ", dateEnchere=" + dateEnchere + ", user=" + user + ", noUtilisateur="
				+ noUtilisateur + ", vente=" + vente + ", archiver=" + archiver + ", prixEnchere=" + prixEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}


	
	
	
	
	
	
	
	
	

}
