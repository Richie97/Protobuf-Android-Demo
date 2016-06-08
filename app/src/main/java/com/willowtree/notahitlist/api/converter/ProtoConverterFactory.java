package com.willowtree.notahitlist.api.converter;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.nano.MessageNano;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by WillowTree, Inc on 6/8/16.
 */

public final class ProtoConverterFactory extends Converter.Factory {
    public static ProtoConverterFactory create() {
        return new ProtoConverterFactory(null);
    }

    /** Create an instance which uses {@code registry} when deserializing. */
    public static ProtoConverterFactory createWithRegistry(ExtensionRegistryLite registry) {
        return new ProtoConverterFactory(registry);
    }

    private final ExtensionRegistryLite registry;

    private ProtoConverterFactory(ExtensionRegistryLite registry) {
        this.registry = registry;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }
        Class<?> c = (Class<?>) type;
        if (!MessageNano.class.isAssignableFrom(c)) {
            return null;
        }
        return new ProtoResponseBodyConverter<>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (!(type instanceof Class<?>)) {
            return null;
        }
        if (!MessageNano.class.isAssignableFrom((Class<?>) type)) {
            return null;
        }
        return new ProtoRequestBodyConverter<>();
    }
}

