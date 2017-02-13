package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Instance;

import java.util.Set;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class CsvTrainingDataLoader implements TrainingDataLoader {

    @SuppressWarnings("PMD")
    private String path;

    public CsvTrainingDataLoader(String path) {
        this.path = path;
    }

    @SuppressWarnings("PMD")
    @Override
    public void load() {
        //TODO: implement
    }

    @SuppressWarnings("PMD")
    @Override
    public Set<Instance> getTrainingData() {
        //TODO: implement
        return null;
    }
}
