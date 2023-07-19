package controller.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.JSONObject;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberDTO;

/**
 * Servlet implementation class MemberController
 */

/*@WebServlet({"/view/member/join","/view/member/checkId", "/view/member/mail", "/view/member/kakaoLogin", "/view/member/kakaoJoin", "/view/member/login","/member/login"})
*/
@WebServlet({"*.member","*.admin"})
public class MemberController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost 실행");
        String viewPage = null;
        String uri = request.getRequestURI();
        System.out.println("uri: " + uri);

        String conPath = request.getContextPath();
        System.out.println("conPath: " + conPath);

        String command = uri.substring(conPath.length() + 6);
        System.out.println("command: " + command);

        // 회원가입 시 ID 중복체크
        if (command.equals("checkId.member")) {
            MemberService service = new MemberServiceImpl();
            String uId = request.getParameter("id");
            System.out.println(uId);

            boolean ckResult = service.checkId(uId);
            System.out.println(ckResult + ": id 중복체크 실행");
            // request.setAttribute("ckResult", ckResult);

            // AJAX 응답 처리
            response.setContentType("text/plain"); // JSON 혹은 다른 형태의 응답을 반환하려면 콘텐트 타입을 적절히 변경
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(ckResult); // boolean 값 반환. 필요에 따라 String 으로 변환할 수 있음.
            out.flush();


        }
        // 회원가입 시 EMAIL 인증
        else if (command.equals("mail.member")) {
            MemberService service = new MemberServiceImpl();
            String email = request.getParameter("email");
            String verifyCode = service.mailSend(email);
            response.setContentType("text/plain"); // JSON 혹은 다른 형태의 응답을 반환하려면 콘텐트 타입을 적절히 변경
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(verifyCode); // boolean 값 반환. 필요에 따라 String 으로 변환할 수 있음.
            out.flush();
        }
        // 회원가입
        else if (command.equals("join.member")) {
            MemberService service = new MemberServiceImpl();
            String userId = request.getParameter("id");
            String userPw = request.getParameter("pw");
            String name = request.getParameter("name");
            String simplePw = request.getParameter("simplePw");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String personID =
                    request.getParameter("personal_id_1") + request.getParameter("personal_id_2");
            String zipcode = request.getParameter("zipcode");
            String address = request.getParameter("address");
            String detailAddress = request.getParameter("detailAddress");
            MemberDTO dto = new MemberDTO(userId, name, userPw, simplePw, email, phone, personID,
                    zipcode, address, detailAddress);

            if (service.joinMember(dto)) {
                viewPage = "/view/mainHana.jsp";
                // 포워딩
                RequestDispatcher reqDpt = request.getRequestDispatcher(viewPage);
                reqDpt.forward(request, response);
            } else {
                response.sendRedirect("join.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("message", "이미 가입된 회원입니다.");
            }
        }
        // 카카오 로그인
        else if (command.equals("kakaoLogin.member")) {
            MemberService service = new MemberServiceImpl();

            // 카카오 로그인 JSON데이터 가져오기
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (reader != null) {
                json = reader.readLine();
            }

            // JSON 데이터 처리
            // 예시로 출력만 함
            System.out.println("Received JSON Data: " + json);

            // 필요한 작업 수행
            JSONObject jsonObject = new JSONObject(json);
            JSONObject properties = jsonObject.getJSONObject("properties");
            String nickname = properties.getString("nickname");

            // json 데이터 중 email 정제
            JSONObject kakaoAccount = jsonObject.getJSONObject("kakao_account");
            boolean hasEmail = kakaoAccount.getBoolean("has_email");
            String email = null;
            if (hasEmail) {
                email = kakaoAccount.getString("email");
            }



            long id = jsonObject.getLong("id");
            String convertId = String.valueOf(id);
            System.out.println("ID: " + id);
            System.out.println("Nickname: " + nickname);

            // 존재하지 않으면 true, 존재하지 않을 때
            if (service.checkId(convertId)) {
                HttpSession session = request.getSession();
                session.setAttribute("kakaoId", convertId);
                service.insertKaKaoMember(convertId, nickname, email); // insert 후 추가 정보 받기
                response.setContentType("application/json"); // 콘텐트 타입을 application/json으로 설정
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                viewPage = "kakaoJoin.jsp";
                out.print("{\"viewPage\":\"" + viewPage + "\"}");
                out.flush();
            } else {
                MemberDTO dto = service.memberLoginCheck(convertId);
                // 세션에 dto 저장
                System.out.println(dto);
                HttpSession session = request.getSession();
                ServletContext application = session.getServletContext();
                application.setAttribute("dto", dto);
                session.setAttribute("loginResult", true);
                response.setContentType("application/json"); // 콘텐트 타입을 application/json으로 설정
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                Gson gson = new Gson(); // Gson 라이브러리를 이용
                out.print(gson.toJson(dto)); // DTO 객체를 JSON 문자열로 변환하여 반환
                out.flush();

            }

        }
        // 카카오 최초 로그인 자 추가정보 받기
        else if (command.equals("kakaoJoin.member")) {
            MemberService service = new MemberServiceImpl();
            String id = request.getParameter("kakaoId");
            System.out.println(id);
            String simplePw = request.getParameter("simplePw");
            String phone = request.getParameter("phone");
            String personID =
                    request.getParameter("personal_id_1") + request.getParameter("personal_id_2");
            String zipcode = request.getParameter("zipcode");
            String address = request.getParameter("address");
            String detailAddress = request.getParameter("detailAddress");
            String gender = null;

            service.kakaoJoin(id, simplePw, phone, personID, gender, zipcode, address, detailAddress);
            
            viewPage = "/gwanjung/view/mainHana.jsp";
            response.sendRedirect(viewPage);
            return;
        }

        // 일반 로그인
        else if (command.equals("login.member")) {
            HttpSession session = request.getSession();
            MemberService service = new MemberServiceImpl();
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            MemberDTO dto = service.memberLoginCheck(id, pw);
            System.out.println(dto + "controller DTO");
            if (dto == null) {
                System.out.println("controller DTO null");
                session.setAttribute("loginResult", "관리자의 승인이 필요합니다.");
                viewPage = "/gwanjung/view/loginFail.jsp";
                response.sendRedirect(viewPage);

            } else {
                ServletContext application = session.getServletContext();
                application.setAttribute("dto", dto);
                //session.setAttribute("loginResult", true);
                System.out.println("로그인 성공 command");
                // viewPage = "/view/loginOk.jsp";
                viewPage = "/gwanjung/view/mainHana.jsp";
                System.out.println(application.getAttribute("dto"));
                response.sendRedirect(viewPage);
                // 포워딩
                //RequestDispatcher reqDpt = request.getRequestDispatcher(viewPage);
                //reqDpt.forward(request, response);
            }
        }
        
        //logout 관련 로직 
        else if(command.equals("logout.member")) {
            HttpSession session = request.getSession();
            ServletContext application = session.getServletContext();
            MemberDTO dto = (MemberDTO) application.getAttribute("dto");
            if(dto.getUserPassword() == null) {
                viewPage = "/gwanjung/view/kakaoLogout.jsp";
            }else {
                viewPage = "/gwanjung/view/mainHana.jsp";
            }
            application.removeAttribute("dto");
            
            response.sendRedirect(viewPage);
        }
        else if(command.equals("login.admin")) {
            HttpSession session = request.getSession();
            MemberService service = new MemberServiceImpl();
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            MemberDTO dto = service.memberLoginCheck(id, pw);

            if (dto == null) {
                //session.setAttribute("loginResult", false);
                viewPage = "/gwanjung/view/mainHana.jsp";
                response.sendRedirect(viewPage);

            } else {
                ServletContext application = session.getServletContext();
                application.setAttribute("dto", dto);
                System.out.println("로그인 성공 command");
                viewPage = "/gwanjung/view/mainHanaAdmin.jsp";
                System.out.println(application.getAttribute("dto"));
                response.sendRedirect(viewPage);
            }
        }
        else if(command.equals("manageMember.admin")) {
            MemberService service = new MemberServiceImpl();
            ArrayList<MemberDTO> dtos = service.memberBeforeSelect();
            request.setAttribute("dtos", dtos);
            viewPage = "/view/adminJoinOk.jsp"; 
            //디스패처 포워드
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
            
        }
        else if(command.equals("joinOkFunction.admin")) {
            MemberService service = new MemberServiceImpl();
            String id = request.getParameter("id");
            boolean result = service.joinOkfunction(id);
            request.setAttribute("result", result);
            viewPage = "/view/joinOkFunction.jsp";
            //디스패처 포워드
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
            
        }
    }
}