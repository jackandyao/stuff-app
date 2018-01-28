/**
 * 
 */
package com.qbao.aisr.app.common.util;

import com.qbao.aisr.stuff.alarm.facade.AlaramServiceFacotryFacade;
import com.qbao.aisr.stuff.alarm.util.MessageConstanUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liaijun
 * @createTime 17/3/27
 * $$LastChangedDate: 2017-04-18 14:31:34 +0800 (Tue, 18 Apr 2017) $$
 * $$LastChangedRevision: 683 $$
 * $$LastChangedBy: wangping $$
 */
public class NotifierUtil {

    public static Set<String> phoneMsgKeys = new HashSet<>();
    public static Set<String> mailMsgKeys = new HashSet<>();
    static Logger logger = Logger.getLogger(NotifierUtil.class);

    public static void notifyByPhone(String msg) {

        if (!phoneMsgKeys.contains(msg)) {
            phoneMsgKeys.add(msg);
            AlaramServiceFacotryFacade.sendAlaramMessageByPhone(MessageConstanUtil.PHONE_ERROR, msg);
        }
    }

    public static void notifyByEmail(String subject, String message) {
        if (!mailMsgKeys.contains(subject)) {
            mailMsgKeys.add(subject);
            SMTPAppender appender = new SMTPAppender();
            try {
                appender.setSMTPUsername("jackaiyaoforever@126.com");
                appender.setSMTPPassword("aa11ss33");
                appender.setTo("786648643@qq.com,sjwangping@qbao.com,jiahongping@qbao.com,liaijun@qbao.com,louxueming@qbao.com,yaojie@qbao.com,sjzhangjun@qbao.com,122715341@qq.com");
                appender.setFrom("jackaiyaoforever@126.com");
                appender.setSMTPHost("smtp.126.com");
                appender.setLocationInfo(true);
                appender.setSubject(subject);
                appender.setLayout(new PatternLayout());
                appender.activateOptions();
                logger.addAppender(appender);
                logger.error(message.toString());
            } catch (Exception e) {
                logger.error("调用LOG4J发送邮件失败", e);
            }
        }
    }
}
