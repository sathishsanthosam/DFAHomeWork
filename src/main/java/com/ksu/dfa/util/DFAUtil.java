package com.ksu.dfa.util;

import org.springframework.stereotype.Component;

import com.ksu.dfa.model.DFARelation;
import com.ksu.dfa.model.DFAState;

@Component
public class DFAUtil {

	public DFAState zeroStarOneStarState() {
		DFAState startState = new DFAState("start", true);
		DFAState zeroState = new DFAState("zero", true);
		DFAState oneState = new DFAState("one", true);
		DFAState failedState = new DFAState("failedState",false);
		/* Start State delta and failed state*/
		startState.getDelta().put("0", new DFARelation(0, zeroState)); /* to zero state*/
		startState.getDelta().put("1", new DFARelation(1, oneState)); /* to zero state*/
		startState.setFailedState(failedState); /* to failed state*/
		/* Zero State delta and failed state*/
		zeroState.getDelta().put("0", new DFARelation(0, zeroState)); /* self loop*/
		zeroState.getDelta().put("1", new DFARelation(1, oneState));/* to one state*/
		zeroState.setFailedState(failedState);
		/* One State delta and failed state*/
		oneState.getDelta().put("1", new DFARelation(0, oneState));/* to one state*/
		oneState.getDelta().put("0", new DFARelation(1, failedState));/* to one state*/
		oneState.setFailedState(failedState);
		/* failed state delta and failed state*/
		failedState.getDelta().put("1", new DFARelation(0, failedState));/* to one state*/
		failedState.getDelta().put("0", new DFARelation(0, failedState));/* to one state*/
		failedState.setFailedState(failedState);
		return startState;
	}
	
	public DFAState evanAaddBNoAB() {
		DFAState startState = new DFAState("start", false);
		DFAState addBState = new DFAState("AddB", true);
		DFAState evenBState = new DFAState("EvenB", false);
		DFAState addAState = new DFAState("AddA", false);
		DFAState evenAState = new DFAState("EvenA", true);
		DFAState failedState = new DFAState("failedState",false);
		/* Start State delta and failed state*/
		startState.getDelta().put("any", new DFARelation(0, evenBState)); /* to addB state*/
		//startState.getDelta().put("a", new DFARelation(1, addAState)); /* to addA state*/
		startState.setFailedState(failedState); /* to failed state*/
		/* AddB State delta and failed state*/
		addBState.getDelta().put("b", new DFARelation(0, evenBState)); /* to evenB state*/
		addBState.getDelta().put("a", new DFARelation(1, addAState));/* to addA state*/
		addBState.setFailedState(failedState);
		/* EvenB state delta and failed state*/
		evenBState.getDelta().put("b", new DFARelation(0, addBState));/* to addB state*/
		evenBState.getDelta().put("a", new DFARelation(1, failedState));/* to failed state*/
		evenBState.setFailedState(failedState);
		/* AddA state delta and failed state*/
		addAState.getDelta().put("a", new DFARelation(0, evenAState));/* to evenA state*/
		addAState.getDelta().put("b", new DFARelation(1, failedState));/* to failed state*/
		addAState.setFailedState(failedState);
		/* EvenA state delta and failed state*/
		evenAState.getDelta().put("a", new DFARelation(0, addAState));/* to addA state*/
		evenAState.getDelta().put("b", new DFARelation(1, failedState));/* to failed state*/
		evenAState.setFailedState(failedState);
		failedState.setFailedState(failedState);
		/* failed state delta and failed state*/
		failedState.getDelta().put("a", new DFARelation(0, failedState));/* to one state*/
		failedState.getDelta().put("b", new DFARelation(0, failedState));/* to one state*/
		failedState.setFailedState(failedState);
		return startState;
	}
	
	public DFAState zeroStarOneStarZeroState() {
		DFAState startState = new DFAState("start", false);
		DFAState zeroState = new DFAState("zero", false);
		DFAState oneState = new DFAState("one", false);
		DFAState secondZero = new DFAState("Q2",true);
		DFAState failedState = new DFAState("failed",false);
		/* Start State delta and failed state*/
		startState.getDelta().put("any", new DFARelation(2, zeroState)); /* Epsilon*/
		startState.getDelta().put("0", new DFARelation(0, zeroState)); /* to zero state*/
		startState.setFailedState(failedState); /* to failed state*/
		/* Zero State delta and failed state*/
		zeroState.getDelta().put("any", new DFARelation(2, oneState)); /* Epsilon*/
		zeroState.getDelta().put("0", new DFARelation(0, zeroState)); /* self loop*/
		zeroState.getDelta().put("1", new DFARelation(1, oneState));/* to one state*/
		zeroState.setFailedState(failedState);
		/* One State delta and failed state*/
		oneState.getDelta().put("1", new DFARelation(0, oneState));/* to one state*/
		oneState.getDelta().put("0", new DFARelation(1, secondZero));/* to one state*/
		oneState.setFailedState(failedState);
		secondZero.setFailedState(failedState);
		return startState;
	}
}
