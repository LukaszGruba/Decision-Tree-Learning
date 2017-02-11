package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

/**
 * Created by Lukasz on 09.02.2017.
 */
public interface TreeWriter {
    void write(DecisionTreeNode root, String path);
}