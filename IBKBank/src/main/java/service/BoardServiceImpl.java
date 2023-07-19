package service;

import java.util.ArrayList;

import repository.BoardRepository;
import repository.BoardRepositoryImpl;
import vo.PostDTO;

public class BoardServiceImpl implements BoardService {

	@Override
	public ArrayList<PostDTO> getAllList() {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		return repository.getAllList();
	}

	@Override
	public void InsertPost(String writer, String title, String content) {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		System.out.println(writer);
		System.out.println(title);
		System.out.println(content);
		PostDTO dto = new PostDTO(writer, title, content);
		repository.InsertPost(dto);

	}

	@Override
	public PostDTO selectPostbyId(int id) {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		return repository.selectPostbyId(id);
	}

	@Override
	public int updatePost(int id, PostDTO dto) {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		return repository.updatePost(id, dto);

	}

	@Override
	public int deletePost(String[] ids) {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		return repository.deletePost(ids);
	}

	@Override
	public int setCategory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void replyPost(int originalPostId, PostDTO replydto) {
		BoardRepository repository = BoardRepositoryImpl.getInstance();
		repository.replyPost(originalPostId, replydto);

	}

}
