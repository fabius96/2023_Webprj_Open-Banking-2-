package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
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

@WebServlet({ "/account-info", "/username", "/my-account-info", "/registrate", "/outer-my-account-info",
		"/my-bank-accounts", "/outer-balance" })
public class AccountInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OkHttpClient client = new OkHttpClient();
	private final Gson gson = new Gson();

	public AccountInfoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String comPath = request.getContextPath();
		String command = uri.substring(comPath.length());
		if (command.equals("/account-info")) {
			String personalIdNumber = request.getParameter("personalIdNumber");

			// 각 은행의 API URL들
			List<String> bankApiUrls = List.of("http://3.38.253.104/gwanjung", "http://3.38.178.177/yurim");

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			for (String bankApiUrl : bankApiUrls) {
				String bankResponse = sendGetRequest(bankApiUrl, personalIdNumber);
				bankResponses.add(bankResponse);
			}

			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(bankResponses));
		} else if (command.equals("/my-account-info")) {
			String personalIdNumber = request.getParameter("personalIdNumber");

			// 각 은행의 API URL들
			List<String> bankApiUrls = List.of("http://3.38.253.104/gwanjung", "http://3.38.178.177/yurim");

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			for (String bankApiUrl : bankApiUrls) {
				String bankResponse = linkedGetRequest(bankApiUrl, personalIdNumber);
				bankResponses.add(bankResponse);
			}

			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(bankResponses));
		} else if (command.equals("/username")) {

			String accountNumber = request.getParameter("accountNumber");
			String bankCode = request.getParameter("bankCode");

			Map<String, String> bankApiUrlMap = new HashMap<>();
			bankApiUrlMap.put("하나", "http://3.38.178.177/yurim");
			bankApiUrlMap.put("우리", "http://3.38.253.104/gwanjung");
			String accountInfo = userNameGetRequest(bankApiUrlMap.get(bankCode), accountNumber);
			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(accountInfo);
		} else if (command.equals("/outer-my-account-info")) {
			String bankName = request.getParameter("bankName");
			String personalIdNumber = request.getParameter("personalIdNumber");

			Map<String, String> allBankApiUrls = new HashMap<>();
			allBankApiUrls.put("관중", "http://3.38.253.104/gwanjung");
			allBankApiUrls.put("유림", "http://3.38.178.177/yurim");

			List<String> bankApiUrls = new ArrayList<>();

			for (String key : allBankApiUrls.keySet()) {
				if (!key.equals(bankName)) {
					bankApiUrls.add(allBankApiUrls.get(key));
				}
			}

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			for (String bankApiUrl : bankApiUrls) {
				String bankResponse = linkedGetRequest(bankApiUrl, personalIdNumber);
				bankResponses.add(bankResponse);
			}

			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(bankResponses));
		} else if (command.equals("/my-bank-accounts")) {
			String bankCode = request.getParameter("bankCode");
			String personalIdNumber = request.getParameter("personalIdNumber");

			Map<String, String> allBankApiUrls = new HashMap<>();
			allBankApiUrls.put("우리", "http://3.38.253.104/gwanjung");
			allBankApiUrls.put("하나", "http://3.38.178.177/yurim");

			String bankApiUrl = "";

			for (String key : allBankApiUrls.keySet()) {
				if (key.equals(bankCode)) {
					bankApiUrl = allBankApiUrls.get(key);
				}
			}

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			String bankResponse = linkedGetRequest(bankApiUrl, personalIdNumber);
			bankResponses.add(bankResponse);

			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(bankResponses));
		} else if (command.equals("/outer-balance")) {
			String accountNumber = request.getParameter("accountNumber");
			String bankCode = request.getParameter("bankCode");

			Map<String, String> allBankApiUrls = new HashMap<>();
			allBankApiUrls.put("우리", "http://3.38.253.104/gwanjung");
			allBankApiUrls.put("하나", "http://3.38.178.177/yurim");

			String bankApiUrl = "";

			for (String key : allBankApiUrls.keySet()) {
				if (key.equals(bankCode)) {
					bankApiUrl = allBankApiUrls.get(key);
				}
			}

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			String bankResponse = getBalanceRequest(bankApiUrl, accountNumber);
			bankResponses.add(bankResponse);

			// JSON 형태로 변환 후 응답
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(bankResponses));
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String comPath = request.getContextPath();
		String command = uri.substring(comPath.length());

		if (command.equals("/registrate")) {
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			// AccountUpdateInfoDTO의 리스트로 JSON 문자열 변환
			Type listType = new TypeToken<List<AccountUpdateInfoDTO>>() {
			}.getType();
			List<AccountUpdateInfoDTO> accountInfoList = gson.fromJson(sb.toString(), listType);

			Map<String, String> bankApiUrlMap = new HashMap<>();
			bankApiUrlMap.put("하나", "http://3.38.178.177/yurim");
			bankApiUrlMap.put("우리", "http://3.38.253.104/gwanjung");
			// 모든 account 정보에 대해 PUT 요청 보내기
			for (AccountUpdateInfoDTO accountInfo : accountInfoList) {

				String bankCode = accountInfo.getBankCode();
				String responseStr = sendPutRequest(bankApiUrlMap.get(bankCode), accountInfoList);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(responseStr);
			}
		}
	}

	private String sendPutRequest(String url, List<AccountUpdateInfoDTO> accountInfoList) throws IOException {
		RequestBody body = RequestBody.create(gson.toJson(accountInfoList),
				MediaType.parse("application/json; charset=utf-8"));
		Request request = new Request.Builder().url(url + "/registrate-response").put(body).build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}

	private String userNameGetRequest(String url, String accountNumber) {
		Request request = new Request.Builder().url(url + "/username-response?accountNumber=" + accountNumber).get()
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 각 은행 API로 Get 요청을 보내고 응답을 받는 메소드
	private String sendGetRequest(String url, String personalIdNumber) throws IOException {
		Request request = new Request.Builder().url(url + "/accounts-response?personalIdNumber=" + personalIdNumber)
				.get().build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}

	// 각 은행 API로 Get 요청을 보내고 응답을 받는 메소드
	private String linkedGetRequest(String url, String personalIdNumber) throws IOException {
		Request request = new Request.Builder().url(url + "/my-accounts-response?personalIdNumber=" + personalIdNumber)
				.get().build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}

	// 잔액 가져오기 메소드
	private String getBalanceRequest(String url, String accountNumber) throws IOException {
		Request request = new Request.Builder().url(url + "/balance-response?accountNumber=" + accountNumber).get()
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}
}