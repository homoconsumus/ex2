package kr.ac.poly.ex2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_memo")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Memo {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY는 auto-incliment
    private int mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
