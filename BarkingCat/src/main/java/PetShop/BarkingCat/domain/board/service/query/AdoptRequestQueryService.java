package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.query.AdoptRequestQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdoptRequestQueryService {
    private final AdoptRequestQueryRepository adoptRequestQueryRepository;
    private final BoardRepository boardRepository;

    public AdoptRequestQueryService(AdoptRequestQueryRepository adoptRequestQueryRepository, BoardRepository boardRepository) {
        this.adoptRequestQueryRepository = adoptRequestQueryRepository;
        this.boardRepository = boardRepository;
    }

    public Page<AdoptRequestResponse> findByBoardId(Long memberId, Long boardId, Pageable pageable) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));

        if (!board.writerIsEqual(memberId)) {
            throw new RuntimeException("게시글을 작성한 사람이 아닙니다");
        }

        return adoptRequestQueryRepository.findByBoardId(boardId, pageable);
    }
}
