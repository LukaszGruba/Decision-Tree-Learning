package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class ClassificationRunner {

    public Map<Value, Collection<Instance>> split(Collection<Instance> data, Attribute attribute) {
        HashMap<Value, Collection<Instance>> subsets = new HashMap<>();
        Stream<Value> values = data.stream()
                .map(instance -> instance.getAttributeValue(attribute))
                .distinct();
        values.forEach(value -> {
            Stream<Instance> instanceStream = data.stream()
                    .filter(instance -> instance.getAttributeValue(attribute).equals(value));
            subsets.put(value, instanceStream.collect(Collectors.toList()));
        });
        return subsets;
    }
}
