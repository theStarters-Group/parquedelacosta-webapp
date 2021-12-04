//package controller.attractions;
//
//import java.io.IOException;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import model.Atraccion;
//import services.AttractionService;
//
//@WebServlet("/attractions/create.do")
//public class CreateAttractionServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 3455721046062278592L;
//	private AttractionService attractionService;
//
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		this.attractionService = new AttractionService();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/create.jsp");
//		dispatcher.forward(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name = req.getParameter("name");
//		double cost = Double.parseDouble(req.getParameter("cost"));
//		double duration = Double.parseDouble(req.getParameter("duration"));
//		int capacity = Integer.parseInt(req.getParameter("capacity"));
//		int type = Integer.parseInt(req.getParameter("type"));
//		Atraccion attraction = attractionService.create(name, cost, duration, capacity, type);
//		if (attraction.isValid()) {
//			resp.sendRedirect("/turismo/attractions/index.do");
//		} else {
//			req.setAttribute("attraction", attraction);
//
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/create.jsp");
//			dispatcher.forward(req, resp);
//		}
//
//	}
//
//}
