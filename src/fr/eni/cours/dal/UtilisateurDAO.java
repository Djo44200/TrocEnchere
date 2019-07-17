package fr.eni.cours.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.util.DBConnection;
import fr.eni.cours.dal.DALException;

public class UtilisateurDAO {
	/**
	 * @author vroch2019
	 */
	private final static String LISTER = "select * from UTILISATEURS where no_utilisateur = ?;";
	private final static String LISTERPSEUDO = "select * from UTILISATEURS where pseudo = ?;";
	private final static String VERIFICATION = "select * from UTILISATEURS where pseudo = ? or email = ? and mot_de_passe = ?;";
	private final static String RECHERCHER = "select * from UTILISATEURS where pseudo = ? and mot_de_passe = ?;";
	private final static String INSERER = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?);";
	private final static String SUPPRIMER = "delete from UTILISATEURS where pseudo = ? and mot_de_passe = ?;";
	private final static String MODIFIER = "update UTILISATEURS  set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur= ?;";
	private final static String AUTREUTILISATEUR = "select pseudo, rue, code_postal, ville, telephone from UTILISATEURS where pseudo = ?; ";
	private final static String MODIFIERCREDIT = "update UTILISATEURS set credit = ? WHERE no_utilisateur= ?;";
	private final static String VERIFMDP = "select mot_de_passe from UTILISATEURS where no_utilisateur = ?;";
	
	private final static String RECHERCHE_UN_PSEUDO = "select pseudo FROM UTILISATEURS where no_utilisateur = ?;";


	
public static Utilisateur rechercher(String pseudo, String mdp) throws DALException {
	Connection cnx=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Utilisateur user = new Utilisateur();
	 
	try {
		cnx=DBConnection.seConnecter();
		pstmt=cnx.prepareStatement(RECHERCHER);
		pstmt.setString(1, pseudo);
		pstmt.setString(2, mdp);
		rs=pstmt.executeQuery();
	if (rs.next()){
		rs.getString("pseudo");
		user.setPseudo(rs.getString("pseudo"));
		rs.getString("mdp");
		user.setMdp(rs.getString("mdp"));
		rs.getString("nom");
		user.setNom(rs.getString("nom"));
		rs.getString("prenom");
		user.setPrenom(rs.getString("prenom"));
		rs.getString("email");
		user.setMail(rs.getString("email"));
		rs.getString("telephone");
		user.setTelephone(rs.getString("telephone"));
		rs.getString("rue");
		user.setRue(rs.getString("rue"));
		rs.getString("cp");
		user.setCp(rs.getString("cp"));
		rs.getString("ville");
		user.setVille(rs.getString("ville"));
		rs.getInt("Credit");
		user.setCredit(Integer.parseInt(rs.getString("Credit")));
	}
	} catch (SQLException e) {
		
			throw new DALException ("Probleme - rechercherUtilisateur - " + e.getMessage());
	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
		}

	}
	return user;
	
}
/**
 * 
 * m�thode ajouter
 */
public static void ajouter(Utilisateur user) throws DALException{
	Connection cnx=null;
	PreparedStatement pstmt=null;
// liste dans la requ�te : pseudo ,nom, prenom, , email, telephone, rue, code_postal, ville, mot_de_passe
	try{
		cnx = DBConnection.seConnecter();
		pstmt=cnx.prepareStatement(INSERER, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, user.getPseudo());
		pstmt.setString(2, user.getNom());
		pstmt.setString(3, user.getPrenom()); // AMR : modifier l'ordre pour être synchro avec la BD
		pstmt.setString(4, user.getMail());
		pstmt.setString(5, user.getTelephone());
		pstmt.setString(6, user.getRue());
		pstmt.setString(7, user.getCp());
		pstmt.setString(8, user.getVille());
		pstmt.setString(9, user.getMdp());
		pstmt.setInt(10, 150);
		pstmt.setInt(11, 0);
		pstmt.executeUpdate();
		
		
		 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                user.setNumUtil(generatedKeys.getInt(1));
	               
	                
	            }
	            else {
	                throw new SQLException("Impossible de créer l'utilisateur, aucun ID obtenu.");
	            }
	        }
		
		
	} catch (SQLException e){
		throw new DALException ("Probleme - ajouterUtilisateur - " + e.getMessage());
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		}catch (SQLException e){
			throw new DALException ("Probleme - fermerConnexion - " + e.getMessage());
		}		
	}
}

