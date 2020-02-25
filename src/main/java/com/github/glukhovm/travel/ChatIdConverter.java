package com.github.glukhovm.travel;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

public interface ChatIdConverter extends SessionKey, SessionIdGenerator {
    void setSessionId(Serializable sessionId);
}