package org.manuel.teambuilting.statistics.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class TeamStatisticQueryService {

	private final TeamStatisticRepository teamStatisticRepository;

	@Inject
	public TeamStatisticQueryService(final TeamStatisticRepository teamStatisticRepository) {
		this.teamStatisticRepository = teamStatisticRepository;
	}

	public Page<TeamStatistic> getTeamMostVisited(final Pageable pageable) {
		Assert.notNull(pageable);
		return teamStatisticRepository.findAll(pageable);
	}
}
