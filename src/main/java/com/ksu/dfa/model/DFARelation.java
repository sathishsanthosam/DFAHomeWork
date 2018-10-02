package com.ksu.dfa.model;

public class DFARelation {
	
	private int index;
	
	private DFAState state;
	
	

	public DFARelation(int index, DFAState state) {
		super();
		this.index = index;
		this.state = state;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public DFAState getState() {
		return state;
	}

	public void setState(DFAState state) {
		this.state = state;
	}
	
	

}
