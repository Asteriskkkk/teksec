package com.example.fitnesscenter.dto;

public class MembershipTypeCountResponse {

    private String membershipType;
    private Long totalPlans;

    public MembershipTypeCountResponse() {
    }

    public MembershipTypeCountResponse(String membershipType, Long totalPlans) {
        this.membershipType = membershipType;
        this.totalPlans = totalPlans;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Long getTotalPlans() {
        return totalPlans;
    }

    public void setTotalPlans(Long totalPlans) {
        this.totalPlans = totalPlans;
    }
}
