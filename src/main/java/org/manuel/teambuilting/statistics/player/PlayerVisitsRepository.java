package org.manuel.teambuilting.statistics.player;

import org.manuel.teambuilting.statistics.PlayerIdDependent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface PlayerVisitsRepository extends PlayerIdDependent<Set<PlayerVisits>>, PagingAndSortingRepository<PlayerVisits, Long> {

}
