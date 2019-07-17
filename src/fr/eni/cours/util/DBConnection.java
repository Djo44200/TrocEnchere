package fr.eni.cours.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.cours.dal.DALException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * 
 * @author vroch2019
 *
 */

public class DBConnection {
	public static Connection seConnecter()throws SQLException {
		Connection cnx = null;
		InitialContext jndi=null;
		DataSource ds=null;
		//----> Obtenir une r�frence sur le contexte initial JNDI
		try{
		jndi=new InitialContext();}
		catch(NamingException e){
			throw new SQLException("Erreur d'accès au contexte initial JNDI");}
		//----> recherche du pool de connexion dans l'annuaire
		try{
		ds=(DataSource)jndi.lookup("java:comp/env/DB_Troc");}
		catch(NamingException e){
			throw new SQLException("Objet introuvable dans l'arbre JNDI:"+e.getMessage());
		}
		//----> obtenir une connexion
		try{
			cnx = ds.getConnection();
			return cnx;
		}
		catch(SQLException e){
			throw new SQLException("Impossible d'obtenir une connexion:"+e.getMessage());}
		}
}
