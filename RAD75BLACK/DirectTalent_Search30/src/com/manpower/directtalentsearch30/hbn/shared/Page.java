package com.manpower.directtalentsearch30.hbn.shared;

import java.util.List;

/**
 * @author MartinMuguiro
 */
@SuppressWarnings("unchecked")
public interface Page {

  boolean isFirstPage();

  boolean isLastPage();

  boolean hasNextPage();

  boolean hasPreviousPage();

  int getLastPageNumber();

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