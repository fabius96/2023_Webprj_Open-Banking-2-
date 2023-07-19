package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import service.BoardServiceImpl;
import vo.PostDTO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("actionDo 실행");
		String viewPage = null;
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);

		String conPath = request.getContextPath();
		System.out.println("conPath: " + conPath);

		String command = uri.substring(conPath.length() + 6);
		System.out.println("command: " + command);

		// 게시글 작성
		if (command.equals("insert.board")) {
			BoardService service = new BoardServiceImpl();
			// 게시판을 작성한다면 작성자, 제목, 내용
			// request.getParameter로 세개 받아
			// 지금 문제는 3개를 가져오는데 insert시에는 4개를 insert해줘야한다
			// category 해주는 곳이 service
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			System.out.println(writer);

			service.InsertPost(writer, title, content);
			response.sendRedirect("getAllList.board");

		}

		else if (command.equals("getAllList.board")) {
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			BoardService service = new BoardServiceImpl();
			ArrayList<PostDTO> getAllList = service.getAllList();
			application.setAttribute("getAllList", getAllList);
			response.sendRedirect("/gwanjung/view/boardGetAllList.jsp");

		} else if (command.equals("detail.board")) {
			System.out.println("상세보기 메소드 실행");
			BoardService service = new BoardServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			PostDTO post = service.selectPostbyId(id);
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			application.setAttribute("post", post);
			response.sendRedirect("/gwanjung/view/boardDetail.jsp");

		}

		else if (command.equals("update.board")) {
			System.out.println("업데이트 메소드 실행");
			BoardService service = new BoardServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			PostDTO post = new PostDTO(writer, title, content);
			service.updatePost(id, post);
			response.sendRedirect("getAllList.board");

		} else if (command.equals("delete.board")) {
			System.out.println("삭제 메소드 실행");
			BoardService service = new BoardServiceImpl();
			String[] selectedIds = request.getParameterValues("postIds");
			service.deletePost(selectedIds);

			response.sendRedirect("getAllList.board");

		} else if (command.equals("reply.board")) {
			System.out.println("답글 메소드 실행");
			int originalPostId = Integer.parseInt(request.getParameter("parentPostId"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("parentPostId");
			
			PostDTO dto = new PostDTO(writer,title,content);
			
			BoardService service = new BoardServiceImpl();

			service.replyPost(originalPostId, dto);
			response.sendRedirect("getAllList.board");

		}
	}

}
