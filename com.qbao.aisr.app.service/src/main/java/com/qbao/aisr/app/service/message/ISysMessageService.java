package com.qbao.aisr.app.service.message;

import java.util.List;

import com.qbao.aisr.app.model.SysMessage;

public interface ISysMessageService {
    public abstract List<SysMessage>querySysMessageByUserId(Long userId);
    public abstract int updateSysMessageStatus(SysMessage sysMessage);
}
