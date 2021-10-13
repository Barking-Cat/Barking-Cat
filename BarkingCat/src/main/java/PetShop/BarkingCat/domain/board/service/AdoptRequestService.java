package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.AdoptRequestRepository;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AdoptRequestService {

    private final AdoptRequestRepository adoptRequestRepository;

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    public AdoptRequestService(AdoptRequestRepository adoptRequestRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.adoptRequestRepository = adoptRequestRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public Long registerAdoptRequest(Long memberId, AdoptRequestForm adoptRequestForm) {
        Board board = boardRepository.findById(adoptRequestForm.getBoardId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다"));

        memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다"));

        AdoptRequest adoptRequest = adoptRequestForm.entity()
                .mapWriter(memberId)
                .mapBoard(board);

        return adoptRequestRepository.save(adoptRequest)
                .id();
    }
}
