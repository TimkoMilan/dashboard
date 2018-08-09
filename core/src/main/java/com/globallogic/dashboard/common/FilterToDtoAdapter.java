package com.globallogic.dashboard.common;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;


public class FilterToDtoAdapter<T> {
    private Class<T> aClass;
    private String filter;
    private FilterValuesParser filterValuesParser;

    public FilterToDtoAdapter(String filter, Class<T> aClass, FilterValuesParser filterValuesParser) {
        this.aClass = aClass;
        this.filter = filter;
        this.filterValuesParser = filterValuesParser;
    }



    public <t> T getDto() {
        try {


            Map<String, String> filterValues = filterValuesParser.parse(filter);

            T t = aClass.newInstance();

            for (String filterKey : filterValues.keySet()) {
                String key = StringUtils.capitalize(filterKey);
                String methodName = "set" + key;
                Method methodByName = findMethodByName(methodName, t);
                Class<?> parameterType = methodByName.getParameterTypes()[0]; //we can do this, as we are calling setter
                Object convert = ConvertUtils.convert(filterValues.get(filterKey), parameterType);
                MethodUtils.invokeExactMethod(t, methodName, convert);


            }
            return t;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
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
