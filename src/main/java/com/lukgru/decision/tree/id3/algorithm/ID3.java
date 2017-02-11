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
        boolean isHomogeneous = isDataHomogeneous(trainingDataSet);
        if (isHomogeneous) {
            Decision homogeneousDecision = trainingDataSet.stream().findFirst().get().getDecision();
            return new DecisionTreeNode(homogeneousDecision);
        }
        else {
            Attribute lowestEntropyAttribute = getLowestEntropyAttribute(trainingDataSet);
            Map<Value, Collection<Instance>> dataSubsets = classificationRunner.split(trainingDataSet, lowestEntropyAttribute);
            Map<Value, DecisionTreeNode> decisions = createDecisions(dataSubsets);
            return new DecisionTreeNode(lowestEntropyAttribute, decisions);
        }
    }

    private Map<Value, DecisionTreeNode> createDecisions(Map<Value, Collection<Instance>> dataSubsets) {
        //TODO: implement
        return null;
    }

    private Attribute getLowestEntropyAttribute(Collection<Instance> trainingDataSet) {
        //TODO: implement
        return null;
    }

    private boolean isDataHomogeneous(Collection<Instance> trainingDataSet) {
        //TODO: implement
        return false;
    }

}
