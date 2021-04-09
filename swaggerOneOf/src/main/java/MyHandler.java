import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.openapi.dsl.OpenApiBuilder;
import io.javalin.plugin.openapi.dsl.OpenApiDocumentation;
import org.jetbrains.annotations.NotNull;

public class MyHandler implements Handler {

  public static OpenApiDocumentation document() {
    return OpenApiBuilder.document()
        .operation(openApiOperation -> {
          openApiOperation.description("top level response");
          openApiOperation.summary("Get block");
        })
        .result("200", MyResponse.class);
  }

  @Override
  public void handle(@NotNull final Context ctx) throws Exception {
    ctx.status(200);

  }
}
