/* 	
	Assignment #:			Assignment 9
	Name: 					David Warren II
	ID:						1205006331
	Lecture Number/Time:	T and Thursday 3:00 - 4:15 PM
	Class Description:		This inputs a list of numbers, finds the minimum number, total number of even
							numbers, sum of the negative numbers, and the number of numbers greater than
							the first number with revursive methods.
*/
import java.util.*;
import java.io.*;

public class Assignment9 
{
	public static void main(String[] args)
	{
		int count = 0; // the amount of numbers in the array
		int[] numbers = new int[100]; // the array for the integers
		String[] input = new String[100]; // the inputs from the file

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			boolean check = true; // this is just to check for EOF

			do {
				input[count] = br.readLine();
				numbers[count] = Integer.parseInt(input[count]); // changes string to integer
				if(numbers[count] == 0)
					check = false; // if EOF then stop inputting
				count++;
			} while(check);

		}
		catch(IOException exception) {
			System.out.println(exception);
		}

		System.out.println("The minimum number is " + findMin(numbers, 0, numbers.length - 1));
		System.out.println("The total count of even numbers is " + countEven(numbers, 0, numbers.length - 1));
		System.out.println("The sum of negative numbers is " + computeSumOfNegatives(numbers, 0, numbers.length - 1));
		System.out.println("The total count of numbers that are greater than the first is " + countGreaterThanFirst(numbers, 0, 1));
	}
	/* This method finds the minimum by comparing the first to the last. If the last is
		the lowest, it compares it to the second. If the first is lowest, it compares
		it to the second to last, etc.
	*/ 
	public static int findMin(int[] numbers, int startIndex, int endIndex)
	{
		if(endIndex - startIndex == 0) 
			return numbers[startIndex];
		else {
			if(numbers[startIndex] < numbers[endIndex])
				return findMin(numbers, startIndex, endIndex - 1);
			else if(numbers[startIndex] > numbers[endIndex])
				return findMin(numbers, startIndex + 1, endIndex);
			else
				return findMin(numbers, startIndex, endIndex - 1);
		}
	}

	/* This methods finds the amount of even numbers by checking if that number mod 2 
		is 0. If it is, then it counts up and then goes to the next number. If it is
		zero, it ends the recursion and adds one because zero is technically even.
	*/
	public static int countEven(int[] numbers, int startIndex, int endIndex)
	{
		if(numbers[startIndex] == 0)
			return numbers[startIndex] + 1;
		else {
			if(endIndex - startIndex == 0)
				return numbers[startIndex];
			else {
				if((numbers[startIndex] % 2) == 0)
					return countEven(numbers, startIndex + 1, endIndex) + 1;
				else
					return countEven(numbers, startIndex + 1, endIndex);
			}
		}
	}

	/* This methods finds the sum of negative numbers. It checks for a zero in the array.
		Then it checks to make sure that the distance between the indices is not zero.
		Then if that number < 0, it adds it, otherwise it just moves on to the next one.
	*/
	public static int computeSumOfNegatives(int[] numbers, int startIndex, int endIndex) 
	{
		if(numbers[startIndex] == 0)
			return numbers[startIndex];
		else {
			if(endIndex - startIndex == 0)     	    
    			return numbers[startIndex];
    		else {
    		 	if(numbers[startIndex] < 0) 	
    		    	return numbers[startIndex] + computeSumOfNegatives(numbers, startIndex + 1 , endIndex);   	   
    		    else 
    		    	return computeSumOfNegatives(numbers, startIndex + 1, endIndex);
    		}
		}
	}

	/* This method checks if how many entries are greater than the first entry. It counts up
		to compare all of them until the zero shows up in the array
	*/
	public static int countGreaterThanFirst(int[] numbers, int startIndex, int endIndex)
	{
		int count = 0;

		if(numbers[endIndex] == 0) {
			if(numbers[0] < 0)
				return count + 1;
			else
				return count;
		}
		else {
			if(numbers[0] < numbers[endIndex]) {
				count++;
				return count + countGreaterThanFirst(numbers, 0, endIndex + 1);
			}
			else
				return count + countGreaterThanFirst(numbers, 0, endIndex + 1);
		}
	}
}
