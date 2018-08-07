package com.globallogic.dashboard.common;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.net.URLDecoder;


public class FilterToDtoAdapter<T> {
    private Class<T> aClass;
    private String filter;
    @Autowired
    private ParsingUrl parsingUrl;

    public FilterToDtoAdapter(String filter, Class<T> aClass) {
        this.aClass = aClass;
        this.filter = filter;
    }

    private static final Logger log = LoggerFactory.getLogger(FilterToDtoAdapter.class);


    public <t> T getDto() {
        //todo validation & reflexion magic over aClass & return newly created instance
        try {
            parsingUrl.parsingUrlAdress(filter);

            String[] urlParts = filter.split("\\?");
            T t = aClass.newInstance();
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    key = StringUtils.capitalize(key);
                    String methodName = "set" + key;

                    Method methodByName = findMethodByName(methodName, t);
                    Class<?> parameterType = methodByName.getParameterTypes()[0];

                    Object convert = ConvertUtils.convert(value, parameterType);
                    MethodUtils.invokeExactMethod(t, methodName, convert);
                }
                return t;
            }

        } catch (Exception e) {
            throw new ServiceException((String.valueOf(e)));
        }
        return null;
    }

    private Method findMethodByName(String methodName, T t) {
        Method method = null;
        Method[] methods = t.getClass().getMethods();
        for (Method methodIter : methods) {
            if (methodIter.getName().equals(methodName)) {
                method = methodIter;
            }
        }
        return method;
    }

}
