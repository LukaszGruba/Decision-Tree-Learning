package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Instance;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lukasz on 09.02.2017.
 */
public interface TrainingDataLoader {
    void load();

    Set<Instance> getTrainingData();

    Map<Attribute,Collection<AttributeClass>> getAttributeClasses();
}
