package com.ApiBarco.Service;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Member;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.MemberRepository;
import com.ApiBarco.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShipRepository shipRepository;

    public MemberDTO getMemberById(long id) throws ClubNauticoNotFoundException {
        Optional<Member> memberOpt = memberRepository.findById(id);
        if (!memberOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El socio con la id " + id + " no existe");
        }
        Member member = memberOpt.get();
        return convertToDTO(member);
    }

    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Member createMember(MemberDTO memberDTO) {
        List<Ship> ships = memberDTO.getShip_ids() != null ? memberDTO.getShip_ids().stream()
                .map(shipId -> {
                    try {
                        return shipRepository.findById(shipId)
                                .orElseThrow(() -> new ClubNauticoNotFoundException("El barco con la id " + shipId + " no existe"));
                    } catch (ClubNauticoNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()) : Collections.emptyList();

        Member member = new Member(memberDTO.getId_member(), memberDTO.getName(), memberDTO.getLast_name(), memberDTO.is_master(), memberDTO.getDockNumber(), memberDTO.getFee(), ships, memberDTO.getPermitNumber());
        ships.forEach(ship -> ship.setMember(member));

        return memberRepository.save(member);
    }

    public void deleteAllMembers() {
        memberRepository.deleteAll();
    }

    public void deleteMemberById(long memberId) throws ClubNauticoNotFoundException {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (!memberOpt.isPresent()) {
            throw new ClubNauticoNotFoundException("El socio con la id " + memberId + " no existe");
        }
        memberRepository.deleteById(memberId);
    }

    public MemberDTO updateMember(Long id, MemberDTO memberDTO) throws ClubNauticoNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new ClubNauticoNotFoundException("El socio con la id " + id + " no existe");
        }

        Member member = optionalMember.get();
        member.setName(memberDTO.getName());
        member.setLast_name(memberDTO.getLast_name());
        member.set_master(memberDTO.is_master());
        System.out.println(memberDTO.is_master());
        member.setDockNumber(memberDTO.getDockNumber());
        member.setFee(memberDTO.getFee());
        member.setPermitNumber(memberDTO.getPermitNumber());



        memberRepository.save(member);
        return convertToDTO(member);
    }

    private MemberDTO convertToDTO(Member member) {
        List<Long> shipIds = member.getShips() != null ? member.getShips().stream().map(Ship::getId_ship).collect(Collectors.toList()) : Collections.emptyList();
        List<String> shipRegistrations = member.getShips() != null ? member.getShips().stream().map(Ship::getRegistration_tag).collect(Collectors.toList()) : Collections.emptyList();
        return new MemberDTO(member.getId_member(), member.getName(), member.getLast_name(), member.is_master(), member.getDockNumber(), member.getFee(), shipIds, shipRegistrations, member.getPermitNumber());
    }
}
