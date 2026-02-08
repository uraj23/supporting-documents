package com.codewithRaj.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.codewithRaj.kafka.model.MatchEvent;
import com.codewithRaj.kafka.model.MatchScore;
import com.codewithRaj.kafka.model.PlayerScore;

@Service
public class MatchScoreHotstarService {

	@KafkaListener(topics = "live-match-scores-topic", groupId = "hotstar-consumer-group")
	public void consumerMatchScore(MatchScore matchScore) {
		System.out.println("HOTSTAR CONSUMER :: MATCH-SCORE");
		System.out.println("MATCH_ID : " + matchScore.getMatchId());
		System.out.println("SCORE    : " + matchScore.getRuns());
		System.out.println("WICKETS  : " + matchScore.getWickets());
		System.out.println("TARGET  : " + matchScore.getTarget());
		System.out.println("STATUS  : " + matchScore.getMatchStatus());
		System.out.println("TIME    : " + matchScore.getTimestamp());
	}

	@KafkaListener(topics = "match-events-topic", groupId = "hotstar-consumer-group")
	public void consumerMatchEvent(MatchEvent matchEvent) {
		System.out.println("HOTSTAR CONSUMER :: MATCH-EVENT");
		System.out.println("EVENT-ID   : " + matchEvent.getEventId());
		System.out.println("MATCH-ID   : " + matchEvent.getMatchId());
		System.out.println("EVENT-TYPE : " + matchEvent.getEventType());
		System.out.println("BATSMAN    : " + matchEvent.getBatsman());
		System.out.println("BOWLER     : " + matchEvent.getBowler());
		System.out.println("TIME       : " + matchEvent.getTimestamp());
	}

	@KafkaListener(topics = "player-scores-topic", groupId = "hotstar-consumer-group")
	public void consumerPlayerScore(PlayerScore playerScore) {
		System.out.println("HOTSTAR CONSUMER :: PLAYER_SCORE");
		System.out.println("TEAM           : " + playerScore.getTeam());
		System.out.println("PLAYER-NAME    : " + playerScore.getPlayerName());
		System.out.println("RUNS-SCORED    : " + playerScore.getRunsScored());
		System.out.println("STRIKE-RATE    : " + playerScore.getStrikeRate());
	}

}
