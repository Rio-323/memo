package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoReqeustDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    public Memo(MemoReqeustDto reqeustDto) {
        this.username = reqeustDto.getUsername();
        this.contents = reqeustDto.getContents();
    }
}