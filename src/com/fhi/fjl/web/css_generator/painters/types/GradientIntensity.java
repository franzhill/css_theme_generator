package com.fhi.fjl.web.css_generator.painters.types;

import com.fhi.fjl.lang.integer.IntegerConstrained;

/**
 * An integer between 0 and 2
 * @author Francois
 * @since 1 déc. 2012
 *
 */
public class GradientIntensity extends IntegerConstrained
{
	public GradientIntensity(int number)
	{	super(number, 0, 4, true);
	}
}
