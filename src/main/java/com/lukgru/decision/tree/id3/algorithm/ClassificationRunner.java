package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Instance;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class ClassificationRunner {

    public Map<AttributeClass, Collection<Instance>> split(Collection<Instance> trainingDataSet,
                                                            Attribute attribute, Collection<AttributeClass> attributeClasses) {
        Map<AttributeClass, Collection<Instance>> subsets = new HashMap<>();
        attributeClasses.forEach(attributeClass -> {
            List<Instance> subset = trainingDataSet.stream()
                    .filter(instance -> attributeClass.meetsCriteria(instance.getAttributeValue(attribute)))
                    .collect(toList());
            subsets.put(attributeClass, subset);
        });
        return subsets;
    }
}
