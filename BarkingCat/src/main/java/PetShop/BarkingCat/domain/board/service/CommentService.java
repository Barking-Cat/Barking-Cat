package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.CommentForm;
import PetShop.BarkingCat.domain.board.model.Board;
import PetShop.BarkingCat.domain.board.model.Comment;
import PetShop.BarkingCat.domain.board.repository.BoardRepository;
import PetShop.BarkingCat.domain.board.repository.CommentRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void registerComment(CommentForm commentForm, Long memberId, Long boardId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("없거나 삭제된 게시물 입니다."));

        Comment comment = commentForm.entity()
                .mapWriter(memberId)
                .mapBoard(board);

        commentRepository.save(comment);
    }


}
