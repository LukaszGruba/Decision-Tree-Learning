package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by Łukasz on 2017-02-08.
 */
public class EntropyEvaluatorTest {

    private static final double EPSILON = 0.0000001;

    private static final Attribute SIGNIFICANT_ATTRIBUTE = new Attribute("significant_attribute");
    private static final Attribute ATTRIBUTE_1 = new Attribute("attribute_1");
    private static final Attribute ATTRIBUTE_2 = new Attribute("attribute_2");
    private static final Attribute ATTRIBUTE_3 = new Attribute("attribute_3");


    private static final Attribute DECISION_ATTRIBUTE = new Attribute("decision_attribute");
    private static final Decision DECISION_1 = new Decision(DECISION_ATTRIBUTE, new Value("1"));
    private static final Decision DECISION_2 = new Decision(DECISION_ATTRIBUTE, new Value("1"));
    private static final Decision DECISION_3 = new Decision(DECISION_ATTRIBUTE, new Value("1"));

    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();
    
    @Test
    public void forHomogeneousShouldReturnZero() {
        //given
        Attribute[] attributes = {SIGNIFICANT_ATTRIBUTE};
        Decision[][] decisionsPerAttribute = {{DECISION_1, DECISION_1, DECISION_1, DECISION_1, DECISION_1, DECISION_1}};
        Collection<Instance> data = createInstances(attributes, decisionsPerAttribute);

        //when
        double entropy = entropyEvaluator.evaluateEntropy(SIGNIFICANT_ATTRIBUTE, data);

        //then
        double expectedEntropy = 0.0;
        assertTrue("Expected entropy: 0.0 actual: " + entropy, Math.abs(expectedEntropy - entropy) < EPSILON);
    }

    private Collection<Instance> createInstances(Attribute[] attributes, Decision[][] decisionsPerAttribute) {

    }

}