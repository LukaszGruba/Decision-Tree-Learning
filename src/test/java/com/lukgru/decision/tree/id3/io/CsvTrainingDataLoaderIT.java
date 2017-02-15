package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Łukasz on 2017-02-15.
 */
public class CsvTrainingDataLoaderIT {
    
    @Test
    public void shouldLoadData() throws IOException {
        //given
        String filePath = "src/test/resources/sample-training-data.csv";

        //when
        TrainingDataLoader loader = new CsvTrainingDataLoader(filePath);
        loader.load();
        Set<Instance> trainingData = loader.getTrainingData();

        //then
        assertAttributeNames(trainingData, "Name", "Surname", "Sex", "Car", "City", "Status");
        assertEquals(7, trainingData.size());
        assertInstanceAttributes(trainingData, "Jacek",   "Antykwariat",  "m",    "Audi",     "Warszawa",     "rich");
        assertInstanceAttributes(trainingData, "Marian",  "Nowicki",      "m",    "Skoda",    "Bialystok",    "poor");
        assertInstanceAttributes(trainingData, "Malwina", "Zabobon",      "f",    "Jeep",     "Gdynia",       "rich");
        assertInstanceAttributes(trainingData, "John",    "Johnson",      "m",    "Porsche",  "New York",     "rich");
        assertInstanceAttributes(trainingData, "Jan",     "Janowski",     "m",    "Polonez",  "Nowy Targ",    "poor");
        assertInstanceAttributes(trainingData, "Beata",   "Kaczka",       "f",    "Skoda",    "Warszawa",     "poor");
        assertInstanceAttributes(trainingData, "Danuta",  "Raciborska",   "f",    "Audi",     "Krakow",       "rich");
    }

    private void assertInstanceAttributes(Set<Instance> trainingData, String name, String surname, String sex, String car, String city, String status) {
        Instance instance = trainingData.stream()
                .filter(i -> new Value(surname).equals(i.getAttributeValue(new Attribute("Surname"))))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No element with surname = " + surname));
        assertEquals(new Value(name), instance.getAttributeValue(new Attribute("Name")));
        assertEquals(new Value(surname), instance.getAttributeValue(new Attribute("Surname")));
        assertEquals(new Value(sex), instance.getAttributeValue(new Attribute("Sex")));
        assertEquals(new Value(car), instance.getAttributeValue(new Attribute("Car")));
        assertEquals(new Value(city), instance.getAttributeValue(new Attribute("City")));
        assertEquals(new Value(status), instance.getDecision().getValue());
    }

    private void assertAttributeNames(Set<Instance> trainingData, String... attributesNames) {
        List<String> namesList = Arrays.asList(attributesNames);
        trainingData.stream()
                .flatMap(i -> i.getAttributes().stream())
                .map(Attribute::getName)
                .distinct()
                .forEach(name -> assertTrue(namesList.contains(name)));
    }

}