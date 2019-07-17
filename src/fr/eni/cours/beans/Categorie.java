package fr.eni.cours.beans;

import java.io.Serializable;

/**
 * 
 * @author jbodet2019
 *
 */

public class Categorie implements Serializable{

	private int numCate;
	private String libelle;
	
	
	// Constructeur par défaut
	public Categorie() {
	
	}

	// Contructeur avec tous les paramètres
	public Categorie(int numCate, String libelle) {
		super();
		this.numCate = numCate;
		this.libelle = libelle;
	}
	
	// Surcharge Objet Categorie sans id(noCategorie)
		public Categorie( String libelle) {
			this.libelle= libelle;
		}
	
	// Getters et Setters 
	public int getNumCate() {
		return numCate;
	}
	public void setNumCate(int numCate) {
		this.numCate = numCate;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	//ToString 
	@Override
	public String toString() {
		return "Categorie [numCate=" + numCate + ", libelle=" + libelle + "]";
	}
	
	
	
	
	
	
}
