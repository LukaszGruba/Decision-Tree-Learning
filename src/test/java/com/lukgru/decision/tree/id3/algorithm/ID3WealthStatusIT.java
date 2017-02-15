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
public class ID3WealthStatusIT {

    private static final Attribute DECISION_ATTRIBUTE = new Attribute("Status");
    private static final Decision RICH = new Decision(DECISION_ATTRIBUTE, new Value("rich"));
    private static final Decision POOR = new Decision(DECISION_ATTRIBUTE, new Value("poor"));

    @Test
    public void createTreeForWealthStatus() throws IOException {
        //given
        TrainingDataLoader loader = new CsvTrainingDataLoader("src/test/resources/wealth-training-data.csv");
        loader.load();
        Set<Instance> trainingDataSet = loader.getTrainingData();

        //when
        DecisionTreeNode root = new ID3().learn(trainingDataSet);

        //then
        assertCityNode(root);
        assertWarszawaNode(root.getDecisions().get(new Value("Warszawa")));
        assertKrakowNode(root.getDecisions().get(new Value("Krakow")));
        assertEquals(RICH, root.getDecisions().get(new Value("Gdynia")).getDecision());
        assertEquals(POOR, root.getDecisions().get(new Value("Bialystok")).getDecision());
        assertEquals(RICH, root.getDecisions().get(new Value("New York")).getDecision());
        assertEquals(POOR, root.getDecisions().get(new Value("Nowy Targ")).getDecision());
        assertEquals(RICH, root.getDecisions().get(new Value("Berlin")).getDecision());
    }

    private void assertWarszawaNode(DecisionTreeNode node) {
        assertEquals("Car", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(2, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("Audi")));
        assertEquals(RICH, node.getDecisions().get(new Value("Audi")).getDecision());

        assertTrue(node.getDecisions().containsKey(new Value("Skoda")));
        DecisionTreeNode skodaSexNode = node.getDecisions().get(new Value("Skoda"));
        assertEquals(1, skodaSexNode.getDecisions().size());
        assertTrue(skodaSexNode.getDecisions().containsKey(new Value("f")));
    }

    private void assertKrakowNode(DecisionTreeNode node) {
        assertEquals("Sex", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(2, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("f")));
        assertEquals(RICH, node.getDecisions().get(new Value("f")).getDecision());
        assertTrue(node.getDecisions().containsKey(new Value("m")));
        assertEquals(POOR, node.getDecisions().get(new Value("m")).getDecision());
    }

    private void assertCityNode(DecisionTreeNode node) {
        assertEquals("City", node.getAttribute().getName());
        assertNull(node.getDecision());
        assertEquals(7, node.getDecisions().size());

        assertTrue(node.getDecisions().containsKey(new Value("Warszawa")));
        assertTrue(node.getDecisions().containsKey(new Value("Krakow")));
        assertTrue(node.getDecisions().containsKey(new Value("Gdynia")));
        assertTrue(node.getDecisions().containsKey(new Value("New York")));
        assertTrue(node.getDecisions().containsKey(new Value("Bialystok")));
        assertTrue(node.getDecisions().containsKey(new Value("Berlin")));
        assertTrue(node.getDecisions().containsKey(new Value("Nowy Targ")));
    }

}