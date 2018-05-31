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
public enum ETypeColorShade
{
	DARKER_2    ( -2   , 2),
	DARKER_1    ( -1   , 1),
	BASE        (  0   , 0),
	LIGHTER_1   (  1   , 3),
	LIGHTER_2   (  2   , 4);

	/**
	 * Value for that type, in database
	 */
	private final int value;
	public        int getValue() {	return value; }

	/**
	 * Index (positional) of that shade in Palette XML file
	 */
	private final int valueInPaletteXml;
	public        int getValueInPaletteXml() {	return valueInPaletteXml; }


	// This constructor is used by Java to build the type
	ETypeColorShade(int value, int valueInPaletteXml)
	{	this.value             = value;
		this.valueInPaletteXml = valueInPaletteXml;
	}


	// ===============================================================
	// REVERSE LOOKUP
	// ===============================================================
	private static final Map<Integer,ETypeColorShade> lookup = new HashMap<Integer,ETypeColorShade>();

	static {	for(ETypeColorShade s : EnumSet.allOf(ETypeColorShade.class))
	        	{	lookup.put(s.getValue(), s);
	        	}
	       }

	/**
	 * Reverse lookup: get a Status from its code
	 */
	public static ETypeColorShade lookup(int value)  {	return lookup.get(value); }


}