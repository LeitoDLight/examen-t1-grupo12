package pe.edu.cibertec.appgrupo12productor.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.appgrupo12productor.config.RabbitMqConfig;

@RestController
public class ProductorController {

	private final RabbitTemplate rabbitTemplate;

	public ProductorController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@GetMapping("/api/numeros")
	public String enviar(@RequestParam String numbers) {
		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, numbers);
		return "Lista enviada a RabbitMQ correctamente.";
	}

}
