package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestDetailResponse;
import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestResponse;
import PetShop.BarkingCat.domain.board.dto.MyAdoptRequestDetailResponse;
import PetShop.BarkingCat.domain.board.repository.query.AdoptRequestQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdoptRequestQueryService {
    private final AdoptRequestQueryRepository adoptRequestQueryRepository;

    public AdoptRequestQueryService(AdoptRequestQueryRepository adoptRequestQueryRepository) {
        this.adoptRequestQueryRepository = adoptRequestQueryRepository;
    }

    public Page<MyAdoptRequestResponse> findByRequests(Long memberId, Pageable pageable) {
        return adoptRequestQueryRepository.findByMemberId(memberId, pageable);
    }

    public MyAdoptRequestDetailResponse findDetail(Long memberId, Long adoptRequestId) {
        return adoptRequestQueryRepository.findDetailByMemberId(memberId, adoptRequestId);
    }

    public Page<AdoptRequestResponse> findByBoardId(Long boardWriterId, Long boardId, Pageable pageable) {
        return adoptRequestQueryRepository.findByBoardId(boardWriterId, boardId, pageable);
    }

    public AdoptRequestDetailResponse findDetail(Long boardWriterId, Long boardId, Long adoptRequestId) {
        return adoptRequestQueryRepository.findDetail(boardWriterId, boardId, adoptRequestId);
    }
}
