package org.manuel.teambuilting.statistic.team;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@RestController
@RequestMapping("statistics/team")
public class TeamStatisticQueryController {

	private final TeamStatisticQueryService service;

	@Inject
	public TeamStatisticQueryController(final TeamStatisticQueryService service) {
		this.service = service;
	}

	@RequestMapping(value = "/myMostVisited", method = RequestMethod.GET)
	public Object myTeamsMostVisited() {
		return null;
	}

	@RequestMapping(value = "/mostVisited", method = RequestMethod.GET)
	public Object teamsMostVisited() {
		return null;
	}
}
