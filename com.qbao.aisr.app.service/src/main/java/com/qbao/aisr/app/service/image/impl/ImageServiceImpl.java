package com.qbao.aisr.app.service.image.impl;//package com.image.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Service;

import com.qbao.aisr.app.common.util.MD5Util;
import com.qbao.aisr.app.service.image.IImageService;
import com.qbao.aisr.app.service.image.pool.ConnectionPool;

@Service
public class  ImageServiceImpl implements IImageService {
   private static Logger  logger=Logger.getLogger(ImageServiceImpl.class);
   public static String PRIFIX="dfs/";
   public static String OPERATE="/";
   private static ConnectionPool connectionPool;
//   static{
//	   connectionPool=new ConnectionPool(20l,100l,400l);
//   }
	public byte[] queryImage(String url) {
		String logId = UUID.randomUUID().toString(); 
		TrackerServer trackerServer =null;
		try{
			long startTime=System.currentTimeMillis();
			logger.info("start queryImage:"+url);

			 //fastdfs配置设置
		    trackerServer = connectionPool.checkout(logId);  
	        StorageServer storageServer = null;  
	        StorageClient storageClient = new StorageClient1(trackerServer,  
	                storageServer);  

		   //图片生成
	        String groupUrl=url.replace(PRIFIX, "");
		    String group=groupUrl.substring(0, groupUrl.indexOf(OPERATE));
		    String path=groupUrl.replace(group+OPERATE, "");
		    byte[] images=storageClient.download_file(group, path);

		    //查询图片总耗时
		    long endTime=System.currentTimeMillis();
		    logger.info("queryImage time:"+(endTime-startTime)+"ms  group:"+group);
		    return images;
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}finally{
			if(connectionPool!=null){
				/** 上传完毕及时释放连接 */
			    connectionPool.checkin(trackerServer, logId);
			}
		}
		return null;
	}
	
	/**
	 * 图片删除：-1:删除遇到异常，0:删除成功
	 */
	public int delImage(String url) {
		logger.info("start delImage url:"+url);
		String logId = UUID.randomUUID().toString(); 
		TrackerServer trackerServer =null;
		try{
			 //fastdfs配置设置
		    trackerServer = connectionPool.checkout(logId);  
	        StorageServer storageServer = null;  
	        StorageClient storageClient = new StorageClient1(trackerServer,  
	                storageServer);  

		    //根据trackerServer去storage获取图片
		    String groupUrl=url.replace(PRIFIX, "");
		    String group=groupUrl.substring(0, groupUrl.indexOf(OPERATE));
		    String path=groupUrl.replace(group+OPERATE, "");
		    int state=storageClient.delete_file( group, path);
		    logger.info("delImage return state:"+state+" groupName:"+group);
		    return state;

		}catch(Exception ex){
		    logger.error(ex.getMessage(),ex);
			return -1;
		}finally{
			if(connectionPool!=null){
				/** 上传完毕及时释放连接 */
			    connectionPool.checkin(trackerServer, logId);
			}
		}
	}

	public Map<String, String> uploadImage(byte[] file_buff,String imageType) {
		String logId = UUID.randomUUID().toString(); 
		TrackerServer trackerServer =null;
		try{
			    logger.info("start uploadImage  fileName " );
			    
			    //fastdfs配置设置
			    trackerServer = connectionPool.checkout(logId);  
		        StorageServer storageServer = null;  
		        StorageClient storageClient = new StorageClient1(trackerServer,  
		                storageServer);  

			    //图片开始上传
			    NameValuePair[] meta_list = new NameValuePair[0];

			    long startTime = System.currentTimeMillis();
			    logger.info("file lenth="+file_buff.length);
			    String[] results = storageClient.upload_file(file_buff, imageType, meta_list);
			    logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
			    
			    if (results == null){
			        logger.error("upload file fail, error code: " + storageClient.getErrorCode());
			        return null;
			    }

			    logger.info("group_name: " + results[0] + ", remote_filename: " + results[1]);
			    Map<String, String> map=new HashMap<String,String>();

			    map.put("src",PRIFIX+results[0]+OPERATE+results[1]);
			    map.put("key", MD5Util.getMD5String(file_buff));
			   
			    return map;
		}catch(Exception ex){
			logger.error(ExceptionUtils.getFullStackTrace(ex));
			return null;
		}finally{
			if(connectionPool!=null){
				/** 上传完毕及时释放连接 */
			    connectionPool.checkin(trackerServer, logId);
			}
		}
	}
	
}
