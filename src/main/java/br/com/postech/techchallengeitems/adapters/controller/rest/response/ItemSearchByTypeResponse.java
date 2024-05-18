package br.com.postech.techchallengeitems.adapters.controller.rest.response;

import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemSearchByTypeResponse {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private ItemType type;
}
