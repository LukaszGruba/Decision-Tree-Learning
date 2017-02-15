package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import com.lukgru.decision.tree.id3.io.CsvTrainingDataLoader;
import com.lukgru.decision.tree.id3.io.TrainingDataLoader;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Łukasz on 2017-02-15.
 */
public class ID3WeatherForecastIT {

    public static final Attribute DECISION_ATTRIBUTE = new Attribute("Play");
    private static final Decision YES_DECISION = new Decision(DECISION_ATTRIBUTE, new Value("Yes"));
    private static final Decision NO_DECISION = new Decision(DECISION_ATTRIBUTE, new Value("No"));

    @Test
    public void createTreeForWeatherForecast() throws IOException {
        //given
        TrainingDataLoader loader = new CsvTrainingDataLoader("src/test/resources/forecast-training-data.csv");
        loader.load();
        Set<Instance> trainingDataSet = loader.getTrainingData();

        //when
        DecisionTreeNode root = new ID3().learn(trainingDataSet);

        //then
        assertOutlookNode(root);
        assertHumidityNode(root.getDecisions().get(new Value("Sun")));
        assertEquals(YES_DECISION, root.getDecisions().get(new Value("Overcast")).getDecision());
        assertWindNode(root.getDecisions().get(new Value("Rain")));
    }

    private void assertOutlookNode(DecisionTreeNode node) {
        assertEquals("Outlook", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(3, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("Sun")));
        assertTrue(node.getDecisions().containsKey(new Value("Overcast")));
        assertTrue(node.getDecisions().containsKey(new Value("Rain")));
    }

    private void assertHumidityNode(DecisionTreeNode node) {
        assertEquals("Humidity", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(2, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("Normal")));
        assertEquals(YES_DECISION, node.getDecisions().get(new Value("Normal")).getDecision());
        assertTrue(node.getDecisions().containsKey(new Value("High")));
        assertEquals(NO_DECISION, node.getDecisions().get(new Value("High")).getDecision());
    }

    private void assertWindNode(DecisionTreeNode node) {
        assertEquals("Wind", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(2, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("Low")));
        assertEquals(YES_DECISION, node.getDecisions().get(new Value("Low")).getDecision());
        assertTrue(node.getDecisions().containsKey(new Value("High")));
        assertEquals(NO_DECISION, node.getDecisions().get(new Value("High")).getDecision());
    }

}