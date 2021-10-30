package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
import PetShop.BarkingCat.domain.board.dto.AdoptRequestForm;
import PetShop.BarkingCat.domain.board.model.AdoptRequest;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.repository.AdoptRequestRepository;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Long registerAdoptRequest(Long memberId, AdoptRequestForm adoptRequestForm) {
        Board board = boardRepository.findById(adoptRequestForm.getBoardId())
                .orElseThrow(() -> new BarkingCatException(ErrorCode.BOARD_NOT_FOUND));

        memberRepository.findById(memberId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        AdoptRequest adoptRequest = adoptRequestForm.entity()
                .mapWriter(memberId)
                .mapBoard(board);

        return adoptRequestRepository.save(adoptRequest)
                .id();
    }

    @Transactional
    public void progressAdoptRequest(Long memberId, Long adoptRequestId) {
        AdoptRequest adoptRequest = adoptRequestRepository.findById(adoptRequestId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.ADOPT_REQUEST_NOT_FOUND));

        if (adoptRequest.boardWriterIsNotEqual(memberId)) {
            throw new BarkingCatException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        adoptRequest.progress();
    }

    @Transactional
    public void cancelAdoptRequest(Long memberId, Long adoptRequestId) {
        AdoptRequest adoptRequest = adoptRequestRepository.findById(adoptRequestId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.ADOPT_REQUEST_NOT_FOUND));

        if (adoptRequest.writerIsNotEqual(memberId)) {
            throw new BarkingCatException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        adoptRequest.cancel();
    }
}
