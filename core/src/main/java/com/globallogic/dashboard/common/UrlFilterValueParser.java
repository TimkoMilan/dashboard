package com.globallogic.dashboard.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class UrlFilterValueParser implements FilterValuesParser {

    @Override
    public Map<String, String> parse(String filterValue) {
        try {
            Map<String, String> data = new HashMap<>();
            String[] urlParts = filterValue.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }
                    data.put(key, value);
                }
            }
            return data;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }


}
