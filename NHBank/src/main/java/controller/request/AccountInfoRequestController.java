package controller.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import message.AccountUpdateInfoDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import service.AccountInfoService;
import service.AccountInfoServiceImpl;
import vo.AccountInfoDTO;

@WebServlet({ "/accounts", "/username", "/my-accounts", "/registrate", "/view/accountInfo/insertAccount", "/balance",
      "/my-inner-accounts", "/outer-my-accounts", "/my-bank-accounts" , "/outer-balance" })
public class AccountInfoRequestController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private final OkHttpClient client = new OkHttpClient();
   private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
      DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

      @Override
      public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
         try {
            return df.parse(json.getAsString());
         } catch (ParseException e) {
            throw new JsonParseException(e);
         }
      }
   }).create();

   public AccountInfoRequestController() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String uri = request.getRequestURI();
      String comPath = request.getContextPath();
      String command = uri.substring(comPath.length());
      if (command.equals("/accounts")) {
         String personalIdNumber = request.getParameter("personalIdNumber");

         // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
         String encodedIdNumber = Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

         String url = "http://3.39.230.202/openapi/account-info?personalIdNumber=" + encodedIdNumber;

         Request okHttpRequest = new Request.Builder().url(url).get().build();

         List<AccountInfoDTO> accountInfos = new ArrayList<>();

         try {
            Response okHttpResponse = client.newCall(okHttpRequest).execute();
            if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
               // OpenAPI 서버로부터 받은 응답
               String apiResponse = okHttpResponse.body().string();

               // 각 은행의 응답 리스트
               Type responseType = new TypeToken<ArrayList<String>>() {
               }.getType();
               List<String> bankResponses = gson.fromJson(apiResponse, responseType);

               // 각 은행 응답에 대해 처리
               for (String bankResponse : bankResponses) {
                  Type listType = new TypeToken<ArrayList<AccountInfoDTO>>() {
                  }.getType();
                  List<AccountInfoDTO> bankAccountInfos = gson.fromJson(bankResponse, listType);
                  accountInfos.addAll(bankAccountInfos);
               }

               System.out.println("Account Information: " + accountInfos);
            } else {
               throw new IOException("Unexpected code " + okHttpResponse);
            }
         } catch (IOException e) {
            System.out.println("Failed to get account information: " + e.getMessage());
            throw new ServletException("Failed to get account information", e);
         }

         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(gson.toJson(accountInfos));

      } else if (command.equals("/my-accounts")) {
          String bankName = request.getParameter("bankName");
          String personalIdNumber = request.getParameter("personalIdNumber");
          // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
          String encodedIdNumber = Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

          String url = "http://3.39.230.202/openapi/outer-my-account-info?bankName=" + bankName + "&personalIdNumber="
                + encodedIdNumber;

          Request okHttpRequest = new Request.Builder().url(url).get().build();

          String memberId = request.getParameter("memberId");
          AccountInfoService accountInfoService = new AccountInfoServiceImpl();
          List<AccountInfoDTO> accountInfos = accountInfoService.findAccountsByMemberId(memberId);

          try {
             Response okHttpResponse = client.newCall(okHttpRequest).execute();
             if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                // OpenAPI 서버로부터 받은 응답
                String apiResponse = okHttpResponse.body().string();

                // 각 은행의 응답 리스트
                Type responseType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> bankResponses = gson.fromJson(apiResponse, responseType);

                // 각 은행 응답에 대해 처리
                for (String bankResponse : bankResponses) {
                   Type listType = new TypeToken<ArrayList<AccountInfoDTO>>() {
                   }.getType();
                   List<AccountInfoDTO> bankAccountInfos = gson.fromJson(bankResponse, listType);
                   accountInfos.addAll(bankAccountInfos);
                }

                System.out.println("Account Information: " + accountInfos);
             } else {
                throw new IOException("Unexpected code " + okHttpResponse);
             }
          } catch (IOException e) {
             System.out.println("Failed to get account information: " + e.getMessage());
             throw new ServletException("Failed to get account information", e);
          }
          accountInfos = accountInfos.stream().sorted(Comparator.comparing(AccountInfoDTO::getBankCode))
                .collect(Collectors.toList());
          
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(gson.toJson(accountInfos));
      } else if (command.equals("/username")) {

         String accountNumber = request.getParameter("accountNumber2");

         String bankCode = request.getParameter("bankCode2");
         System.out.println(accountNumber);
         System.out.println(bankCode);
         String url = "http://3.39.230.202/openapi/username?accountNumber=" + accountNumber + "&bankCode=" + bankCode;

         Request okHttpRequest = new Request.Builder().url(url).get().build();
         try {
            Response okHttpResponse = client.newCall(okHttpRequest).execute();
            if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
               // 응답받은 데이터를 String으로 변환
               String responseData = okHttpResponse.body().string();

               // 응답 데이터를 클라이언트에게 전송
               response.setContentType("application/json");
               response.setCharacterEncoding("UTF-8");
               response.getWriter().write(responseData);
            } else {
               throw new IOException("Unexpected code " + okHttpResponse);
            }
         } catch (IOException e) {
            System.out.println("Failed to get account information: " + e.getMessage());
            throw new ServletException("Failed to get account information", e);
         }
      } else if (command.equals("/balance")) {
         String accountNumber = request.getParameter("accountNumber");
         AccountInfoService accountInfoService = new AccountInfoServiceImpl();
         int balance = accountInfoService.checkBalance(accountNumber);
         PrintWriter out = response.getWriter();
         out.print(balance);
         out.flush();
      } else if (command.equals("/my-inner-accounts")) {
         String memberId = request.getParameter("memberId");
         AccountInfoService accountInfoService = new AccountInfoServiceImpl();
         List<AccountInfoDTO> accountInfos = accountInfoService.findAccountsByMemberId(memberId);

         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(gson.toJson(accountInfos));
      // 오픈뱅킹에 등록된 타행 계좌 조회
      } else if (command.equals("/outer-my-accounts")) {
          String personalIdNumber = request.getParameter("personalIdNumber");
          // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
          String encodedIdNumber = Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

          String bankName = request.getParameter("bankName");
          String url = "http://3.39.230.202/openapi/outer-my-account-info?bankName=" + bankName +"&personalIdNumber=" + encodedIdNumber;

          Request okHttpRequest = new Request.Builder().url(url).get().build();

          List<AccountInfoDTO> accountInfos = new ArrayList<>();

          try {
             Response okHttpResponse = client.newCall(okHttpRequest).execute();
             if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                // OpenAPI 서버로부터 받은 응답
                String apiResponse = okHttpResponse.body().string();

                // 각 은행의 응답 리스트
                Type responseType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> bankResponses = gson.fromJson(apiResponse, responseType);

                // 각 은행 응답에 대해 처리
                for (String bankResponse : bankResponses) {
                   Type listType = new TypeToken<ArrayList<AccountInfoDTO>>() {
                   }.getType();
                   List<AccountInfoDTO> bankAccountInfos = gson.fromJson(bankResponse, listType);
                   accountInfos.addAll(bankAccountInfos);
                }

                System.out.println("Account Information: " + accountInfos);
             } else {
                throw new IOException("Unexpected code " + okHttpResponse);
             }
          } catch (IOException e) {
             System.out.println("Failed to get account information: " + e.getMessage());
             throw new ServletException("Failed to get account information", e);
          }

          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(gson.toJson(accountInfos));
          }else if (command.equals("/my-bank-accounts")) { // 다른 은행 이체

              String personalIdNumber = request.getParameter("personalIdNumber");
              // Base64를 통하여 입력받은 주민등록번호를 인코딩(보안 강화)
              String encodedIdNumber =
                      Base64.getEncoder().encodeToString(personalIdNumber.getBytes());

              String bankCode = request.getParameter("bankCode");
              String url = "http://3.39.230.202/openapi/my-bank-accounts?bankCode=" + bankCode
                      + "&personalIdNumber=" + encodedIdNumber;

              Request okHttpRequest = new Request.Builder().url(url).get().build();

              List<AccountInfoDTO> accountInfos = new ArrayList<>();

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
                          Type listType = new TypeToken<ArrayList<AccountInfoDTO>>() {}.getType();
                          List<AccountInfoDTO> bankAccountInfos =
                                  gson.fromJson(bankResponse, listType);
                          accountInfos.addAll(bankAccountInfos);
                      }

                      System.out.println("Account Information: " + accountInfos);
                  } else {
                      throw new IOException("Unexpected code " + okHttpResponse);
                  }
              } catch (IOException e) {
                  System.out.println("Failed to get account information: " + e.getMessage());
                  throw new ServletException("Failed to get account information", e);
              }

              response.setContentType("application/json");
              response.setCharacterEncoding("UTF-8");
              response.getWriter().write(gson.toJson(accountInfos));
          } else if (command.equals("/outer-balance")) {
              String accountNumber = request.getParameter("accountNumber");
              String bankCode = request.getParameter("bankCode");

              String url = "http://3.39.230.202/openapi/outer-balance?accountNumber=" + accountNumber
                      + "&bankCode=" + bankCode;

              Request okHttpRequest = new Request.Builder().url(url).get().build();
              try {
                  Response okHttpResponse = client.newCall(okHttpRequest).execute();
                  if (okHttpResponse.isSuccessful() && okHttpResponse.body() != null) {
                      // 응답받은 데이터를 String으로 변환
                      String responseData = okHttpResponse.body().string();

                      // 응답 데이터를 클라이언트에게 전송
                      response.setContentType("application/json");
                      response.setCharacterEncoding("UTF-8");
                      response.getWriter().write(responseData);
                  } else {
                      throw new IOException("Unexpected code " + okHttpResponse);
                  }
              } catch (IOException e) {
                  System.out.println("Failed to get account information: " + e.getMessage());
                  throw new ServletException("Failed to get account information", e);
              }
          }
   }
   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
     String uri = request.getRequestURI();
     String comPath = request.getContextPath();
     String command = uri.substring(comPath.length());
     if (command.equals("/registrate")) {
       // Request body를 String으로 가져옴
       StringBuilder buffer = new StringBuilder();
       BufferedReader reader = request.getReader();
       String line;
       while ((line = reader.readLine()) != null) {
         buffer.append(line);
       }
       String data = buffer.toString();

       // HttpURLConnection을 이용하여 PUT 요청 보내기
       URL url = new URL("http://3.39.230.202/openapi/registrate");
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestMethod("PUT");
       connection.setDoOutput(true);
       connection.setRequestProperty("Content-Type", "application/json; utf-8");
       connection.setRequestProperty("Accept", "application/json");

       byte[] postData = data.getBytes(StandardCharsets.UTF_8);
       OutputStream outputStream = connection.getOutputStream();
       outputStream.write(postData);
       outputStream.flush();
       outputStream.close();

       int responseCode = connection.getResponseCode();
       if (responseCode != HttpURLConnection.HTTP_OK) {
         InputStream errorStream = connection.getErrorStream(); // 에러 스트림 가져오기
         BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
         StringBuilder errorResponse = new StringBuilder();
         String errLine;
         while ((errLine = errorReader.readLine()) != null) {
           errorResponse.append(errLine);
         }
         errorReader.close();

         // 에러 메시지 전송
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write("{\"message\":\"Error occurred. Response Code: " 
           + responseCode + ", Error message: " + errorResponse.toString() + "\"}");
         return;
       }

       connection.disconnect();

       // 모든 계좌 정보가 성공적으로 업데이트 되었다고 가정하고, 클라이언트에게 성공 메시지를 보냅니다.
       response.setContentType("application/json");
       response.setCharacterEncoding("UTF-8");
       response.getWriter().write("{\"message\":\"All accounts successfully updated.\"}");
     }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      System.out.println("doPost 실행");
      String viewPage = null;
      String uri = request.getRequestURI();
      System.out.println("uri: " + uri);

      String conPath = request.getContextPath();
      System.out.println("conPath: " + conPath);

      String command = uri.substring(conPath.length() + 18);
      System.out.println("command: " + command);

      if (command.equals("insertAccount")) {
         String memberId = request.getParameter("memberId");
         String productName = request.getParameter("product-name");
         String bankCode = request.getParameter("bank");
         String nickname = request.getParameter("account-nickname");
         String accountPassword = request.getParameter("account-password");

         AccountInfoService service = new AccountInfoServiceImpl();
         service.insertAccount(memberId, productName, bankCode, nickname, accountPassword);
         viewPage = "/gwanjung/view/mainHana.jsp";
         response.sendRedirect(viewPage);

      }
   }
}