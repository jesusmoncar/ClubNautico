package com.ApiBarco.controller;

import com.ApiBarco.DTO.MemberDTO;

import com.ApiBarco.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public MemberDTO getMemberById( @PathVariable long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public void createMember(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }
}
