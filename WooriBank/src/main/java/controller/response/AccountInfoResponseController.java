package controller.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import message.AccountUpdateInfoDTO;
import service.AccountInfoService;
import service.AccountInfoServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;
import vo.AccountInfoDTO;

@WebServlet({"/accounts-response", "/username-response", "/my-accounts-response","/registrate-response","/balance-response"})
public class AccountInfoResponseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();
    private final MemberService memberService = new MemberServiceImpl();
    private final AccountInfoService accountInfoService = new AccountInfoServiceImpl();

    public AccountInfoResponseController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String comPath = request.getContextPath();
        String command = uri.substring(comPath.length());
        if(command.equals("/accounts-response")) {
            String encodedPersonalIdNumber = request.getParameter("personalIdNumber");
            // 주민등록번호를 BASE64 형식으로 디코딩합니다.
            byte[] decodedBytes = Base64.getDecoder().decode(encodedPersonalIdNumber);
            String personalIdNumber = new String(decodedBytes);
    
            String memberId = memberService.findMemberIdByPersonalIdNumber(personalIdNumber);
            System.out.println(memberId);
            List<AccountInfoDTO> accountInfos = accountInfoService.findAccountsByMemberId(memberId);
    
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(accountInfos));
        }else if(command.equals("/my-accounts-response")) {
            String encodedPersonalIdNumber = request.getParameter("personalIdNumber");
            // 주민등록번호를 BASE64 형식으로 디코딩합니다.
            byte[] decodedBytes = Base64.getDecoder().decode(encodedPersonalIdNumber);
            String personalIdNumber = new String(decodedBytes);
    
            String memberId = memberService.findMemberIdByPersonalIdNumber(personalIdNumber);
            System.out.println(memberId);
            List<AccountInfoDTO> accountInfos = accountInfoService.findMyAccountsByMemberId(memberId);
    
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(accountInfos));
        } else if(command.equals("/username-response")) {
            String accountNumber = request.getParameter("accountNumber");

            String memberName = accountInfoService.getMemberName(accountNumber);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(memberName);
        }else if (command.equals("/balance-response")) {
            String accountNumber = request.getParameter("accountNumber");
            AccountInfoService accountInfoService = new AccountInfoServiceImpl();
            int balance = accountInfoService.checkBalance(accountNumber);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(balance));
        }
    }
    
        // 오픈뱅킹 등록(재영)
 // 오픈뱅킹 등록(재영)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String comPath = request.getContextPath();
        String command = uri.substring(comPath.length());

        if (command.equals("/registrate-response")) {
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();

            try {
                // 받아온 JSON 문자열을 AccountUpdateInfoDTO 객체 배열로 변환
                AccountUpdateInfoDTO[] accountInfoArray = gson.fromJson(data, AccountUpdateInfoDTO[].class);

                // 각 AccountUpdateInfoDTO 객체에 대해 DB 업데이트
                for (AccountUpdateInfoDTO accountInfo : accountInfoArray) {
                    accountInfoService.registrateOpenbanking(accountInfo.getAccountNumber(), accountInfo.getBankCode());
                }

                // 클라이언트에게 성공 메시지를 보냅니다.
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\":\"Accounts successfully updated.\"}");
            } catch (Exception e) {
                // 실패 시, 클라이언트에게 실패 메시지를 보냅니다.
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\":\"Failed to update accounts: " + e.getMessage() + "\"}");
            }
        }
    }
    
    

}