package org.manuel.teambuilting.statistics.listeners;

import java.util.Optional;

import javax.inject.Inject;

import org.manuel.teambuilting.messages.TeamDeletedEvent;
import org.manuel.teambuilting.messages.TeamVisitedEvent;
import org.manuel.teambuilting.statistics.team.TeamStatisticCommandService;
import org.manuel.teambuilting.statistics.team.TeamVisits;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@RabbitListener(bindings = @QueueBinding(
	value = @Queue(value = "${messaging.amqp.team.queue.name}",
		durable = "${messaging.amqp.team.queue.durable}", autoDelete = "${messaging.amqp.team.queue.autodelete}"),
	exchange = @Exchange(value = "${messaging.amqp.team.exchange.name}", type = ExchangeTypes.TOPIC,
		durable = "${messaging.amqp.team.exchange.durable}", autoDelete = "${messaging.amqp.team.exchange.autodelete}"),
	key = "${messaging.amqp.team.queue.binding}"))
@Component
public class TeamListener {

	private final TeamStatisticCommandService teamStatisticCommandService;

	@Inject
	public TeamListener(final TeamStatisticCommandService teamStatisticCommandService) {
		this.teamStatisticCommandService = teamStatisticCommandService;
	}

	@RabbitHandler
	public void teamDeleted(final TeamDeletedEvent event) {
		teamStatisticCommandService.deleteTimesVisited(event.getTeamId());
	}

	@RabbitHandler
	public void teamVisited(final TeamVisitedEvent event) {
		teamStatisticCommandService.updateTimesVisited(event.getTeamId());
		if (Optional.ofNullable(event.getUserId()).isPresent()) {
			final TeamVisits teamVisits = TeamVisits.builder().userId(event.getUserId()).
				teamId(event.getTeamId()).when(event.getDate()).build();
			teamStatisticCommandService.save(teamVisits);
		}
	}

}
