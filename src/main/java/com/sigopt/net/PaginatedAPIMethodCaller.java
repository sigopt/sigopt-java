package com.sigopt.net;

import com.sigopt.model.APIObject;
import com.sigopt.model.APIResource;
import com.sigopt.model.Pagination;

public class PaginatedAPIMethodCaller<T extends APIObject> extends APIMethodCaller<Pagination<T>> {
    Class<T> subklass;

    public PaginatedAPIMethodCaller(String method, String path, Class<T> klass) {
        super(method, path);
        this.subklass = subklass;
    }

    @Override
    protected Pagination<T> processBody(String body) {
        return APIResource.constructPaginationFromJson(apiMethod.response.body, this.subklass);
    }
}
