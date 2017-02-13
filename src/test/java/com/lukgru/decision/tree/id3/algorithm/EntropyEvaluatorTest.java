package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 2017-02-08.
 */
public class EntropyEvaluatorTest {

    private static final double EPSILON = 0.0000001;

    private static final Attribute DECISION_ATTRIBUTE = new Attribute("decision_attribute");
    private static final Decision DECISION_1 = new Decision(DECISION_ATTRIBUTE, new Value("1"));
    private static final Decision DECISION_2 = new Decision(DECISION_ATTRIBUTE, new Value("2"));
    private static final Decision DECISION_3 = new Decision(DECISION_ATTRIBUTE, new Value("3"));

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();

    @Test
    public void forHomogeneousType1ShouldReturnZero() {
        //given
        Decision[] decisions = {
            DECISION_1, DECISION_1, DECISION_1, DECISION_1, DECISION_1, DECISION_1
        };
        Collection<Instance> data = createInstances(decisions);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(data);

        //then
        double expectedEntropy = 0.0;
        assertTrue("Expected entropy: " + expectedEntropy + " actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    @Test
    public void forHomogeneousType2ShouldReturnZero() {
        //given
        Decision[] decisions = {
                DECISION_2, DECISION_2, DECISION_2, DECISION_2, DECISION_2, DECISION_2, DECISION_2, DECISION_2
        };
        Collection<Instance> data = createInstances(decisions);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(data);

        //then
        double expectedEntropy = 0.0;
        assertTrue("Expected entropy: " + expectedEntropy + " actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    @Test
    public void forEqualQuantityOfTwoTypesShouldReturn1() {
        //given
        Decision[] decisions = {
                DECISION_1, DECISION_1, DECISION_1, DECISION_1, DECISION_1,
                DECISION_2, DECISION_2, DECISION_2, DECISION_2, DECISION_2
        };
        Collection<Instance> data = createInstances(decisions);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(data);

        //then
        double expectedEntropy = 1.0;
        assertTrue("Expected entropy: " + expectedEntropy + " actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    @Test
    public void equalQuantityOfThreeTypes() {
        //given
        Decision[] decisions = {
                DECISION_1, DECISION_3, DECISION_1, DECISION_2, DECISION_3,
                DECISION_3, DECISION_2, DECISION_1, DECISION_3, DECISION_1,
                DECISION_2, DECISION_3, DECISION_2, DECISION_2, DECISION_1
        };
        Collection<Instance> data = createInstances(decisions);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(data);

        //then
        double expectedEntropy = 1.58496250072;
        assertTrue("Expected entropy: " + expectedEntropy + " actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    @Test
    public void differentQuantityOfThreeTypes() {
        //given
        Decision[] decisions = {
                DECISION_1, DECISION_1, DECISION_1, DECISION_2, DECISION_3, DECISION_3,
                DECISION_3, DECISION_2, DECISION_1, DECISION_3, DECISION_1, DECISION_2,
                DECISION_1, DECISION_2, DECISION_2, DECISION_2, DECISION_1
        };
        Collection<Instance> data = createInstances(decisions);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(data);

        //then
        double expectedEntropy = 1.54856522603002;
        assertTrue("Expected entropy: " + expectedEntropy + " actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    private Collection<Instance> createInstances(Decision[] decisions) {
        return Arrays.stream(decisions)
                .map(this::mockInstance)
                .collect(Collectors.toList());
    }

    private Instance mockInstance(Decision decision) {
        Instance instance = mock(Instance.class);
        when(instance.getDecision()).thenReturn(decision);
        return instance;
    }

}