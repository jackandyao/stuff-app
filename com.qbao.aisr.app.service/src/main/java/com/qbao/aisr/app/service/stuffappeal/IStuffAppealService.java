package com.qbao.aisr.app.service.stuffappeal;

import java.util.List;
import java.util.Map;

import com.qbao.aisr.app.dto.StuffAppealDetailDto;
import com.qbao.aisr.app.dto.StuffAppealDto;

/**
 * @author zhangjun
 * @createTime 17/5/24 上午11:58
 * $$LastChangedDate: 2017-03-11 20:41:57 +0800 (周六, 11 三月 2017) $$
 * $$LastChangedRevision: 150 $$
 * $$LastChangedBy: zhangjun $$
 */
public interface IStuffAppealService {

    /**
     * 是否可以提交申诉
     * @param appealId
     * @param userId
     * @return
     */
	public Boolean right(long userId);

	/**
	 * 图片上传
	 * @param userId
	 * @param image
	 * @param imageType
	 * @return
	 */
	public Map<String, String> upload(long userId, byte[] image,String imageType);

	/**
	 * 申诉提交
	 * @param appeal
	 */
	public void submit(long userId, String orderId, String source, int device, String phoneBrand, String phoneType, String content,
			String reason, String imgUrl, String qq, String phone);

	/**
	 * 申诉/售后
	 * @param userId
	 * @return
	 */
	public List<StuffAppealDto> list(long userId);

	/**
	 * 取消申诉
	 * @param appealId
	 * @param userId
	 */
	public void cancel(long appealId, long userId);

	/**
	 * 申诉详细
	 * @param appealId
	 * @param userId
	 * @return
	 */
	public StuffAppealDetailDto detail(long appealId, long userId);

}
