package kr.ac.poly.ex2.repository;

import jakarta.transaction.Transactional;
import kr.ac.poly.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // select 내림차순으로 mno가 ~인 것부터 ~인 것까지
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // select 내림차순으로 mno가 ~인 것부터 ~인 것까지2( 페이징 처리 추가 )
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    // delete mno가 조건에 해당하는 tuple삭제
    void deleteMemoByMnoLessThan(Long num);
    
    // @Query annotation을 사용하여 JPQL로 query문 실행
    // select문
    @Query("select m from Memo m order by m.mno desc ")
    List<Memo> getListDesc();
    
    // update문, update된 tuple의 수 반환
    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
    int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno}")
    int updateMemoText2(@Param("param") Memo memo);

    @Query(value = "select m from Memo m where m.mno > :mno",
        countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Memo> getListWithQuery(@Param("mno") Long mno, Pageable pageable);

    @Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno",
            countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

    @Query(value = "select * from tbl_memo where mno > 20", nativeQuery = true)
    List<Object[]> getNativeResult();
}