// méthode pour vérifier que le pseudo soit unique
public static boolean verifPseudo(String pseudo) {
	boolean i = false;
	Connection cnx=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		cnx=DBConnection.seConnecter();
		pstmt=cnx.prepareStatement("select * from UTILISATEURS WHERE pseudo = ?;" );
		pstmt.setString(1, pseudo);
		rs=pstmt.executeQuery();
	if (rs.next()){
		
		System.out.println("Ce pseudo est déjà utilisé.");
	} else {
		i = true;
	
	}
	} catch (SQLException e) {
		
	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				
		}
	}

	return i;
}

//méthode pour vérifier que le mail soit unique
public static boolean verifMail(String mail) {
	boolean i = false;
	Connection cnx=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		cnx=DBConnection.seConnecter();
		pstmt=cnx.prepareStatement("select * from UTILISATEURS WHERE email = ?;" );
		pstmt.setString(1, mail);
		rs=pstmt.executeQuery();
	if (rs.next()){
		
		
	} else {
		i = true;
	
	}
	} catch (SQLException e) {
		
	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				
		}
	}

	return i;
}

//méthode pour vérifier que le numéro de tel soit unique
public static boolean verifTel(String numTel) {
	boolean i = false;
	Connection cnx=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		cnx=DBConnection.seConnecter();
		pstmt=cnx.prepareStatement("select * from UTILISATEURS WHERE telephone = ?;" );
		pstmt.setString(1, numTel);
		rs=pstmt.executeQuery();
	if (rs.next()){
		
		System.out.println("Ce pseudo est déjà utilisé.");
	} else {
		i = true;
	
	}
	} catch (SQLException e) {
		
	
	}finally{
		try{
			if (pstmt!=null) pstmt.close();
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
				
		}
	}

	return i;
}


