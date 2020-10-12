package yegeeee.springtutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yegeeee.springtutorial.repository.MemberRepository;
import yegeeee.springtutorial.repository.MemoryMemberRepository;
import yegeeee.springtutorial.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
