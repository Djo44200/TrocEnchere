package fr.eni.cours.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.cours.beans.Vente;
import fr.eni.cours.dal.DALException;
import fr.eni.cours.dal.VenteDAO;

/**
 * Servlet implementation class ServPubliVente
 */
@WebServlet("/ServPubliVente")
public class ServPubliVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numVente = Integer.parseInt(request.getParameter("numVente"));
		System.out.println(numVente);
		if (numVente !=0) {
			
		//récupérer vente à publier
		try {
			Vente vente = VenteDAO.AfficherArticle(numVente);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// test si bouton publier, alors méthode visibilité à 1
		if (request.getParameter("sPublier")!=null) {
			System.out.println(request.getParameter("sPublier"));
			try {
				VenteDAO.publier(numVente);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}			
			}
		} else {
			request.setAttribute("erreur", "Veuillez enregistrer votre nouvelle vente avant de la publier");
		this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
	}
	
	this.getServletContext().getRequestDispatcher("/WEB-INF/erreur/erreur.jsp").forward(request, response);
	}
}
