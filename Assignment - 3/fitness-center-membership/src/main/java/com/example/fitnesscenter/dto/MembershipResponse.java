package com.example.fitnesscenter.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipResponse {

    private Long membershipId;
    private String planName;
    private String membershipType;
    private Integer monthlyAccessHours;
    private LocalDate launchDate;
    private LocalDate expirationDate;
    private BigDecimal monthlyFee;
    private String benefits;
    private Boolean dietPlanOpted;

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Integer getMonthlyAccessHours() {
        return monthlyAccessHours;
    }

    public void setMonthlyAccessHours(Integer monthlyAccessHours) {
        this.monthlyAccessHours = monthlyAccessHours;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public Boolean getDietPlanOpted() {
        return dietPlanOpted;
    }

    public void setDietPlanOpted(Boolean dietPlanOpted) {
        this.dietPlanOpted = dietPlanOpted;
    }
}
