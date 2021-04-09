import io.swagger.v3.oas.annotations.media.Schema;

public class Level1 {
  public String info;
  @Schema(oneOf = {Level2a.class, level2b.class})
  public Object data;
}
