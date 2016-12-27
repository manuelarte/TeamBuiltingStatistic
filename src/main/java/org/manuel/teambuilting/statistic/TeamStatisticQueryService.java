package org.manuel.teambuilting.statistic;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	public void get(final Pageable page) {
		teamStatisticRepository.findAll(page);
	}
}
