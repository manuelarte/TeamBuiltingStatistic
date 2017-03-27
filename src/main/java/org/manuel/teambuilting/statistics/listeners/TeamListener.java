package org.manuel.teambuilting.statistics.listeners;

import org.manuel.teambuilting.messages.TeamDeletedMessage;
import org.manuel.teambuilting.messages.TeamVisitedMessage;
import org.manuel.teambuilting.statistics.team.TeamStatisticCommandService;
import org.manuel.teambuilting.statistics.team.TeamVisits;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@RabbitListener(bindings = @QueueBinding(
	value = @Queue(durable = "true", value = "${messaging.amqp.team.queue.name}"),
	exchange = @Exchange(durable = "true", value = "${messaging.amqp.team.exchange.name}", type = ExchangeTypes.TOPIC),
	key = "${messaging.amqp.team.queue.binding}"))
@Component
public class TeamListener {

	private final TeamStatisticCommandService teamStatisticCommandService;

	@Inject
	public TeamListener(final TeamStatisticCommandService teamStatisticCommandService) {
		this.teamStatisticCommandService = teamStatisticCommandService;
	}

	@RabbitHandler
	public void teamDeleted(final TeamDeletedMessage message) {
		teamStatisticCommandService.deleteTimesVisited(message.getTeam().getId());
	}

	@RabbitHandler
	public void teamVisited(final TeamVisitedMessage message) {
		teamStatisticCommandService.updateTimesVisited(message.getTeam().getId());
		if (Optional.ofNullable(message.getUserId()).isPresent()) {
			final TeamVisits teamVisits = TeamVisits.builder().userId(message.getUserId()).
				teamId(message.getTeam().getId()).when(message.getDate()).build();
			teamStatisticCommandService.save(teamVisits);
		}
	}

}
