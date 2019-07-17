package fr.eni.cours.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Categorie;
import fr.eni.cours.beans.Retrait;
import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.CategorieDAO;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.EnchereDAO;
import fr.eni.cours.dal.RetraitDAO;
import fr.eni.cours.dal.VenteDAO;

/**
 * Servlet implementation class rechercheVente
 */
@WebServlet("/rechercheVente")
public class RechercheVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//UTILISATION DE CETTE DOGET POUR REDIRIGER VERS LA JSP ENVOYER MESSAGE MEME SI IL N'Y A PAS DE RAPPORT
		String pseudo;
		
		pseudo = request.getParameter("pseudo");
		
		request.setAttribute("pseudo", pseudo);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/envoyerMessage.jsp").include(request, response);
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//affichage des ventes dans la page principale
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String nom = null;
		int num;
		List<Vente> ventes = new ArrayList<>();
		Utilisateur user = new Utilisateur();

		//r�cup�ration session
		HttpSession session = request.getSession();
		user = (Utilisateur) session.getAttribute("sessionUtilisateur");
		List<Integer> numVente = new ArrayList<>();
		Retrait retrait = new Retrait();
		List<Retrait> listRetrait = new ArrayList<>();

		try {								
	/**
	 * ajout vro
	 */
		
			
//gestion des cas o� les filtres ne sont pas activ�s
			if(request.getParameter("mVentes") == null && 
					request.getParameter("mEnchere") == null &&
						request.getParameter("mAcquisitions") == null &&
							request.getParameter("mAutres") == null) {
		
		// *Par cat�gorie* recherche des ventes d'une cat�gorie	sans condition sur le nom
				
				if (request.getParameter("sCategorie").equals("Toutes") 
						&& request.getParameter("sRecherche").isEmpty()
							) {
							nom = request.getParameter("sCategorie");	
							ventes = VenteDAO.listeVentes();
						}else
						if(!request.getParameter("sCategorie").equals("Toutes")
							&& request.getParameter("sRecherche").isEmpty()){		
								nom = request.getParameter("sCategorie");	
								Categorie cate = new Categorie();
								cate = VenteDAO.obtenirLeNumCate(nom);
								num = cate.getNumCate();
								ventes = VenteDAO.ventesCategorie(num);
						
					}
		// *Par nom* recherche des ventes � partir du nom, sans condition sur la cat�gorie 	
				
				if (request.getParameter("sRecherche") != null  &&
						!request.getParameter("sRecherche").isEmpty() &&
						request.getParameter("sCategorie").equals("Toutes" )
							) {
					nom = (request.getParameter("sRecherche"));
						
						ventes = VenteDAO.listeVenteArticle(nom);
				}
			}
			
//gestion des cas o� les filtres sont activ�s
			
			// *Par utilisateur* recherche des Ventes de l'utilisateur connect�
				// (mes ventes coch�, cat�gorie � Toutes, rien dans nom)
			
			if (request.getParameter("mVentes") != null 
					&&	request.getParameter("sCategorie").equals("Toutes") 
					&&	request.getParameter("sRecherche").isEmpty()
						) {					
						ventes = VenteDAO.ventesUtilisateur(user.getNumUtil());
						}
							
			// *Par utilisateur et cat�gorie* recherche des ventes de l'utilisateur dans une cat�gorie
				// (mes ventes coch�, cat�gorie!= Toutes, rien dans nom)
			
			if (request.getParameter("mVentes") != null 
					&&!request.getParameter("sCategorie").equals("Toutes") 
						&& request.getParameter("sRecherche").isEmpty()) {			
			
							nom = request.getParameter("sCategorie");
							
							Categorie cate = new Categorie();
							cate = VenteDAO.obtenirLeNumCate(nom);
							num = cate.getNumCate();
							
							
								ventes = VenteDAO.listeUtCat(num, user.getNumUtil());
								
					}
			
			//*Par utilisateur, cat�gorie et nom* 
				//(mes ventes coch�, cat�gorie!= Toutes, nom saisi)
			
			if (request.getParameter("mVentes")!= null
					&& !request.getParameter("sCategorie").equals("Toutes") 
						&& !request.getParameter("sRecherche").isEmpty() ) {
				
							nom = request.getParameter("sRecherche");
							Categorie cate = new Categorie();
							cate = VenteDAO.obtenirLeNumCate(request.getParameter("sCategorie"));						
							num = cate.getNumCate();

								ventes = VenteDAO.listeUtNomCat(nom, user.getNumUtil(), num);
				
			}	
			//recherche ventes utilisateur � partir du nom de l'article
			if (request.getParameter("sCategorie").equals("Toutes") 
					&& !request.getParameter("sRecherche").isEmpty() 
						&& request.getParameter("mVentes") != null) {
				
							nom = request.getParameter("sRecherche");	
							
								ventes = VenteDAO.listeUtNom(request.getParameter("sRecherche"), user.getNumUtil());
				
			}
			
		//*Mes ench�res* recherche des ventes pour lesquelles le user a fait une ench�re
			
			if(request.getParameter("mEnchere")!=null) {
				ArrayList<Integer> listV = new ArrayList<>();
				listV = EnchereDAO.quelleVenteEncherie(user.getNumUtil());
				
				if (listV !=null ) {
				//*Mes ench�res par cat�gorie et/ou nom*
					if(!request.getParameter("sCategorie").equals("Toutes")) {
						if(!request.getParameter("sRecherche").isEmpty()) {
							nom = request.getParameter("sRecherche");
							Categorie cate = new Categorie();
							cate = VenteDAO.obtenirLeNumCate(request.getParameter("sCategorie"));						
							num = cate.getNumCate();
						for (Integer no : listV) {
							if (VenteDAO.AfficherEnchereEnCours(no) !=null) {
							ventes.add( VenteDAO.mesEncheresParNomCat(no,num, request.getParameter("sRecherche")));
							}
						}
						}
					}else {
						
				for (Integer no : listV) {
					if (VenteDAO.AfficherEnchereEnCours(no).getNumVente() !=0) {
					ventes.add(VenteDAO.AfficherEnchereEnCours(no));	
					}
				}
				}
			}	
			}	
	// *Mes acquisitions* ench�res termin�es que j'ai gagn�es
			if(request.getParameter("mAcquisitions")!=null) {
				ArrayList<Integer> listV = new ArrayList<>();
				listV = EnchereDAO.quelleVenteEncherie(user.getNumUtil());	
					if (listV !=null ) {
						for (Integer no : listV) {
							if (VenteDAO.mesAcquisitions(no).getNumVente() !=0) {
							ventes.add(VenteDAO.mesAcquisitions(no));	
							System.out.println(ventes);
							}
						}
							}	
					}
			
		} catch (DALException e) {
			
			e.printStackTrace();

		}
		
		for (Vente v : ventes) {
			
			num=v.getNumVente();
			numVente.add(num);
		}

		
		for (Integer test : numVente) {
			try {
				retrait = RetraitDAO.AfficherRetrait(test);
				listRetrait.add(retrait);
			} catch (DALException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
		
		
	/**
	 * fin ajout vro
	 */
		request.setAttribute("listeRetrait", listRetrait);
		request.setAttribute("listeRecherchee", ventes);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/PagePrincipale.jsp").include(request, response);
			
		
	}
}

