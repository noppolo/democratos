package webtier;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GestionePopolazioneServlet
 */
@WebServlet("/GestionePopolazioneServlet")
public class GestionePopolazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionePopolazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* Preleva il bean dell' utente */
		HttpSession session = request.getSession(true);
		UserBean user=(UserBean) session.getAttribute("currentSessionUser");
	
		/* e gli setta una popolazione a caso (per prova) */
		
		/* rimettiamo il bean nella sessione e redirezioniamo l'utente */
		session.setAttribute("currentSessionUser", user);
		response.sendRedirect("guiGestionePopolazione.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
