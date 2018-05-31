package com.fhi.fjl.web.color.palette;

import java.util.ArrayList;
import java.util.List;

import com.fhi.fjl.web.color.Color2;
import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;

public class ColorSet
{
	private String        id;
	private String        title;
	private ETypeColorSet type;
	private List<Color2>   colors;


	public ColorSet(String id, String title, ETypeColorSet type, List<Color2>   colors)
	{
		this.id    = id;
		this.title = title;
		this.type  = type;
		this.colors= colors;
	}


	public String getId()
	{	return id;
	}

	public String getTitle()
	{	return title;
	}

	public ETypeColorSet getType()
	{	return type;
	}

	public List<Color2> getColors()
	{	return colors;
	}


	@Override
	public String toString()
	{
		return
		                                           "\n" +
		"Colorset = [id="        + id    +        ",\n" +
		"            title="     + title +        ",\n" +
		"            type="      + type  +        ",\n" +
		"            colors="    + colors +        "\n" +
		"           ]";
	}

	/**
	 * 0 for base color
	 * -1 lighter
	 * -2 event lighter
	 * 1 darker
	 * 2 even darker
	 * @param shade
	 */
	public Color2 getColorByShade(ETypeColorShade shade)
	{
		return colors.get(shade.getValueInPaletteXml());
	}


	/**
	 * Alias for {@link #getColorByShade(int)}
	 * @param shade
	 * @return
	 */
	public Color2 getShade(ETypeColorShade shade)
	{
		return getColorByShade(shade);
	}


	public static ColorSet empty()
	{
		List<Color2>   listColors = new ArrayList<Color2>();
		               listColors.add(new Color2("default-1", 1, "9B9892"));
		               listColors.add(new Color2("default-2", 2, "AFABA5"));
		               listColors.add(new Color2("default-3", 3, "C2BEB7"));
		               listColors.add(new Color2("default-4", 4, "C8C4BE"));
		               listColors.add(new Color2("default-5", 5, "CECBC5"));
		ColorSet empty = grey_1();
		return empty;
	}


	private static ColorSet grey_1()
	{
		List<Color2>   listColors = new ArrayList<Color2>();
		               listColors.add(new Color2("default-1", 1, "C2BEB7"));
		               listColors.add(new Color2("default-2", 2, "C8C4BE"));
		               listColors.add(new Color2("default-3", 3, "CECBC5"));
		               listColors.add(new Color2("default-4", 4, "AFABA5"));
		               listColors.add(new Color2("default-5", 5, "9B9892"));


		ColorSet ret = new ColorSet("grey_1", "grey_1", ETypeColorSet.DEFAULT, listColors);
		return ret;
	}


	private static ColorSet grey_2()
	{
		List<Color2>   listColors = new ArrayList<Color2>();
		               listColors.add(new Color2("default-1", 1, "DBDBDB"));
		               listColors.add(new Color2("default-2", 2, "D2D2D2"));
		               listColors.add(new Color2("default-3", 3, "CACACA"));
		               listColors.add(new Color2("default-4", 4, "E4E4E4"));
		               listColors.add(new Color2("default-5", 5, "EDEDED"));
		ColorSet ret = new ColorSet("grey_1", "grey_1", ETypeColorSet.DEFAULT, listColors);
		return ret;
	}

}
