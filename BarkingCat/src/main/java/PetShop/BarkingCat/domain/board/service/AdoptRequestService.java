package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.AdoptRequestRepository;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class AdoptRequestService {

    private final AdoptRequestRepository adoptRequestRepository;

    private final BoardRepository boardRepository;

    public AdoptRequestService(AdoptRequestRepository adoptRequestRepository, BoardRepository boardRepository) {
        this.adoptRequestRepository = adoptRequestRepository;
        this.boardRepository = boardRepository;
    }

    public Long registerAdoptRequest(AdoptRequestForm adoptRequestForm) {
        Board board = boardRepository.findById(adoptRequestForm.getBoardId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다"));

        AdoptRequest adoptRequest = adoptRequestForm.entity()
                .mapBoard(board);

        return adoptRequestRepository.save(adoptRequest)
                .id();
    }
}
