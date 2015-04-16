package com.sigopt.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class APIResourceTestBase {
    protected String resource(String path) throws IOException {
        InputStream resource = getClass().getResourceAsStream(path);

        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buf = new byte [1024];

        for( int i = resource.read(buf); i > 0; i = resource.read(buf)) {
            os.write(buf,0,i);
        }

        return os.toString("utf8");
    }
}
