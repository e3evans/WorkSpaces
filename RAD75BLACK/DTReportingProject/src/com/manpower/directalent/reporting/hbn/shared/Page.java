package com.manpower.directalent.reporting.hbn.shared;

import java.util.List;

/**
 * @author MartinMuguiro
 */
public interface Page {

  boolean isFirstPage();

  boolean isLastPage();

  boolean hasNextPage();

  boolean hasPreviousPage();

  int getLastPageNumber();

  @SuppressWarnings("unchecked")
List getThisPageElements();

  int getTotalNumberOfElements();

  int getThisPageFirstElementNumber();

  int getThisPageLastElementNumber();

  int getNextPageNumber();

  int getPreviousPageNumber();

  int getPageSize();

  int getPageNumber();
  
  boolean isDataValid();
}