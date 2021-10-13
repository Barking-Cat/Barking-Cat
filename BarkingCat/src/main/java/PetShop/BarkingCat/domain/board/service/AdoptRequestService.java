package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.AdoptRequestRepository;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.query.AdoptRequestQueryRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptRequestService {

    private final AdoptRequestRepository adoptRequestRepository;

    private final AdoptRequestQueryRepository adoptRequestQueryRepository;

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    public AdoptRequestService(AdoptRequestRepository adoptRequestRepository, AdoptRequestQueryRepository adoptRequestQueryRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.adoptRequestRepository = adoptRequestRepository;
        this.adoptRequestQueryRepository = adoptRequestQueryRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
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

    @Transactional
    public void progressAdoptRequest(Long memberId, Long adoptRequestId) {
        AdoptRequest adoptRequest = adoptRequestRepository.findById(adoptRequestId)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 요청입니다"));

        if (adoptRequest.boardWriterIsNotEqual(memberId)) {
            throw new RuntimeException("본인의 게시물의 요청만 진행할 수 있습니다");
        }

        adoptRequest.progress();
    }

    @Transactional
    public void cancelAdoptRequest(Long memberId, Long adoptRequestId) {
        AdoptRequest adoptRequest = adoptRequestRepository.findById(adoptRequestId)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 요청입니다"));

        if (adoptRequest.writerIsNotEqual(memberId)) {
            throw new RuntimeException("본인의 요청이 아니면 취소할 수 없습니다");
        }

        adoptRequest.cancel();
    }
}
