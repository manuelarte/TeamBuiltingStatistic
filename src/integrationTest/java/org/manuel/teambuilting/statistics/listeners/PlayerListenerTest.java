package org.manuel.teambuilting.statistics.listeners;

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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@RabbitListenerTest(capture = true)
public class PlayerListenerTest {

	@Value("${messaging.amqp.player.exchange.name}")
	private String playerExchange;

	@Inject
	private PlayerStatisticRepository playerStatisticRepository;

	@Inject
	private PlayerVisitsRepository playerVisitsRepository;

	@Test
	public void playerVisitedTest() {
		final Player player = Player.builder().build();
	}

}
