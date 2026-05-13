package com.example.fitnessmembership.controller;

import com.example.fitnessmembership.dto.MembershipCountDTO;
import com.example.fitnessmembership.entity.Membership;
import com.example.fitnessmembership.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/memberships")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    /**
     * Add a new membership
     * @param membership the membership object to add
     * @return ResponseEntity with the created membership and HTTP status CREATED (201)
     */
    @PostMapping
    public ResponseEntity<Membership> addMembership(@Valid @RequestBody Membership membership) {
        Membership createdMembership = membershipService.addMembership(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembership);
    }

    /**
     * Get a membership by its ID
     * @param membershipId the ID of the membership to retrieve
     * @return ResponseEntity with the membership and HTTP status OK (200)
     */
    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long membershipId) {
        Membership membership = membershipService.getMembershipById(membershipId);
        return ResponseEntity.ok(membership);
    }

    /**
     * Get all memberships
     * @return ResponseEntity with list of all memberships and HTTP status OK (200)
     */
    @GetMapping
    public ResponseEntity<List<Membership>> getAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        return ResponseEntity.ok(memberships);
    }

    /**
     * Get memberships filtered by benefits
     * @param benefits the benefits to filter by
     * @return ResponseEntity with list of memberships and HTTP status OK (200)
     */
    @GetMapping("/filter/benefits")
    public ResponseEntity<List<Membership>> getMembershipsByBenefits(@RequestParam String benefits) {
        List<Membership> memberships = membershipService.getMembershipsByBenefits(benefits);
        return ResponseEntity.ok(memberships);
    }

    /**
     * Get memberships by type and monthly access hours
     * @param membershipType the membership type to filter by
     * @param monthlyAccessHours the minimum monthly access hours
     * @return ResponseEntity with list of memberships and HTTP status OK (200)
     */
    @GetMapping("/filter/type-hours")
    public ResponseEntity<List<Membership>> getMembershipsByTypeAndAccessHours(
            @RequestParam String membershipType,
            @RequestParam Integer monthlyAccessHours) {
        List<Membership> memberships = membershipService.getMembershipsByTypeAndAccessHours(
                membershipType, monthlyAccessHours);
        return ResponseEntity.ok(memberships);
    }

    /**
     * Get count of memberships by membership type
     * @return ResponseEntity with list of membership counts and HTTP status OK (200)
     */
    @GetMapping("/count/by-type")
    public ResponseEntity<List<MembershipCountDTO>> getCountByMembershipType() {
        List<MembershipCountDTO> counts = membershipService.getCountByMembershipType();
        return ResponseEntity.ok(counts);
    }

    /**
     * Update an existing membership
     * @param membershipId the ID of the membership to update
     * @param membershipDetails the updated membership details
     * @return ResponseEntity with the updated membership and HTTP status OK (200)
     */
    @PutMapping("/{membershipId}")
    public ResponseEntity<Membership> updateMembership(
            @PathVariable Long membershipId,
            @Valid @RequestBody Membership membershipDetails) {
        Membership updatedMembership = membershipService.updateMembership(membershipId, membershipDetails);
        return ResponseEntity.ok(updatedMembership);
    }

    /**
     * Delete a membership by its ID
     * @param membershipId the ID of the membership to delete
     * @return ResponseEntity with HTTP status NO_CONTENT (204)
     */
    @DeleteMapping("/{membershipId}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long membershipId) {
        membershipService.deleteMembership(membershipId);
        return ResponseEntity.noContent().build();
    }

}
