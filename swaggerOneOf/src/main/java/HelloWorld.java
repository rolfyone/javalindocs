import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.dsl.OpenApiBuilder;
import io.javalin.plugin.openapi.jackson.JacksonModelConverterFactory;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.commons.lang3.StringUtils;

public class HelloWorld {
  public static void main(String[] args) {
    final Info applicationInfo =
        new Info()
            .title(StringUtils.capitalize("helloWorldSwagger"))
            .version("1.0")
            .license(
                new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html"));
    Javalin app = Javalin.create(javalinConfig -> {
      final ObjectMapper objectMapper = new ObjectMapper();
      final JacksonModelConverterFactory factory =
          new JacksonModelConverterFactory(objectMapper);
      final OpenApiOptions options =
          new OpenApiOptions(applicationInfo).modelConverterFactory(factory);
        options.path("/swagger-docs").swagger(new SwaggerOptions("/swagger-ui"));
      javalinConfig.registerPlugin(new OpenApiPlugin(options));
    })
        .start(7000);
    app.get("/", OpenApiBuilder.documented(MyHandler.document(), new MyHandler()) );
  }
}