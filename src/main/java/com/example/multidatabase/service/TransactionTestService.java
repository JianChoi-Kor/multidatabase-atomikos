package com.example.multidatabase.service;

import com.example.multidatabase.first.entity.Product;
import com.example.multidatabase.first.repository.ProductRepository;
import com.example.multidatabase.second.entity.User;
import com.example.multidatabase.second.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionTestService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public void transactionTest() {
        // firstDatabase
        Product product = Product.builder()
                .name("transactionTest")
                .code("transactionTest")
                .price(100L)
                .build();
        productRepository.save(product);

        // secondDatabase
        User user = User.builder()
                .email("transactionTest")
                .name("transactionTest")
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void transactionTestFirstAndSecond() {
        // secondDatabase
        User user = User.builder()
                .email("transactionTest")
                .name("transactionTest")
                .build();
        userRepository.save(user);

        // firstDatabase (exception 발생)
        Product product = Product.builder()
                .code("transactionTest")
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void transactionTestSecondAndFirst() {
        // firstDatabase
        Product product = Product.builder()
                .name("transactionTest")
                .code("transactionTest")
                .price(100L)
                .build();
        productRepository.save(product);

        // secondDatabase (exception 발생)
        User user = User.builder()
                .email("transactionTest")
                .build();
        userRepository.save(user);
    }
}
