package com.sigopt.model;

import com.sigopt.exception.APIException;
import com.sigopt.exception.SigoptException;
import com.sigopt.net.APIMethodCaller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class PaginationIterator<T extends APIObject> implements Iterator {
    Pagination<T> pagination;
    Iterator<T> page;
    T nextItem;

    public PaginationIterator(Pagination<T> pagination) {
        this.pagination = pagination;
        this.page = this.pagination.getData().iterator();
        this.advance();
    }

    private void advance() {
        try {
            while (!this.page.hasNext() && this.pagination.hasNext()) {
                this.pagination = this.pagination.nextPagination();
                this.page = this.pagination.getData().iterator();
            }

            if (this.page.hasNext()) {
                this.nextItem = this.page.next();
            } else {
                this.nextItem = null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch next data page", e);
        }
    }

    public boolean hasNext() {
        return (this.nextItem != null);
    }

    public T next() {
        T ret = this.nextItem;
        if (ret == null) {
            throw new NoSuchElementException();
        }
        this.advance();
        return ret;
    }
}

class PaginationIterable<T extends APIObject> implements Iterable {
    Pagination<T> pagination;

    public PaginationIterable(Pagination<T> pagination) {
        this.pagination = pagination;
    }

    public Iterator<T> iterator() {
        return new PaginationIterator(this.pagination);
    }
}

public class Pagination<T extends APIObject> extends StructObject {
    Class<T> klass;
    APIMethodCaller<Pagination<T>> apiMethodCaller;

    public Pagination(Class<T> klass) {
        super();
        this.klass = klass;
        this.apiMethodCaller = null;
    }

    public void bind(APIMethodCaller<Pagination<T>> apiMethodCaller) {
        this.apiMethodCaller = apiMethodCaller;
    }

    private boolean useBefore() {
        // TODO(patrick): Set this more appropriately
        return true;
    }

    boolean hasNext() {
        if (this.useBefore()) {
            return this.getPaging().getBefore() != null;
        } else {
            return this.getPaging().getAfter() != null;
        }
    }

    Pagination<T> nextPagination() throws SigoptException {
        if (this.useBefore()) {
            this.apiMethodCaller.addParam("before", this.getPaging().getBefore());
            this.apiMethodCaller.removeParam("after");
        } else {
            this.apiMethodCaller.addParam("after", this.getPaging().getAfter());
            this.apiMethodCaller.removeParam("before");
        }
        return this.apiMethodCaller.call();
    }

    public Integer getCount() {
        return Utils.asInteger(this.get("count"));
    }

    public List<T> getData() {
        return Utils.mergeIntoList(new ArrayList<T>(), this.get("data"), this.klass);
    }

    public Paging getPaging() {
        return Utils.mergeInto(new Paging(), this.get("paging"));
    }

    public Iterable<T> iteratePages() {
        return new PaginationIterable<T>(this);
    }
}

