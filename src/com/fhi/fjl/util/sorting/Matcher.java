package com.fhi.fjl.util.sorting;

/**
 *
 * @author fhill
 * @since 10 mars 2011
 *
 * @param <T>
 */
public interface Matcher<T>
{
	/**
	 * Return true if t is considered to match.
	 *
	 * @param t
	 * @return
	 */
	public boolean matches( T t );
}




