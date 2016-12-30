package org.manuel.teambuilting.statistics.listeners;

import org.manuel.teambuilting.statistics.messages.PlayerEventMessage;
import org.manuel.teambuilting.statistics.player.PlayerStatisticCommandService;
import org.manuel.teambuilting.statistics.player.PlayerVisits;
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
public class PlayerListener {

	private final PlayerStatisticCommandService playerStatisticCommandService;

	@Inject
	public PlayerListener(final PlayerStatisticCommandService playerStatisticCommandService) {
		this.playerStatisticCommandService = playerStatisticCommandService;
	}

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(durable = "false", value="${messaging.event.amqp.queue}"),
		exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
		key = "${messaging.event.amqp.team-crud-routing-key}") )
	public void playerVisited(final @Payload PlayerEventMessage message) {
		playerStatisticCommandService.updateTimesVisited(message.getPlayer().getId());
		if (message.getUser() != null) {
			final PlayerVisits playerVisits = new PlayerVisits(null, message.getUser().getUser_id(), message.getPlayer().getId(), message.getDate());
			playerStatisticCommandService.updateTeamVisited(playerVisits);
		}
	}
}
