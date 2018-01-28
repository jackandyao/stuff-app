package com.qbao.aisr.app.service.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.model.SysMessage;
import com.qbao.aisr.app.repository.mybatis.dao.message.SysMessageDao;
@Service
public class SysMessageServiceImpl implements ISysMessageService {

    @Autowired
    private SysMessageDao sysMessageDao;
    
    
    @Override
    public int updateSysMessageStatus(SysMessage sysMessage) {
        return sysMessageDao.updateSysMessageStatus(sysMessage);
    }
    @Override
    public List<SysMessage> querySysMessageByUserId(Long userId) {
        
        return sysMessageDao.querySysMessageByUserId(userId);
    }

}
