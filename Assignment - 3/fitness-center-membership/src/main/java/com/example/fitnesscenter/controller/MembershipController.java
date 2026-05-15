package com.example.fitnesscenter.controller;

import com.example.fitnesscenter.dto.MembershipRequest;
import com.example.fitnesscenter.dto.MembershipResponse;
import java.util.Map;
import com.example.fitnesscenter.service.MembershipService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberships")
@Validated
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public ResponseEntity<MembershipResponse> addMembership(@Valid @RequestBody MembershipRequest request) {
        return new ResponseEntity<>(membershipService.addMembership(request), HttpStatus.CREATED);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<MembershipResponse> getMembershipById(@PathVariable Long membershipId) {
        return ResponseEntity.ok(membershipService.getMembershipById(membershipId));
    }

    @GetMapping("/benefits")
    public ResponseEntity<List<MembershipResponse>> getMembershipsByBenefits(
            @RequestParam @NotBlank(message = "Benefit is required") String benefit) {
        return ResponseEntity.ok(membershipService.getMembershipsByBenefits(benefit));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MembershipResponse>> getMembershipsByTypeAndHours(
            @RequestParam @NotBlank(message = "Membership type is required") String membershipType,
            @RequestParam @Min(value = 0, message = "Minimum hours cannot be negative") Integer minHours) {
        return ResponseEntity.ok(membershipService.getMembershipsByTypeAndHours(membershipType, minHours));
    }

    @GetMapping("/count-by-type")
    public ResponseEntity<Map<String, Long>> getMembershipCountByType() {
        return ResponseEntity.ok(membershipService.getMembershipPlanCountByType());
    }
}
