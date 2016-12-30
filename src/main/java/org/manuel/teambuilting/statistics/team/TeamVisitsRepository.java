package org.manuel.teambuilting.statistics.team;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface TeamVisitsRepository extends PagingAndSortingRepository<TeamVisits, Long> {

    TeamVisits findByTeamId(String teamId);

}
