package com.willowtree.notahitlist.api.converter;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.nano.MessageNano;
import com.willowtree.notahitlist.TargetProto;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by WillowTree, Inc on 6/8/16.
 */

public class ProtoResponseBodyConverter<T extends MessageNano>
        implements Converter<ResponseBody, T> {

    ProtoResponseBodyConverter() {
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {
            return (T) TargetProto.Targets.parseFrom(value.bytes());
        } catch (InvalidProtocolBufferException e) {
            return null; //throw new RuntimeException(e); // Despite extending IOException, this is data mismatch.
        } finally {
            value.close();
        }
    }
}