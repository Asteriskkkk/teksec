package com.example.fitnesscenter.repository;

import com.example.fitnesscenter.entity.Membership;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByBenefitsContainingIgnoreCase(String benefits);

    List<Membership> findByMembershipTypeIgnoreCaseAndMonthlyAccessHoursGreaterThan(String membershipType,
                                                                                     Integer monthlyAccessHours);

    @Query("SELECT m.membershipType AS membershipType, COUNT(m) AS totalPlans " +
            "FROM Membership m GROUP BY m.membershipType")
    List<MembershipTypeCountProjection> countPlansByMembershipType();
}
