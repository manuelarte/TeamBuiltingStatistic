package org.manuel.teambuilting.statistics.player;

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
public class PlayerStatisticQueryService {

	private final PlayerStatisticRepository playerStatisticRepository;

	@Inject
	public PlayerStatisticQueryService(final PlayerStatisticRepository playerStatisticRepository) {
		this.playerStatisticRepository = playerStatisticRepository;
	}

	public Page<PlayerStatistic> getPlayerMostVisited(final Pageable pageable) {
		Assert.notNull(pageable);
		return playerStatisticRepository.findAll(pageable);
	}
}
