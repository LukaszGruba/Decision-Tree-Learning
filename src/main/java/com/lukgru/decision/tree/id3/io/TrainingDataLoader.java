package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Instance;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Lukasz on 09.02.2017.
 */
public interface TrainingDataLoader {

    void load() throws IOException;

    Set<Instance> getTrainingData();

}
