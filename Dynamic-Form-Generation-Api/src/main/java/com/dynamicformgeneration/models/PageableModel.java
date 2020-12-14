package com.dynamicformgeneration.models;

import java.util.List;

public class PageableModel {

	 private Long totalRows;

	  private List<?> items;

	  /**
	   * @return the totalRows
	   */
	  public Long getTotalRows() {
	    return totalRows;
	  }

	  /**
	   * @param totalRows the totalRows to set
	   */
	  public void setTotalRows(Long totalRows) {
	    this.totalRows = totalRows;
	  }

	  /**
	   * @return the items
	   */
	  public List<?> getItems() {
	    return items;
	  }

	  /**
	   * @param items the items to set
	   */
	  public void setItems(List<?> items) {
	    this.items = items;
	  }
}
