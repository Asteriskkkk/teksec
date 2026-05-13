package com.example.fitnessmembership;

import com.example.fitnessmembership.controller.MembershipController;
import com.example.fitnessmembership.entity.Membership;
import com.example.fitnessmembership.service.MembershipService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MembershipService membershipService;

    private Membership testMembership;

    @BeforeEach
    public void setUp() {
        testMembership = new Membership();
        testMembership.setPlanName("Silver Plan");
        testMembership.setMembershipType("Standard");
        testMembership.setMonthlyAccessHours(150);
        testMembership.setLaunchDate(LocalDate.of(2024, 1, 1));
        testMembership.setExpirationDate(LocalDate.of(2025, 1, 1));
        testMembership.setMonthlyFee(49.99);
        testMembership.setDietPlanOpted(false);
        testMembership.setBenefits("Gym access, yoga classes");
    }

    @Test
    public void testAddMembershipSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMembership)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.planName", equalTo("Silver Plan")))
                .andExpect(jsonPath("$.membershipType", equalTo("Standard")));
    }

    @Test
    public void testAddMembershipValidationError() throws Exception {
        testMembership.setPlanName(""); // Invalid: empty plan name
        
        mockMvc.perform(post("/api/v1/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMembership)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", equalTo(400)));
    }

    @Test
    public void testGetMembershipByIdSuccess() throws Exception {
        Membership savedMembership = membershipService.addMembership(testMembership);
        
        mockMvc.perform(get("/api/v1/memberships/{id}", savedMembership.getMembershipId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planName", equalTo("Silver Plan")));
    }

    @Test
    public void testGetMembershipByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/memberships/{id}", 999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo(404)));
    }

    @Test
    public void testGetAllMemberships() throws Exception {
        membershipService.addMembership(testMembership);
        
        mockMvc.perform(get("/api/v1/memberships"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void testUpdateMembershipSuccess() throws Exception {
        Membership savedMembership = membershipService.addMembership(testMembership);
        
        testMembership.setPlanName("Updated Silver Plan");
        testMembership.setMonthlyFee(59.99);
        
        mockMvc.perform(put("/api/v1/memberships/{id}", savedMembership.getMembershipId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMembership)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planName", equalTo("Updated Silver Plan")))
                .andExpect(jsonPath("$.monthlyFee", equalTo(59.99)));
    }

    @Test
    public void testDeleteMembershipSuccess() throws Exception {
        Membership savedMembership = membershipService.addMembership(testMembership);
        
        mockMvc.perform(delete("/api/v1/memberships/{id}", savedMembership.getMembershipId()))
                .andExpect(status().isNoContent());
    }

}
