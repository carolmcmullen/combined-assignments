package com.cooksys.ftd.assignments.control;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {

    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int index) {
    	if (index < 0)
    		throw new IllegalArgumentException();
    	
    	int[] fibNumbers = fibonacci(index+1);
    	return fibNumbers[index];
    }    	
    	

    /**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
    	if (start < 0)
    		throw new IllegalArgumentException();
    	if (end < 0)
    		throw new IllegalArgumentException();
    	if (end < start)
    		throw new IllegalArgumentException();
    	
    	if(end == 0){
    		return new int[0];
    	}
    	
    	// Determine number of integers that need to be returned.
    	int size = end - start;
    	
    	
    	// Create array and set size
    	int[] numbers = new int[size];
    	
    	int[] allFibNumbers = fibonacci(end);
    	int arrayIndex = 0;
    	for (int index = 0; index < allFibNumbers.length; index++)
    	{
    		if (index >= start){
    			numbers[arrayIndex] = allFibNumbers[index];
    			arrayIndex++;
    		}
    			
    	}
    	
    	return numbers;
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
    	if (count < 0)
    		throw new IllegalArgumentException();
    	if (count == 0)
    		return new int[0];
    	
    	int[] fibNumbers = new int[count];
    	fibNumbers[0] = 1;
    	
    	if(count > 1){
    		fibNumbers[1] = 1; 
    	}
    	
    	for (int index = 2; index < count; index++)
    	{
    		fibNumbers[index] = fibNumbers[index-1] + fibNumbers[index-2];
    	}
		return fibNumbers;
    }
}
