package org.manuel.teambuilting.statistics.listeners;

import java.util.Optional;

import javax.inject.Inject;

import org.manuel.teambuilting.messages.PlayerDeletedEvent;
import org.manuel.teambuilting.messages.PlayerVisitedEvent;
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
	value = @Queue(value = "${messaging.amqp.player.queue.name}",
		durable = "${messaging.amqp.player.queue.durable}", autoDelete = "${messaging.amqp.player.queue.autodelete}"),
	exchange = @Exchange(value = "${messaging.amqp.player.exchange.name}", type = ExchangeTypes.TOPIC,
		durable = "${messaging.amqp.player.exchange.durable}", autoDelete = "${messaging.amqp.player.exchange.autodelete}"),
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
	public void handle(final PlayerDeletedEvent event) {
		playerStatisticRepository.delete(playerStatisticRepository.findByPlayerId(event.getPlayerId()));
		playerVisitsRepository.delete(playerVisitsRepository.findByPlayerId(event.getPlayerId()));
	}

	@RabbitHandler
	public void handle(final PlayerVisitedEvent event) {
		playerStatisticCommandService.updateTimesVisited(event.getPlayerId());
		if (Optional.ofNullable(event.getUserId()).isPresent()) {
			final PlayerVisits playerVisits = PlayerVisits.builder().userId(event.getUserId())
				.playerId(event.getPlayerId()).when(event.getDate()).build();
			playerStatisticCommandService.updatePlayerVisited(playerVisits);
		}
	}
}
