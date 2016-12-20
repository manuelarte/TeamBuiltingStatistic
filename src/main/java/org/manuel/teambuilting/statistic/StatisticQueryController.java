package org.manuel.teambuilting.statistic;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author manuel.doncel.martos
 * @since 20-12-2016
 */
@RestController
@RequestMapping("statistics")
public class StatisticQueryController {

	private final StatisticQueryService service;

	@Inject
	public StatisticQueryController(final StatisticQueryService service) {
		this.service = service;
	}

	@RequestMapping(value = "/myTeamsMostVisited", method = RequestMethod.GET)
	public Object myTeamsMostVisited() {
		return null;
	}

	@RequestMapping(value = "/teamsMostVisited", method = RequestMethod.GET)
	public Object teamsMostVisited() {
		return null;
	}
}
