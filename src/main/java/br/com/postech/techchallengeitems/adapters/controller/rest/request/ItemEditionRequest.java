package br.com.postech.techchallengeitems.adapters.controller.rest.request;

import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemEditionRequest {
  private String name;

  private String description;

  private BigDecimal price;

  private ItemType type;

}
