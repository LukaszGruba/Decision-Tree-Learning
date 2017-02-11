package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.AttributeClass;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Łukasz on 2017-02-08.
 */
public class EntropyEvaluatorTest {

    private static final double epsilon = 0.000000001;
    private EntropyEvaluator entropyEvaluator = new EntropyEvaluator();

    @Test
    public void calcEntropyForSingleValueAndMultipleClasses() {
        //given
        Collection<AttributeClass> classes = getAttributeClasses();
        Collection<Value> values = Arrays.asList(
                new Value("XXS")
        );

        //when
        double entropy = entropyEvaluator.evaluateEntropy(values, classes);

        //then
        assertTrue("Expected 0.0, actual " + entropy, Math.abs(entropy - 0.0) < epsilon);
    }

    @Test
    public void calcEntropyForEqualNumberOfDifferentValues() {
        //given
        Collection<AttributeClass> classes = getAttributeClasses();
        Collection<Value> values = Arrays.asList(
                new Value("XXS"),
                new Value("XS"),
                new Value("S"),
                new Value("M"),
                new Value("L"),
                new Value("XL"),
                new Value("XXL")
        );

        //when
        double entropy = entropyEvaluator.evaluateEntropy(values, classes);

        //then
        assertTrue("Expected 0.0, actual " + entropy, Math.abs(entropy - 0.0) < epsilon);
    }

    @Test
    public void calcEntropyForNotEqualProportionsOfValues() {
        //given
        Collection<AttributeClass> classes = getAttributeClasses();
        Collection<Value> values = Arrays.asList(
                new Value("XXS"), new Value("XXS"),new Value("XXS"),new Value("XXS"),new Value("XXS"),
                new Value("XS"), new Value("XS"),new Value("XS"),
                new Value("S"), new Value("S"),new Value("S"),new Value("S"),new Value("S"),new Value("S"),new Value("S"),
                new Value("M"),
                new Value("L"), new Value("L"),new Value("L"),
                new Value("XL"), new Value("XL"),new Value("XL"),new Value("XL"),
                new Value("XXL"), new Value("XXL"),new Value("XXL"),new Value("XXL")
        );

        //when
        double entropy = entropyEvaluator.evaluateEntropy(values, classes);

        //then
        assertTrue("Expected 0.0, actual " + entropy, Math.abs(entropy - 0.0) < epsilon);
    }

    private List<AttributeClass> getAttributeClasses() {
        Attribute sizeAttribute = new Attribute("size");
        return Arrays.asList(
                new AttributeClass("XXS", sizeAttribute),
                new AttributeClass("XS", sizeAttribute),
                new AttributeClass("S", sizeAttribute),
                new AttributeClass("M", sizeAttribute),
                new AttributeClass("L", sizeAttribute),
                new AttributeClass("XL", sizeAttribute),
                new AttributeClass("XXL", sizeAttribute)
        );
    }

}