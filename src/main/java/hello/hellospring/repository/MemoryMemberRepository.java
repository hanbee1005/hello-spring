package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  // 자동으로 증가하는 ID
        store.put(member.getId(), member);  // store 에 {회원 ID : 회원 객체} 형태로 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // 회원 ID 를 통해 회원을 찾는데 Null 인 경우 따로 처리할 수 있도록 함
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store 에 저장된 값(회원 객체)들을 하나씩 돌면서 filter 로 주어진 이름과 member 의 이름이 같은 경우만 찾기
        // findAny() 는 찾는 값이 없는 경우 자동으로 Optional 타입을 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // 새로운 리스트를 생성하여 store 에 저장된 값(회원 객체)들을 그대로 반환
    }

    public void clearStore() {
        store.clear();
    }
}
