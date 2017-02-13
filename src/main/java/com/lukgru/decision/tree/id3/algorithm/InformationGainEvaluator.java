package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Instance;

import java.util.Collection;

/**
 * Created by Łukasz on 2017-02-13.
 */
public class InformationGainEvaluator {

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();

    public Double evaluateInformationGain(Double initialEntropy, Collection<Collection<Instance>> subsets) {
        long allElementsCount = subsets.stream()
                .mapToInt(Collection::size)
                .sum();
        double entropySum = subsets.stream()
                .mapToDouble(subset -> {
                    double proportion = (double) subset.size() / allElementsCount;
                    return proportion * entropyEvaluator.evaluateEntropy(subset);
                })
                .sum();
        return initialEntropy - entropySum;
    }

}
