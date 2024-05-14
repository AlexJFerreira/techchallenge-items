package br.com.postech.techchallengeitems.core.usecase.impl;


import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import br.com.postech.techchallengeitems.core.usecase.SearchItemUseCase;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchItemUseCaseImpl implements SearchItemUseCase {

  private ItemGateway itemGateway;

  @Override
  public List<Item> execute(@NotNull ItemType type) {
    try {
      return itemGateway.searchItemByType(type);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid item type.");
    }
  }
}
