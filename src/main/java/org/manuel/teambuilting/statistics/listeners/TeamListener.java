package org.manuel.teambuilting.statistics.listeners;

import javax.inject.Inject;

import org.manuel.teambuilting.statistics.messages.TeamDeletedMessage;
import org.manuel.teambuilting.statistics.messages.TeamVisitedMessage;
import org.manuel.teambuilting.statistics.team.TeamStatisticCommandService;
import org.manuel.teambuilting.statistics.team.TeamVisits;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@RabbitListener(bindings = @QueueBinding(
	value = @Queue(durable = "true", value = "${messaging.event.amqp.queue}"),
	exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
	key = "team.#"))
@Configuration
public class TeamListener {

	private final TeamStatisticCommandService teamStatisticCommandService;

	@Inject
	public TeamListener(final TeamStatisticCommandService teamStatisticCommandService) {
		this.teamStatisticCommandService = teamStatisticCommandService;
	}

	@RabbitHandler
	public void teamVisited(final TeamVisitedMessage message) {
		teamStatisticCommandService.updateTimesVisited(message.getTeam().getId());
		if (message.getUserId() != null) {
			final TeamVisits teamVisits = new TeamVisits(null, message.getUserId(), message.getTeam().getId(), message.getDate());
			teamStatisticCommandService.updateTeamVisited(teamVisits);
		}
	}

	@RabbitHandler
	public void teamDeleted(final TeamDeletedMessage message) {
		teamStatisticCommandService.deleteTimesVisited(message.getTeam().getId());

	}
}
