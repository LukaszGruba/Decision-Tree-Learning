package com.lukgru.decision.tree.id3.data;

import java.util.function.Predicate;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class AttributeClass {

    private String className;
    private Attribute attribute;
    private Predicate<Value> condition;

    public AttributeClass(String className, Attribute attribute) {
        this.className = className;
        this.attribute = attribute;
        condition = className::equals;
    }

    public AttributeClass(String className, Attribute attribute, Predicate<Value> condition) {
        this.className = className;
        this.attribute = attribute;
        this.condition = condition;
    }

    public boolean meetsCriteria(Value value) {
        return condition.test(value);
    }

    public String getClassName() {
        return className;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
