package com.lukgru.decision.tree.id3;

import com.lukgru.decision.tree.id3.algorithm.ID3;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.io.CsvTrainingDataLoader;
import com.lukgru.decision.tree.id3.io.TrainingDataLoader;
import com.lukgru.decision.tree.id3.io.TreeWriter;
import com.lukgru.decision.tree.id3.io.XmlTreeWriter;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.util.Set;

/**
 * Created by Łukasz on 2017-02-07.
 */
public class Main {

    public static void main(String[] args) {
        TrainingDataLoader loader = new CsvTrainingDataLoader("training.csv");
        loader.load();
        Set<Instance> trainingDataSet = loader.getTrainingData();

        DecisionTreeNode root = new ID3().learn(trainingDataSet);

        TreeWriter writer = new XmlTreeWriter();
        writer.write(root, "tree.xml");
    }

}
