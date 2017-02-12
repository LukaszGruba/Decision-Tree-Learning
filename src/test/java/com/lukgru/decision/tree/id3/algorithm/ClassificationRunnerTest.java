package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Attribute;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 09.02.2017.
 */
public class ClassificationRunnerTest {

    private static final String AUDI = "audi";
    private static final String SKODA = "skoda";
    private static final String HONDA = "honda";
    private static final String FORD = "ford";
    private static final String PORSCHE = "porsche";

    private static final Attribute SPLIT_ATTRIBUTE = new Attribute("split_attribute");

    private ClassificationRunner classificationRunner = new ClassificationRunner();

    @Test
    public void splitOnTwoValues() {
        //given
        Collection<Instance> data = Arrays.asList(
                mockInstance(AUDI, "John1", "Johnson1"),
                mockInstance(FORD, "John2", "Johnson2"),
                mockInstance(AUDI, "John3", "Johnson3"),
                mockInstance(FORD, "John4", "Johnson4"),
                mockInstance(AUDI, "John5", "Johnson5"),
                mockInstance(FORD, "John6", "Johnson6"),
                mockInstance(AUDI, "John7", "Johnson7"),
                mockInstance(FORD, "John8", "Johnson8"),
                mockInstance(FORD, "John9", "Johnson9"),
                mockInstance(FORD, "John10", "Johnso10n"),
                mockInstance(FORD, "John11", "Johnson11"),
                mockInstance(FORD, "John12", "Johnson12"),
                mockInstance(AUDI, "John13", "Johnson13"),
                mockInstance(AUDI, "John14", "Johnson14"),
                mockInstance(FORD, "John15", "Johnson15"),
                mockInstance(FORD, "John16", "Johnson16"),
                mockInstance(FORD, "John17", "Johnson17"),
                mockInstance(AUDI, "John18", "Johnson18"),
                mockInstance(FORD, "John19", "Johnson19")
        );

        //when
        Map<Value, Collection<Instance>> split = classificationRunner.split(data, SPLIT_ATTRIBUTE);

        //then
        assertEquals(2, split.size());
        assertEquals(7, split.get(new Value(AUDI)).size());
        assertEquals(12, split.get(new Value(FORD)).size());
        assertThatSplitCorrectly(split.get(new Value(AUDI)));
        assertThatSplitCorrectly(split.get(new Value(FORD)));
    }

    @Test
    public void splitOnThreeValues() {
        //given
        Collection<Instance> data = Arrays.asList(
                mockInstance(AUDI, "John1", "Johnson1"),
                mockInstance(SKODA, "John2", "Johnson2"),
                mockInstance(AUDI, "John3", "Johnson3"),
                mockInstance(SKODA, "John4", "Johnson4"),
                mockInstance(SKODA, "John5", "Johnson5"),
                mockInstance(HONDA, "John6", "Johnson6"),
                mockInstance(SKODA, "John7", "Johnson7"),
                mockInstance(AUDI, "John8", "Johnson8"),
                mockInstance(SKODA, "John9", "Johnson9")
        );

        //when
        Map<Value, Collection<Instance>> split = classificationRunner.split(data, SPLIT_ATTRIBUTE);

        //then
        assertEquals(3, split.size());
        assertEquals(1, split.get(new Value(HONDA)).size());
        assertEquals(5, split.get(new Value(SKODA)).size());
        assertEquals(3, split.get(new Value(AUDI)).size());
        assertThatSplitCorrectly(split.get(new Value(HONDA)));
        assertThatSplitCorrectly(split.get(new Value(SKODA)));
        assertThatSplitCorrectly(split.get(new Value(AUDI)));
    }

    @Test
    public void splitOnFourValues() {
        //given
        Collection<Instance> data = Arrays.asList(
                mockInstance(SKODA, "John1", "Johnson1"),
                mockInstance(AUDI, "John2", "Johnson2"),
                mockInstance(AUDI, "John3", "Johnson3"),
                mockInstance(FORD, "John4", "Johnson4"),
                mockInstance(SKODA, "John5", "Johnson5"),
                mockInstance(FORD, "John6", "Johnson6"),
                mockInstance(AUDI, "John7", "Johnson7"),
                mockInstance(SKODA, "John8", "Johnson8"),
                mockInstance(PORSCHE, "John9", "Johnson9"),
                mockInstance(SKODA, "John10", "Johnso10n"),
                mockInstance(FORD, "John11", "Johnson11"),
                mockInstance(SKODA, "John12", "Johnson12"),
                mockInstance(SKODA, "John13", "Johnson13"),
                mockInstance(FORD, "John14", "Johnson14"),
                mockInstance(SKODA, "John15", "Johnson15"),
                mockInstance(AUDI, "John16", "Johnson16"),
                mockInstance(FORD, "John17", "Johnson17"),
                mockInstance(FORD, "John18", "Johnson18"),
                mockInstance(SKODA, "John19", "Johnson19")
                );

        //when
        Map<Value, Collection<Instance>> split = classificationRunner.split(data, SPLIT_ATTRIBUTE);

        //then
        assertEquals(4, split.size());
        assertEquals(4, split.get(new Value(AUDI)).size());
        assertEquals(6, split.get(new Value(FORD)).size());
        assertEquals(8, split.get(new Value(SKODA)).size());
        assertEquals(1, split.get(new Value(PORSCHE)).size());
        assertThatSplitCorrectly(split.get(new Value(AUDI)));
        assertThatSplitCorrectly(split.get(new Value(FORD)));
        assertThatSplitCorrectly(split.get(new Value(SKODA)));
        assertThatSplitCorrectly(split.get(new Value(PORSCHE)));
    }

    private Instance mockInstance(String... attributeValues) {
        Instance instance = mock(Instance.class);
        when(instance.getAttributeValue(SPLIT_ATTRIBUTE)).thenReturn(new Value(attributeValues[0]));
        for (int i = 1; i < attributeValues.length; i++) {
            when(instance.getAttributeValue(new Attribute("" + i))).thenReturn(new Value(attributeValues[i]));
        }
        return instance;
    }

    private void assertThatSplitCorrectly(Collection<Instance> instances) {
        long count = instances.stream()
                .map(instance -> instance.getAttributeValue(SPLIT_ATTRIBUTE))
                .distinct()
                .count();
        assertEquals(1, count);
    }

}