package com.ApiBarco.service;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.entity.Member;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.MemberRepository;
import com.ApiBarco.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShipRepository shipRepository;

    public MemberDTO getMemberById(long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        return convertToDTO(member);
    }

    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertToDTO).toList();
    }

    public Member createMember(MemberDTO memberDTO) {
        List<Ship> ships = memberDTO.getShip_ids().stream()
                .map(shipId -> shipRepository.findById(shipId)
                        .orElseThrow(() -> new RuntimeException("Ship not found")))
                .collect(Collectors.toList());
        Member member = new Member(memberDTO.getId_member(), memberDTO.getName(), memberDTO.getLast_name(), memberDTO.is_master(), ships);
        memberRepository.save(member);
        return member;
    }

    private MemberDTO convertToDTO(Member member) {
        List<Long> shipIds = member.getShips().stream().map(Ship::getId_ship).collect(Collectors.toList());
        return new MemberDTO(member.getId_member(), member.getName(), member.getLast_name(), member.is_master(), shipIds);
    }
}
