package PetShop.BarkingCat.domain.member;

import PetShop.BarkingCat.domain.member.dto.MemberForm;
import PetShop.BarkingCat.domain.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody MemberForm memberForm) {
        memberService.joinMember(memberForm);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
