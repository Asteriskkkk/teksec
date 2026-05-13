package com.example.fitnessmembership.service;

import com.example.fitnessmembership.dto.MembershipCountDTO;
import com.example.fitnessmembership.entity.Membership;
import com.example.fitnessmembership.exception.MembershipNotFoundException;
import com.example.fitnessmembership.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    /**
     * Add a new membership record to the database
     * @param membership the membership object to add
     * @return the saved membership
     */
    public Membership addMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    /**
     * View a membership by its ID
     * @param membershipId the ID of the membership to retrieve
     * @return the membership object
     * @throws MembershipNotFoundException if membership is not found
     */
    public Membership getMembershipById(Long membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new MembershipNotFoundException(
                        "Membership not found with ID: " + membershipId));
    }

    /**
     * Retrieve all memberships filtered by benefits
     * @param benefits the benefits to filter by
     * @return list of memberships with matching benefits
     */
    public List<Membership> getMembershipsByBenefits(String benefits) {
        return membershipRepository.findByBenefitsContainingIgnoreCase(benefits);
    }

    /**
     * View memberships based on membership type and monthly access hours
     * @param membershipType the membership type to filter by
     * @param monthlyAccessHours the minimum monthly access hours
     * @return list of memberships matching the criteria
     */
    public List<Membership> getMembershipsByTypeAndAccessHours(String membershipType, Integer monthlyAccessHours) {
        return membershipRepository.findByMembershipTypeAndMonthlyAccessHoursGreaterThan(
                membershipType, monthlyAccessHours);
    }

    /**
     * Get the total count of membership plans for each membership type
     * @return list of membership types with their counts
     */
    public List<MembershipCountDTO> getCountByMembershipType() {
        return membershipRepository.getCountByMembershipType();
    }

    /**
     * Get all memberships
     * @return list of all memberships
     */
    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    /**
     * Update an existing membership
     * @param membershipId the ID of the membership to update
     * @param membershipDetails the updated membership details
     * @return the updated membership
     * @throws MembershipNotFoundException if membership is not found
     */
    public Membership updateMembership(Long membershipId, Membership membershipDetails) {
        Membership membership = getMembershipById(membershipId);
        
        membership.setPlanName(membershipDetails.getPlanName());
        membership.setMembershipType(membershipDetails.getMembershipType());
        membership.setMonthlyAccessHours(membershipDetails.getMonthlyAccessHours());
        membership.setLaunchDate(membershipDetails.getLaunchDate());
        membership.setExpirationDate(membershipDetails.getExpirationDate());
        membership.setMonthlyFee(membershipDetails.getMonthlyFee());
        membership.setDietPlanOpted(membershipDetails.getDietPlanOpted());
        membership.setBenefits(membershipDetails.getBenefits());

        return membershipRepository.save(membership);
    }

    /**
     * Delete a membership by its ID
     * @param membershipId the ID of the membership to delete
     * @throws MembershipNotFoundException if membership is not found
     */
    public void deleteMembership(Long membershipId) {
        Membership membership = getMembershipById(membershipId);
        membershipRepository.delete(membership);
    }

}
