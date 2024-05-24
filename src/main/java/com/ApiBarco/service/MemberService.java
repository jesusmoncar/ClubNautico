package com.ApiBarco.service;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.entity.Member;
import com.ApiBarco.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO getMemberById(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return convertToDTO(member);
    }

    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void createMember( MemberDTO memberDTO) {
        Member member = new Member(memberDTO.getId_member(), memberDTO.getName(), memberDTO.getLast_name(), memberDTO.is_master(), memberDTO.getId_ship());
        memberRepository.save(member);
    }

    private MemberDTO convertToDTO(Member member) {
        return new MemberDTO(member.getId_member(), member.getName(), member.getLast_name(), member.is_master(), member.getId_ship());
    }
}
