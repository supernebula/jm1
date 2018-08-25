/**
 * 
 */
package com.jm1.common;

import java.util.List;

/**
 * @author evol
 *
 */
public class PagedList<T> {
	
	
	private List<T> items;
	
	public PagedList(List<T> items, int total) {
		this.items = items;
		recordTotal = total;
	}
	
	public int recordTotal;
	
	public int pageTotal;
	
	public int pageSize;
	
	public int pageIndex;
}
