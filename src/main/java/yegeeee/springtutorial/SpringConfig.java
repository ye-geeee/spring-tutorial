package yegeeee.springtutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yegeeee.springtutorial.repository.JdbcMemberRepository;
import yegeeee.springtutorial.repository.MemberRepository;
import yegeeee.springtutorial.repository.MemoryMemberRepository;
import yegeeee.springtutorial.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}
