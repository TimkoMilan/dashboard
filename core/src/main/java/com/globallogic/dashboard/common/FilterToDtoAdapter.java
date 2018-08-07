package com.globallogic.dashboard.common;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterToDtoAdapter<T> {
    private Class<T> aClass;
    private String filter;

    public FilterToDtoAdapter(String filter, Class<T> aClass) {
        this.aClass = aClass;
        this.filter = filter;
    }

    public T getDto() {
        //todo validation & reflexion magic over aClass & return newly created instance
        try {
            Map<String, List<String>> params = new HashMap<>();
            String[] urlParts = filter.split("\\?");
            if (urlParts.length < 2) {
                return null;
            }
            String query = urlParts[1];
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                String key = URLDecoder.decode(pair[0], "UTF-8");
                String value = "";
                if (pair.length > 1) {
                    value = URLDecoder.decode(pair[1], "UTF-8");
                }
                if ("".equals(key) && pair.length == 1) {
                    continue;
                }

                List<String> values = params.get(key);
                if (values == null) {
                    values = new ArrayList<String>();
                    params.put(key, values);
                }
                values.add(value);
            }
            System.out.println(params);
            T t = aClass.newInstance();
            MethodUtils.invokeExactMethod(t,"setMemberId",filter);


            return t;

        } catch (Exception e) {
            throw new ServiceException("Error while parsing Data");
        }
    }

}
