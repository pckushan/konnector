package spring.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kushan
 * @date
 * @time
 */
@RestController
public class KafkaController {
@Autowired
private KafkaService kafkaService;

@PostMapping("/publish")
public void sendMessage(@RequestBody Topic topic) {
	
	String message = topic.getMessage();
	String topicName = topic.getTopic();
	
	kafkaService.publish(topicName, message);
}
}
