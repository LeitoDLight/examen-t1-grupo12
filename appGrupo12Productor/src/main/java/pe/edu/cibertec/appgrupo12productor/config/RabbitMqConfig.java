package pe.edu.cibertec.appgrupo12productor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String QUEUE = "Grupo12Queue";
	public static final String EXCHANGE = "Grupo12Exchange";
	public static final String ROUTING_KEY = "Grupo12Routing";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

}
