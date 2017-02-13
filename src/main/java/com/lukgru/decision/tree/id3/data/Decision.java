package com.lukgru.decision.tree.id3.data;

/**
 * Created by Lukasz on 2017-02-11.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Decision decision = (Decision) o;

        if (attribute != null ? !attribute.equals(decision.attribute) : decision.attribute != null) {
            return false;
        }
        return value != null ? value.equals(decision.value) : decision.value == null;

    }

    @Override
    public int hashCode() {
        int result = attribute != null ? attribute.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
