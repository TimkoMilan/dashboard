package com.globallogic.dashboard.old;

import java.util.List;
import java.util.stream.Collectors;

public class DataLoaderUtil {
    public static List<String> toListOfStrings(List<Object> list) {
        return list.stream().map(Object::toString).collect(Collectors.toList());
    }
}
