package com.example.multidatabase;

import com.example.multidatabase.service.TransactionTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("jta")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MultidatabaseTest {

    @Autowired
    private TransactionTestService transactionTestService;

    @Test
    void test() {
        transactionTestService.transactionTest();
    }

    @Test
    void test1() {
        transactionTestService.transactionTestFirstAndSecond();
    }

    @Test
    void test2() {
        transactionTestService.transactionTestSecondAndFirst();
    }
}
