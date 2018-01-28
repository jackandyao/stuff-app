package com.qbao.aisr.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.stream.FileImageInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.service.stuffappeal.IStuffAppealService;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 16:48 
 * $$LastChangedDate$$ 
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring_service.xml" })
public class StuffSearchServiceTest {

//	@Autowired
//	ISearchStuffService iSearchStuffService;
//
//	@Autowired
//	IServiceswitchService iServiceswitchService;

	@Autowired
	IStuffAppealService iStuffAppealService;

	@Test
	public void test() throws UnsupportedEncodingException, HttpProcessException {
//		Page<ZnStuff> page = iSearchStuffService.searchZn(0, "手机", "sort:view_price:desc", 1, 10);
//		System.out.println(JSON.toJSONString(page));
//
//		Page<ZwStuff> p = iSearchStuffService.searchZw(0, "手机", "", "sort:final_price:desc", 1, 10);
//		System.out.println(JSON.toJSONString(p));
//
//		Page<Suggest> s = iSearchStuffService.suggest("手机", 1, 10);
//		System.out.println(JSON.toJSONString(s));
//
//		StuffServiceSwitch service = iServiceswitchService.findStuffServiceSwitchByKey("search_off");
//		System.out.println(JSON.toJSONString(service));
//		System.out.println(iStuffAppealService.detail(1, 1));
//		System.out.println(iStuffAppealService.list(1));
//		iStuffAppealService.cancel(1, 1);
		System.out.println(iStuffAppealService.list(140001239));
//		iStuffAppealService.submit(1, "111", "JD", 1, "111", "111", "111", "111", "111", "111", "111");
//
//		Map<String, String> map = iStuffAppealService.upload(79822109L,
//				image2byte("C:\\Users\\sjzhangjun\\Desktop\\小智问题解析流程.png"), "png");
//		for (String key : map.keySet()) {
//			System.out.println(key + " : " + map.get(key));
//		}
	}

	private static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}
}
