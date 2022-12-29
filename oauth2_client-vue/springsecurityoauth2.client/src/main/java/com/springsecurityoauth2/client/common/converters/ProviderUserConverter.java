package com.springsecurityoauth2.client.common.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
