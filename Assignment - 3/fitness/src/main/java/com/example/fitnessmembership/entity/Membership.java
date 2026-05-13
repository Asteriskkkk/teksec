package com.example.fitnessmembership.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @NotNull(message = "Plan name cannot be null")
    @Size(min = 2, max = 100, message = "Plan name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String planName;

    @NotNull(message = "Membership type cannot be null")
    @Size(min = 2, max = 50, message = "Membership type must be between 2 and 50 characters")
    @Column(nullable = false)
    private String membershipType;

    @NotNull(message = "Monthly access hours cannot be null")
    @Min(value = 1, message = "Monthly access hours must be at least 1")
    @Max(value = 744, message = "Monthly access hours cannot exceed 744 (total hours in a month)")
    @Column(nullable = false)
    private Integer monthlyAccessHours;

    @NotNull(message = "Launch date cannot be null")
    @Column(nullable = false)
    private LocalDate launchDate;

    @NotNull(message = "Expiration date cannot be null")
    @Column(nullable = false)
    private LocalDate expirationDate;

    @NotNull(message = "Monthly fee cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly fee must be greater than 0")
    @Column(nullable = false)
    private Double monthlyFee;

    @NotNull(message = "Diet plan opted cannot be null")
    @Column(nullable = false)
    private Boolean dietPlanOpted;

    @NotNull(message = "Benefits cannot be null")
    @Size(min = 2, max = 500, message = "Benefits must be between 2 and 500 characters")
    @Column(nullable = false, length = 500)
    private String benefits;

}
