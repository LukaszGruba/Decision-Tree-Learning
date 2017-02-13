package com.lukgru.decision.tree.id3.algorithm;

import com.lukgru.decision.tree.id3.data.Instance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Łukasz on 2017-02-13.
 */
@RunWith(MockitoJUnitRunner.class)
public class InformationGainEvaluatorTest {

    private static final double EPSILON = 0.00000001;

    @Mock
    private EntropyEvaluator entropyEvaluator;

    @InjectMocks
    private InformationGainEvaluator informationGainEvaluator = new InformationGainEvaluator();
    
    @Test
    public void calcForTwoEqualInSizeSubsetsWithEqualEntropy() {
        //given
        Collection subset1 = mock(Collection.class);
        when(subset1.size()).thenReturn(100);
        when(entropyEvaluator.evaluateEntropy(subset1)).thenReturn(1.0);

        Collection subset2 = mock(Collection.class);
        when(subset2.size()).thenReturn(100);
        when(entropyEvaluator.evaluateEntropy(subset2)).thenReturn(1.0);

        Collection<Collection<Instance>> subsets = Arrays.asList(subset1, subset2);
        double initialEntropy = 2.0;

        //when
        double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, subsets);

        //then
        double expectedInformationGain = 1.0;
        assertTrue("Expected information gain: " + expectedInformationGain + " actual: " + informationGain,
                Math.abs(expectedInformationGain - informationGain) < EPSILON);
    }

    @Test
    public void calcForTwoEqualInSizeSubsetsWithDifferentEntropy() {
        //given
        Collection subset1 = mock(Collection.class);
        when(subset1.size()).thenReturn(100);
        when(entropyEvaluator.evaluateEntropy(subset1)).thenReturn(1.5);

        Collection subset2 = mock(Collection.class);
        when(subset2.size()).thenReturn(100);
        when(entropyEvaluator.evaluateEntropy(subset2)).thenReturn(1.0);

        Collection<Collection<Instance>> subsets = Arrays.asList(subset1, subset2);
        double initialEntropy = 2.0;

        //when
        double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, subsets);

        //then
        double expectedInformationGain = 0.75;
        assertTrue("Expected information gain: " + expectedInformationGain + " actual: " + informationGain,
                Math.abs(expectedInformationGain - informationGain) < EPSILON);
    }

    @Test
    public void calcForTwoDifferentInSizeSubsetsWithEqualEntropy() {
        //given
        Collection subset1 = mock(Collection.class);
        when(subset1.size()).thenReturn(18);
        when(entropyEvaluator.evaluateEntropy(subset1)).thenReturn(1.0);

        Collection subset2 = mock(Collection.class);
        when(subset2.size()).thenReturn(45);
        when(entropyEvaluator.evaluateEntropy(subset2)).thenReturn(1.0);

        Collection<Collection<Instance>> subsets = Arrays.asList(subset1, subset2);
        double initialEntropy = 2.0;

        //when
        double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, subsets);

        //then
        double expectedInformationGain = 1.0;
        assertTrue("Expected information gain: " + expectedInformationGain + " actual: " + informationGain,
                Math.abs(expectedInformationGain - informationGain) < EPSILON);
    }

    @Test
    public void calcForTwoDifferentInSizeSubsetsWithDifferentEntropy() {
        //given
        Collection subset1 = mock(Collection.class);
        when(subset1.size()).thenReturn(18);
        when(entropyEvaluator.evaluateEntropy(subset1)).thenReturn(1.534);

        Collection subset2 = mock(Collection.class);
        when(subset2.size()).thenReturn(45);
        when(entropyEvaluator.evaluateEntropy(subset2)).thenReturn(1.209);

        Collection<Collection<Instance>> subsets = Arrays.asList(subset1, subset2);
        double initialEntropy = 2.0;

        //when
        double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, subsets);

        //then
        double expectedInformationGain = 0.6981428571429;
        assertTrue("Expected information gain: " + expectedInformationGain + " actual: " + informationGain,
                Math.abs(expectedInformationGain - informationGain) < EPSILON);
    }

    @Test
    public void calcForThreeDifferentInSizeSubsetsWithDifferentEntropy() {
        //given
        Collection subset1 = mock(Collection.class);
        when(subset1.size()).thenReturn(18);
        when(entropyEvaluator.evaluateEntropy(subset1)).thenReturn(1.534);

        Collection subset2 = mock(Collection.class);
        when(subset2.size()).thenReturn(45);
        when(entropyEvaluator.evaluateEntropy(subset2)).thenReturn(1.209);

        Collection subset3 = mock(Collection.class);
        when(subset3.size()).thenReturn(71);
        when(entropyEvaluator.evaluateEntropy(subset3)).thenReturn(1.871);

        Collection<Collection<Instance>> subsets = Arrays.asList(subset1, subset2, subset3);
        double initialEntropy = 2.0;

        //when
        double informationGain = informationGainEvaluator.evaluateInformationGain(initialEntropy, subsets);

        //then
        double expectedInformationGain = 0.3965820895522388;
        assertTrue("Expected information gain: " + expectedInformationGain + " actual: " + informationGain,
                Math.abs(expectedInformationGain - informationGain) < EPSILON);
    }
}