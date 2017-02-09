package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class ID3 {

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();
    private ClassificationRunner classificationRunner = new ClassificationRunner();

    public DecisionTreeNode learn(Collection<Instance> trainingDataSet, Map<Attribute, Collection<AttributeClass>> attributesClasses) {
        Optional<Attribute> lowestEntropyAttribute = getLowestEntropyAttribute(trainingDataSet, attributesClasses);
        if (lowestEntropyAttribute.isPresent()) {
            Map<AttributeClass, Collection<Instance>> dataSubsets = classificationRunner.split(
                    trainingDataSet,
                    lowestEntropyAttribute.get(),
                    attributesClasses.get(lowestEntropyAttribute));
            attributesClasses.remove(lowestEntropyAttribute);
            Collection<DecisionTreeNode> children = getChildrenNodes(dataSubsets, attributesClasses);
            return new DecisionTreeNode(lowestEntropyAttribute.get(), children);
        }
        return null;
    }

    private Collection<DecisionTreeNode> getChildrenNodes(Map<AttributeClass, Collection<Instance>> dataSubsets,
                                                          Map<Attribute, Collection<AttributeClass>> attributesClasses) {
        HashMap<Attribute, Collection<AttributeClass>> attributeClassesCopy = new HashMap<>(attributesClasses);
        return dataSubsets.entrySet().stream()
                .map(entry -> {
                    DecisionTreeNode node = this.learn(entry.getValue(), attributeClassesCopy);
                    node.setAttributeClass(entry.getKey());
                    return node;
                })
                .collect(toList());
    }

    private Optional<Attribute> getLowestEntropyAttribute(Collection<Instance> trainingDataSet,
                                                          Map<Attribute, Collection<AttributeClass>> attributes) {
        return attributes.keySet().stream().min((a1, a2) -> {
            Double a1Entropy = calculateEntropy(trainingDataSet, attributes, a1);
            Double a2Entropy = calculateEntropy(trainingDataSet, attributes, a2);
            return a1Entropy.compareTo(a2Entropy);
        });
    }

    private Double calculateEntropy(Collection<Instance> trainingDataSet,
                                    Map<Attribute, Collection<AttributeClass>> attributes, Attribute attribute) {
        List<Value> a1Values = trainingDataSet.stream().map(instance -> instance.getAttributeValue(attribute)).collect(toList());
        return entropyEvaluator.evaluateEntropy(a1Values, attributes.get(attribute));
    }

}
