package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestDetailResponse;
import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestResponseDetail;
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

    public Page<MyAdoptRequestResponse> findByRequests(Long memberId, Pageable pageable) {
        return adoptRequestQueryRepository.findByMemberId(memberId, pageable);
    }

    public MyAdoptRequestResponseDetail findByRequestsDetail(Long memberId) {
        return adoptRequestQueryRepository.findDetailByMemberId(memberId);
    }

    public Page<AdoptRequestResponse> findByBoardId(Long memberId, Long boardId, Pageable pageable) {
        checkWriter(memberId, boardId);
        return adoptRequestQueryRepository.findByBoardId(boardId, pageable);
    }

    public AdoptRequestDetailResponse findDetail(Long memberId, Long boardId, Long adoptRequestId) {
        checkWriter(memberId, boardId);
        return adoptRequestQueryRepository.findDetail(adoptRequestId);
    }

    private void checkWriter(Long memberId, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다"));

        if (board.writerIsNotEqual(memberId)) {
            throw new RuntimeException("게시글을 작성한 사람이 아닙니다");
        }
    }
}
