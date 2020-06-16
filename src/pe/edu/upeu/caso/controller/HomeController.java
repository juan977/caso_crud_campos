package pe.edu.upeu.caso.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.edu.upeu.caso.dao.AlumnoDao;
import pe.edu.upeu.caso.dao.EscuelaDao;
import pe.edu.upeu.caso.daoImp.AlumnoDaoImp;
import pe.edu.upeu.caso.daoImp.EscuelaDaoImp;
import pe.edu.upeu.caso.entity.Alumno;


/**
 * Servlet implementation class HomeController
 */
@WebServlet("/hc")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlumnoDao ad1 = new AlumnoDaoImp();
	private EscuelaDao ed = new EscuelaDaoImp();
	private Gson g = new Gson();
	int ida, idescuela; String nom,correo,telefono;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1: //listar escuelas	
			out.println(g.toJson(ed.readAll()));
			break;
		case 2: //listar alumnos
			out.println(g.toJson(ad1.readAll()));
			break;
		case 3: //registrar alumno
			ad1.create(new Alumno(0,
			Integer.parseInt(request.getParameter("escuela")),
			request.getParameter("nom"),
			request.getParameter("correo"),
			request.getParameter("telefono")));
			out.println(g.toJson("Registro guardado correctamente"));
			break;
		case 4: //buscar por ID
			out.println(g.toJson(ad1.read(Integer.parseInt(request.getParameter("id")))));
			break;
		case 5: //eliminar alumno
			int x = ad1.delete(Integer.parseInt(request.getParameter("id")));
			out.println(g.toJson(x));
			break;
		case 6: //modificar alumno
			ida = Integer.parseInt(request.getParameter("ida"));
			idescuela = Integer.parseInt(request.getParameter("idescuela"));
			nom = request.getParameter("nom");
			correo = request.getParameter("correo");
			telefono = request.getParameter("telefono");
			out.println(g.toJson(ad1.update(new Alumno(ida, idescuela, nom, correo, telefono))));
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
