package cteam.cteamproject.web.member.controller;

import cteam.cteamproject.domain.member.Member;
import cteam.cteamproject.domain.member.memberservice.MemberService;
import cteam.cteamproject.domain.memberproject.relationservice.RelationService;
import cteam.cteamproject.domain.project.Project;
import cteam.cteamproject.web.Success;
import cteam.cteamproject.web.member.form.MemberJoinForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final RelationService relationService;

    @GetMapping
    public List<Member> members() {
        return memberService.findMembers();
    }

    @GetMapping("/my-info")
    public Member myInfo(@SessionAttribute(name = "loginMember") Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("/{memberId}")
    public Member member(@PathVariable Long memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("/joining-projects/my")
    public List<Project> getMyJoiningProjects(@SessionAttribute(name = "loginMember") Long memberId) {
        return relationService.findJoiningProjects(memberId);
    }

    @GetMapping("joining-projects/{memberId}")
    public List<Project> getJoiningProjects(@PathVariable Long memberId) {
        return relationService.findJoiningProjects(memberId);
    }

    @PostMapping("/join") //MemberJoinFormTest 상의 후에 바꾸기
    public Success join(@Validated @ModelAttribute MemberJoinForm memberForm) {

        Member member = new Member();
        member.setLoginId(memberForm.getLoginId());
        member.setPw(memberForm.getPw());
        member.setName(memberForm.getName());
        member.setPosition(memberForm.getPosition());
        member.setTechList(memberForm.getTechList());

        log.info("회원가입 성공 [{}]", member.getName());
        memberService.join(member);

        return new Success(true);
    }

    //데이터 수정, 일단 빼기로 함
//    @PostMapping("/edit")
//    public void edit(@SessionAttribute(name = "loginMember") Long memberId,
//                     @RequestBody Member updateParam) {
//        Member member = memberService.findById(memberId);
//        memberService.updateMember(member, updateParam);
//    }

    @PostMapping("/add-project/{projectId}") //ProjectFormTest 테스트하려고 만든것이다 나중에 Project id로 바꾸든 Project 통으로 받든 변경 -> 이건 지금 테스트에서 잘 돌아간다
    public void addProject(@SessionAttribute(name = "loginMember") Long memberId, @PathVariable Long projectId) {
        relationService.addRelation(memberId, projectId);
    }

    @PostMapping("/remove-project/{projectId}")//이건 같은 Project 객체를 테스트에선 받아올수없어서 안돌아간다. 나중에 프로젝트 아이디로 받든뭐든 해야된다.
    public void removeProject(@SessionAttribute(name = "loginMember") Long memberId, @PathVariable Long projectId) {
        relationService.removeRelation(memberId, projectId);
    }
}
