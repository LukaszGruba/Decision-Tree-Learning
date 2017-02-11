package com.lukgru.decision.tree.id3.data;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class Value {

    private String value;

    public Value(String value) {
        this.value = value;
    }

    public String getValue() {
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

        Value value1 = (Value) o;

        return value != null ? value.equals(value1.value) : value1.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
