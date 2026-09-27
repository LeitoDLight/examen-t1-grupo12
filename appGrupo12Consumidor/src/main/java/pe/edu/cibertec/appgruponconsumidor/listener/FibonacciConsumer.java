package pe.edu.cibertec.appgruponconsumidor.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import pe.edu.cibertec.appgruponconsumidor.service.FibonacciService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FibonacciConsumer {

    private final FibonacciService fibonacciService;

    public FibonacciConsumer(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void recibirMensaje(String cadenaNumeros) {
        System.out.println("Mensaje recibido: " + cadenaNumeros);

        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        List<Integer> positions = Arrays.asList(integerArray);

        try {
            System.out.println("Esperando 20 segundos antes de calcular...");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Long> resultado = fibonacciService.calculateSequence(positions);
        System.out.println("Resultado Fibonacci: " + resultado);
    }
}