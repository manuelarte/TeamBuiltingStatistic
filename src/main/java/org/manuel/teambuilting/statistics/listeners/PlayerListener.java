package org.manuel.teambuilting.statistics.listeners;

import org.manuel.teambuilting.statistics.messages.PlayerDeletedMessage;
import org.manuel.teambuilting.statistics.messages.PlayerEventMessage;
import org.manuel.teambuilting.statistics.player.PlayerStatisticCommandService;
import org.manuel.teambuilting.statistics.player.PlayerVisits;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import javax.inject.Inject;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@RabbitListener(bindings = @QueueBinding(
		value = @Queue(durable = "true", value = "${messaging.event.amqp.queue}"),
		exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
		key = "player.#"))
@Configuration
public class PlayerListener {

	private final PlayerStatisticCommandService playerStatisticCommandService;

	@Inject
	public PlayerListener(final PlayerStatisticCommandService playerStatisticCommandService) {
		this.playerStatisticCommandService = playerStatisticCommandService;
	}

	public void playerVisited(@Payload final PlayerEventMessage message) {
		playerStatisticCommandService.updateTimesVisited(message.getPlayer().getId());
		if (message.getUser_id() != null) {
			final PlayerVisits playerVisits = new PlayerVisits(null, message.getUser_id(), message.getPlayer().getId(), message.getDate());
			playerStatisticCommandService.updateTeamVisited(playerVisits);
		}
	}

	@RabbitHandler
	public void handle(final PlayerDeletedMessage message) {
		System.out.println(message);
		// apply subscriber to event patter, and delete all the player statistics
	}
}
