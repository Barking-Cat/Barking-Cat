package PetShop.BarkingCat.domain.member;

import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.member.service.MemberService;
import PetShop.BarkingCat.domain.member.service.query.MemberQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    public MemberController(MemberService memberService, MemberQueryService memberQueryService) {
        this.memberService = memberService;
        this.memberQueryService = memberQueryService;
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> findMyPage(@JwtClaim("info.id") Long memberId){ return ResponseEntity.ok(memberQueryService.findMyPage(memberId)); }

}
