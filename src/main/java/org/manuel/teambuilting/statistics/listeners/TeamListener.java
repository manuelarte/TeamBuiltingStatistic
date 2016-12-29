package org.manuel.teambuilting.statistics.listeners;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author manuel.doncel.martos
 * @since 16-12-2016
 */
@Configuration
public class TeamListener {

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(durable = "false", value="${messaging.event.amqp.queue}"),
		exchange = @Exchange(durable = "true", value = "${messaging.event.amqp.exchange}", type = ExchangeTypes.TOPIC),
		key = "${messaging.event.amqp.team-crud-routing-key}") )
	public void teamVisited(final Message message) {
		System.out.println("Message received");
	}
}
