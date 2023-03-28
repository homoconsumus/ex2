package kr.ac.poly.ex2.repository;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }
}
