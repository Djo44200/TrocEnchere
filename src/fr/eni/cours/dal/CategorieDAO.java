package fr.eni.cours.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.eni.cours.beans.Categorie;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.util.DBConnection;

public class CategorieDAO {
	/**
	 * @author vroch2019
	 */
		private final static String NUMCATEGORIE= "select * from CATEGORIES where no_categorie = ? ;";
		private final static String LIBCATEGORIE= "select * from CATEGORIES where libelle = ? ;";
		
	// m�thode chercher libell� cat�gorie � partir du num�ro	
		public static Categorie libelleCategorie (int noCate) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Categorie cate = new Categorie();
			
			try {
				cnx=DBConnection.seConnecter();
				pstmt=cnx.prepareStatement(NUMCATEGORIE);
				pstmt.setInt(1, noCate);
				rs=pstmt.executeQuery();
				
				if (rs.next()) {
					cate.setLibelle(rs.getString("libelle"));
				}
			} catch (SQLException e) {		
					throw new DALException ("Probleme - m�thode libelleCat�gorie - " + e.getMessage());	
			}finally{
				try{
					if (pstmt!=null) pstmt.close();
					if (cnx!=null) cnx.close();
				} catch (SQLException e) {
						throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
				}
			}
			return cate;		
		}	
		
		// m�thode chercher num�ro cat�gorie � partir du libell�	
				public static Categorie numCategorie (String libel) throws DALException {
					Connection cnx=null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					Categorie cate = new Categorie();
					
					try {
						cnx=DBConnection.seConnecter();
						pstmt=cnx.prepareStatement(LIBCATEGORIE);
						pstmt.setString(1, libel);
						rs=pstmt.executeQuery();
						
						while (rs.next()) {
							cate.setNumCate(rs.getInt("no_categorie"));
							cate.setLibelle(libel);
						}
					} catch (SQLException e) {		
							throw new DALException ("Probleme - m�thode libelleCat�gorie - " + e.getMessage());	
					}finally{
						try{
							if (pstmt!=null) pstmt.close();
							if (cnx!=null) cnx.close();
						} catch (SQLException e) {
								throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
						}
					}
					return cate;
					
				}			
}
