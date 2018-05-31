package com.fhi.fjl.web.color.palette.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author u103860
 * @since 15 nov. 2012
 *
 */
public enum ETypeColorSet
{
	PRIMARY      ( "primary"       ),
	SECONDARY_A  ( "secondary-a"   ),
	SECONDARY_B  ( "secondary-b"   ),
	COMPLEMENT   ( "complement"    ),
	DEFAULT      ( "default"       );

	/**
	 * Value for that type, in database
	 */
	private final String value;
	public String getValue() {	return value; }

	// This constructor is used by Java to build the type
	ETypeColorSet(String value)
	{	this.value      = value;
	}



	// ===============================================================
	// REVERSE LOOKUP
	// ===============================================================
	private static final Map<String,ETypeColorSet> lookup = new HashMap<String,ETypeColorSet>();

	static {	for(ETypeColorSet s : EnumSet.allOf(ETypeColorSet.class))
	        	{	lookup.put(s.getValue(), s);
	        	}
	       }

	/**
	 * Reverse lookup: get a Status from its code
	 */
	public static ETypeColorSet lookup(String value)  {	return lookup.get(value); }


}