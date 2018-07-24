package com.globallogic.dashboard.team;

import com.globallogic.dashboard.common.BusinessException;

public class TeamException extends BusinessException {

    public TeamException(String message, String... params) {
        super(message, params);
    }
}
