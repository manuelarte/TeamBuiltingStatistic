package org.manuel.teambuilting.statistics.team;

import org.manuel.teambuilting.statistics.TeamIdDependent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface TeamVisitsRepository extends TeamIdDependent<Set<TeamVisits>>, PagingAndSortingRepository<TeamVisits, Long> {

}
