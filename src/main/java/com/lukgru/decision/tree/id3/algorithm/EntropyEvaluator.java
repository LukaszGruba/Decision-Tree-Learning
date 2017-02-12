package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class EntropyEvaluator {

    public Double evaluateEntropy(Collection<Instance> data) {
        int size = data.size();
        Stream<Decision> possibleDecisions = data.stream()
                .map(Instance::getDecision)
                .distinct();
        return possibleDecisions.mapToDouble(d -> {
            long count = data.stream().filter(i -> i.getDecision().equals(d)).count();
            double proportion = (double) count / size;
            return -1 * proportion * log2(proportion);
        }).sum();
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
