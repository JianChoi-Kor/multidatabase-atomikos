package com.example.multidatabase;

import com.example.multidatabase.config.FirstDatasourceConfig;
import com.example.multidatabase.config.SecondDatasourceConfig;
import com.example.multidatabase.first.entity.Product;
import com.example.multidatabase.first.repository.ProductRepository;
import com.example.multidatabase.second.entity.User;
import com.example.multidatabase.second.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({FirstDatasourceConfig.class, SecondDatasourceConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MultiDatasourceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void multidatabaseTest() {
        List<Product> productList = productRepository.findAll();
        productList.forEach(System.out::println);

        List<User> userList = userRepository.findAll();
        userList.forEach(System.out::println);
    }
}
