package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Decision;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.data.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 2017-02-11.
 */
public class ID3isDataHomogeneousTest {

    Predicate<Collection<Instance>> isDataHomogeneous = new ID3()::isDataHomogeneous;
    
    @Test
    public void shouldReturnTrueForHomogeneous() {
        //given
        Collection<Instance> dataSet = Arrays.asList(
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0")
        );

        //when
        boolean isHomogeneous = isDataHomogeneous.test(dataSet);

        //then
        assertTrue(isHomogeneous);
    }

    @Test
    public void shouldReturnFalseForHomogeneous() {
        //given
        Collection<Instance> dataSet = Arrays.asList(
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("9999"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0"),
                mockInstance("0")
        );

        //when
        boolean isHomogeneous = isDataHomogeneous.test(dataSet);

        //then
        assertFalse(isHomogeneous);
    }

    private Instance mockInstance(String value) {
        Instance mock = mock(Instance.class);
        Decision decision = mockDecision(value);
        when(mock.getDecision()).thenReturn(decision);
        return mock;
    }

    private Decision mockDecision(String v) {
        Decision decision = mock(Decision.class);
        when(decision.getValue()).thenReturn(new Value(v));
        return decision;
    }

}