package PetShop.BarkingCat.domain.board.service.query;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestResponse;
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

    public Page<AdoptRequestResponse> findByBoardId(Long boardId, Pageable pageable) {
        return adoptRequestQueryRepository.findByBoardId(boardId, pageable);
    }
}
