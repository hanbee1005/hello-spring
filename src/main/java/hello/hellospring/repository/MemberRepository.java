package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  // 회원 저장 후 저장된 회원 반환
    Optional<Member> findById(Long id);  // 회원 ID 로 찾기 (Optional 은 Java8 에 추가된 것으로 반환 값이 null 인 경우를 처리)
    Optional<Member> findByName(String name);  // 회원 이름으로 찾기
    List<Member> findAll();  // 전체 회원 조회
}
