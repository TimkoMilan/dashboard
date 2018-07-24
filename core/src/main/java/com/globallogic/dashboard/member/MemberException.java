package com.globallogic.dashboard.member;

import com.globallogic.dashboard.common.BusinessException;

public class MemberException extends BusinessException {
    public MemberException(String message, String... params) {
        super(message, params);
    }
}
