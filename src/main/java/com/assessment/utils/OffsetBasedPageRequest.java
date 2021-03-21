package com.assessment.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Custom pageable implementation to support pagination
 * based on offset and limit instead of page number and
 * page size
 *
 */
public class OffsetBasedPageRequest implements Pageable {
    private int limit;
    private int offset;
    private Sort sort;
    
    /**
     * Constructor to instantiate offset and limit in the page request
     * 
     * @param limit - Maximum number of records to be retrieved
     * in a page
     * @param offset - Starting index from which the records needs to
     * be retrieved
     */
    public OffsetBasedPageRequest(int limit, int offset) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        this.limit = limit;
        this.offset = offset;
    }
    
    /**
     * Constructor to instantiate offset, limit and sort object for
     * the page request
     * 
     * @param limit - Maximum number of records that needs to be retrieved
     * in a page
     * @param offset - Starting index from which the records needs to
     * be retrieved
     * @param sort - Sort order in which the records needs to be retrieved
     */
    public OffsetBasedPageRequest(int limit, int offset, Sort sort) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }
    
    /**
     * Retrieve page number
     */
    @Override
    public int getPageNumber() {
    	// Calculate the page number based on the offset and
    	// limit provided in request
        return offset / limit;
    }
    
    @Override
    public int getPageSize() {
        return limit;
    }
    
    @Override
    public long getOffset() {
        return offset;
    }
    
    @Override
    public Sort getSort() {
        return sort;
    }
    
    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() + getPageSize()));
    }
    
    public Pageable previous() {
        return hasPrevious() ?
                new OffsetBasedPageRequest(getPageSize(), (int) (getOffset() - getPageSize())): this;
    }
    
    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }
    
    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(getPageSize(), 0);
    }
    
    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}