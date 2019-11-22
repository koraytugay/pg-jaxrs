package biz.tugay.pg.jaxrs.filter;

import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import biz.tugay.pg.jaxrs.binding.RequiresAuthentication;

import static java.nio.charset.StandardCharsets.UTF_8;

@Provider
@RequiresAuthentication
public class MyAuthenticationFilter
    implements ContainerRequestFilter
{
  private static final String AUTHORIZATION_HEADER = "Authorization";

  @Override
  public void filter(ContainerRequestContext requestContext) {
    List<String> authorizationValues = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
    if (authorizationValues == null) {
      requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
      return;
    }

    String authorization = authorizationValues.get(0);
    authorization = authorization.replaceFirst("Basic ", "");
    StringTokenizer st = new StringTokenizer(new String(Base64.getDecoder().decode(authorization), UTF_8), ":");

    String username = st.nextToken();
    String password = st.nextToken();

    if (!(username.equals("koraytugay") && password.equals("password"))) {
      requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
    }
  }
}
