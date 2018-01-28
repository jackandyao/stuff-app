package com.qbao.aisr.app.service.image;

import java.util.Map;

public interface IImageService {

	/**
	 * 如果上传成功，则返回map map包含的key：url（图片url） image_key (图片key）
	 * @param image
	 * @param imageType (png jpg等等)
	 * @return
	 */
	public Map<String, String> uploadImage(byte[] image, String imageType);
	
	public  byte[]  queryImage(String url);
	
	public int  delImage(String url);
}
