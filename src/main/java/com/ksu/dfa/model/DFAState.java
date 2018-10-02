package com.ksu.dfa.model;

import java.util.HashMap;
import java.util.Map;

public class DFAState {

	private String name;
	
	private boolean accepted;
	
	private DFAState failedState;
	
	private Map<String, DFARelation> delta = new HashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Map<String, DFARelation> getDelta() {
		return delta;
	}

	public void setDelta(Map<String, DFARelation> delta) {
		this.delta = delta;
	}

	public DFAState getFailedState() {
		return failedState;
	}

	public void setFailedState(DFAState failedState) {
		this.failedState = failedState;
	}

	public DFAState(String name, boolean accepted) {
		this.name = name;
		this.accepted = accepted;
	}
	
	
	
	
}
