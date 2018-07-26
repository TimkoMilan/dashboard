package com.globallogic.dashboard.loader;

import java.util.List;
import java.util.stream.Collectors;

public class DataLoaderUtil {
    private DataLoaderUtil() {
    }

    public static List<String> toListOfStrings(List<Object> list) {
        return list.stream().map(Object::toString).collect(Collectors.toList());
    }
}
