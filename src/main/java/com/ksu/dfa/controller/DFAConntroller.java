package com.ksu.dfa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksu.dfa.model.DFARelation;
import com.ksu.dfa.model.DFAState;
import com.ksu.dfa.util.DFAUtil;

@RestController
public class DFAConntroller {
	
	@Autowired
	DFAUtil dfaUtil;

	@RequestMapping(value="/ZeroStarOneStarState", method=RequestMethod.POST) 
	public String ZeroStarOneStarState(@RequestBody String input) {
		DFAState currentState = dfaUtil.zeroStarOneStarState();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			DFARelation relation = currentState.getDelta().get(""+c);
			if(relation == null) {
				return "reject";
			}else {
				currentState = relation.getState();
			}
		}
		return currentState.isAccepted() ? "Accepted" : "failed";
	}
}
