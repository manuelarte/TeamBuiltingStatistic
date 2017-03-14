package org.manuel.teambuilting.statistics.listeners;

import java.util.Optional;

import javax.inject.Inject;

import org.manuel.teambuilting.statistics.messages.PlayerDeletedMessage;
import org.manuel.teambuilting.statistics.messages.PlayerVisitedMessage;
import org.manuel.teambuilting.statistics.player.PlayerStatisticCommandService;
import org.manuel.teambuilting.statistics.player.PlayerStatisticRepository;
import org.manuel.teambuilting.statistics.player.PlayerVisits;
import org.manuel.teambuilting.statistics.player.PlayerVisitsRepository;
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
		value = @Queue(durable = "true", value = "${messaging.amqp.player.queue.name}"),
		exchange = @Exchange(durable = "true", value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC),
		key = "${messaging.amqp.player.queue.binding}"))
@Component
public class PlayerListener {

	private final PlayerStatisticCommandService playerStatisticCommandService;
	private final PlayerStatisticRepository playerStatisticRepository;
	private final PlayerVisitsRepository playerVisitsRepository;

	@Inject
	public PlayerListener(final PlayerStatisticCommandService playerStatisticCommandService, final PlayerStatisticRepository playerStatisticRepository, final PlayerVisitsRepository playerVisitsRepository) {
		this.playerStatisticCommandService = playerStatisticCommandService;
		this.playerStatisticRepository = playerStatisticRepository;
		this.playerVisitsRepository = playerVisitsRepository;
	}

	@RabbitHandler
	public void handle(final PlayerDeletedMessage message) {
		playerStatisticRepository.delete(playerStatisticRepository.findByPlayerId(message.getPlayer().getId()));
		playerVisitsRepository.delete(playerVisitsRepository.findByPlayerId(message.getPlayer().getId()));
	}

	@RabbitHandler
	public void handle(final PlayerVisitedMessage message) {
		playerStatisticCommandService.updateTimesVisited(message.getPlayer().getId());
		if (Optional.ofNullable(message.getUserId()).isPresent()) {
			final PlayerVisits playerVisits = PlayerVisits.builder().userId(message.getUserId())
				.playerId(message.getPlayer().getId()).when(message.getDate()).build();
			playerStatisticCommandService.updatePlayerVisited(playerVisits);
		}
	}
}
