package com.codewithRaj.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.codewithRaj.kafka.model.MatchEvent;
import com.codewithRaj.kafka.model.MatchScore;
import com.codewithRaj.kafka.model.PlayerScore;

@Component
public class CricketProducer {

//	private static final String TOPIC_NAME = "live-match-score-topic";

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public CricketProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMatchScore(MatchScore matchScore) {
		// Key = matchId (important for ordering)
		kafkaTemplate.send(CricketKafkaTopics.MATCH_SCORE, matchScore.getMatchId(), matchScore);
	}

	public void sendMatchEvent(MatchEvent matchEvent) {
		kafkaTemplate.send(CricketKafkaTopics.MATCH_EVENT, matchEvent.getMatchId(), matchEvent);
	}

	public void sendPlayerScore(PlayerScore playerScore) {
		kafkaTemplate.send(CricketKafkaTopics.PLAYER_SCORE, playerScore.getMatchId(), playerScore);
	}

}
