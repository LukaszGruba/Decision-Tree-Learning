package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Instance;

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
}
