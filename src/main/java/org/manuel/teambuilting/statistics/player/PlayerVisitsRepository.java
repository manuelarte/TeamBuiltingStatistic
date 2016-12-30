package org.manuel.teambuilting.statistics.player;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface PlayerVisitsRepository extends PagingAndSortingRepository<PlayerVisits, Long> {

    PlayerVisits findByPlayerId(String playerId);

}
