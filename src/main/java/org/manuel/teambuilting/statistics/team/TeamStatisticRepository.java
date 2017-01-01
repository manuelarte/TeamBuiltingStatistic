package org.manuel.teambuilting.statistics.team;

import org.manuel.teambuilting.statistics.TeamIdDependent;
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
public interface TeamStatisticRepository extends TeamIdDependent<TeamStatistic>, PagingAndSortingRepository<TeamStatistic, Long> {

    @Modifying
    @Query("update TeamStatistic teamStatistic set teamStatistic.timesVisited = teamStatistic.timesVisited + 1 where teamStatistic.teamId = :teamId")
    int updateTimesVisited(@Param("teamId") String teamId);
}
