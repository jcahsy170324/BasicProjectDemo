package com.lg.bsp.service;

import com.lg.bsp.model.OperationRecord;

import java.util.List;

public interface OperationRecordService {
    Integer insert(OperationRecord record);
    Integer batchInsert(List<OperationRecord> list);
}
