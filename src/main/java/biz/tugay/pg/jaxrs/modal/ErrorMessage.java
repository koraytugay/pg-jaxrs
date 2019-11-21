package biz.tugay.pg.jaxrs.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor // Needed by Builder
@NoArgsConstructor  // Needed for JSON conversion
public class ErrorMessage
{
  private String errorMessage;

  private String documentation;
}
