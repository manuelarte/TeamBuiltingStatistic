package org.manuel.teambuilting.statistics.player;

import org.manuel.teambuilting.statistics.PlayerIdDependent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface PlayerStatisticRepository extends PlayerIdDependent<PlayerStatistic>, PagingAndSortingRepository<PlayerStatistic, Long> {

    @Modifying
    @Query("update PlayerStatistic playerStatistic set playerStatistic.timesVisited = playerStatistic.timesVisited + 1 where playerStatistic.playerId = :playerId")
    int updateTimesVisited(@Param("playerId") String playerId);
}
