package com.fhi.fjl.web.css_generator.painters;

import com.fhi.fjl.web.color.palette.types.ETypeColorSet;
import com.fhi.fjl.web.color.palette.types.ETypeColorShade;

/**
 * Paints a shine effect on a box.
 * This is nothing more than a convenience painter, short for a box shadow painter with the "inset" property.
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterBoxShine_DEPR extends PainterBoxShadow
{

	public PainterBoxShine_DEPR(ETypeColorSet colorSet, ETypeColorShade shade)
	{
		super("0px 1px 3px 0px", colorSet, shade, true);
	}




}
