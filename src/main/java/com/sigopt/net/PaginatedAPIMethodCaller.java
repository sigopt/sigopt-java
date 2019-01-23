package com.sigopt.net;

import com.sigopt.model.APIObject;
import com.sigopt.model.APIResource;
import com.sigopt.model.Pagination;

public class PaginatedAPIMethodCaller<T extends APIObject> extends APIMethodCaller<Pagination<T>> {
    Class<T> subklass;

    public PaginatedAPIMethodCaller(String method, String path, Class<T> klass) {
        super(method, path);
        this.subklass = klass;
    }

    @Override
    protected Pagination<T> processBody(String body) {
        Pagination<T> ret = APIResource.constructPaginationFromJson(apiMethod.response.body, this.subklass);
        ret.bind(this);
        return ret;
    }
}
