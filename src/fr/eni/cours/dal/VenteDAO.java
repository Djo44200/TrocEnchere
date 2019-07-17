package fr.eni.cours.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import fr.eni.cours.beans.Categorie;
import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.util.DBConnection;

public class VenteDAO {
/**
 * @author vroch2019
 */
	private final static String LISTER = "select * from VENTES;";
	private final static String LISTACQUISITION= "select * from VENTES where  no_vente = ? AND date_fin_encheres <= GETDATE();";
	private final static String UNEVENTE= "select * from VENTES where no_vente = ? ;";
	private final static String UNEENCHERE= "select * from VENTES where no_vente = ? and date_fin_encheres > GETDATE();";
	private final static String DATEVENTE= "select date_fin_encheres from VENTES where no_vente = ? ;";
	private final static String NUMVENDEUR= "select no_utilisateur from VENTES where no_vente = ? ;";
	private final static String LISTVENTESUTILISATEUR= "select * from VENTES where no_utilisateur = ? ;";
	private final static String LISTVENTESCATEGORIE= "select * from VENTES where no_categorie = ? ;";
	private final static String LISTVENTESARTICLE= "select * from VENTES where nomarticle LIKE ? ;";
	private final static String INSERER = "insert into VENTES(nomarticle, description, date_fin_encheres, prix_initial, prix_vente ,no_utilisateur, no_categorie, visibilite_publication) values (?,?,?,?,?,?,?,?);";
	private final static String SUPPRIMER = "delete from VENTES where no_utilisateur = ? and no_vente = ? ;";
	private final static String RECHPARUTETNOM = "select * from VENTES where no_utilisateur = ? and nomarticle LIKE ?";
	private final static String RECHPARUTETCATE = "select * from VENTES where no_utilisateur = ? and no_categorie =?";
	private final static String RECHPARUTILCATETNOM="select * from VENTES where no_utilisateur = ? and no_categorie = ? and nomarticle LIKE ?";
	private final static String RECHPARNOVTECATNOM="select * from VENTES where no_vente = ? and no_categorie = ? and nomarticle LIKE ? and date_fin_encheres > GETDATE()";
	private final static String NUMARTICLE = "select no_categorie from CATEGORIES where libelle = ? ;";
	private final static String MODIFIERPRIX = "update VENTES set prix_vente = ? WHERE no_vente= ?;";
	private final static String FINIRVENTE = "update VENTES set fin_enchere = ? WHERE no_vente= ?;";
	private final static String PUBLIER = "update VENTES set visibilite_publication = 1 WHERE no_vente=?;";

// M�thode listeToutesVentes	
	public static ArrayList<Vente> listeVentes() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Vente> listeV = new ArrayList<>();
		
