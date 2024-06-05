package com.ApiBarco.Controller;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.MemberService;
import com.ApiBarco.entity.Member;
import com.ApiBarco.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/socios")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable long id, @RequestParam(required = false) Long permitNumber) throws ClubNauticoNotFoundException {
        MemberDTO memberDTO = memberService.getMemberById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(@RequestParam(required = false) Long permitNumber) throws ClubNauticoNotFoundException {
        List<MemberDTO> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMembers() {
        memberService.deleteAllMembers();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable long id) throws ClubNauticoNotFoundException {
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }

    // Manejar las excepciones de las anotaciones @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Member member = optionalMember.get();
        member.setName(memberDetails.getName());
        member.setLast_name(memberDetails.getLast_name());
        member.set_master(memberDetails.is_master());
        member.setDockNumber(memberDetails.getDockNumber());
        member.setFee(memberDetails.getFee());
        member.setPermitNumber(memberDetails.getPermitNumber());
        member.setShips(memberDetails.getShips());

        Member updatedMember = memberRepository.save(member);
        return ResponseEntity.ok(updatedMember);
    }
}

