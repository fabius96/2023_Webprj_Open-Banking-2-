package controller.request;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import message.TransferInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import service.AccountInfoService;
import service.AccountInfoServiceImpl;
import service.AccountTransferService;
import service.AccountTransferServiceImpl;
import vo.AccountInfoDTO;
import vo.AccountTransferInfoDTO;

/* transfer는 이체 진행, transfer-info는 계좌내역 불러오기 */
@WebServlet({"/transfer", "/transfer-info", "/all-transfer-info", "/inner-transfer-info",
        "/outer-transfer-info"})
public class AccountTransferInfoRequestController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public AccountTransferInfoRequestController() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String comPath = request.getContextPath();
        String command = uri.substring(comPath.length());

        if (command.equals("/transfer")) {
            String accountNumber1 = request.getParameter("accountNumber1");
            System.out.println(accountNumber1);
            String bankCode1 = request.getParameter("bankCode1");
            String accountNumber2 = request.getParameter("accountNumber2");

            String bankCode2 = request.getParameter("bankCode2");
            int amount = Integer.parseInt(request.getParameter("amount"));
            String content = request.getParameter("content");
            System.out.println(accountNumber1);
            TransferInfo transferInfo = new TransferInfo(accountNumber1, bankCode1, accountNumber2,
                    bankCode2, amount, content);

            Gson gson = new Gson();
            String jsonPayload = gson.toJson(transferInfo);

            URL url = new URL("http://3.39.230.202/openapi/transfer");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            byte[] postData = jsonPayload.getBytes(StandardCharsets.UTF_8);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response.setStatus(HttpServletResponse.SC_OK);

                // response.getWriter().write("PUT request succeeded.");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("PUT request failed. Response Code: " + responseCode);
            }
            connection.disconnect();
        }
    }

    // 특정 은행 계좌의 거래 내역 모두 조회
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        String comPath = req.getContextPath();
        String command = uri.substring(comPath.length());
        System.out.println(command);
        if (command.equals("/transfer")) {
            String accountNumber1 = req.getParameter("accountNumber1");
            System.out.println(accountNumber1);
            String bankCode1 = req.getParameter("bankCode1");
            String accountNumber2 = req.getParameter("accountNumber2");
            String bankCode2 = req.getParameter("bankCode2");
            int amount = Integer.parseInt(req.getParameter("amount"));
            String content = req.getParameter("content");
            System.out.println(bankCode2);
            TransferInfo transferInfo = new TransferInfo(accountNumber1, bankCode1, accountNumber2,
                    bankCode2, amount, content);

            Gson gson = new Gson();
            String jsonPayload = gson.toJson(transferInfo);

            URL url = new URL("http://3.39.230.202/openapi/transfer");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            byte[] postData = jsonPayload.getBytes(StandardCharsets.UTF_8);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                resp.setStatus(HttpServletResponse.SC_OK);
                // resp.getWriter().write("PUT request succeeded.");
                // resp.sendRedirect("/yurim/view/mainHana.jsp");

                resp.getWriter().println("<script type=\"text/javascript\">");
                resp.getWriter().println("alert('Transfer successed');");
                resp.getWriter().println("location='/yurim/view/mainHana.jsp';");
                resp.getWriter().println("</script>");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("PUT request failed. Response Code: " + responseCode);
                resp.getWriter().println("<script type=\"text/javascript\">");
                resp.getWriter().println("alert('Transfer failed');");
                resp.getWriter().println("location='/yurim/view/accountTransferIner.jsp';");
                resp.getWriter().println("</script>");
            }
            connection.disconnect();
        }
        if (command.equals("/transfer-info")) {
            String accountNumber = req.getParameter("accountNumber");
            String bankCode = req.getParameter("bankCode");

            String url = "http://3.39.230.202/openapi/transfer-info?accountNumber=" + accountNumber
                    + "&bankCode=" + bankCode;

            Request okHttpRequest = new Request.Builder().url(url).get().build();
            List<AccountTransferInfoDTO> accountTransferInfos = new ArrayList<>();
            try {
                Response okHttpResponse = client.newCall(okHttpRequest).execute();
                if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                    Type listType = new TypeToken<ArrayList<AccountTransferInfoDTO>>() {}.getType();
                    accountTransferInfos = gson.fromJson(okHttpResponse.body().string(), listType);
                    // Account Information 출력
                    System.out.println("Account Information: " + accountTransferInfos);

                } else {
                    throw new IOException("Unexpected code " + okHttpResponse);
                }
            } catch (IOException e) {
                // 에러 메시지 출력
                System.out.println("Failed to get account information: " + e.getMessage());
                throw new ServletException("Failed to get account information", e);
            }
            accountTransferInfos = accountTransferInfos
                    .stream().sorted(Comparator.comparing(AccountTransferInfoDTO::getTranDate)
                            .reversed().thenComparing(Comparator
                                    .comparing(AccountTransferInfoDTO::getTranTime).reversed()))
                    .collect(Collectors.toList());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(accountTransferInfos));
        } else if (command.equals("/all-transfer-info")) {
            String bankName = req.getParameter("bankName");
            String personalIdNumber = req.getParameter("personalIdNumber");
            String memberId = req.getParameter("memberId");

            AccountInfoService accountInfoService = new AccountInfoServiceImpl();
            AccountTransferService accountTransferService = new AccountTransferServiceImpl();

            // 계좌번호 리스트
            List<String> accountNumbers =
                    accountInfoService.findMyBankAccountNumbersByMemberId(memberId);

            // 거래내역 DTO 리스트
            List<AccountTransferInfoDTO> allTransferInfos = new ArrayList<>();

            for (String accountNumber : accountNumbers) {
                allTransferInfos.addAll(
                        accountTransferService.findTransferInfoByAccountNumber(accountNumber));
            }

            // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
            String encodedIdNumber =
                    Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

            String url = "http://3.39.230.202/openapi/outer-transfer-info?bankName=" + bankName
                    + "&personalIdNumber=" + encodedIdNumber;

            Request okHttpRequest = new Request.Builder().url(url).get().build();

            try {
                Response okHttpResponse = client.newCall(okHttpRequest).execute();
                if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                    // OpenAPI 서버로부터 받은 응답
                    String apiResponse = okHttpResponse.body().string();

                    // 각 은행의 응답 리스트
                    Type responseType = new TypeToken<ArrayList<String>>() {}.getType();
                    List<String> bankResponses = gson.fromJson(apiResponse, responseType);

                    // 각 은행 응답에 대해 처리
                    for (String bankResponse : bankResponses) {
                        Type listType =
                                new TypeToken<ArrayList<AccountTransferInfoDTO>>() {}.getType();
                        List<AccountTransferInfoDTO> bankAccountTransferInfos =
                                gson.fromJson(bankResponse, listType);
                        allTransferInfos.addAll(bankAccountTransferInfos);
                    }

                    System.out.println("Account Information: " + allTransferInfos);
                } else {
                    throw new IOException("Unexpected code " + okHttpResponse);
                }
            } catch (IOException e) {
                System.out.println("Failed to get account information: " + e.getMessage());
                throw new ServletException("Failed to get account information", e);
            }

            allTransferInfos = allTransferInfos
                    .stream().sorted(Comparator.comparing(AccountTransferInfoDTO::getTranDate)
                            .reversed().thenComparing(Comparator
                                    .comparing(AccountTransferInfoDTO::getTranTime).reversed()))
                    .collect(Collectors.toList());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(allTransferInfos));
        } else if (command.equals("/inner-transfer-info")) {
            String memberId = req.getParameter("memberId");
            AccountInfoService accountInfoService = new AccountInfoServiceImpl();
            AccountTransferService accountTransferService = new AccountTransferServiceImpl();

            // 계좌번호 리스트
            List<String> accountNumbers =
                    accountInfoService.findMyBankAccountNumbersByMemberId(memberId);

            // 거래내역 DTO 리스트
            List<AccountTransferInfoDTO> allTransferInfos = new ArrayList<>();

            for (String accountNumber : accountNumbers) {
                allTransferInfos.addAll(
                        accountTransferService.findTransferInfoByAccountNumber(accountNumber));
            }

            allTransferInfos = allTransferInfos
                    .stream().sorted(Comparator.comparing(AccountTransferInfoDTO::getTranDate)
                            .reversed().thenComparing(Comparator
                                    .comparing(AccountTransferInfoDTO::getTranTime).reversed()))
                    .collect(Collectors.toList());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(allTransferInfos));
        } else if (command.equals("/outer-transfer-info")) {
            String personalIdNumber = req.getParameter("personalIdNumber");
            String bankName = req.getParameter("bankName");

            // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
            String encodedIdNumber =
                    Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

            String url = "http://3.39.230.202/openapi/outer-transfer-info?bankName=" + bankName
                    + "&personalIdNumber=" + encodedIdNumber;

            Request okHttpRequest = new Request.Builder().url(url).get().build();

            List<AccountTransferInfoDTO> accountTransferInfos = new ArrayList<>();

            try {
                Response okHttpResponse = client.newCall(okHttpRequest).execute();
                if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                    // OpenAPI 서버로부터 받은 응답
                    String apiResponse = okHttpResponse.body().string();

                    // 각 은행의 응답 리스트
                    Type responseType = new TypeToken<ArrayList<String>>() {}.getType();
                    List<String> bankResponses = gson.fromJson(apiResponse, responseType);

                    // 각 은행 응답에 대해 처리
                    for (String bankResponse : bankResponses) {
                        Type listType =
                                new TypeToken<ArrayList<AccountTransferInfoDTO>>() {}.getType();
                        List<AccountTransferInfoDTO> bankAccountTransferInfos =
                                gson.fromJson(bankResponse, listType);
                        accountTransferInfos.addAll(bankAccountTransferInfos);
                    }

                    System.out.println("Account Information: " + accountTransferInfos);
                } else {
                    throw new IOException("Unexpected code " + okHttpResponse);
                }
            } catch (IOException e) {
                System.out.println("Failed to get account information: " + e.getMessage());
                throw new ServletException("Failed to get account information", e);
            }

            accountTransferInfos = accountTransferInfos
                    .stream().sorted(Comparator.comparing(AccountTransferInfoDTO::getTranDate)
                            .reversed().thenComparing(Comparator
                                    .comparing(AccountTransferInfoDTO::getTranTime).reversed()))
                    .collect(Collectors.toList());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(gson.toJson(accountTransferInfos));
        }

    }

}
