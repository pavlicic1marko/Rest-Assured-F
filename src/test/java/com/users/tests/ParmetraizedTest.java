package com.users.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParmetraizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                        {0, 0}, {1, 1}, {2, 1}
                });

    }
    private int fInput;
    private int fExpected;

    public ParmetraizedTest(int input, int expected){
        this.fInput = input;
        this.fExpected = expected;
    }

    @Test
    public void parameterizedTest(){
        System.out.println(fInput + " " + fExpected);
    }
}
