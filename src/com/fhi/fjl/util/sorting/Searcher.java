package com.fhi.fjl.util.sorting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 *
 * @author fhill
 * @since 10 mars 2011
 *
 */
public class Searcher
{

	/**
	 * Return list of objects that satisfy criteria provided by matcher,
	 * amongst given list of objects.
	 * @param <T>
	 * @param list
	 * @param m
	 * @return
	 */
	public static <T> List<T> searchIn( List<T> list , Matcher<T> m )
	{
		List<T> r = new ArrayList<T>();
		for( T t : list )
		{
			if( m.matches( t ) )
			{	r.add( t );
			}
		}
		return r;
	}



	/**
	 * In a map, return the list of keys that hold the specified value.
	 * @param <T>
	 * @param <E>
	 * @param map
	 * @param value
	 * @return
	 */
	public static <T, E> Set<T> getKeysForValue(Map<T, E> map, E value)
	{
     Set<T> keys = new HashSet<T>();
     for (Entry<T, E> entry : map.entrySet()) {
         if (entry.getValue().equals(value)) {
             keys.add(entry.getKey());
         }
     }
     return keys;
	}
}
