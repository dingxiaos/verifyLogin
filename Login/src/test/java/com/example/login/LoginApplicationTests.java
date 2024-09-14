package com.example.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.login.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloWorldController.class)
public class LoginApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", password = "123456", roles = "USER") // 模拟已登录用户
    void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World")); // 验证返回内容
        System.out.println("Successful");
    }

    @Test
    void testHelloUnauthorized() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized()); // 验证未授权的访问
    }
}
