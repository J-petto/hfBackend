package com.ll.hfback.domain.member.member.controller;

import com.ll.hfback.domain.member.member.dto.*;
import com.ll.hfback.domain.member.member.entity.Member;
import com.ll.hfback.domain.member.member.entity.Member.LoginType;
import com.ll.hfback.domain.member.member.service.MemberService;
import com.ll.hfback.domain.member.member.service.PasswordService;
import com.ll.hfback.domain.member.member.service.SocialConnectService;
import com.ll.hfback.global.exceptions.ErrorCode;
import com.ll.hfback.global.exceptions.ServiceException;
import com.ll.hfback.global.rsData.RsData;
import com.ll.hfback.global.webMvc.LoginUser;
import com.ll.hfback.standard.base.Empty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

    private final MemberService memberService;
    private final PasswordService passwordService;
    private final PasswordEncoder passwordEncoder;
    private final SocialConnectService socialConnectService;



    // [1. 회원 정보 관리]
    // MEM01_MODIFY01 : 회원정보 수정 전 비밀번호 인증
    @PostMapping("/me/password")
    public RsData<Void> checkPassword(
        @Valid @RequestBody CheckPasswordRequest request,
        @LoginUser Member loginUser
    ) {
        if (!passwordEncoder.matches(request.password(), loginUser.getPassword())) {
            throw new ServiceException(ErrorCode.INVALID_PASSWORD);
        }
        return new RsData<>("200-1", "비밀번호가 확인되었습니다.");
    }


    // MEM01_MODIFY02 - 비밀번호 변경
    @PatchMapping("/me/password")
    public RsData<Void> changePassword(
        @Valid @RequestBody ChangePasswordRequest request,
        @LoginUser Member loginUser
    ) {
        passwordService.changePassword(
            loginUser.getEmail(),
            request.currentPassword(),
            request.newPassword()
        );
        return new RsData<>("200-1", "비밀번호가 변경되었습니다.");
    }


    // MEM01_MODIFY03 : 회원정보 수정  (성별, 전화번호, 주소, 마케팅 수신여부 ...)
    @PutMapping("/me/profile")
    public RsData<MemberDto> updateMember(
        @LoginUser Member loginUser,
        @Valid @RequestBody MemberUpdateRequest memberUpdateRequest
    ) {
        Member modifiedMember = memberService.updateInfo(loginUser, memberUpdateRequest);
        return new RsData<>(
            "200",
            "회원 정보 업데이트가 성공하였습니다.",
            new MemberDto(modifiedMember)
        );
    }


    // MEM01_MODIFY04 : 전화번호 인증코드 발송 (SMS 인증)
    // @PostMapping("/me/phone/verification-code")

    // MEM01_MODIFY05 : 전화번호 인증코드 확인 (SMS 인증)
    // @PostMapping("/me/phone/verify")

    // MEM01_MODIFY06 : 주소 등록 (도로명 주소 찾기)
    //@PostMapping("/me/address")





    // [2. 프로필 이미지 관리]
    // MEM02_IMAGE01 : 프로필 이미지 업로드
    @PostMapping("/me/profile-image")
    public RsData<MemberUpdateResult> updateProfileImage(
        @LoginUser Member loginUser,
        @RequestParam("profileImage") MultipartFile profileImage
    ) {
        Member member = memberService.updateProfileImage(loginUser.getId(), profileImage);
        return new RsData<>(
            "200",
            "프로필 사진 변경이 성공하였습니다.",
            MemberUpdateResult.of(member)
        );
    }


    // MEM02_IMAGE01 : 프로필 이미지 초기화
    @DeleteMapping("/me/profile-image")
    public RsData<MemberUpdateResult> resetProfileImage(
        @LoginUser Member loginUser
    ) {
        Member member = memberService.resetToDefaultProfileImage(loginUser.getId());
        return new RsData<>(
            "200",
            "프로필 사진 초기화가 성공하였습니다.",
            MemberUpdateResult.of(member)
        );
    }




    // [3. 소셜 계정 연동 관리]
    // MEM03_SOCIAL01 : 소셜전용 계정 비번 추가
    @PostMapping("/me/social/password")
    public RsData<Void> addPasswordToSocialAccount(
        @Valid @RequestBody AddPasswordRequest request,
        @LoginUser Member loginUser
    ) {
        if (loginUser.getPassword() != null) {
            throw new ServiceException(ErrorCode.ALREADY_HAS_PASSWORD);
        }

        memberService.addPassword(loginUser.getId(), request.password());
        return new RsData<>("200", "소셜 계정에 비밀번호가 추가되었습니다.");
    }


    // MEM03_SOCIAL02 : 소셜 계정 연동 가능 여부 검증 후 로그인 유저 정보 저장 => 프론트에서 확인 후 처리
    @GetMapping("/me/social/{provider}/validate")
    public RsData<Void> validateSocialConnection(
        @PathVariable String provider, @LoginUser Member loginUser
    ) {
        String upperProvider = provider.toUpperCase();
        if (!LoginType.isValid(upperProvider)) {
            throw new ServiceException(ErrorCode.INVALID_LOGIN_TYPE);
        }

        if (loginUser.hasSocialAccount(upperProvider)) {   // 이미 해당 소셜 계정으로 연동된 경우
            throw new ServiceException(ErrorCode.ALREADY_CONNECTED_SOCIAL_ACCOUNT);
        }

        socialConnectService.storeOrigin(loginUser.getId());

        return new RsData<>("200", "%s 소셜 계정 연동이 가능합니다.".formatted(provider));
    }


    // MEM03_SOCIAL03 : 소셜 계정 연동 해제
    @DeleteMapping("/me/social/{provider}")
    public RsData<Void> disconnectSocialAccount(
        @PathVariable String provider, @LoginUser Member loginUser
    ) {
        String upperProvider = provider.toUpperCase();
        if (!LoginType.isValid(upperProvider)) {
            throw new ServiceException(ErrorCode.INVALID_LOGIN_TYPE);
        }

        if (!loginUser.hasSocialAccount(upperProvider)) {   // 연동되지 않은 소셜 계정인 경우
            throw new ServiceException(ErrorCode.NOT_CONNECTED_SOCIAL_ACCOUNT);
        }

        if (loginUser.getPassword() == null && loginUser.getConnectedSocialCount() == 1) {  // 마지막 로그인 수단인 경우
            throw new ServiceException(ErrorCode.CANNOT_DISCONNECT_LAST_LOGIN_METHOD);
        }

        memberService.disconnectSocialAccount(loginUser.getId(), upperProvider);
        return new RsData<>("200", "%s 소셜 계정 연동을 해제하였습니다.".formatted(upperProvider));
    }




    // [4. 회원 상태 관리]
    // MEM04_DELETE : 회원 탈퇴
    @PatchMapping("/me/deactivate")
    public RsData<Void> deactivateMember(@LoginUser Member loginUser) {
        memberService.deactivateMember(loginUser.getId());
        return new RsData<>("200", "회원 탈퇴가 성공하였습니다.");
    }



    // [5. 관리자 회원 관리]
    // ADMIN01_MEMBER01 : 회원 목록 (관리자)
    @GetMapping
    public List<MemberDto> getMembers() {
        List<Member> members = memberService.findAll();
        return members.stream().map(MemberDto::new).toList();
    }


    // ADMIN01_MEMBER02 : 회원 상세 조회 (관리자)
    @GetMapping("/{memberId}")
    public MemberDto getMember(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId).orElse(null);
        return new MemberDto(member);
    }


    // ADMIN01_MEMBER03 : 회원 탈퇴 복구 (관리자)
    @PatchMapping("/{memberId}/restore")
    public RsData<Void> restoreMember(@PathVariable Long memberId) {
        memberService.restoreMember(memberId);
        return new RsData<>("200", "회원 복구가 성공하였습니다.");
    }


    // ADMIN01_MEMBER04 : 회원 차단 처리 (관리자)
    @PatchMapping("/{memberId}/block")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RsData<Empty> banMember(@PathVariable Long memberId) {
        memberService.banMember(memberId);
        return new RsData("200-1", "%d번 회원을 차단했습니다.".formatted(memberId));
    }

}
