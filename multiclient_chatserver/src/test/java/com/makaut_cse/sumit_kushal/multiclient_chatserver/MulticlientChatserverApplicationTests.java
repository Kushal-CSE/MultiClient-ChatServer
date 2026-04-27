package com.makaut_cse.sumit_kushal.multiclient_chatserver;

import com.makaut_cse.sumit_kushal.multiclient_chatserver.entity.User;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MulticlientChatserverApplicationTests {

    @Autowired
    private JwtService  jwtService;

	@Test
	void contextLoads() {

        User user = new User(41L, "kushal@gmail.com", "1234", "kushal");

        String token = jwtService.generateToken(user);

        System.out.println(token);

        Long id = jwtService.getUserIdFromToken(token);

        System.out.println(id);

	}

}
