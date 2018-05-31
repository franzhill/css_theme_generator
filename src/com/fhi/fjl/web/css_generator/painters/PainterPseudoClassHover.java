package com.fhi.fjl.web.css_generator.painters;

public class PainterPseudoClassHover extends PainterPseudoClass
{

	public PainterPseudoClassHover(IPainter painterPseudoClass)
	{	super(painterPseudoClass);
	}

	@Override
	String getPseudoClassName()
	{	return "hover";
	}


}
