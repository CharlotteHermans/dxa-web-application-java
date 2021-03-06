package com.sdl.dxa.common.dto;

import org.junit.Test;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DepthCounterTest {

    @Test
    public void shouldSayIfItIsTooDeep_IfZero() {
        assertTrue(new DepthCounter(1).isNotTooDeep());
        assertFalse(new DepthCounter(0).isNotTooDeep());
    }

    @Test
    public void shouldIncreaseAndCheck_WhileOneStillWorks() {
        //given 
        DepthCounter counter = new DepthCounter(1);

        assertTrue(counter.depthIncreaseAndCheckIfSafe());
        assertEquals(0, counter.getCounter());
        assertFalse(counter.depthIncreaseAndCheckIfSafe());
    }

    @Test
    public void shouldDecreaseDepth() {
        DepthCounter counter = new DepthCounter(0);
        counter.depthDecrease();
        assertEquals(1, counter.getCounter());
    }

    @Test
    public void shouldBeUnlimited_MaxValue() {
        //given 

        //when
        int deep = DepthCounter.UNLIMITED_DEPTH.getCounter();

        //then
        assertEquals(MAX_VALUE, deep);
    }

    @Test
    public void shouldConsiderNegativeValuesAsUnlimited() {
        //given 

        //when
        DepthCounter counter = new DepthCounter(-1);

        //then
        assertEquals(DepthCounter.UNLIMITED_DEPTH, counter);
    }
}