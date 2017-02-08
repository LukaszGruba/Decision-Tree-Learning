package com.lukgru.decision.tree.id3.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class Instance {

    private Map<Attribute, Value> attributeValues = new HashMap<>();

    public Value getAttributeValue(Attribute attribute) {
        return attributeValues.get(attribute);
    }
}
