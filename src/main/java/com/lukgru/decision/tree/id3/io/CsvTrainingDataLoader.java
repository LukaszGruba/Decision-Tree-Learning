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
public class CsvTrainingDataLoader implements TrainingDataLoader {

    private String path;

    public CsvTrainingDataLoader(String path) {
        this.path = path;
    }

    @Override
    public void load() {
        //TODO: implement
    }

    @Override
    public Set<Instance> getTrainingData() {
        //TODO: implement
        return null;
    }

    @Override
    public Map<Attribute, Collection<AttributeClass>> getAttributeClasses() {
        //TODO: implement
        return null;
    }
}
