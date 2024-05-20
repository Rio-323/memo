package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// @Component
// @Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc(); // -> 수정 시간을 기준으로 내림차순 해서 모든 정보를 가져오는 것
    List<Memo> findAllByUsername(String username);
}
