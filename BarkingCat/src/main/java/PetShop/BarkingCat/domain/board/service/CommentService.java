package PetShop.BarkingCat.domain.board.service;

import PetShop.BarkingCat.domain.board.dto.CommentForm;
import PetShop.BarkingCat.domain.board.model.Comment;
import PetShop.BarkingCat.domain.board.repository.CommentRepository;
import PetShop.BarkingCat.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long registerComment(CommentForm commentForm, Long memberId){
        memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("등록된 사용자가 없습니다."));

        Comment comment = commentForm.entity()
                .mapWriter(memberId);

        return commentRepository.save(comment).id();
    }


}
