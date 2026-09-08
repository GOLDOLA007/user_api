package com.api.user_api.controller;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

@WebMvcTest(AuthenticationControllerTests.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTests {

}
