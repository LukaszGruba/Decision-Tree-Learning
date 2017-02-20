package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Lukasz on 2017-02-07.
 */
public class ID3 {

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();
    private InformationGainEvaluator informationGainEvaluator = new InformationGainEvaluator();
    private ClassificationRunner classificationRunner = new ClassificationRunner();

    public DecisionTreeNode learn(Collection<Instance> trainingDataSet) {
        Collection<Attribute> allAttributes = trainingDataSet.stream()
                .flatMap(i -> i.getAttributes().stream())
                .collect(toSet());
        return this.learn(trainingDataSet, allAttributes);
    }

    private DecisionTreeNode learn(Collection<Instance> trainingDataSet, Collection<Attribute> attributesLeft) {
        if (isDataHomogeneous(trainingDataSet)) {
            Decision homogeneousDecision = trainingDataSet.stream().findFirst().get().getDecision();
            return new DecisionTreeNode(homogeneousDecision);
        } else if (attributesLeft.isEmpty() || !getHighestInformationGainAttribute(trainingDataSet, attributesLeft).isPresent()) {
            Decision mostCommonDecision = mostCommonDecision(trainingDataSet);
            return new DecisionTreeNode(mostCommonDecision);
        } else {
            Attribute highestInformationGainAttribute = getHighestInformationGainAttribute(trainingDataSet, attributesLeft).get();
            Map<Value, Collection<Instance>> dataSubsets = classificationRunner.split(trainingDataSet, highestInformationGainAttribute);

            Collection<Attribute> attributesLeftForChildren = attributesLeftForChildren(attributesLeft, highestInformationGainAttribute);
            Map<Value, DecisionTreeNode> decisions = createDecisions(dataSubsets, attributesLeftForChildren);
            return new DecisionTreeNode(highestInformationGainAttribute, decisions);
        }
    }

    private Decision mostCommonDecision(Collection<Instance> trainingDataSet) {
        return trainingDataSet.stream()
                .map(Instance::getDecision)
                .distinct()
                .max((d1, d2) -> {
                    Long d1Count = trainingDataSet.stream().map(Instance::getDecision).filter(d1::equals).count();
                    Long d2Count = trainingDataSet.stream().map(Instance::getDecision).filter(d2::equals).count();
                    return d1Count.compareTo(d2Count);
                })
                .orElseThrow(() -> new RuntimeException("Could not find most common decision."));
    }

    private Collection<Attribute> attributesLeftForChildren(Collection<Attribute> attributes, Attribute toOmit) {
        return attributes.stream().filter(a -> !toOmit.equals(a)).collect(toSet());
    }

    private Optional<Attribute> getHighestInformationGainAttribute(Collection<Instance> data, Collection<Attribute> attributesLeft) {
        Double initialEntropy = entropyEvaluator.evaluateEntropy(data);
        return attributesLeft.stream()
                .max((a1, a2) -> {
                    Map<Value, Collection<Instance>> dataA1 = classificationRunner.split(data, a1);
                    Double informationGainA1 = informationGainEvaluator.evaluateInformationGain(initialEntropy, dataA1.values());

                    Map<Value, Collection<Instance>> dataA2 = classificationRunner.split(data, a2);
                    Double informationGainA2 = informationGainEvaluator.evaluateInformationGain(initialEntropy, dataA2.values());

                    return informationGainA1.compareTo(informationGainA2);
                }).filter(a -> {
                    Map<Value, Collection<Instance>> split = classificationRunner.split(data, a);
                    Double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, split.values());
                    return informationGain > 0.0;
                });
    }

    boolean isDataHomogeneous(Collection<Instance> trainingDataSet) {
        long numberOfValueTypes = trainingDataSet.stream()
                .map(instance -> instance.getDecision().getValue())
                .distinct()
                .count();
        return numberOfValueTypes == 1;
    }

    private Map<Value, DecisionTreeNode> createDecisions(Map<Value, Collection<Instance>> dataSubsets, Collection<Attribute> attributesLeftForChildren) {
        Map<Value, DecisionTreeNode> decisions = new HashMap<>();
        dataSubsets.forEach((value, subset) -> {
            DecisionTreeNode node = learn(subset, attributesLeftForChildren);
            decisions.put(value, node);
        });
        return decisions;
    }

}
