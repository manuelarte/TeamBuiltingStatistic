package org.manuel.teambuilting.statistics.listeners;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.statistics.player.PlayerStatisticRepository;
import org.manuel.teambuilting.statistics.player.PlayerVisitsRepository;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	@Ignore
	public void playerVisitedTest() {

	}

}