package com.example.fitnessmembership.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCountDTO {

    private String membershipType;
    private Long count;

}
