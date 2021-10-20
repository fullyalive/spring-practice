package demo.demospring.service;

import demo.demospring.domain.Member;
import demo.demospring.repository.MemberRepository;
import demo.demospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 중복 이름 방지
        validateDuplicateName(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->  {
                    throw new IllegalStateException("이미 존재하는 이름입니다. 다른 이름을 입력해주세요.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
