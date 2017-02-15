package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class XmlTreeWriter implements TreeWriter {

    @Override
    public void write(DecisionTreeNode root, String path) throws FileNotFoundException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\"?>\n");
        traverse(root, xml, "");
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.write(xml.toString());
        }
    }

    private void traverse(DecisionTreeNode node, StringBuilder xml, String indentation) {
        if (node.getDecision() != null) {
            Decision decision = node.getDecision();
            xml.append(indentation).append("<decision>").append("\n");
            xml.append(indentation).append("\t<attribute>").append(decision.getAttribute().getName()).append("</attribute>").append("\n");
            xml.append(indentation).append("\t<value>").append(decision.getValue().getValue()).append("</value>").append("\n");
            xml.append(indentation).append("</decision>").append("\n");
        } else {
            Map<Value, DecisionTreeNode> decisions = node.getDecisions();
            xml.append(indentation).append("<split attribute=\"").append(node.getAttribute().getName()).append("\">").append("\n");
            decisions.forEach((value, child) -> {
                xml.append(indentation).append("\t").append("<node>").append("\n");
                xml.append(indentation).append("\t\t").append("<value>").append(value.getValue()).append("</value>").append("\n");
                traverse(child, xml, indentation + "\t\t");
                xml.append(indentation).append("\t").append("</node>").append("\n");
            });
            xml.append(indentation).append("</split>").append("\n");
        }
    }

}
