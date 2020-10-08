package yegeeee.springtutorial.service;

import yegeeee.springtutorial.domain.Member;
import yegeeee.springtutorial.repository.MemberRepository;
import yegeeee.springtutorial.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memoryRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memoryRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memoryRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memoryRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memoryRepository.findById(memberId);
    }
}
