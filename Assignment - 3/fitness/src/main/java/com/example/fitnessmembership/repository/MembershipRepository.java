package com.example.fitnessmembership.repository;

import com.example.fitnessmembership.dto.MembershipCountDTO;
import com.example.fitnessmembership.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    /**
     * Find all memberships that contain the specified benefits
     * @param benefits the benefits to search for
     * @return list of memberships with matching benefits
     */
    List<Membership> findByBenefitsContainingIgnoreCase(String benefits);

    /**
     * Find memberships by membership type and with monthly access hours greater than the specified value
     * @param membershipType the membership type to filter by
     * @param monthlyAccessHours the minimum monthly access hours
     * @return list of matching memberships
     */
    List<Membership> findByMembershipTypeAndMonthlyAccessHoursGreaterThan(String membershipType, Integer monthlyAccessHours);

    /**
     * Get the count of membership plans for each membership type
     * @return list of membership type with their counts
     */
    @Query("SELECT new com.example.fitnessmembership.dto.MembershipCountDTO(m.membershipType, COUNT(m)) " +
           "FROM Membership m GROUP BY m.membershipType")
    List<MembershipCountDTO> getCountByMembershipType();

}
