package org.manuel.teambuilting.statistics.listeners;

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