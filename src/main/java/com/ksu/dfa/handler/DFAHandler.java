package com.ksu.dfa.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ksu.dfa.model.DFARelation;
import com.ksu.dfa.model.DFAState;
import com.ksu.dfa.util.DFAUtil;

@Component
public class DFAHandler extends TextWebSocketHandler{
	
	@Autowired
	DFAUtil dfaUtil;


	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String[] textMessage = message.getPayload().split(",");
		String name = textMessage[0];
		String input = textMessage[1];
		DFAState currentState = null;
		switch(name) {
			case "zeroStartOneStar" :
				currentState = dfaUtil.zeroStarOneStarState();
				break;
			case "evanAaddBNoab" :
				currentState = dfaUtil.evanAaddBNoAB();
				break;
			case "zeroStartOneStarZero" :
				currentState = dfaUtil.zeroStarOneStarZeroState();
				break;
			default:
				currentState = dfaUtil.zeroStarOneStarState();
		}
		for (int i = 0; i < input.length(); i++) {
			Thread.sleep(1000);
			char c = input.charAt(i);
			DFARelation relation = null;
			if(i == input.length() - 1) {
				relation = currentState.getDelta().get("any");
				if(relation != null) {
					session.sendMessage(new TextMessage(name + "," +currentState.getName()+","+relation.getIndex()+"," + relation.getState().getName()));
					currentState = relation.getState();
					Thread.sleep(1000);
				}
			}
			relation = currentState.getDelta().get(""+c);
			if(relation == null) {
				relation = currentState.getDelta().get("any");
				if(relation != null) {
					session.sendMessage(new TextMessage(name + "," +currentState.getName()+","+relation.getIndex()+"," + relation.getState().getName()));
					currentState = relation.getState();
					i = i-1;
				}else {
					currentState = currentState.getFailedState();
					session.sendMessage(new TextMessage(name + "," +currentState.getName()+","+0+"," + currentState.getName()));
				}
				
			}else {
				session.sendMessage(new TextMessage(name + "," +currentState.getName()+","+relation.getIndex()+"," + relation.getState().getName()));
				currentState = relation.getState();
				
			}
			
		}
		Thread.sleep(1000);
		session.sendMessage(new TextMessage(currentState.isAccepted() ? name+",Accepted,0,Accepted" : name+",failed,0,"+currentState.getName()));
	}
	


}
