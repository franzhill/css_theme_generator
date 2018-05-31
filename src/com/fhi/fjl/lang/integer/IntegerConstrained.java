package com.fhi.fjl.lang.integer;

/**
 * Integer constrained to a range.                                                                                      <br />
 *                                                                                                                      <br />
 * If given a value higher (lower) than the accepted range, it will take the value of the high (low) range boundary,
 * or throw an exception, depending on the setting.                                                                     <br />
 *                                                                                                                      <br />
 * See subpackage "tests" to see how to extend this class.                                                              <br />
 * Example, nevertheless:
 *
 * <pre>
 * public class IntegerConstrainedExample extends IntegerConstrained
 * {
 * 	public IntegerConstrainedExample(int number)
 * 	{	super(number, -4, 3, true);     // number, low boundary, high boundary, throw exception?
 * 	}
 * }
 * </pre>
 *
 * @author fhi
 * @since 28 nov. 2012
 *
 */
public abstract class IntegerConstrained
{
	protected int     value;
	public    int     getValue()                 {return value;}


	protected IntegerConstrained(int number, int rangeBoundaryLow, int rangeBoundaryHigh, boolean throwExceptionIfOutOfRange)
	{
		if ((number > rangeBoundaryHigh || number < rangeBoundaryLow) && throwExceptionIfOutOfRange)
		{	throw new IllegalArgumentException("Constrained integer is out of range: " + number + ". Accepted range is: " + "[" + rangeBoundaryLow + ", " + rangeBoundaryHigh + "].");
		}
		if (number > rangeBoundaryHigh)
		{	value = rangeBoundaryHigh;
		}
		else if (number < rangeBoundaryLow)
		{	value = rangeBoundaryLow;
		}
		else
		{	value = number;
		}
	}


}
