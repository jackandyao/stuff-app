package com.qbao.aisr.app.repository.rest.search;

import com.qbao.aisr.app.common.http.HttpClientUtil;
import com.qbao.aisr.app.common.http.common.HttpConfig;
import com.qbao.aisr.app.common.http.exception.HttpProcessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author shuaizhihu
 * @createTime 2017/3/11 12:33
 * $$LastChangedDate$$
 * $$LastChangedRevision$$
 * $$LastChangedBy$$
 */
@Repository
public class KwSuggestBiz {

    @Value("${search.domain}")
    private String search_domain;

    @Value("${search.suggest.url}")
    private String search_suggest_url;


    public String suggest(String kw,int page,int size) throws UnsupportedEncodingException, HttpProcessException {
        String url = this.createSuggestUrl(kw,page,size);
        String result = HttpClientUtil.get(HttpConfig.custom().url(url));
        return result;
    }

    private String createSuggestUrl(String kw,int page,int size) throws UnsupportedEncodingException {
        String kwEncode = URLEncoder.encode(kw,"UTF-8");
        return search_domain+search_suggest_url+"?kw="+ kwEncode+"&type=1&p="+page+"&size="+size;


    }
}
