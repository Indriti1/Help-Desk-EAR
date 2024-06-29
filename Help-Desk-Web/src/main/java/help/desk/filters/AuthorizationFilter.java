package help.desk.filters;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import help.desk.managedbeans.SessionBean;
import help.desk.utils.UserRole;

public class AuthorizationFilter implements Filter {

    @Inject
    private SessionBean sessionBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        if (requestURI.contains("index.xhtml") || sessionBean.isLoggedIn()) {
            if (sessionBean.isLoggedIn()) {
                UserRole userRole = sessionBean.getLoggedInUser().getRole();
                if (requestURI.contains("adminDashboard") && userRole != UserRole.ADMIN) {
                    httpResponse.sendRedirect(contextPath + "/faces/index.xhtml");
                    return;
                }
                if (requestURI.contains("employeeDashboard") && userRole != UserRole.EMPLOYEE) {
                    httpResponse.sendRedirect(contextPath + "/faces/index.xhtml");
                    return;
                }
                if (requestURI.contains("customerDashboard") && userRole != UserRole.CUSTOMER) {
                    httpResponse.sendRedirect(contextPath + "/faces/index.xhtml");
                    return;
                }
                
                // Redirect users to their appropriate dashboards if they're accessing the index page
                if (requestURI.contains("index.xhtml")) {
                    switch (userRole) {
                        case ADMIN:
                            httpResponse.sendRedirect(contextPath + "/faces/adminDashboard.xhtml");
                            return;
                        case EMPLOYEE:
                            httpResponse.sendRedirect(contextPath + "/faces/employeeDashboard.xhtml");
                            return;
                        case CUSTOMER:
                            httpResponse.sendRedirect(contextPath + "/faces/customerDashboard.xhtml");
                            return;
                    }
                }
            }
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(contextPath + "/faces/index.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}