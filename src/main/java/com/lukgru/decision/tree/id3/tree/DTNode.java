package com.lukgru.decision.tree.id3.tree;

import com.lukgru.decision.tree.id3.data.Attribute;

import java.util.Collection;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class DTNode {
    private final Attribute attribute;
    private final Collection<DTNode> children;

    public DTNode(Attribute attribute, Collection<DTNode> children) {
        this.attribute = attribute;
        this.children = children;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Collection<DTNode> getChildren() {
        return children;
    }
}
