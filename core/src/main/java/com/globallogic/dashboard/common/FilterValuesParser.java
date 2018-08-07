package com.globallogic.dashboard.common;

import java.util.Map;

public interface FilterValuesParser {

    Map<String, String> parse(String filterValue);
}
