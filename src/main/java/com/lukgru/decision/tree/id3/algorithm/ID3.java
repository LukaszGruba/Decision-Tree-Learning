package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.tree.DTNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class ID3 {

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();

    //TODO: needs re-check
    public DTNode learn(Set<Instance> trainingDataSet, Map<Attribute, Collection<AttributeClass>> attributesClasses) {
        Attribute lowestEntropyAttribute = getLowestEntropyAttribute();
        Map<AttributeClass, Collection<Instance>> dataSubsets = split(trainingDataSet, lowestEntropyAttribute, attributesClasses.get(lowestEntropyAttribute));
        attributesClasses.remove(lowestEntropyAttribute);
        Collection<DTNode> children = getChildrenNodes(dataSubsets, attributesClasses);
        return new DTNode(lowestEntropyAttribute, children);
    }

    private Collection<DTNode> getChildrenNodes(Map<AttributeClass, Collection<Instance>> dataSubsets, Map<Attribute, Collection<AttributeClass>> attributesClasses) {
        //TODO: implement
        return null;
    }

    //TODO: move to separate class
    private Map<AttributeClass, Collection<Instance>> split(Set<Instance> trainingDataSet, Attribute attribute, Collection<AttributeClass> attributeClasses) {
        Map<AttributeClass, Collection<Instance>> subsets = new HashMap<>();
        attributeClasses.forEach(attributeClass -> {
            List<Instance> subset = trainingDataSet.stream()
                    .filter(instance -> attributeClass.meetsCriteria(instance.getAttributeValue(attribute)))
                    .collect(Collectors.toList());
            subsets.put(attributeClass, subset);
        });
        return subsets;
    }

    private Attribute getLowestEntropyAttribute() {
        //TODO: implement
        return null;
    }

}
