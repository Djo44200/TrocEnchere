package fr.eni.cours.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.cours.beans.Utilisateur;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.UtilisateurDAO;

/**
 * AMR Servlet implementation class AjouterUtilisateur
 */
@WebServlet("/AjouterUtilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajouter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur unUtilisateur = null;
		boolean testP, testM, testT;

		testP = UtilisateurDAO.verifPseudo(request.getParameter("sPseudo"));
		testM = UtilisateurDAO.verifMail(request.getParameter("sMail"));
		testT = UtilisateurDAO.verifTel(request.getParameter("sTelephone"));
	

		if (testP == false) {
			request.setAttribute("erreurPseudo", "Ce pseudo est déjà utilisé, veuillez en saisir un autre.");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajouter.jsp").forward(request, response);	
			} else if(testM == false) {
				request.setAttribute("erreurMail", "Ce mail est déjà utilisé, veuillez en saisir un autre.");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajouter.jsp").forward(request, response);
				} else if (testT==false){
					request.setAttribute("erreurTel", "Ce numéro de téléphone est déjà utilisé, veuillez en saisir un autre.");
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajouter.jsp").forward(request, response);
				}
		
			
		

		

		else if (!request.getParameter("sPseudo").isEmpty() && !request.getParameter("sNom").isEmpty()
				&& !request.getParameter("sPrenom").isEmpty() && !request.getParameter("sMail").isEmpty()
				&& !request.getParameter("sTelephone").isEmpty() && !request.getParameter("sRue").isEmpty()
				&& !request.getParameter("sCP").isEmpty() && !request.getParameter("sVille").isEmpty()
				&& !request.getParameter("sMDP").isEmpty() && !request.getParameter("sVMDP").isEmpty()) {
			unUtilisateur = new Utilisateur(request.getParameter("sPseudo"), request.getParameter("sNom"),
					request.getParameter("sPrenom"), request.getParameter("sMail"), request.getParameter("sTelephone"),
					request.getParameter("sRue"), request.getParameter("sCP"), request.getParameter("sVille"),
					request.getParameter("sMDP"), 150);

			if (request.getParameter("sMDP").equals(request.getParameter("sVMDP"))) {
				try {
					UtilisateurDAO.ajouter(unUtilisateur);
				} catch (DALException e) {
					
					e.printStackTrace();
				}
				request.setAttribute("messageCreation", unUtilisateur.getCredit());
				this.getServletContext().getRequestDispatcher("/index.html?successCreateUser=1").forward(request, response);
			} else {
				request.setAttribute("erreurMDP", "les 2 mdp ne sont pas identiques");
				this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ajouter.jsp").forward(request, response);
			}

		}

		else {

			System.out.println("Problème lors de la création de l'utilisateur");
			doGet(request, response);
		}

	}

}
