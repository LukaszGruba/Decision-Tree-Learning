package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Value;

import java.util.Collection;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class EntropyEvaluator {

    public double evaluateEntropy(Collection<Value> values, Collection<AttributeClass> classes) {
        int size = values.size();
        return classes.stream().mapToDouble(attributeClass -> {
            long count = values.stream()
                    .filter(attributeClass::meetsCriteria)
                    .count();
            double probability = count / size;
            return probability != 0.0 ? -probability * Math.log(probability) / Math.log(2) : 0.0;
        }).sum();
    }
}
