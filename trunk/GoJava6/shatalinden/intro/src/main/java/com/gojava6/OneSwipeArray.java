package com.gojava6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergiigetman on 9/22/15.
 */
public class OneSwipeArray {

    public boolean solution(int[] trueArray) {
        if (trueArray == null) {
            throw new IllegalArgumentException();
        }
        if (trueArray.length < 2) {
            throw new IllegalArgumentException();
        }

        return checkArray(trueArray);
    }

    private boolean checkArray(int[] arrayToCheck) {

        int[] arrayCopy = arrayToCheck.clone();
        Arrays.sort(arrayCopy);
        int count = 0;
        for (int i = 0; i < arrayCopy.length; i++) {
            if (arrayToCheck[i] != arrayCopy[i]) {
                count++;
            }
        }
        if (count == 2) return true;

        return false;
    }
}