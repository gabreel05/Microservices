package br.com.market.demands.consumer;

import br.com.market.demands.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @RabbitListener(queues = "${queue.name}")
    public void receive(@Payload String message) {
        User user = objectMapper.readValue(message, User.class);


    }
}
