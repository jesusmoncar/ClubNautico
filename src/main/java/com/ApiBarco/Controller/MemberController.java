package com.ApiBarco.Controller;

import com.ApiBarco.DTO.MemberDTO;
import com.ApiBarco.Exeption.ClubNauticoNotFoundException;
import com.ApiBarco.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/socios")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable long id) throws ClubNauticoNotFoundException {
        MemberDTO memberDTO = memberService.getMemberById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() throws ClubNauticoNotFoundException {
        List<MemberDTO> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping()

    public ResponseEntity<Void> deleteAllMembers() {
        memberService.deleteAllMembers();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable long id) throws ClubNauticoNotFoundException {
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }
    //manejar las expeciones de las anotaciones @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }

}
