package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.CommentResponse;
import PetShop.BarkingCat.domain.board.repository.query.CommentQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentQueryService {

    private final CommentQueryRepository commentQueryRepository;

    public CommentQueryService(CommentQueryRepository commentQueryRepository) {
        this.commentQueryRepository = commentQueryRepository;
    }

    public List<CommentResponse> findAllByBoardId(Long boardId) {

        return commentQueryRepository.findAllByBoardId(boardId);

    }
}
