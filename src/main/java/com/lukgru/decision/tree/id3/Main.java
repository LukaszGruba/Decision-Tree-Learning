package com.lukgru.decision.tree.id3;

import com.lukgru.decision.tree.id3.algorithm.ID3;
import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.tree.DTNode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Set<Instance> trainingDataSet = main.loadTrainingData();
        Map<Attribute, Collection<AttributeClass>> attributeClasses = main.loadAttributeClasses();
        DTNode root = new ID3().learn(trainingDataSet, attributeClasses);
        main.writeTreeToXml(root, "someFile.xml");
    }

    private Map<Attribute, Collection<AttributeClass>> loadAttributeClasses() {
        //TODO: implement
        //TODO: move to separate class
        return null;
    }

    private void writeTreeToXml(DTNode root, String fileName) {
        //TODO: move to separate class + implement
    }

    private Set<Instance> loadTrainingData() {
        //TODO: move to separate class + implement
        return null;
    }

}
