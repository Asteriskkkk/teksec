package com.example.fitnesscenter.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MembershipRequest {

    @NotBlank(message = "Plan name is required")
    @Size(min = 3, max = 100, message = "Plan name must be between 3 and 100 characters")
    private String planName;

    @NotBlank(message = "Membership type is required")
    @Size(min = 3, max = 30, message = "Membership type must be between 3 and 30 characters")
    private String membershipType;

    @NotNull(message = "Monthly access hours is required")
    @Min(value = 1, message = "Monthly access hours must be at least 1")
    @Max(value = 744, message = "Monthly access hours cannot exceed 744")
    private Integer monthlyAccessHours;

    @NotNull(message = "Launch date is required")
    private LocalDate launchDate;

    @NotNull(message = "Expiration date is required")
    private LocalDate expirationDate;

    @NotNull(message = "Monthly fee is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly fee must be greater than 0")
    private BigDecimal monthlyFee;

    @NotBlank(message = "Benefits are required")
    @Size(min = 3, max = 255, message = "Benefits must be between 3 and 255 characters")
    private String benefits;

    @NotNull(message = "Diet plan opted flag is required")
    private Boolean dietPlanOpted;

    @AssertTrue(message = "Expiration date must be after launch date")
    public boolean isDateRangeValid() {
        if (launchDate == null || expirationDate == null) {
            return true;
        }
        return expirationDate.isAfter(launchDate);
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
