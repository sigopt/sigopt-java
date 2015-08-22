package com.sigopt.model;

import com.sigopt.exception.APIException;
import com.sigopt.net.APIObject;

import java.lang.reflect.Type;
import java.util.List;

public class Pagination<T> extends APIObject {
    int count;
    List<T> data;
    Paging paging;

    public Pagination() {
    }

    public Pagination(int count, List<T> data, Paging paging) {
        this.count = count;
        this.data = data;
        this.paging = paging;
    }

    public int getCount() {
        return this.count;
    }

    public List<T> getData() {
        return this.data;
    }

    public Paging getPaging() {
        return this.paging;
    }

    public static class Builder<TT> {
        int count;
        List<TT> data;
        Paging paging;

        public Pagination<TT> build() {
            return new Pagination<TT>(count, data, paging);
        }

        public Builder<TT> count(int count) {
            this.count = count;
            return this;
        }

        public Builder<TT> data(List<TT> data) {
            this.data = data;
            return this;
        }

        public Builder<TT> paging(Paging paging) {
            this.paging = paging;
            return this;
        }
    }
}

class Paging extends APIObject {
    String before;
    String after;

    public Paging(String before, String after) {
        this.before = before;
        this.after = after;
    }

    public String getBefore() {
        return this.before;
    }

    public String getAfter() {
        return this.after;
    }

    public static class Builder {
        String before;
        String after;

        public Paging build() {
            return new Paging(before, after);
        }

        public Builder before(String before) {
            this.before = before;
            return this;
        }

        public Builder after(String after) {
            this.after = after;
            return this;
        }
    }
}
