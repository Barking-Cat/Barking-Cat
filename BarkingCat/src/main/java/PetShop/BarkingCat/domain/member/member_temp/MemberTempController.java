package PetShop.BarkingCat.domain.member.member_temp;

import PetShop.BarkingCat.domain.member.member_temp.dto.Certify;
import PetShop.BarkingCat.domain.member.member_temp.dto.MemberForm;
import PetShop.BarkingCat.domain.member.member_temp.dto.Phone;
import PetShop.BarkingCat.domain.member.member_temp.service.MemberTempService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberTempController {

    private final MemberTempService memberTempService;

    public MemberTempController(MemberTempService memberTempService) {
        this.memberTempService = memberTempService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMember(@RequestBody MemberForm memberForm) {
        memberTempService.join(memberForm);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/send/auth")
    public ResponseEntity<?> sendAuthNumber(@RequestBody Phone phone) {
        memberTempService.sendAuthNumber(phone);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/certify/auth")
    public ResponseEntity<?> certifyAuthNumber(@RequestBody Certify certify) {
        memberTempService.certifyAuthNumber(certify);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authMember(@RequestParam Long memberTempId) {
        memberTempService.auth(memberTempId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
