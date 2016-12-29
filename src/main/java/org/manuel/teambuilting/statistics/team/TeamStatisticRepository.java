package org.manuel.teambuilting.statistics.team;

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
public interface TeamStatisticRepository extends PagingAndSortingRepository<TeamStatistic, Long> {

    TeamStatistic findByTeamId(String teamId);

    @Modifying
    @Query("update team_statistic team set team.times_visited = team.times_visited + 1 where team.id = :id")
    int updateTimesVisited(@Param("id") String teamId);
}
