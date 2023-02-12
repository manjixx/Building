package com.eva.backend.filter;

import com.eva.backend.service.RightService;
import com.eva.backend.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Author：
 * Date：2023/1/1815:55
 * Desc:
 */
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    RightService rightService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 放行 options 请求
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        // 在 Shiro 的配置文件中，我们不能把 URLPathMatchingFilter 用 @Bean 被 Spring 管理起来。
        // 原因是 Shiro 存在 bug, 这个也是过滤器，ShiroFilterFactoryBean 也是过滤器，当他们都出现的时候，默认的anno,authc 过滤器就失效了。
        // 所以不能把他声明为 @Bean。
        //因此，我们无法在 URLPathMatchingFilter 中使用 @Autowired 注入 AdminPermissionService 类，
        // 所以需要借助一个工具类利用 Spring 应用上下文获取 AdminPermissionService 的实例。

        if (null==rightService) {
            rightService = SpringContextUtils.getContext().getBean(RightService.class);
        }

        String requestAPI = getPathWithinApplication(request);

        Subject subject = SecurityUtils.getSubject();

        String user = subject.getPrincipal().toString();
        System.out.println("用户：" + user + "访问：" + requestAPI + "接口");

        if (!subject.isAuthenticated()) {
            System.out.println("未登录用户尝试访问需要登录的接口");
            return false;
        }

        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = rightService.needFilter(requestAPI);
        if (!needFilter) {
            System.out.println("接口：" + requestAPI + "无需权限");
            return true;
        } else {
            System.out.println("验证访问权限：" + requestAPI);
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionAPIs = rightService.listRightURLsByUser(username);
            for (String api : permissionAPIs) {
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                System.out.println("用户：" + username + "访问了：" + requestAPI + "接口");
                return true;
            } else {
                System.out.println("用户：" + username + "访问了没有权限的接口：" + requestAPI);
                return false;
            }
        }
    }
}
