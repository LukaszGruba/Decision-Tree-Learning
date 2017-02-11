package com.lukgru.decision.tree.id3.data;

/**
 * Created by Łukasz on 2017-02-11.
 */
public class Decision {

    private Attribute attribute;
    private Value value;

    public Decision(Attribute attribute, Value value) {
        this.attribute = attribute;
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Value getValue() {
        return value;
    }
}
