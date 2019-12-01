package com.evaluation.demo.controller;

import com.evaluation.demo.dto.MemberDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.service.MemberServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberServiceImplementation memberServiceImplementation;

    @PostMapping(value = "/member/add")
    public ResponseDTO addNewMember(@RequestBody MemberDTO memberDTO) {
        return memberServiceImplementation.addNewMember(memberDTO);
    }

    ;

    @GetMapping(value = "/member/{memberID}")
    public MemberDTO getMember(@PathVariable int memberID) {
        return memberServiceImplementation.getMember(memberID);
    }

    ;



}
