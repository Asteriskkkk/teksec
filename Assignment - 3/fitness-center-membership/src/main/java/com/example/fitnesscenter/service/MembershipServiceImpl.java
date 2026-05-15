package com.example.fitnesscenter.service;

import com.example.fitnesscenter.dto.MembershipRequest;
import com.example.fitnesscenter.dto.MembershipResponse;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.fitnesscenter.entity.Membership;
import com.example.fitnesscenter.exception.MembershipNotFoundException;
import com.example.fitnesscenter.repository.MembershipRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public MembershipResponse addMembership(MembershipRequest request) {
        Membership membership = mapToEntity(request);
        Membership savedMembership = membershipRepository.save(membership);
        return mapToResponse(savedMembership);
    }

    @Override
    public MembershipResponse getMembershipById(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new MembershipNotFoundException(
                        "Membership not found for id: " + membershipId));
        return mapToResponse(membership);
    }

    @Override
    public List<MembershipResponse> getMembershipsByBenefits(String benefits) {
        return membershipRepository.findByBenefitsContainingIgnoreCase(benefits)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MembershipResponse> getMembershipsByTypeAndHours(String membershipType, Integer minimumHours) {
        return membershipRepository
                .findByMembershipTypeIgnoreCaseAndMonthlyAccessHoursGreaterThan(membershipType, minimumHours)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
        public Map<String, Long> getMembershipPlanCountByType() {
        return membershipRepository.countPlansByMembershipType()
            .stream()
            .collect(Collectors.toMap(
                item -> item.getMembershipType(),
                item -> item.getTotalPlans()
            ));
        }

    private Membership mapToEntity(MembershipRequest request) {
        Membership membership = new Membership();
        membership.setPlanName(request.getPlanName());
        membership.setMembershipType(request.getMembershipType());
        membership.setMonthlyAccessHours(request.getMonthlyAccessHours());
        membership.setLaunchDate(request.getLaunchDate());
        membership.setExpirationDate(request.getExpirationDate());
        membership.setMonthlyFee(request.getMonthlyFee());
        membership.setBenefits(request.getBenefits());
        membership.setDietPlanOpted(request.getDietPlanOpted());
        return membership;
    }

    private MembershipResponse mapToResponse(Membership membership) {
        MembershipResponse response = new MembershipResponse();
        response.setMembershipId(membership.getMembershipId());
        response.setPlanName(membership.getPlanName());
        response.setMembershipType(membership.getMembershipType());
        response.setMonthlyAccessHours(membership.getMonthlyAccessHours());
        response.setLaunchDate(membership.getLaunchDate());
        response.setExpirationDate(membership.getExpirationDate());
        response.setMonthlyFee(membership.getMonthlyFee());
        response.setBenefits(membership.getBenefits());
        response.setDietPlanOpted(membership.getDietPlanOpted());
        return response;
    }
}
