package com.example.fitnessmembership;

import com.example.fitnessmembership.entity.Membership;
import com.example.fitnessmembership.repository.MembershipRepository;
import com.example.fitnessmembership.service.MembershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MembershipServiceTest {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipRepository membershipRepository;

    private Membership testMembership;

    @BeforeEach
    public void setUp() {
        membershipRepository.deleteAll();
        
        testMembership = new Membership();
        testMembership.setPlanName("Gold Plan");
        testMembership.setMembershipType("Premium");
        testMembership.setMonthlyAccessHours(300);
        testMembership.setLaunchDate(LocalDate.of(2024, 1, 1));
        testMembership.setExpirationDate(LocalDate.of(2025, 1, 1));
        testMembership.setMonthlyFee(99.99);
        testMembership.setDietPlanOpted(true);
        testMembership.setBenefits("Gym access, personal trainer, swimming pool");
    }

    @Test
    public void testAddMembership() {
        Membership savedMembership = membershipService.addMembership(testMembership);
        
        assertNotNull(savedMembership.getMembershipId());
        assertEquals("Gold Plan", savedMembership.getPlanName());
        assertEquals("Premium", savedMembership.getMembershipType());
    }

    @Test
    public void testGetMembershipById() {
        Membership savedMembership = membershipService.addMembership(testMembership);
        Membership retrievedMembership = membershipService.getMembershipById(savedMembership.getMembershipId());
        
        assertNotNull(retrievedMembership);
        assertEquals(savedMembership.getMembershipId(), retrievedMembership.getMembershipId());
    }

    @Test
    public void testGetMembershipsByBenefits() {
        membershipService.addMembership(testMembership);
        
        List<Membership> memberships = membershipService.getMembershipsByBenefits("personal trainer");
        
        assertFalse(memberships.isEmpty());
        assertTrue(memberships.stream().anyMatch(m -> m.getPlanName().equals("Gold Plan")));
    }

    @Test
    public void testGetMembershipsByTypeAndAccessHours() {
        membershipService.addMembership(testMembership);
        
        List<Membership> memberships = membershipService.getMembershipsByTypeAndAccessHours("Premium", 250);
        
        assertFalse(memberships.isEmpty());
        assertTrue(memberships.stream().allMatch(m -> m.getMonthlyAccessHours() > 250));
    }

    @Test
    public void testUpdateMembership() {
        Membership savedMembership = membershipService.addMembership(testMembership);
        
        savedMembership.setPlanName("Platinum Plan");
        savedMembership.setMonthlyFee(149.99);
        
        Membership updatedMembership = membershipService.updateMembership(
                savedMembership.getMembershipId(), savedMembership);
        
        assertEquals("Platinum Plan", updatedMembership.getPlanName());
        assertEquals(149.99, updatedMembership.getMonthlyFee());
    }

    @Test
    public void testDeleteMembership() {
        Membership savedMembership = membershipService.addMembership(testMembership);
        Long membershipId = savedMembership.getMembershipId();
        
        membershipService.deleteMembership(membershipId);
        
        assertThrows(Exception.class, () -> membershipService.getMembershipById(membershipId));
    }

}
