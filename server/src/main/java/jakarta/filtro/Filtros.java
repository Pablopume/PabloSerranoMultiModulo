package jakarta.filtro;

import jakarta.ConstantesRest;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = ConstantesRest.FILTER_LOGIN, urlPatterns = {ConstantesRest.API_EMPLEADOS, ConstantesRest.API_EMPLEADOSALL, ConstantesRest.API_PROYECTOS, ConstantesRest.API_PROYECTOSALL, ConstantesRest.API_EQUIPOS, ConstantesRest.API_EQUIPOSALL} )
public class Filtros implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute(ConstantesRest.LOGIN) == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ConstantesRest.NO_AUTORIZADO);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


}
