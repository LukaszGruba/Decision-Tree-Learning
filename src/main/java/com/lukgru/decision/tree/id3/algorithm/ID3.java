package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class ID3 {

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();
    private ClassificationRunner classificationRunner = new ClassificationRunner();

    public DecisionTreeNode learn(Collection<Instance> trainingDataSet) {
        if (isDataHomogeneous(trainingDataSet)) {
            Decision homogeneousDecision = trainingDataSet.stream().findFirst().get().getDecision();
            return new DecisionTreeNode(homogeneousDecision);
        }
        else {
            Attribute highestInformationGainAttribute = getHighestInformationGainAttribute(trainingDataSet);
            Map<Value, Collection<Instance>> dataSubsets = classificationRunner.split(trainingDataSet, highestInformationGainAttribute);
            Map<Value, DecisionTreeNode> decisions = createDecisions(dataSubsets);
            return new DecisionTreeNode(highestInformationGainAttribute, decisions);
        }
    }

    private Attribute getHighestInformationGainAttribute(Collection<Instance> data) {
        Double initialEntropy = entropyEvaluator.evaluateEntropy(data);
        return data.stream()
                .flatMap(i -> i.getAttributes().stream())
                .distinct()
                .max((a1, a2) -> {
                    Map<Value, Collection<Instance>> dataA1 = classificationRunner.split(data, a1);
                    Double informationGainA1 = evaluateInformationGain(initialEntropy, dataA1.values());

                    Map<Value, Collection<Instance>> dataA2 = classificationRunner.split(data, a2);
                    Double informationGainA2 = evaluateInformationGain(initialEntropy, dataA2.values());

                    return informationGainA1.compareTo(informationGainA2);
                }).orElseThrow(() -> new RuntimeException("No maximum information gain."));
    }

    boolean isDataHomogeneous(Collection<Instance> trainingDataSet) {
        long numberOfValueTypes = trainingDataSet.stream()
                .map(instance -> instance.getDecision().getValue())
                .distinct()
                .count();
        return numberOfValueTypes == 1;
    }

    private Double evaluateInformationGain(Double initialEntropy, Collection<Collection<Instance>> values) {
        //TODO: implement
        return null;
    }

    private Map<Value, DecisionTreeNode> createDecisions(Map<Value, Collection<Instance>> dataSubsets) {
        //TODO: implement
        return null;
    }

}
