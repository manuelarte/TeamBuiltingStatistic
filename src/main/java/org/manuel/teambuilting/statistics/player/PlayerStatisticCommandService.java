package org.manuel.teambuilting.statistics.player;

import org.manuel.teambuilting.statistics.team.TeamStatistic;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class PlayerStatisticCommandService {

	private final PlayerStatisticRepository playerStatisticRepository;
	private final PlayerVisitsRepository playerVisitsRepository;

	@Inject
	public PlayerStatisticCommandService(final PlayerStatisticRepository playerStatisticRepository, final PlayerVisitsRepository playerVisitsRepository) {
		this.playerStatisticRepository = playerStatisticRepository;
		this.playerVisitsRepository = playerVisitsRepository;
	}

	@Transactional
	public PlayerStatistic updateTimesVisited(final String playerId) {
		final PlayerStatistic playerStatistic = playerStatisticRepository.findByPlayerId(playerId);
		if (playerStatistic == null) {
			playerStatisticRepository.save(new PlayerStatistic(null, playerId, 1));
		} else {
			playerStatisticRepository.updateTimesVisited(playerId);
		}
		return playerStatisticRepository.findByPlayerId(playerId);
	}

	public void updateTeamVisited(final PlayerVisits playerVisits) {
		playerVisitsRepository.save(playerVisits);
	}
}
