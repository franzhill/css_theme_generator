package com.fhi.fjl.util.date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class representing a 24hour time (ex : 23:58)
 * Wraps a Calendar that actually is the component holding the time. (allows us to benefit from all Calendar functionalities)
 *
 * Made mutable to integrate effortlessly in the Struts paradigm
 * (fields of form are mapped as so : CalendrierBO.JourBO.hours)
 *
 * @author fhill
 * @since 8 juil. 2011
 */


public class Time
{
	//#private int hours;
	//#private int minutes;

	/**
	 * Wrapped Calendar (Date is deprecated...) object
	 * @param hours
	 * @param minutes
	 */
	private final Calendar calendar = new GregorianCalendar();

	/**
	 * Hours have not been set.
	 */
	private boolean undefinedHours = true;

	/**
	 * Minutes have not been set.
	 */
	private boolean undefinedMinutes = true;



	/**
	 *
	 * @param hours
	 * @param minutes
	 * @throws ArrayIndexOutOfBoundsException if hours or minutes specified outside conventional ranges
	 */
	public Time (int hours, int minutes)
	{	this();
		this.setHours(hours);
		this.setMinutes(minutes);
	}


	public Time()
	{	calendar.clear();
	}




	/**
	 *
	 * @param t
	 * @return true if this time is before the passed time
	 */
	public boolean before(Time t)
	{	return this.calendar.before(t.calendar);
	}

	public boolean after(Time t)
	{	return this.calendar.after(t.calendar);
	}


	@Override
	public String toString()
	{
		return "Time = [ " + this.getHours() + ":" + this.getMinutes() + "]";
	}


	@Override
	public boolean equals(Object o)
	{	if (!(o instanceof Time))
			return false;
		Time t = (Time) o;
		return this.calendar.equals(t.calendar);
	}

	/**
	 * Since we override equals we have to override hashCode too.
	 * [Bloch Item 9]
	 */
	@Override
	public int hashCode()
	{	return this.calendar.hashCode();
	}


	/**
	 *
	 * @return 0 if hours not defined
	 */
	public int getHours()
	{	if (this.undefinedHours)
			return 0;
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	public void setHours(int hours)
	{	if ( hours < 0 || hours > 23)
			throw new IllegalArgumentException("hours should be between 0 and 23");

		this.calendar.set(Calendar.HOUR_OF_DAY, hours);
		this.undefinedHours = false;
	}

	/**
	 *
	 * @return 0 if minutes not defined
	 */
	public int getMinutes()
	{
		if (this.undefinedMinutes)
			return 0;
		return calendar.get(Calendar.MINUTE);
	}
	public void setMinutes(int min)
	{	if ( min < 0 || min > 59)
			throw new IllegalArgumentException("minutes should be between 0 and 59");

		this.calendar.set(Calendar.MINUTE, min);
		this.undefinedMinutes = false;
	}




}
