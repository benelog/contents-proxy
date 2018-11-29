package com.naver.campushackday.contentsproxyblog.presentation;

import com.naver.campushackday.contentsproxyblog.entity.Account;
import com.naver.campushackday.contentsproxyblog.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("")
    public String showSignUpPage() {
        return "/account/registerForm";
    }

    @PostMapping("")
    public String signUp(@ModelAttribute Account account){
        service.register(account);
        return "redirect:/";
    }

}
