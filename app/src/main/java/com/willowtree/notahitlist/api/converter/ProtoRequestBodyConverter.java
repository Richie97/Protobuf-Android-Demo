package com.willowtree.notahitlist.api.converter;

import com.google.protobuf.nano.MessageNano;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by WillowTree, Inc on 6/8/16.
 */

public class ProtoRequestBodyConverter<T extends MessageNano> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");

    @Override
    public RequestBody convert(T value) throws IOException {
        byte[] bytes = MessageNano.toByteArray(value);
        return RequestBody.create(MEDIA_TYPE, bytes);
    }
}
