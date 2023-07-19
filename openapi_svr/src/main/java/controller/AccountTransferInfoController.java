package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
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
import vo.AccountTransferInfoDTO;

@WebServlet({ "/transfer", "/transfer-info", "/all-transfer-info", "/outer-transfer-info" })
public class AccountTransferInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// OkHttpClient와 Gson 객체 생성
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

	public AccountTransferInfoController() {
		super();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String comPath = req.getContextPath();
		String command = uri.substring(comPath.length());
		String contentType = req.getContentType();
		if (command.equals("/transfer")) {
			if (contentType != null && contentType.contains("application/json")) {
				Gson gson = new Gson();
				TransferInfo info = gson.fromJson(new InputStreamReader(req.getInputStream()), TransferInfo.class);

				// 개별 은행 서버 URL 리스트
				Map<String, String> bankApiUrlMap = new HashMap<>();
				bankApiUrlMap.put("하나", "http://3.38.178.177/yurim");
				bankApiUrlMap.put("우리", "http://3.38.253.104/gwanjung");

				// A쪽에서 출금하는 put request
				withdrawPutRequest(bankApiUrlMap.get(info.getBankCode1()), info.getBankCode1(),
						info.getAccountNumber1(), info.getBankCode2(), info.getAccountNumber2(), info.getAmount(),
						info.getContent());

				// B쪽에서 입금되는 put request
				depositPutRequest(bankApiUrlMap.get(info.getBankCode2()), info.getBankCode1(), info.getAccountNumber1(),
						info.getBankCode2(), info.getAccountNumber2(), info.getAmount(), info.getContent());
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request format");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String comPath = req.getContextPath();
		String command = uri.substring(comPath.length());

		if (command.equals("/transfer-info")) {
			String accountNumber = req.getParameter("accountNumber");
			String bankCode = req.getParameter("bankCode");

			// 개별 은행 서버 URL 리스트
			Map<String, String> bankApiUrlMap = new HashMap<>();
			bankApiUrlMap.put("하나", "http://3.38.178.177/yurim");
			bankApiUrlMap.put("우리", "http://3.38.253.104/gwanjung");

			List<AccountTransferInfoDTO> accountInfos = transferInfoGetRequest(bankApiUrlMap.get(bankCode), bankCode,
					accountNumber);
			// JSON 형태로 변환 후 응답
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(accountInfos));
		} else if (command.equals("/all-transfer-info")) {
			String personalIdNumber = req.getParameter("personalIdNumber");

			// 각 은행의 API URL들
			List<String> bankApiUrls = List.of("http://3.38.253.104/gwanjung", "http://3.38.178.177/yurim");

			// 각 은행 API로 요청을 보내고, 모든 은행의 응답을 저장
			List<String> bankResponses = new ArrayList<>();
			for (String bankApiUrl : bankApiUrls) {
				String bankResponse = sendGetRequest(bankApiUrl, personalIdNumber);
				bankResponses.add(bankResponse);
			}

			// JSON 형태로 변환 후 응답
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(new Gson().toJson(bankResponses));
		} else if (command.equals("/outer-transfer-info")) {
			String bankName = req.getParameter("bankName");
			String personalIdNumber = req.getParameter("personalIdNumber");

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
				String bankResponse = sendGetRequest(bankApiUrl, personalIdNumber);
				bankResponses.add(bankResponse);
			}

			// JSON 형태로 변환 후 응답
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(bankResponses));
		}
	}

	// 각 은행 API로 Get 요청을 보내고 응답을 받는 메소드
	private String sendGetRequest(String url, String personalIdNumber) throws IOException {
		Request request = new Request.Builder()
				.url(url + "/all-transfer-info-response?personalIdNumber=" + personalIdNumber).get().build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}

	private List<AccountTransferInfoDTO> transferInfoGetRequest(String url, String bankCode, String accountNumber)
			throws IOException {
		// OkHttp를 이용해 Get 요청
		Request request = new Request.Builder().url(url + "/accounts-transfer-response?accountNumber=" + accountNumber)
				.get().build();

		// 요청 보내고 응답 받기
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful() && response.body() != null) {
				String responseBody = response.body().string();
				Gson gson = new Gson();
				Type type = new TypeToken<List<AccountTransferInfoDTO>>() {
				}.getType();
				List<AccountTransferInfoDTO> accountInfos = gson.fromJson(responseBody, type);
				return accountInfos;
			} else {
				throw new IOException("Unexpected code " + response);
			}
		}
	}

	// A쪽에서 출금하는 put request
	private void withdrawPutRequest(String url, String bankCode1, String accountNumber1, String bankCode2,
			String accountNumber2, int amount, String content) throws IOException {

		URL bankUrl = new URL(url + "/withdraw");
		HttpURLConnection connection = (HttpURLConnection) bankUrl.openConnection();

		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json; utf-8");

		TransferInfo info = new TransferInfo();
		info.setBankCode1(bankCode1);
		info.setAccountNumber1(accountNumber1);
		info.setBankCode2(bankCode2);
		info.setAccountNumber2(accountNumber2);
		info.setAmount(amount);
		info.setContent(content);

		Gson gson = new Gson();
		String requestData = gson.toJson(info);

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = requestData.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("PUT request to withdraw succeeded.");
		} else {
			System.out.println("PUT request to withdraw failed. Response Code: " + responseCode);
		}

		connection.disconnect();
	}

	// B쪽에서 입금되는 put request
	private void depositPutRequest(String url, String bankCode1, String accountNumber1, String bankCode2,
			String accountNumber2, int amount, String content) throws IOException {

		URL bankUrl = new URL(url + "/deposit");
		HttpURLConnection connection = (HttpURLConnection) bankUrl.openConnection();

		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json; utf-8");

		TransferInfo info = new TransferInfo();
		info.setBankCode1(bankCode1);
		info.setAccountNumber1(accountNumber1);
		info.setBankCode2(bankCode2);
		info.setAccountNumber2(accountNumber2);
		info.setAmount(amount);
		info.setContent(content);

		Gson gson = new Gson();
		String requestData = gson.toJson(info);

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = requestData.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			System.out.println("PUT request to deposit succeeded.");
		} else {
			System.out.println("PUT request to deposit failed. Response Code: " + responseCode);
		}

		connection.disconnect();
	}

}