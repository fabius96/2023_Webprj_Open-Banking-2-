package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import vo.MemberDTO;
import vo.PostDTO;

public class BoardRepositoryImpl implements BoardRepository {

	private static BoardRepositoryImpl instance = null;

	private final String DB_URL = "jdbc:oracle:thin:@dinkdb_medium?TNS_ADMIN=/opt/wallet/Wallet_DinkDB";
	private final String USER = "DA2321";
	private final String PASS = "Data2321";

	public BoardRepositoryImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BoardRepositoryImpl getInstance() {
		if (instance == null) {
			synchronized (MemberRepositoryImpl.class) {
				if (instance == null) {
					instance = new BoardRepositoryImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public ArrayList<PostDTO> getAllList() {
		System.out.println("전체 게시글조회 시작");
		ArrayList<PostDTO> dtos = new ArrayList<PostDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// conn = DriverManager.getConnection(url, uid, upw);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "SELECT * FROM BANK_BOARD START WITH CATEGORY = 0 CONNECT BY PRIOR ID = CATEGORY ORDER SIBLINGS BY LEVEL_NUM ASC";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp write_Date = rs.getTimestamp("write_Date");
				int views = rs.getInt("views");
				int category = rs.getInt("category");
				int levelNum = rs.getInt("level_Num");
				int blank = rs.getInt("blank");

				PostDTO dto = new PostDTO(id, writer, title, content, write_Date, views, category, levelNum, blank);
				dtos.add(dto);
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(dtos);
		return dtos;
	}

	@Override
	public void InsertPost(PostDTO post) {
		System.out.println("게시글 등록 시작");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(post.getWriter());
		System.out.println(post.getTitle());
		System.out.println(post.getContent());

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "INSERT INTO BANK_BOARD (WRITER, TITLE, CONTENT, category) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, post.getWriter());
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getContent());
			pstmt.setInt(4, post.getCategory());

			int iResult = pstmt.executeUpdate(); // executeQuery() 대신 executeUpdate()로 수정

			System.out.println(iResult);
			if (iResult > 0) {
				System.out.println("게시글이 성공적으로 생성되었습니다.");
			} else {
				System.out.println("게시글 생성에 실패하였습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public PostDTO selectPostbyId(int id) {
		System.out.println("세부사항 조회 시작");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostDTO dto = null;

		try {
			// conn = DriverManager.getConnection(url, uid, upw);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "SELECT * FROM BANK_BOARD WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp write_Date = rs.getTimestamp("write_Date");
				int views = rs.getInt("views") + 1;
				int category = rs.getInt("category");
				int levelNum = rs.getInt("level_Num");
				int blank = rs.getInt("blank");

				dto = new PostDTO(id, writer, title, content, write_Date, views, category, levelNum, blank);

				String query1 = "UPDATE BANK_BOARD SET VIEWS =? WHERE ID = ? ";
				pstmt = conn.prepareStatement(query1);
				pstmt.setInt(1, dto.getViews());
				pstmt.setInt(2, id);
				pstmt.executeUpdate();

			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// sysout 추가
		System.out.println("Selected post: " + dto);
		return dto;
	}

	@Override
	public int updatePost(int id, PostDTO dto) {
		System.out.println("업데이트 시작");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int iResult = 0;

		try {
			// conn = DriverManager.getConnection(url, uid, upw);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "UPDATE BANK_BOARD SET writer = ?, title = ?, content = ?, write_Date = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(5, id);

			System.out.println(id);
			iResult = pstmt.executeUpdate();

			if (iResult > 0) {
				System.out.println("Update successful. Rows affected: " + iResult);
			} else {
				System.out.println("Update failed. No rows affected.");
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(iResult);
		return iResult;
	}

	@Override
	public int deletePost(String[] ids) {
		System.out.println("삭제시작");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int iResult = 0;

		try {
			// conn = DriverManager.getConnection(url, uid, upw);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "DELETE FROM BANK_BOARD WHERE ID = ?";
			pstmt = conn.prepareStatement(query);

			for (String id : ids) {
				int convertedId = Integer.parseInt(id);
				pstmt.setInt(1, convertedId);
				iResult = pstmt.executeUpdate();
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(iResult);
		return iResult;
	}

	@Override
	public int setCategory() {
		System.out.println("카테고리");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;

		try {
			// conn = DriverManager.getConnection(url, uid, upw);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = "SELECT MAX(ID) FROM BANK_BOARD";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				num = rs.getInt(1) + 1;
				System.out.println("여긴 setCategory rs.getInt 값: " + rs.getInt(1));
			System.out.println("여긴 setCategory num값 : " + num);
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("카테고리성공:" + num);
		return num;
	}

	@Override
	public void replyPost(int originalPostId, PostDTO replydto) {
		System.out.println("답글 시작");
		System.out.println(originalPostId + " 답글 postId값");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String originalPostQuery = "SELECT CATEGORY, LEVEL_NUM FROM BANK_BOARD WHERE ID = ?";
			pstmt = conn.prepareStatement(originalPostQuery);
			pstmt.setInt(1, originalPostId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
//				int originalID = rs.getInt("ID");
				int originalLevel = rs.getInt("LEVEL_NUM");

				// 답글 작성
				String replyQuery = "INSERT INTO BANK_BOARD (WRITER, TITLE, CONTENT, CATEGORY, LEVEL_NUM) VALUES (?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(replyQuery);
				pstmt.setString(1, replydto.getWriter());

				// 제목에 레벨만큼 "-" 기호 추가
				String replyTitle = "-".repeat(originalLevel) + " " + replydto.getTitle();
				pstmt.setString(2, replyTitle);

				pstmt.setString(3, replydto.getContent());
				pstmt.setInt(4, originalPostId);
				pstmt.setInt(5, originalLevel + 1);

				int iResult = pstmt.executeUpdate();

				if (iResult > 0) {
					System.out.println("답글이 성공적으로 생성되었습니다.");
				} else {
					System.out.println("답글 생성에 실패하였습니다.");
				}
			} else {
				System.out.println("원글을 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
