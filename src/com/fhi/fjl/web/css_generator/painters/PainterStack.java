package com.fhi.fjl.web.css_generator.painters;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;

import com.fhi.fjl.web.css_generator.rules.CssRule;

/**
 * Stack of painters
 *
 *
 *
 * @author francois hill
 * @since 29 nov. 2012
 *
 */
public class PainterStack extends Painter
{
	// ==========================================================================
	// LOGGING
	// ==========================================================================

	// Redefine preset fields :
	// See base parent class
	{
		logger  = LogFactory.getLog("com.fhi.fjl.web.css_generator.painters");
		debugOn = true
		                       && logger.isDebugEnabled();
	}


	// ==========================================================================
	// BUSINESS
	// ==========================================================================

	private ArrayList<IPainter> listPainters = new ArrayList<IPainter>();


	//#/**
	//# * Add a painter to this painter stack.
	//# * This painter stack as a result is modified.
	//# *
	//# * If the added painter is itself a painterStack, all the painters of that stack
	//# * are added one by one to this stack (in the natural order of the passed stack).
	//# *
	//# * If a painter of the same type as the added painter already exists in the stack, it will be
	//# * overwritten by the new painter.
	//# *
	//# * This is the ONLY function that can modify this painter stack. As a result, the
	//# * painter stack can be thought of as immutable aside of this function.
	//# *
	//# * @param painter
	//# * @return the modified painter stack (so as to enable method chaining)
	//# */

	/**
	 * Add a painter to this painter stack.                                                                                    <br />
	 *                                                                                                                         <br />
	 * First clones this painter stack, then adds the painter to this clone and finally returns the resulting painterStack.
	 * This painter stack is NOT modified.
	 * This way, a painterStack is immutable.                                                                                  <br />
	 *                                                                                                                         <br />
	 * If the added painter is itself a painterStack, all the painters of that stack
	 * are added one by one to this stack (in the natural order of the passed stack).                                          <br />
	 *                                                                                                                         <br />
	 * If not planning to use the overwrite feature, please use {@link #add(IPainter)} instead.                                <br />
	 *
	 * @param overwrite If a painter of the same type as the added painter already exists in the stack,
	 *                  it will be overwritten by the new painter if this is set to true.
	 *                  See {@link #add(IPainter)}
	 *
	 *
	 * @param painter
	 * @return a new painter stack (so as to enable method chaining)
	 */
	public PainterStack add(IPainter painter, boolean overwrite)
	{
		PainterStack ls = this.clone();
		//PainterStack ls = this;

		if (painter instanceof PainterStack)
		{	for (IPainter p : ((PainterStack) painter).listPainters)
			{	addSub(ls, p, overwrite);
			}
		}
		else
		{	addSub(ls, painter, overwrite);
		}
		return ls;
	}


	/**
	 * Convenience.
	 * Alias for <tt>add(painter, false)</tt>, which should be the default way of adding painters.
	 *
	 * @see {@link #add(IPainter, boolean)}
	 * @param painter
	 * @return
	 */
	@Override
	public PainterStack add(IPainter painter)
	{	return add(painter, false);
	}


	//#/**
	//# * Same as add but does not return a new, cloned stack, modifies this stack instead.
	//# * @param painter
	//# * @deprecated it's probably dangerous to use add without clone
	//# */
	//#public PainterStack addWithoutClone(IPainter painter)
	//#{
	//#	PainterStack ls = this;
	//#	return addSub(ls, painter);
	//#}


