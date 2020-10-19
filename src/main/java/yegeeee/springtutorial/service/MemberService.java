package yegeeee.springtutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yegeeee.springtutorial.domain.Member;
import yegeeee.springtutorial.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memoryRepository;

    public MemberService(MemberRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);

            memoryRepository.save(member);
        } finally {
            long end = System.currentTimeMillis();
            long timems = end - start;
            System.out.println("join = " + timems + "ms");
        }
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
