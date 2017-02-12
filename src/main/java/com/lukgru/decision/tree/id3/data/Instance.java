package com.lukgru.decision.tree.id3.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class Instance {

    private Map<Attribute, Value> attributeValues = new HashMap<>();
    private Decision decision;

    public Instance(Map<Attribute, Value> attributeValues, Decision decision) {
        this.attributeValues = attributeValues;
        this.decision = decision;
    }

    public Value getAttributeValue(Attribute attribute) {
        return attributeValues.get(attribute);
    }

    public Collection<Attribute> getAttributes() {
        return attributeValues.keySet();
    }

    public Decision getDecision() {
        return decision;
    }
}
