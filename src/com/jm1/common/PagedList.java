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
	
	
	public List<T> items;
	
	public PagedList(List<T> items, int total, int pageIndex, int pageSize) {
		this.items = items;
		this.recordTotal = total;
		this.pageTotal = total / pageSize + (total % pageSize > 0 ? 1 : 0);
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	
	
	public int recordTotal;
	
	public int pageTotal;
	
	public int pageSize;
	
	public int pageIndex;
}
