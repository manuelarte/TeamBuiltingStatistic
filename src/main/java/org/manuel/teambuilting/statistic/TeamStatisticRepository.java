package org.manuel.teambuilting.statistic;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author manuel.doncel.martos
 * @since 27-12-2016
 */
@Repository
public interface TeamStatisticRepository extends PagingAndSortingRepository<TeamStatistic, Long> {
}
