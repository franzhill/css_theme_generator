package com.fhi.fjl.web.html;



/**
 * General purpose test class
 * Just write the piece of code to test inside the main() function, and run this class.
 *
 *
 * @author fhill
 * @since 13 déc. 2011
 *
 */
public class Runnable_Test
{
	public static void main(String[] args)
	{
		test_applySizeFactor();
	}

	public static void test_applySizeFactor()
	{
		String input = "15.2px 20.4px 22px 30em 3.45em #A0B734";
		Double factor= (double) 2;
		test_applySizeFactor_sub(input, factor);

		input="0px 10px 3px 0px";
		factor= 2.0;
		test_applySizeFactor_sub(input, factor);

		input="12px";
		factor = 1.6;
		test_applySizeFactor_sub(input, factor);

		input="19.2px";
		factor=2.2;
		test_applySizeFactor_sub(input, factor);

		input="199.23px";
		factor=22.42;
		test_applySizeFactor_sub(input, factor);

		input="";
		factor=22.42;
		test_applySizeFactor_sub(input, factor);

		input=null;
		factor=22.42;
		test_applySizeFactor_sub(input, factor);

	}


	private static void test_applySizeFactor_sub(String input, Double factor)
	{
		System.out.println("\ninput=" + input + ", factor=" + factor);
		String res = HtmlUtils.applySizeFactorDouble(input, factor);
		System.out.println("res=" + res);
	}

}


