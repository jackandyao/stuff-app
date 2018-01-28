package com.qbao.aisr.app.service.facade;

import com.qbao.aisr.app.dto.StuffAppealDetailDto;
import com.qbao.aisr.app.dto.StuffAppealDto;
import com.qbao.aisr.app.service.stuffappeal.IStuffAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午11:59
 * $$LastChangedDate: 2017-03-30 16:51:33 +0800 (Thu, 30 Mar 2017) $$
 * $$LastChangedRevision: 595 $$
 * $$LastChangedBy: zhangjun $$
 */
@Component
public class StuffAppealFacade {
    @Autowired
    private IStuffAppealService stuffAppealService;

    /**
     * 是否可以提交申诉
     * @param userId
     * @return
     */
	public Boolean right(Long userId) {
		return stuffAppealService.right(userId);
	}

	/**
	 * 图片上传
	 * @param userId
	 * @param file
	 * @return
	 */
	public Map<String, String> upload(Long userId, byte[] image,String imageType) {
		return stuffAppealService.upload(userId, image, imageType);
	}

	/**
	 * 申诉提交
	 * @param userId
	 * @param orderId
	 * @param source
	 * @param device
	 * @param phoneType
	 * @param content
	 * @param reason
	 * @param imgUrl
	 * @param qq
	 * @param phone
	 */
	public void submit(Long userId, String orderId, String source, int device, String phoneBrand, String phoneType, String content,
			String reason, String imgUrl, String qq, String phone) {
		stuffAppealService.submit(userId, orderId,source,device,phoneBrand,phoneType,content,reason,imgUrl,qq,phone);
	}

	/**
	 * 申诉/售后
	 * @param userId
	 * @return
	 */
	public List<StuffAppealDto> list(Long userId) {
		return stuffAppealService.list(userId);
	}

	/**
	 * 取消申诉
	 * @param appealId
	 * @param userId
	 */
	public void cancel(Long appealId, Long userId) {
		stuffAppealService.cancel(appealId, userId);
		
	}

	/**
	 * 申诉详细
	 * @param appealId
	 * @param userId
	 * @return
	 */
	public StuffAppealDetailDto detail(Long appealId, Long userId) {
		return stuffAppealService.detail(appealId, userId);
	}


}
