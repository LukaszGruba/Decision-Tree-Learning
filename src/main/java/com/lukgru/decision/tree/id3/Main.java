package com.lukgru.decision.tree.id3;

import com.lukgru.decision.tree.id3.algorithm.ID3;
import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.io.CsvTrainingDataLoader;
import com.lukgru.decision.tree.id3.io.TrainingDataLoader;
import com.lukgru.decision.tree.id3.io.TreeWriter;
import com.lukgru.decision.tree.id3.io.XmlTreeWriter;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Lukasz on 2017-02-07.
 */
public class Main {

    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];

        try {
            TrainingDataLoader loader = new CsvTrainingDataLoader(inputFilename);
            loader.load();
            Set<Instance> trainingDataSet = loader.getTrainingData();

            DecisionTreeNode root = new ID3().learn(trainingDataSet);

            TreeWriter writer = new XmlTreeWriter();
            writer.write(root, outputFilename);

            applyDecisionTreeForInputRows(root, loader.getAttributesList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void applyDecisionTreeForInputRows(DecisionTreeNode root, List<Attribute> attributesList) {
        Scanner inputScanner = new Scanner(System.in);
        String line = inputScanner.nextLine();
        while (inputScanner.hasNextLine()) {
            Instance inputInstance = parseInputLine(line, attributesList);
            Decision decision = applyOnTree(inputInstance, root);
            System.out.println("Result: " + decision.getValue().getValue());
            line = inputScanner.nextLine();
        }
    }

    private static Instance parseInputLine(String line, List<Attribute> attributesList) {
        Map<Attribute, Value> attributesMap = new HashMap<>();
        List<Value> attributesValues = Arrays.stream(line.split(";"))
                .map(Value::new)
                .collect(toList());
        IntStream.range(0, attributesList.size())
                .forEach(i -> attributesMap.put(attributesList.get(i), attributesValues.get(i)));
        return new Instance(attributesMap, null);
    }

    private static Decision applyOnTree(Instance instance, DecisionTreeNode node) {
        if (node.getDecision() != null) {
            return node.getDecision();
        }
        Attribute nodeAttribute = node.getAttribute();
        Value instanceValue = instance.getAttributeValue(nodeAttribute);
        DecisionTreeNode nextNode = node.getDecisions().get(instanceValue);
        return applyOnTree(instance, nextNode);
    }
}
