package controller.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import message.TransferInfo;
import service.AccountInfoService;
import service.AccountInfoServiceImpl;
import service.AccountTransferService;
import service.AccountTransferServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;
import vo.AccountTransferInfoDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.google.gson.Gson;

@WebServlet({"/withdraw", "/deposit", "/accounts-transfer-response", "/all-transfer-info-response"})
public class AccountTransferInfoResponseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Gson gson = new Gson();
    private final MemberService memberService = new MemberServiceImpl();
    private final AccountInfoService accountInfoService = new AccountInfoServiceImpl();
    
    // AccountTransferService 객체 추가 (예시, 실제로는 적절히 구현이 필요합니다.)
    private AccountTransferService accountTransferService = new AccountTransferServiceImpl();

    public AccountTransferInfoResponseController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        String comPath = req.getContextPath();
        String command = uri.substring(comPath.length());
        if (command.equals("/accounts-transfer-response")) {
            String accountNumber = req.getParameter("accountNumber");

            List<AccountTransferInfoDTO> accountInfos =
                    accountTransferService.findTransferInfoByAccountNumber(accountNumber);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(accountInfos));

        }else if(command.equals("/all-transfer-info-response")){
            String encodedPersonalIdNumber = req.getParameter("personalIdNumber");
            // Decode the personal id number which is in BASE64 format.
            byte[] decodedBytes = Base64.getDecoder().decode(encodedPersonalIdNumber);
            String personalIdNumber = new String(decodedBytes);

            String memberId = memberService.findMemberIdByPersonalIdNumber(personalIdNumber);
            System.out.println(memberId);
            List<String> accountInfos =  accountInfoService.findAccountNumbersByMemberId(memberId);

            // Create a list to store all transfer info
            List<AccountTransferInfoDTO> allTransferInfos = new ArrayList<>();

            for (String accountInfo : accountInfos) {
                // Get transfer info for each account and add it to the list
                List<AccountTransferInfoDTO> transferInfos = accountTransferService.findTransferInfoByAccountNumber(accountInfo);
                allTransferInfos.addAll(transferInfos);
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(allTransferInfos));
        }

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String comPath = request.getContextPath();
        String command = uri.substring(comPath.length());

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String jsonPayload = sb.toString();

        Gson gson = new Gson();
        TransferInfo transferInfo = gson.fromJson(jsonPayload, TransferInfo.class);

        if(command.equals("/withdraw")) {
            boolean result = accountTransferService.withdraw(transferInfo.getAccountNumber1(), transferInfo.getAmount());

            if(result) {
                accountTransferService.insertTransferInfo(transferInfo.getAccountNumber1(), transferInfo.getBankCode1(), 
                                                          transferInfo.getAccountNumber2(), transferInfo.getBankCode2(), 
                                                          transferInfo.getAmount(), transferInfo.getContent(), "OUT");
            }
        } else if(command.equals("/deposit")) {
            boolean result = accountTransferService.deposit(transferInfo.getAccountNumber2(), transferInfo.getAmount());

            if(result) {
                accountTransferService.insertTransferInfo(transferInfo.getAccountNumber2(), transferInfo.getBankCode2(), 
                                                          transferInfo.getAccountNumber1(), transferInfo.getBankCode1(), 
                                                          transferInfo.getAmount(), transferInfo.getContent(), "IN");
            }
        }
    }
}
