package spring.connector.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

/**
 * @author kushan
 * @date
 * @time
 */
@Service
class KafkaService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	void publish(String topicName, String message){
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
	}
}
