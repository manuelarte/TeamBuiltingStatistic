package org.manuel.teambuilting.statistics.player;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.manuel.teambuilting.statistics.StatisticService;
import org.springframework.stereotype.Service;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@Service
public class PlayerStatisticCommandService implements StatisticService<PlayerStatistic> {

	private final PlayerStatisticRepository playerStatisticRepository;
	private final PlayerVisitsRepository playerVisitsRepository;

	@Inject
	public PlayerStatisticCommandService(final PlayerStatisticRepository playerStatisticRepository, final PlayerVisitsRepository playerVisitsRepository) {
		this.playerStatisticRepository = playerStatisticRepository;
		this.playerVisitsRepository = playerVisitsRepository;
	}

	@Override
	@Transactional
	public PlayerStatistic updateTimesVisited(final String playerId) {
		final Optional<PlayerStatistic> playerStatistic = Optional.ofNullable(playerStatisticRepository.findByPlayerId(playerId));
		if (!playerStatistic.isPresent()) {
			playerStatisticRepository.save(new PlayerStatistic(null, playerId, 1));
		} else {
			playerStatisticRepository.updateTimesVisited(playerId);
		}
		return playerStatisticRepository.findByPlayerId(playerId);
	}

	public void updatePlayerVisited(final PlayerVisits playerVisits) {
		playerVisitsRepository.save(playerVisits);
	}
}
