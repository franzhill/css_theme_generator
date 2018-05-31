package com.fhi.fjl.lang.integer.tests;





/**
 * General purpose test class
 * Just write the piece of code to test inside the main() function, and run this class.
 *
 *
 * @author fhill
 * @since
 *
 */
public class Runnable_Test
{
	public static void main(String[] args)
	{
		test_1();
	}

	public static void test_1()
	{
		test_1_sub(-1);
		test_1_sub(0);
		test_1_sub(4);
		test_1_sub(20);
		test_1_sub(25);
		test_1_sub(222);
	}

	public static void test_1_sub(int val)
	{	System.out.println("Trying to assign value to IntegerConstrainedExample: " + val);

		IntegerConstrainedExample val_constr = new IntegerConstrainedExample(val);

		System.out.println("IntegerConstrainedExample value: " + val_constr.getValue());
		System.out.println("");
	}



}