		try {
			cnx=DBConnection.seConnecter();
			stmt=cnx.createStatement();
			rs=stmt.executeQuery(LISTER);
			
			while (rs.next()) {
				Vente venteU = new Vente();
				venteU.setNumVente(rs.getInt("no_vente"));
				venteU.setNomArticle(rs.getString("nomarticle"));
				venteU.setDescription(rs.getString("description"));
				venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				venteU.setPrixInitial(rs.getInt("prix_initial"));
				venteU.setPrixvente(rs.getInt("prix_vente"));
				venteU.setNumUtil(rs.getInt("no_utilisateur"));
				venteU.setNumCate(rs.getInt("no_categorie"));
				listeV.add(venteU);

			}
		} catch (SQLException e) {		
				throw new DALException ("Probleme - liste ventes - " + e.getMessage());	
		}finally{
			try{
				if (stmt!=null) stmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
					throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
			}
		}
		return listeV;		
	}			
	
	

	// m�thode cat�gorie � partir du libell�

	public static Categorie obtenirLeNumCate(String libelle) throws DALException {
		Categorie categorie = new Categorie();
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
		cnx=DBConnection.seConnecter();
		pstmt=cnx.prepareStatement(NUMARTICLE);
		pstmt.setString(1, libelle);
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			
			categorie.setNumCate(rs.getInt("no_categorie"));
			categorie.setLibelle(libelle);
		}
			} catch (SQLException e) {		
				throw new DALException ("Probleme - obtenirLeNumArticle - " + e.getMessage());	
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
					throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
			}
		}
		
		return categorie;
		
	}
	
	public static ArrayList<Vente> ventesUtilisateur(int no_utilisateur) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Vente> listeVentes = new ArrayList<>();
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(LISTVENTESUTILISATEUR);
			pstmt.setInt(1, no_utilisateur);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Vente venteU = new Vente();
				venteU.setNumVente(rs.getInt("no_vente"));
				venteU.setNomArticle(rs.getString("nomarticle"));
				venteU.setDescription(rs.getString("description"));
				venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
			//	venteU.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
				venteU.setPrixInitial(rs.getInt("prix_initial"));
				venteU.setPrixvente(rs.getInt("prix_vente"));
				venteU.setNumCate(rs.getInt("no_categorie"));
				venteU.setNumUtil(rs.getInt("no_utilisateur"));
				listeVentes.add(venteU);
			}
		} catch (SQLException e) {		
				throw new DALException ("Probleme - ventesUtilisateur - " + e.getMessage());	
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
					throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
			}
		}
		return listeVentes;		
	}			
	




	// methode listeVenteCategorie prealable affichage ventes par categorie
	public static ArrayList<Vente> ventesCategorie (int no_categorie) throws DALException {
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Vente> listeVentes = new ArrayList<>();
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(LISTVENTESCATEGORIE);
			pstmt.setInt(1, no_categorie);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				Vente venteU = new Vente();
				venteU.setNumVente(rs.getInt("no_vente"));
				venteU.setNomArticle(rs.getString("nomarticle"));
				venteU.setDescription(rs.getString("description"));
				venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
			//	venteU.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
				venteU.setPrixInitial(rs.getInt("prix_initial"));
				venteU.setPrixvente(rs.getInt("prix_vente"));
				venteU.setNumCate(rs.getInt("no_categorie"));
				venteU.setNumUtil(rs.getInt("no_utilisateur"));
				listeVentes.add(venteU);
			}
		} catch (SQLException e) {		
				throw new DALException ("Probleme - ventesCategorie - " + e.getMessage());	
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e) {
					throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
			}
		}
		return listeVentes;		
	}			

	
	// m�thode listeVenteArticle pr�alable � l'affichage des ventes en fonction du nom de l'article
		public static ArrayList<Vente> listeVenteArticle(String nomArticle) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<Vente> listeVentes = new ArrayList<>();
			
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(LISTVENTESARTICLE);
				pstmt.setString(1, "%"+nomArticle+"%");
				rs=pstmt.executeQuery();
				
				while (rs.next()){
					Vente venteA = new Vente();
					venteA.setNumVente(rs.getInt("no_vente"));
					venteA.setNumUtil(rs.getInt("no_utilisateur"));
					venteA.setNomArticle(rs.getString("nomarticle"));
					venteA.setDescription(rs.getString("description"));
					venteA.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
					venteA.setPrixInitial(rs.getInt("prix_initial"));
					venteA.setPrixvente(rs.getInt("prix_vente"));
					venteA.setNumCate(rs.getInt("no_categorie"));
					listeVentes.add(venteA);

					
				}
			} catch (SQLException e) {
				
					throw new DALException ("Probleme - listeVentesArticle - " + e.getMessage());
			
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeVentes;		
		}	
		
		// methode listeVenteUtNom pr�alable affichage ventes par utilisateur et nom article
		public static ArrayList<Vente> listeUtNom (String nomarticle, int no_utilisateur) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ArrayList<Vente> listeVentes = new ArrayList<>();
			
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(RECHPARUTETNOM);			
				pstmt.setInt(1, no_utilisateur);
				pstmt.setString(2, "%"+nomarticle+"%");
				rs=pstmt.executeQuery();
				
				while (rs.next()) {
					Vente venteU = new Vente();
					venteU.setNumVente(rs.getInt("no_vente"));
					venteU.setNomArticle(nomarticle);
					venteU.setDescription(rs.getString("description"));
					venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				//	venteU.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
					venteU.setPrixInitial(rs.getInt("prix_initial"));
					venteU.setPrixvente(rs.getInt("prix_vente"));
					venteU.setNumCate(rs.getInt("no_categorie"));
					venteU.setNumUtil(no_utilisateur);
					listeVentes.add(venteU);
					
				}
			} catch (SQLException e) {		
					throw new DALException ("Probleme - listeUtNom - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return listeVentes;		
		}			
		// methode listeVenteUtCat pr�alable affichage ventes par utilisateur et categorie article
				public static ArrayList<Vente> listeUtCat (int no_cate, int no_utilisateur) throws DALException {
					Connection cnx=null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					ArrayList<Vente> listeVentes = new ArrayList<>();
					
					try {
						cnx=DBConnection.seConnecter();
						pstmt=cnx.prepareStatement(RECHPARUTETCATE);
						pstmt.setInt(1, no_utilisateur);
						pstmt.setInt(2, no_cate);
						
						rs=pstmt.executeQuery();
						
						while (rs.next()) {
							Vente venteU = new Vente();
							venteU.setNumVente(rs.getInt("no_vente"));
							venteU.setNomArticle(rs.getString("nomarticle"));
							venteU.setDescription(rs.getString("description"));
							venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
						//	venteU.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
							venteU.setPrixInitial(rs.getInt("prix_initial"));
							venteU.setPrixvente(rs.getInt("prix_vente"));
							venteU.setNumCate(rs.getInt("no_categorie"));
							venteU.setNumUtil(no_utilisateur);
							listeVentes.add(venteU);
						}
					} catch (SQLException e) {		
							throw new DALException ("Probleme - listeUtNom - " + e.getMessage());	
					}finally{
						try{
							if (pstmt!=null) pstmt.close();
							if (cnx!=null) cnx.close();
						} catch (SQLException e) {
								throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
						}
					}
					return listeVentes;		
				}			
				
				
	// methode listeVenteUtNom pr�alable affichage ventes par utilisateur cat�gorie et nom article
			public static ArrayList<Vente> listeUtNomCat (String nomarticle, int no_utilisateur, int no_cate) throws DALException {
				Connection cnx=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				ArrayList<Vente> listeVentes = new ArrayList<>();
					
					try {
						cnx=DBConnection.seConnecter();
						pstmt=cnx.prepareStatement(RECHPARUTILCATETNOM);					
						pstmt.setInt(1, no_utilisateur);
						pstmt.setInt(2, no_cate);
						pstmt.setString(3, "%"+nomarticle+"%");
						rs=pstmt.executeQuery();
						
						while (rs.next()) {
							Vente venteU = new Vente();
							venteU.setNumVente(rs.getInt("no_vente"));
							venteU.setNomArticle(nomarticle);
							venteU.setDescription(rs.getString("description"));
							venteU.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
						//	venteU.setDateFinEnchere(rs.getDate("dateFinEnchere").toLocalDate());
							venteU.setPrixInitial(rs.getInt("prix_initial"));
							venteU.setPrixvente(rs.getInt("prix_vente"));
							venteU.setNumCate(no_cate);
							venteU.setNumUtil(rs.getInt("no_utilisateur"));
							listeVentes.add(venteU);
							
						}
					} catch (SQLException e) {		
							throw new DALException ("Probleme - listeUtNomCat - " + e.getMessage());	
					}finally{
						try{
							if (pstmt!=null) pstmt.close();
							if (cnx!=null) cnx.close();
						} catch (SQLException e) {
								throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
						}
					}
					return listeVentes;		
				}				
		
	// m�thode ins�rer, cr�er une nouvelle vente
		public static void ajouter(Vente nVente)  throws DALException{
			Connection cnx=null;
			PreparedStatement pstmt=null;
		// liste dans la requ�te : nomarticle, description, date_fin_encheres, prix_initial, prix_vente ,no_utilisateur, no_categorie
			try{
				cnx = DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(INSERER, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, nVente.getNomArticle());
				pstmt.setString(2, nVente.getDescription());
				pstmt.setDate(3, java.sql.Date.valueOf(nVente.getDateFinEnchere()));
				pstmt.setInt(4, nVente.getPrixInitial());
				pstmt.setInt(5, nVente.getPrixvente());
				pstmt.setInt(6, nVente.getNumUtil());
				pstmt.setInt(7, nVente.getNumCate());
				if (nVente.isPublier()) {
					pstmt.setInt(8, 1);
				}else {
					pstmt.setInt(8, 0);
				};
				pstmt.executeUpdate();
				
				
			
				 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                nVente.setNumVente(generatedKeys.getInt(1));
			               
			            }
			            else {
			                throw new SQLException("Impossible de creer la vente, aucun ID obtenu.");
			            }
			        }				
			} catch (SQLException e){
				throw new DALException ("Probleme - ajouterVente - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch (SQLException e){
					throw new DALException ("Probleme - fermerConnexion - " + e.getMessage());
				}		
			}
		}

