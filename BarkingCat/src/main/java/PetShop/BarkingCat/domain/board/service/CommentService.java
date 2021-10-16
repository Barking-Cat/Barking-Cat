package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.common.exception.BarkingCatException;
import PetShop.BarkingCat.common.exception.ErrorCode;
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
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.BOARD_NOT_FOUND));

        Comment comment = commentForm.entity()
                .mapWriter(memberId)
                .mapBoard(board);

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long memberId, Long commentId){

        memberRepository.findById(memberId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.COMMENT_NOT_FOUND));

        if (comment.writerIsNotEqual(memberId)) {
            throw new BarkingCatException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        comment.delete();
    }

    @Transactional
    public void updateComment(CommentForm commentForm, Long memberId, Long commentId){

        memberRepository.findById(memberId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.MEMBER_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BarkingCatException(ErrorCode.COMMENT_NOT_FOUND));

        if (comment.writerIsNotEqual(memberId)) {
            throw new BarkingCatException(ErrorCode.UNAUTHORIZED_MEMBER);
        }

        comment.updateContent(commentForm.getContent());

    }
}
