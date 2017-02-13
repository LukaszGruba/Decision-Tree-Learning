package com.lukgru.decision.tree.id3.tree;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Value;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lukasz on 2017-02-07.
 */
public class DecisionTreeNode {

    private Attribute attribute;
    private Map<Value, DecisionTreeNode> decisions;
    private Decision decision;

    public DecisionTreeNode(Decision decision) {
        this.decision = decision;
    }

    public DecisionTreeNode(Attribute attribute, Map<Value, DecisionTreeNode> decisions) {
        this.attribute = attribute;
        this.decisions = decisions;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Map<Value, DecisionTreeNode> getDecisions() {
        return Collections.unmodifiableMap(new HashMap<>(decisions));
    }

    public Decision getDecision() {
        return decision;
    }
}