private static Utilisateur getUtilisateur(ResultSet vResultSet, Utilisateur vUtilisateur) throws SQLException {
	
	vUtilisateur.setNumUtil(vResultSet.getInt(1));
	vUtilisateur.setPseudo(vResultSet.getString(2));
	vUtilisateur.setNom(vResultSet.getString(3));
	vUtilisateur.setPrenom(vResultSet.getString(4));
	vUtilisateur.setMail(vResultSet.getString(5));
	vUtilisateur.setTelephone(vResultSet.getString(6));
	vUtilisateur.setRue(vResultSet.getString(7));
	vUtilisateur.setCp(vResultSet.getString(8));
	vUtilisateur.setVille(vResultSet.getString(9));
	vUtilisateur.setMdp(vResultSet.getString(10));
	vUtilisateur.setCredit(vResultSet.getInt(11)); 
	
	return vUtilisateur;
}


	/**
	 * 
	 * m�thode ajouter
	 */
	
	public static Utilisateur verification(String pseudo, String mdp, String mail) throws DALException {

		Utilisateur vUtilisateur = new Utilisateur();

		try {

			PreparedStatement vPreparedStatement = DBConnection.seConnecter().prepareStatement(VERIFICATION);
			vPreparedStatement.setString(1, pseudo);
			vPreparedStatement.setString(2, mail);
			vPreparedStatement.setString(3, mdp);
			ResultSet vResultSet = vPreparedStatement.executeQuery();
			while (vResultSet.next()) {
				vUtilisateur = getUtilisateur(vResultSet, vUtilisateur);

			}

		} catch (SQLException e) {

			throw new DALException("Probleme - Verification d'un utilisateur - " + e.getMessage());
		}

		return vUtilisateur;

	}

	/**
	 * M2thode supprimer
	 */
	public static int supprimer(String pseudo, String mdp) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		int nblig = 0;
		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, mdp);
			nblig = pstmt.executeUpdate();
			return nblig;
		} catch (SQLException e) {
			throw new DALException("Probleme - supprimerUtilisateur - " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("DAL - fermerConnexion - " + e.getMessage());
			}
		}
	}

	public static int modifier(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String cp, String ville, String mdp, int numUtil) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		int nblig = 0;
		try {
			cnx = DBConnection.seConnecter();
			pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			pstmt.setString(6, rue);
			pstmt.setString(7, cp);
			pstmt.setString(8, ville);
			pstmt.setString(9, mdp);
			pstmt.setInt(10, numUtil);

			nblig = pstmt.executeUpdate();
			return nblig;

			

		} catch (SQLException e) {
			throw new DALException("Probleme - modifierProfil - " + e.getMessage());
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
		public static Utilisateur autreUtilisateur(String pseudo
				) throws DALException {
			Connection cnx = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Utilisateur autreUtilisateur = new Utilisateur();
			try {
				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(AUTREUTILISATEUR);
				pstmt.setString(1, pseudo);	
				rs = pstmt.executeQuery();
				if (rs.next()) {
					rs.getString("pseudo");
					autreUtilisateur.setPseudo(rs.getString("pseudo"));
					rs.getString("rue");
					autreUtilisateur.setRue(rs.getString("rue"));
					rs.getString("code_postal");
					autreUtilisateur.setCp(rs.getString("code_postal"));
					rs.getString("ville");
					autreUtilisateur.setVille(rs.getString("ville"));
					rs.getString("telephone");
					autreUtilisateur.setTelephone(rs.getString("telephone"));

					
				}
			} catch (SQLException e) {

				throw new DALException("Probleme - rechercherAutreUtilisateur - " + e.getMessage());

			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (cnx != null)
						cnx.close();
				} catch (SQLException e) {
					throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
				}
			}

			return autreUtilisateur;
		}
		
		public static String rechercheUnPseudo(int numUtil) throws DALException {
			// Méthode pour chercher le  pseudo de la personne qui à créé la vente
			Connection cnx = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String pseudoUtil = null ;
			
			try {
				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(RECHERCHE_UN_PSEUDO);
				pstmt.setInt(1, numUtil);
				rs = pstmt.executeQuery();
				while (rs.next()) {
		
					pseudoUtil= rs.getString("pseudo");
					
				}

			} catch (SQLException e) {
				
				throw new DALException("Probleme - rechercher un pseudo - " + e.getMessage());
			}finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (cnx != null)
						cnx.close();
				} catch (SQLException e) {
					throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return pseudoUtil;
		
		
		}



		public Utilisateur verificationMdp(int numUtil) throws DALException {
			// récupération du mdp avant modification du profil
			Connection cnx = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Utilisateur autreUtilisateur = new Utilisateur();
		try {				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(VERIFMDP);
							
				pstmt.setInt(1, numUtil);				
				rs = pstmt.executeQuery();
				if (rs.next()){
					rs.getString("mot_de_passe");
					autreUtilisateur.setMdp(rs.getString("mot_de_passe"));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (cnx != null)
						cnx.close();
				} catch (SQLException e) {
					throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
				
			
			return autreUtilisateur;
		
		}
		
		
		public static void modifierCredit(int credit, int numUtil) throws DALException {
			Connection cnx = null;
			PreparedStatement pstmt = null;
	
			try {
				cnx = DBConnection.seConnecter();
				pstmt = cnx.prepareStatement(MODIFIERCREDIT);
				pstmt.setInt(1, credit);
				pstmt.setInt(2, numUtil);

				pstmt.executeUpdate();

			} catch (SQLException e) {
				throw new DALException("Probleme - modifierCredit - " + e.getMessage());
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
		
		
		public static Utilisateur obtenirUnUtil(int noUtil) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Utilisateur user = new Utilisateur();
			 
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(LISTER);
				pstmt.setInt(1, noUtil);
				rs=pstmt.executeQuery();
			if (rs.next()){
				
				user.setPseudo(rs.getString("pseudo"));
				
				user.setMdp(rs.getString("mot_de_passe"));
				
				user.setNom(rs.getString("nom"));
				
				user.setPrenom(rs.getString("prenom"));
		
				user.setMail(rs.getString("email"));
				
				user.setTelephone(rs.getString("telephone"));
				
				user.setRue(rs.getString("rue"));
				
				user.setCp(rs.getString("code_postal"));
				
				user.setVille(rs.getString("ville"));
				
				user.setCredit(Integer.parseInt(rs.getString("Credit")));
			}
			} catch (SQLException e) {
				
					throw new DALException ("Probleme - obtenirUnUtil - " + e.getMessage());
			
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}

			}
			return user;
			
		}
		
		
		public static Utilisateur obtenirUnUtilpseudo(String pseudo) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Utilisateur user = new Utilisateur();
			 
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(LISTERPSEUDO);
				pstmt.setString(1, pseudo);
				rs=pstmt.executeQuery();
			if (rs.next()){
				

				user.setNumUtil(rs.getInt("no_utilisateur"));
				
				user.setPseudo(pseudo);
				
				user.setMdp(rs.getString("mot_de_passe"));
				
				user.setNom(rs.getString("nom"));
				
				user.setPrenom(rs.getString("prenom"));
		
				user.setMail(rs.getString("email"));
				
				user.setTelephone(rs.getString("telephone"));
				
				user.setRue(rs.getString("rue"));
				
				user.setCp(rs.getString("code_postal"));
				
				user.setVille(rs.getString("ville"));
				
				user.setCredit(Integer.parseInt(rs.getString("Credit")));
			}
			} catch (SQLException e) {
				
					throw new DALException ("Probleme - obtenirUnUtil - " + e.getMessage());
			
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}

			}
			return user;
			
		}
		
		
		
}