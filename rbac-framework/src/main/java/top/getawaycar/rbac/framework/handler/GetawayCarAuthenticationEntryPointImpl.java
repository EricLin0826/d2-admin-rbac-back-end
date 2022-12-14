package top.getawaycar.rbac.framework.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.getawaycar.rbac.framework.constant.HttpStatus;
import top.getawaycar.rbac.framework.util.ServletUtil;
import top.getawaycar.rbac.mvc.factory.RestControllerResponseFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author EricJeppesen
 */
@Component
public class GetawayCarAuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtil.renderString(response, JSONUtil.toJsonStr(RestControllerResponseFactory.buildErrorResponse(HttpStatus.UNAUTHORIZED, msg)));
    }
}
