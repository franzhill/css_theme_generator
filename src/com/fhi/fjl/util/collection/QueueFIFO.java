package com.fhi.fjl.util.collection;

import java.util.LinkedList;

/**
 *
 * @author fhill
 * Adapted from Jodd Team (jodd.org)
 */

public class QueueFIFO<E>
{
	  private final LinkedList<E> list = new LinkedList<E>();

	  /**
	   * Alias for push
	   */
	  public void put(E o) {
	  	push(o);
	  }

	  public void push(E o) {
	    list.addLast(o);
	  }


	  /**
	   * Returns an element (object) from queue.
	   *
	   * @return element from queue or <code>null</code> if queue is empty
	   */
	  public E pop() {
	    if (list.isEmpty()) {
	      return null;
	    }
	    return list.removeFirst();
	  }


	/**
	 * Alias for pop
	 */
	  public E get() {
	    if (list.isEmpty()) {
	      return null;
	    }
	    return list.removeFirst();
	  }


	  /**
	   * Returns all elements from the queue and clears it.
	   */
	  public Object[] getAll() {
	    Object[] res = new Object[list.size()];
	    for (int i = 0; i < res.length; i++) {
	      res[i] = list.get(i);
	    }
	    list.clear();
	    return res;
	  }


	  /**
	   * Peeks an element in the queue. Returned elements is not removed from the queue.
	   */
	  public E peek() {
	    return list.getFirst();
	  }

	  /**
	   * Returns <code>true</code> if queue is empty, otherwise <code>false</code>
	   */
	  public boolean isEmpty() {
	    return list.isEmpty();
	  }

	  /**
	   * Returns queue size.
	   */
	  public int size() {
	    return list.size();
	  }
	}
