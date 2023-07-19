package repository;

import java.util.ArrayList;

import vo.PostDTO;

public interface BoardRepository {
	// 모든 게시글 가져오기
	public ArrayList<PostDTO> getAllList();

	// 게시글 작성 createPost
	public void InsertPost(PostDTO post);

	// 상세보기 및 조회수 업데이트
	public PostDTO selectPostbyId(int id);

	// 게시글 수정하기
	public int updatePost(int id, PostDTO dto);

	// 게시글 삭제하기
	public int deletePost(String[] ids);

	// 그룹 설정해주기
	public int setCategory();

	// 답글달기
	public void replyPost(int originalPostId, PostDTO replydto);
}
