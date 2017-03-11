package org.manuel.teambuilting.statistics.team;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@RestController
@RequestMapping("/statistics/teams")
public class TeamStatisticQueryController {

	private final TeamStatisticQueryService service;

	@Inject
	public TeamStatisticQueryController(final TeamStatisticQueryService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<TeamStatistic> teamsMostVisited(@PageableDefault(page = 0, size = 20, sort={"timesVisited"}, direction = Sort.Direction.DESC) final Pageable pageable) {

		return service.getTeamMostVisited(pageable);
	}
}
