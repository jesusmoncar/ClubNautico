package com.ApiBarco.Service;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.entity.Member;
import com.ApiBarco.entity.Ship;
import com.ApiBarco.repository.MemberRepository;
import com.ApiBarco.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<MemberDTO> getAllMembers() throws ClubNauticoNotFoundException {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> convertToDTO(member)).collect(Collectors.toList());
    }

    public Member createMember(MemberDTO memberDTO) {
        List<Ship> ships = memberDTO.getShip_ids().stream()
                .map(shipId -> {
                    try {
                        return shipRepository.findById(shipId)
                                .orElseThrow(() -> new ClubNauticoNotFoundException("El barco con la id " + shipId + " no existe"));
                    } catch (ClubNauticoNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

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

    private MemberDTO convertToDTO(Member member) {
        List<Long> shipIds = member.getShips().stream().map(Ship::getId_ship).collect(Collectors.toList());
        List<String> shipRegistrations = member.getShips().stream().map(Ship::getRegistration_tag).collect(Collectors.toList());

        return new MemberDTO(member.getId_member(), member.getName(), member.getLast_name(), member.is_master(), member.getDockNumber(), member.getFee(), shipIds, shipRegistrations, member.getPermitNumber());
    }


    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member updateMember(Long id, Member memberDetails) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new RuntimeException("Member not found");
        }

        Member member = optionalMember.get();
        member.setName(memberDetails.getName());
        member.setLast_name(memberDetails.getLast_name());
        member.set_master(memberDetails.is_master());
        member.setDockNumber(memberDetails.getDockNumber());
        member.setFee(memberDetails.getFee());
        member.setPermitNumber(memberDetails.getPermitNumber());
        member.setShips(memberDetails.getShips());

        return memberRepository.save(member);
    }

}
