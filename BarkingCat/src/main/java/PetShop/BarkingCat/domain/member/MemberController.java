package PetShop.BarkingCat.domain.member;

import PetShop.BarkingCat.common.security.annotations.JwtClaim;
import PetShop.BarkingCat.domain.member.dto.PasswordReset;
import PetShop.BarkingCat.domain.member.dto.PhoneAuthForPassword;
import PetShop.BarkingCat.domain.member.service.MemberService;
import PetShop.BarkingCat.domain.member.service.query.MemberQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> findMyPage(@JwtClaim("info.id") Long memberId) {
        return ResponseEntity.ok(memberQueryService.findMyPage(memberId));
    }

    @PostMapping("/password/send/auth")
    public ResponseEntity<?> sendAuthCode(@RequestBody PhoneAuthForPassword phoneAuthForPassword) {
        memberService.sendAuthNumber(phoneAuthForPassword);
        return ResponseEntity.ok()
                .build();
    }


    @PutMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordReset passwordReset) {
        memberService.resetPassword(passwordReset);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
