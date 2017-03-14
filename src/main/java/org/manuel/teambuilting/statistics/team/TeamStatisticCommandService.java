package org.manuel.teambuilting.statistics.team;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.manuel.teambuilting.statistics.StatisticService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class TeamStatisticCommandService implements StatisticService<TeamStatistic> {

	private final TeamStatisticRepository teamStatisticRepository;
	private final TeamVisitsRepository teamVisitsRepository;

	@Inject
	public TeamStatisticCommandService(final TeamStatisticRepository teamStatisticRepository, final TeamVisitsRepository teamVisitsRepository) {
		this.teamStatisticRepository = teamStatisticRepository;
		this.teamVisitsRepository = teamVisitsRepository;
	}

	@Override
	@Transactional
	public TeamStatistic updateTimesVisited(final String teamId) {
		Assert.hasLength(teamId);
		final Optional<TeamStatistic> teamStatistic = Optional.ofNullable(teamStatisticRepository.findByTeamId(teamId));
		if (!teamStatistic.isPresent()) {
			final TeamStatistic statistic = TeamStatistic.builder().teamId(teamId).timesVisited(1).build();
			teamStatisticRepository.save(statistic);
		} else {
			teamStatisticRepository.updateTimesVisited(teamId);
		}
		return teamStatisticRepository.findByTeamId(teamId);
	}

	public void deleteTimesVisited(final String teamId) {
		teamStatisticRepository.delete(teamStatisticRepository.findByTeamId(teamId));
		teamVisitsRepository.delete(teamVisitsRepository.findByTeamId(teamId));
	}

	public void save(final TeamVisits teamVisits) {
		teamVisitsRepository.save(teamVisits);
	}

}