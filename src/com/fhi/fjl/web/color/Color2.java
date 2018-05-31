package com.fhi.fjl.web.color;

import java.awt.Color;

public class Color2 implements Comparable
{
	private String  id;

	/**
	 * Index (from 1 to approx 5) - See Colorset
	 */
	private int     index;
	/**
	 * Ex: "FF0096" (no leading "#" !)
	 */
	private String rgb;

	/**
	 * Internal representation
	 */
	private Color   color;

	public Color2(String id, int index, String rgb)
	{
		super();
		this.id = id;
		this.index = index;
		this.rgb = rgb;
		this.color = Color.decode("#" + this.rgb);
	}

	@Override
	public String toString()
	{
		return "Color2 = [id=" + id + ", index=" + index + ", rgb=" + rgb + "]";
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * I.e.
	 * <pre>
	 * 	x.compareTo(y)==0) == (x.equals(y)).
	 * </pre>
	 * is NOT true.
	 */
	public int compareTo(Object o)
	{
		Color2 compared = (Color2) o;
		if (this.index <  compared.index )   { return -1;}
		if (this.index == compared.index )   { return  0;}
		if (this.index >  compared.index )   { return  1;}

		return 0;
	}

	/**
	 * @return ex: #AA89B5
	 */
	public String printHex()
	{	return "#" + this.rgb;
	}

	public static Color2 empty()
	{	Color2 ret = new Color2("empty", 0, "000000");
		return ret;
	}

}
