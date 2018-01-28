package com.qbao.aisr.app.repository.rest.search;

import com.qbao.aisr.app.common.http.HttpClientUtil;
import com.qbao.aisr.app.common.http.common.HttpConfig;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import com.qbao.aisr.app.common.util.SortUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:31
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public class StuffSearchBiz {
    public Logger logger = Logger.getLogger(getClass());

    @Value("${search.domain}")
    private String search_domain;
    @Value("${search.znstuff.url}")
    private String search_znstuff_url;
    @Value("${search.zwstuff.url}")
    private String search_zwstuff_url;
    @Value("${search.img.zwstuff.url}")
    private String search_img_zwstuff_url;
    @Value("${search.facets.img.url}")
    private String search_facets_img_url;
    @Value("${search.sim.zwstuff.url}")
    private String search_sim_zwstuff_url;



    /**
     * 站内商品搜素
     * @param userId
     * @param kw
     * @param sort
     * @param page
     * @param size
     * @return
     * @throws UnsupportedEncodingException
     * @throws HttpProcessException
     */
    public  String searchZn(long userId,String kw,String sort,int page,int size) throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createZnSearchUrl(userId,kw,sort,page,size);
        logger.info("SearchZn url : " + url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        logger.info("SearchZn result : " + result);
        return result;
    }

    /**
     * 站外商品搜索
     * @param userId
     * @param kw
     * @param source
     * @param sort
     * @param page
     * @param size
     * @return
     * @throws UnsupportedEncodingException
     * @throws HttpProcessException
     */
    public String searchZw(long userId,String kw,String source,String sort,int page,int size,String activities,int coupon) throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createZwSearchUrl(userId,kw,source,sort,page,size,  activities,  coupon);
        logger.info("SearchZw url : " + url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        logger.info("SearchZw result : " + result);
        return result;
    }

    public String searchZwSim(long userId,String kw,String cId,String source,String sort,int page,int size)
            throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createZwSearchSimUrl(userId,kw,cId,source,sort,page,size);
        logger.info("SearchSim url : " + url);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        logger.info("SearchSim result : " + result);
        return result;
    }

    /**
     * 根据图片获取分类信息
     * @param imgUrl
     * @return
     */
    public String facetsImg(String imgUrl) throws HttpProcessException {
        String url = this.createFacetsImgUrl(imgUrl);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        return result;
    }

    /**
     * 图片搜索站外商品
     * @param imgUrl
     * @param userId
     * @param cId
     * @param source
     * @param sort
     * @param page
     * @param size
     * @return
     * @throws HttpProcessException
     * @throws UnsupportedEncodingException
     */
    public String searchZwImg(String imgUrl,long userId,String cId,String source,String sort,int page, int size)
            throws HttpProcessException, UnsupportedEncodingException {
        String url = this.createSearchImgUrl(imgUrl,userId,cId,source,sort,page,size);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        return result;
    }

    private String createZnSearchUrl(long userId,String kw,String sort,int page,int size) throws UnsupportedEncodingException {
        String sortF = SortUtils.getSortField(sort);
        String orderBy = SortUtils.getSortOrderBy(sort);
        if(orderBy.equals("asc")){
            orderBy="0";
        }else{
            orderBy="1";
        }
//        String filter = "{\"is_haohuo\":\"1\"}";
//        filter= URLEncoder.encode(filter,"UTF-8");
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        return search_domain+search_znstuff_url+"?kw="+ kwEncode+"&user_id="+userId+"&sort_f="+sortF+"&order_by="+orderBy+"&p="+page+"&size="+size+"&filter=";
    }

    private String createZwSearchUrl(long userId,String kw,String source,String sort,int page,int size,String activities,int coupon) throws UnsupportedEncodingException {
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        String filter ="";
        if(!StringUtils.isEmpty(source)) {
            String[] sources = source.split(",");
            StringBuffer sq = new StringBuffer("");
            int i = 0;
            for(String s:sources){
                if(i>0){
                    sq.append(" OR ");
                }
                sq.append("source:").append(s);
                i++;
            }
            filter = "{\"fq\":\""+sq.toString()+"\"}";
        }
        filter= URLEncoder.encode(filter,"UTF-8");
        activities=URLEncoder.encode(activities,"UTF-8");
        return search_domain+search_zwstuff_url+"?kw="+kwEncode+"&user_id="+userId+"&sort="+sort+"&filter="+filter+"&p="+page+"&size="+size+"&activities="+activities+"&coupon="+coupon;
    }

    private String createZwSearchSimUrl(long userId,String kw,String cId,String source,String sort,int page, int size) throws UnsupportedEncodingException {
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        String filter ="";
        if(!StringUtils.isEmpty(source)) {
            String[] sources = source.split(",");
            StringBuffer sq = new StringBuffer("");
            int i = 0;
            for(String s:sources){
                if(i>0){
                    sq.append(" OR ");
                }
                sq.append("source:").append(s);
                i++;
            }
            filter = "{\"fq\":\""+sq.toString()+"\"}";
        }
        filter= URLEncoder.encode(filter,"UTF-8");
        return search_domain+search_sim_zwstuff_url+"?kw="+kwEncode+"&c_id="+cId+"&user_id="+userId+"&sort="+sort+"&filter="+filter+"&p="+page+"&size="+size;
    }

    private String createFacetsImgUrl(String imgUrl){
        return search_domain+search_facets_img_url+"?imgurl="+imgUrl;
    }

    private String createSearchImgUrl(String imgUrl,long userId,String cId,String source,String sort,int page, int size) throws UnsupportedEncodingException {
        String filter ="";
        if(!StringUtils.isEmpty(source)) {
            String[] sources = source.split(",");
            StringBuffer sq = new StringBuffer("");
            int i = 0;
            for(String s:sources){
                if(i>0){
                    sq.append(" OR ");
                }
                sq.append("source:").append(s);
                i++;
            }
            filter = "{\"fq\":\""+sq.toString()+"\"}";
        }
        filter= URLEncoder.encode(filter,"UTF-8");
        return search_domain+search_img_zwstuff_url+"?imgurl="+imgUrl+"&c_id="+cId+"&user_id="+userId+"&sort="+sort+"&filter="+filter+"&p="+page+"&size="+size;

    }




}
