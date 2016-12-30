package org.manuel.teambuilting.statistics.listeners;

import org.manuel.teambuilting.statistics.messages.TeamEventMessage;
import org.manuel.teambuilting.statistics.team.TeamStatisticCommandService;
import org.manuel.teambuilting.statistics.team.TeamVisits;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Configuration
public class TeamListener {

	private final TeamStatisticCommandService teamStatisticCommandService;

	@Inject
	public TeamListener(final TeamStatisticCommandService teamStatisticCommandService) {
		this.teamStatisticCommandService = teamStatisticCommandService;
	}

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(durable = "false", value="${messaging.event.amqp.queue}"),
		exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
		key = "${messaging.event.amqp.team-crud-routing-key}") )
	public void teamVisited(final @Payload TeamEventMessage message) {
		teamStatisticCommandService.updateTimesVisited(message.getTeam().getId());
		if (message.getUser_id() != null) {
			final TeamVisits teamVisits = new TeamVisits(null, message.getUser_id(), message.getTeam().getId(), message.getDate());
			teamStatisticCommandService.updateTeamVisited(teamVisits);
		}
	}
}
