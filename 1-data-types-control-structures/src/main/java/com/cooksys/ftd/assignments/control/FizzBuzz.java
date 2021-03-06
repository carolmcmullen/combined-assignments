package com.cooksys.ftd.assignments.control;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * FizzBuzz is an old programming exercise.
 * The goal is to iterate over a range of numbers and print a message about each number's divisibility.
 * <p>
 * The message is generated the following way:
 * *) if the number is divisible by three, the message is `Fizz`
 * *) if the number is divisible by five, the message is `Buzz`
 * *) if the number is divisible by both three and five, the message is `FizzBuzz`
 * *) otherwise, no message is produced
 * <p>
 * The exact message format for this assignment is specified in the `message(n)` method.
 */
public class FizzBuzz {

    /**
     * Checks whether a given int `a` is evenly divisible by a given int `b` or not.
     * For example, `divides(4, 2)` returns `true` and `divides(4, 3)` returns `false`.
     *
     * @param a the number to be divided
     * @param b the number to divide by
     * @return `true` if a is evenly divisible by b, `false` otherwise
     * @throws IllegalArgumentException if b is zero
     */
    public static boolean divides(int a, int b) throws IllegalArgumentException {
    	if (b == 0){
    		throw new IllegalArgumentException();
    	}
    	
        if (a % b == 0)
        	return true;
        else return false;
        	
    } 
    

    /**
     * Generates a divisibility message for a given number. Returns null if the given number is not divisible by 3 or 5.
     * Message formatting examples:
     * 1 -> null // not divisible by 3 or 5
     * 3 -> "3: Fizz" // divisible by only 3
     * 5 -> "5: Buzz" // divisible by only 5
     * 15 -> "15: FizzBuzz" // divisible by both three and five
     *
     * @param n the number to generate a message for
     * @return a message according to the format above, or null if n is not divisible by either 3 or 5
     */
    public static String message(int n) {
        // if n divisible by both 3 and 5 return fizzbuzz
    	if (divides(n,3) && divides(n,5)) {

    		return n+": FizzBuzz";

    	}
    	
    	// if n divisible by 3 return fizz
    	if (divides(n,3)) {

    		return n+": Fizz";

    			
    	}
    	
    	// if n divisible by 5 return buzz
    	if (divides(n,5)) {

    		return n+": Buzz";

    	}
    	
    	// if n not divisible by 3 or 5 return null
    	return null;
    }

    /**
     * Generates an array of messages to print for a given range of numbers.
     * If there is no message for an individual number (i.e., `message(n)` returns null),
     * it should be excluded from the resulting array.
     *
     * @param start the number to start with (inclusive)
     * @param end the number to end with (exclusive)
     * @return an array of divisibility messages
     * @throws IllegalArgumentException if the given end is less than the given start
     */
    public static int messages(int start, int end) throws IllegalArgumentException {
    	//if end < start 
    	if (end < start)
    		throw new IllegalArgumentException();

    	if (end == start)
    		return new String[0];
    	// find the size of the array by subtracting end and start
    	int size = end - start;
    	// create an array (size)
    	String[] messages = new String[size];
    	int x = 0;
    	// loop through start to end number (filling array)
    	for (int i = 0; i < end; i++){
    		if(i >= start){
    			if(message(i) != null)
    			{
    				messages[x] = message(i);
    				x++;
    			}
    		}
    	}
    	String[] messageWithNullsRemoved = new String[x];
    	for (int i = 0; i < x; i++){
    		messageWithNullsRemoved[i] = messages[i];
    	}
    	
    	//return array
    	return messageWithNullsRemoved; 

    	
    }

    /**
     * For this main method, iterate over the numbers 1 through 115 and print
     * the relevant messages to sysout
     */
    public static void main(String[] args) {
        throw new NotImplementedException();
    }

}
