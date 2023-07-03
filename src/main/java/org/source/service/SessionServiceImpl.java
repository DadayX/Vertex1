package org.source.service;

import org.source.entity.Transaction;

public interface SessionServiceImpl {
    public String getSessionId();
    public String injectData(Transaction transaction);
}
