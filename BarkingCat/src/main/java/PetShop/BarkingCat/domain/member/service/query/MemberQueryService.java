package PetShop.BarkingCat.domain.member.service.query;

import PetShop.BarkingCat.domain.member.dto.MyPageMemberResponse;
import PetShop.BarkingCat.domain.member.repository.query.MemberQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberQueryService(MemberQueryRepository memberQueryRepository) {
        this.memberQueryRepository = memberQueryRepository;
    }

    public MyPageMemberResponse findMyPage(Long memberId) {
        return memberQueryRepository.findMyPageById(memberId);
    }
}
