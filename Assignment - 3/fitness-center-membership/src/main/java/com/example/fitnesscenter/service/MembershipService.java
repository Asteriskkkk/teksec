package com.example.fitnesscenter.service;

import com.example.fitnesscenter.dto.MembershipRequest;
import com.example.fitnesscenter.dto.MembershipResponse;
import java.util.List;
import java.util.Map;

public interface MembershipService {

    MembershipResponse addMembership(MembershipRequest request);

    MembershipResponse getMembershipById(Long membershipId);

    List<MembershipResponse> getMembershipsByBenefits(String benefits);

    List<MembershipResponse> getMembershipsByTypeAndHours(String membershipType, Integer minimumHours);

    Map<String, Long> getMembershipPlanCountByType();
}
