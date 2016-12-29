package org.manuel.teambuilting.statistics.team;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class TeamStatisticCommandService {

	private final TeamStatisticRepository teamStatisticRepository;

	@Inject
	public TeamStatisticCommandService(final TeamStatisticRepository teamStatisticRepository) {
		this.teamStatisticRepository = teamStatisticRepository;
	}

	public TeamStatistic updateTeamVisited(final String teamId) {
		teamStatisticRepository.updateTimesVisited(teamId);
		return teamStatisticRepository.findByTeamId(teamId);
	}
}
