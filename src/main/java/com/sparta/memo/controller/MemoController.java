package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoReqeustDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoReqeustDto reqeustDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(reqeustDto);

        // Memo Max ID Check -> 중복이 되면 안되기 때문
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponsDto변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {

        // Map -> List 변환
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
        // stream -> for문과 같은 역할

        return responseList;
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoReqeustDto reqeustDto) {

        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {
            Memo memo = memoList.get(id);

            // memo 수정
            memo.update(reqeustDto);

            return memo.getId();

        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {

            // 해당 메모 삭제
            memoList.remove(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}