//
//
	//m�thode supprimer une vente

		public static int supprimerVente(int noVente, int noUtil) throws DALException{
			Connection cnx=null;
			PreparedStatement pstmt = null;
			int nblig=0;
			try{
				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(SUPPRIMER);
				pstmt.setInt(1, noUtil);
				pstmt.setInt(2, noVente);
				
				nblig= pstmt.executeUpdate();
				return nblig;
			}catch(SQLException e){
				throw new DALException ("Probleme - supprimer Vente - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch(SQLException e){
					throw new DALException ("DAL - fermerConnexion - " + e.getMessage());
				}
			}
		}



		public static Vente  AfficherArticle(int numVente) throws DALException {
			// Recherche d'un article avec le numVente
			
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Vente venteA = new Vente();
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(UNEVENTE);
				pstmt.setInt(1, numVente);
				rs=pstmt.executeQuery();
				
				
				
				while (rs.next()){
				venteA.setNumVente(numVente);
				venteA.setNomArticle(rs.getString("nomarticle"));
				venteA.setDescription(rs.getString("description"));
				venteA.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				venteA.setPrixInitial(rs.getInt("prix_initial"));
				venteA.setPrixvente(rs.getInt("prix_vente"));
				venteA.setNumCate(rs.getInt("no_categorie"));
				venteA.setNumUtil(Integer.parseInt(rs.getString("no_utilisateur")));
				venteA.setFinEnchere(rs.getInt("fin_enchere"));
				}
			
			} catch (SQLException e) {
				throw new DALException ("Probleme - Impossible d'affichage d' un article ( VenteDAO ) - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch(SQLException e){
					throw new DALException ("DAL - fermerConnexion - " + e.getMessage());
				}

			return venteA;
			
		}
		}
		
		public static Vente  AfficherEnchereEnCours(int numVente) throws DALException {
			// Recherche d'un article avec le numVente
			
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Vente venteA = new Vente();
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(UNEENCHERE);
				pstmt.setInt(1, numVente);
				rs=pstmt.executeQuery();
				
				
				
				while (rs.next()){
				venteA.setNumVente(numVente);
				venteA.setNomArticle(rs.getString("nomarticle"));
				venteA.setDescription(rs.getString("description"));
				venteA.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				venteA.setPrixInitial(rs.getInt("prix_initial"));
				venteA.setPrixvente(rs.getInt("prix_vente"));
				venteA.setNumCate(rs.getInt("no_categorie"));
				venteA.setNumUtil(Integer.parseInt(rs.getString("no_utilisateur")));
				}
			
			} catch (SQLException e) {
				throw new DALException ("Probleme - Impossible d'affichage d' une enchere ( VenteDAO ) - " + e.getMessage());
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				}catch(SQLException e){
					throw new DALException ("DAL - fermerConnexion - " + e.getMessage());
				}

			return venteA;
			
		}
		}
		
		public static void initPrixVente(int proposEnchere, int numVente) throws DALException {
			Connection cnx = null;
			PreparedStatement pstmt = null;
	
			try {
				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(MODIFIERPRIX);
				pstmt.setInt(1, proposEnchere);
				pstmt.setInt(2, numVente);

				pstmt.executeUpdate();
			


			} catch (SQLException e) {
				throw new DALException("Probleme - modifierPrix - " + e.getMessage());
			} finally {
				try {

					if (pstmt != null)
						pstmt.close();
					if (cnx != null)
						cnx.close();
				} catch (SQLException e) {
					throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
				}
			}
		}
		
		
		public static Vente obtenirDateFinEnchere(int numVente) throws DALException {
			Vente laVente = new Vente();
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			
			try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(DATEVENTE);
			pstmt.setInt(1, numVente);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				
				laVente.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				
				
			}
				} catch (SQLException e) {		
					throw new DALException ("Probleme - obtenirLa DateFinEnchere - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			
			return laVente;
			
		}
		
		public static int obtenirLeNumVendeur(int numVente) throws DALException {
			int numVendeur = -1;
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			
			try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(NUMVENDEUR);
			pstmt.setInt(1, numVente);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				
				numVendeur = rs.getInt("no_utilisateur");
				
				
			}
				} catch (SQLException e) {		
					throw new DALException ("Probleme - obtenir leNumUtilDuVendeur - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			
			return numVendeur;
			
		}
		
	public static Vente mesEncheresParNomCat (int noVente, int noCat, String nom) throws DALException{
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Vente venteA = new Vente();
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(RECHPARNOVTECATNOM);
			pstmt.setInt(1, noVente);
			pstmt.setInt(2, noCat);
			pstmt.setString(3, "%"+nom+"%");
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				
				venteA.setNumVente(noVente);
				venteA.setNumUtil(rs.getInt("no_utilisateur"));
				venteA.setNomArticle(rs.getString("nomarticle"));
				venteA.setDescription(rs.getString("description"));
				venteA.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				venteA.setPrixInitial(rs.getInt("prix_initial"));
				venteA.setPrixvente(rs.getInt("prix_vente"));
				venteA.setNumCate(rs.getInt("no_categorie"));
				
			}
		} catch (SQLException e) {		
			throw new DALException ("Probleme - recherche mes enchères par nom et cat - " + e.getMessage());	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
		}
	}
		return venteA;
	}
	
	public static void finirVente(int numVente) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(FINIRVENTE);
			pstmt.setInt(1, -1);
			pstmt.setInt(2, numVente);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Probleme - finirVente - " + e.getMessage());
		} finally {
			try {

				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}
public static Vente mesAcquisitions (int noVente) throws DALException{
		
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Vente venteA = new Vente();
		
		
		try {
			cnx=DBConnection.seConnecter();
			pstmt=cnx.prepareStatement(LISTACQUISITION);
			pstmt.setInt(1, noVente);
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				
				venteA.setNumVente(noVente);
				venteA.setNumUtil(Integer.parseInt(rs.getString("no_utilisateur")));
				venteA.setNomArticle(rs.getString("nomarticle"));
				venteA.setDescription(rs.getString("description"));
				venteA.setDateFinEnchere(LocalDate.parse(rs.getString("date_fin_encheres")));
				venteA.setPrixInitial(rs.getInt("prix_initial"));
				venteA.setPrixvente(rs.getInt("prix_vente"));
				venteA.setNumCate(rs.getInt("no_categorie"));
				
			}
		} catch (SQLException e) {		
			throw new DALException ("Probleme - recherche mes acquisitions - " + e.getMessage());	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
		}
	}
		return venteA;
	}	
	// m�thode publier versus enregistrer
public static void publier (int noVente) throws DALException {
	Connection cnx=null;
	PreparedStatement pstmt = null;
	int nblig=0;
	try{
		cnx = DBConnection.seConnecter();
		pstmt = cnx.prepareStatement(PUBLIER);
		pstmt.setInt(1, noVente);
		nblig= pstmt.executeUpdate();
		
	}catch(SQLException e){
		throw new DALException ("Probleme - publier Vente - " + e.getMessage());
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		}catch(SQLException e){
			throw new DALException ("DAL - fermerConnexion - " + e.getMessage());
		}
	}
}
	
}


