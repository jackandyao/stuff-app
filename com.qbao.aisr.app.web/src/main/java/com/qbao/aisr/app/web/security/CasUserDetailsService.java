package com.qbao.aisr.app.web.security;

import com.qbao.aisr.app.service.user.UserInterfaceApi;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by zhangchanghong on 15/12/6.
 */
public class CasUserDetailsService implements UserDetailsService, Serializable {
    private static final Logger log = LoggerFactory.getLogger(CasUserDetailsService.class);

    @Resource
    private UserInterfaceApi userInterfaceApi;

/*    @Autowired
    private AssOrderService assOrderService;*/

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        CasUser out = null;
        List<String> roles = userInterfaceApi.getUserRoleByUserName(username);
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String ur : roles) {
            authorities.add(new SimpleGrantedAuthority(ur));
        }


        // 获取用户基本信息
        Map<String, Object> map = userInterfaceApi.getUserBaseInfo(username);
        log.info("用户基本信息map"+map.size());
        if (map != null) {
            long userId = (Integer) map.get(UserInterfaceApi.USER_ID);
            //Object nickObj = map.get(UserInterfaceApi.NICK_NAME_KEY);
            String nickName = UserInterfaceApi.default_nickname;
            String mobile = StringUtils.EMPTY;
            String email =  map.get(UserInterfaceApi.USER_EMAIL_KEY) == null?StringUtils.EMPTY:map.get(UserInterfaceApi.USER_EMAIL_KEY).toString() ;
            Long createTime = (Long) map.get(UserInterfaceApi.USER_CREATE_TIME);
            boolean isEnabled = BooleanUtils.toBoolean( (Integer)map.get(UserInterfaceApi.USER_ENABLE_KEY));
            if (log.isDebugEnabled()) {
                log.debug("userId: {}", userId);
            }
            out = new CasUser(userId, username, nickName, mobile, isEnabled, email, createTime, authorities);
        }
        return out;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    CasUser cu = (CasUser) principal;
                    authorities = (List<GrantedAuthority>) cu.getAuthorities();
                    log.info("CasUserDetailsService.getAuthorities()" + authorities.size());
                    //authorities.add(new SimpleGrantedAuthority("ROLE_HAS_ORDER"));
                }
            }
        }
        return authorities;
    }
}