	/**
	 * Applies this painter stack to the passed css rule.
	 *
	 * @return
	 */
	public CssRule apply(CssRule rule, String pseudoClass)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("applying for pseudoClass = " + pseudoClass);
		/*        */	if (debug_on) logger.debug("compiling painter stack : " + this);
		PainterStack compiled = compile();
		/*        */	if (debug_on) logger.debug("compiled painter stack : " + compiled);
		for (IPainter p : compiled.listPainters)
		{	/*        */	if (debug_on) logger.debug("applying painter: " + p);
			p.apply(rule, pseudoClass);
		}
		/*        */	if (debug_on) logger.debug(">>");
		return rule;
	}


	/**
	 * Updates all painters of the stack with the passed painter.
	 */
	public boolean update(IPainter painter)
	{
		for (IPainter p : listPainters)
		{	p.update(painter);
		}
		return false;
	}


	@Override
	public PainterStack clone()
	{	PainterStack ret = (PainterStack) super.clone();
		// Deep clone the list
		ret.listPainters = new ArrayList<IPainter>(listPainters.size());
		for(IPainter p: listPainters)
		{	ret.listPainters.add(p.clone());
		}
		return ret;
	}


	@Override
	public String toString()
	{
		String ret = "";
		for (IPainter painter : listPainters)
		{	ret += "\n" + painter;
		}

		return                      "\n" +
		       "PainterStack = "  + "\n" +
		       "["                +
		       StringUtils.join(ret.split("\n"), "\n\t")
		                          + "\n" +
		       "]";
	}


	private PainterStack addSub(PainterStack ls, IPainter painter, boolean overwrite)
	{	/*        */	boolean debug_on = false && debugOn;
		/*        */	if (debug_on) logger.debug("<<");
		/*        */	if (debug_on) logger.debug("adding painter:" + painter.getClass().getSimpleName());
		/*        */	if (debug_on) logger.debug("stack before add :" + ls);

		if (overwrite)
		{
			// Remove all previous similar painters:
			// If an painter of same type exists: remove it: the newer one takes complete precedence:
			List<IPainter> listLayersToBeRemoved = new ArrayList<IPainter>();
			for (IPainter ret_painter: ls.listPainters)
			{	if (ret_painter.getClass().equals(painter.getClass()))          // DESIGN NOTE: possible weakness here . Comparing class is not ideal... (what if painters get exteended ?)
				{	listLayersToBeRemoved.add(ret_painter);
				}
			}
			ls.listPainters.removeAll(listLayersToBeRemoved);
			/*        */	if (debug_on) logger.debug("stack after removal:" + ls);
		}

		// Add
		/*        */	if (debug_on) logger.debug("Adding new painter...");
		ls.listPainters.add(painter);
		/*        */	if (debug_on) logger.debug("stack after add:" + ls);

		/*        */	if (debug_on) logger.debug(">>");
		return ls;
	}


	/**
	 * Return a compiled (i.e. all painters updated in a cascading fashion) version of this painter stack.
	 * Does not modify this painter stack.
	 * Made public to allow for debugging.
	 */
	public PainterStack compile()
	{	/*        */	boolean debug_on = true && debugOn;
		// Update all previous painters:
		// In what fashion the update action is carried along the stack could be CRUCIAL
		// and could give varying results, depending on whether a painter that can update other
		// painters can itself be updated : should it start from the bottom of the stack ? from the top ?

		PainterStack ret = this.clone();

		/*        */	if (debug_on) logger.debug("Updating previous painters...");

		//#// Method 1: Start from the last, update the preceding, and work our way to the beginning of the list
		//#ListIterator pit = listPainters.listIterator(listPainters.size());
		//#while(pit.hasPrevious())
		//#{
		//#	Painter updater = (Painter) pit.previous();
		//#	ListIterator pit2 = listPainters.listIterator(pit.previousIndex()+1);
		//#	while(pit2.hasPrevious())
		//#	{	((Painter) pit2.previous()).update(updater);
		//#	}
		//#}

		// Method 2: Start from the first (well, the second...), update all preceding painter(s),
		//           and start again with the next painter, until the the end of the list
		ListIterator<IPainter> pit = ret.listPainters.listIterator(0);

		boolean        should_remove   = false;
		List<IPainter> listToBeRemoved = new ArrayList<IPainter>();

		while(pit.hasNext())
		{
			Painter      updater = (Painter) pit.next();
			/*        */	if (debug_on) logger.debug("Updater:" + updater.getClass().getSimpleName());
			ListIterator<IPainter> pit2    = ret.listPainters.listIterator(pit.previousIndex());
			while(pit2.hasPrevious())
			{	should_remove = ((Painter) pit2.previous()).update(updater);
				if (should_remove) listToBeRemoved.add(updater);
			}
		}

		ret.listPainters.removeAll(listToBeRemoved);

		return ret;
	}






}
