package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class CsvTrainingDataLoader implements TrainingDataLoader {

    private static final String CSV_DELIMITER = ";";

    private final String stringPath;
    private boolean loaded = false;

    private Set<Instance> data;
    private List<Attribute> attributesList;

    public CsvTrainingDataLoader(String stringPath) {
        this.stringPath = stringPath;
    }

    public void load() throws IOException {
        List<String> lines = readLines();
        Attribute[] attributes = getAttributes(lines.get(0));
        this.attributesList = Arrays.asList(attributes);
        int valueAttributesAmount = attributes.length - 1;
        Attribute decisionAttribute = attributes[valueAttributesAmount];
        lines.remove(0);

        this.data = lines.stream()
                .map(line -> line.split(CSV_DELIMITER))
                .map(stringValues -> {
                    Map<Attribute, Value> values = new HashMap<>();
                    for (int i = 0; i < valueAttributesAmount; i++) {
                        values.put(attributes[i], new Value(stringValues[i]));
                    }
                    Decision decision = new Decision(decisionAttribute, new Value(stringValues[valueAttributesAmount]));
                    return new Instance(values, decision);
                }).collect(toSet());
        loaded = true;
    }

    @Override
    public List<Attribute> getAttributesList() {
        return this.attributesList;
    }

    @Override
    public Set<Instance> getTrainingData() {
        if (!loaded) {
            throw new IllegalStateException("Data has to be loaded before accessing.");
        }
        return data;
    }

    private Attribute[] getAttributes(String header) {
        return Arrays.stream(header.split(CSV_DELIMITER))
                .map(Attribute::new)
                .toArray(Attribute[]::new);
    }

    private List<String> readLines() throws IOException {
        File file = new File(stringPath);
        Path path = file.toPath();
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        }
        return lines;
    }
}
