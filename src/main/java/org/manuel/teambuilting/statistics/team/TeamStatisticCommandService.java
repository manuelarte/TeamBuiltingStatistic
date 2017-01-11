package org.manuel.teambuilting.statistics.team;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
		final TeamStatistic teamStatistic = teamStatisticRepository.findByTeamId(teamId);
		if (teamStatistic == null) {
			teamStatisticRepository.save(new TeamStatistic(null, teamId, 1));
		} else {
			teamStatisticRepository.updateTimesVisited(teamId);
		}
		return teamStatisticRepository.findByTeamId(teamId);
	}

	public void deleteTimesVisited(final String teamId) {
		teamStatisticRepository.delete(teamStatisticRepository.findByTeamId(teamId));
		teamVisitsRepository.delete(teamVisitsRepository.findByTeamId(teamId));
	}

	public void updateTeamVisited(final TeamVisits teamVisits) {
		teamVisitsRepository.save(teamVisits);
	}
}
