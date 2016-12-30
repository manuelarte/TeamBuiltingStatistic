package org.manuel.teambuilting.statistics.team;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class TeamStatisticCommandService {

	private final TeamStatisticRepository teamStatisticRepository;
	private final TeamVisitsRepository teamVisitsRepository;

	@Inject
	public TeamStatisticCommandService(final TeamStatisticRepository teamStatisticRepository, final TeamVisitsRepository teamVisitsRepository) {
		this.teamStatisticRepository = teamStatisticRepository;
		this.teamVisitsRepository = teamVisitsRepository;
	}

	@Transactional
	public TeamStatistic updateTimesVisited(final String teamId) {
		teamStatisticRepository.updateTimesVisited(teamId);
		return teamStatisticRepository.findByTeamId(teamId);
	}

	public void updateTeamVisited(final TeamVisits teamVisits) {
		teamVisitsRepository.save(teamVisits);
	}
}
