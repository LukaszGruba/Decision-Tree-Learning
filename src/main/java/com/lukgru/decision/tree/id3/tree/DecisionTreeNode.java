package com.lukgru.decision.tree.id3.tree;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;

import java.util.Collection;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class DecisionTreeNode {
    private final Attribute attribute;
    private final Collection<DecisionTreeNode> children;
    private AttributeClass attributeClass;

    public DecisionTreeNode(Attribute attribute, Collection<DecisionTreeNode> children) {
        this.attribute = attribute;
        this.children = children;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Collection<DecisionTreeNode> getChildren() {
        return children;
    }

    public AttributeClass getAttributeClass() {
        return attributeClass;
    }

    public void setAttributeClass(AttributeClass attributeClass) {
        this.attributeClass = attributeClass;
    }
}
