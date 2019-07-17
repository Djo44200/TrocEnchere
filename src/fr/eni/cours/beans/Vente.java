package fr.eni.cours.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 
 * @author jbodet2019
 *
 */
// Constructeur par d�faut
public class Vente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVente;
	private String nomArticle;
	private String description;
	private LocalDate dateFinEnchere;
	private int prixInitial;
	private int prixvente;
	private int numUtil;
	private int numCate;
	private boolean archiver;
	private boolean publier;
	private Utilisateur user;
	private int finEnchere;
	
	
/**
	 * @return the finEnchere
	 */
	public int getFinEnchere() {
		return finEnchere;
	}

	/**
	 * @param finEnchere the finEnchere to set
	 */
	public void setFinEnchere(int finEnchere) {
		this.finEnchere = finEnchere;
	}

	// Constructeur sans param�tre
	public Vente () {
		
	}
	
	//
	public Vente(int numVente, String nomArticle, String description, LocalDate dateFinEnchere, int prixInitial,
			int prixvente, int numUtil, boolean publier, boolean archiver) {
		super();
		this.numVente = numVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixvente = prixvente;
		this.numUtil = numUtil;
		this.archiver = archiver;
		this.publier = publier;
		
	}
	
	//Constructeur sans user pour créer la vente
	public Vente( String nomArticle, String description, LocalDate dateFinEnchere, int prixInitial,
			int prixvente, int numUtil,  boolean publier, boolean archiver) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixvente = prixvente;
		this.numUtil = numUtil;
		this.archiver = archiver;
		this.publier = publier;
		
	}
	
	
	
	// Constructeur sans le numVente
	
	public Vente( String nomArticle, String description, LocalDate dateFinEnchere, int prixInitial,
			int prixvente, int numUtil,  boolean publier, boolean archiver, Utilisateur user) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixvente = prixvente;
		this.numUtil = numUtil;
		this.archiver = archiver;
		this.publier = publier;
		this.user = user;
	}
	
	//Constructeur pour créer une vente
	public Vente(String vArticle, String vDescription, LocalDate dateFin, int prixInit, int prixVente, int numUtilisateur, int numCat,
			boolean b, boolean c) {
		
		setNomArticle(vArticle); 
		setDescription(vDescription);
		setDateFinEnchere(dateFin);
		setPrixInitial(prixInit);
		setPrixvente(prixVente);
		setNumUtil(numUtilisateur);
		setNumCate(numCat);
		setPublier(b);
		setArchiver(c);

	}
	
	
	
	
	// Getters et Setters

	

	public int getNumVente() {
		return numVente;
	}

	public void setNumVente(int numVente) {
		this.numVente = numVente;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixvente() {
		return prixvente;
	}

	public void setPrixvente(int prixvente) {
		this.prixvente = prixvente;
	}

	public int getNumUtil() {
		return numUtil;
	}

	public void setNumUtil(int numUtil) {
		this.numUtil = numUtil;
	}

	public int getNumCate() {
		return numCate;
	}

	public void setNumCate(int numCate) {
		this.numCate = numCate;
	}
	public boolean isArchiver() {
		return archiver;
	}
	public void setArchiver(boolean archiver) {
		this.archiver = archiver;
	}
	public boolean isPublier() {
		return publier;
	}
	public void setPublier(boolean publier) {
		this.publier = publier;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}

	//toString
	
	
	@Override
	public String toString() {
		return "Vente [numVente=" + getNumVente() + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEnchere=" + dateFinEnchere + ", prixInitial=" + prixInitial + ", prixvente=" + prixvente
				+ ", numUtil=" + numUtil + ", numCate=" + numCate + ", archiver=" + archiver + ", publier=" + publier
				+ ", user=" + user + "]";
	}
	
	
	
	
}
