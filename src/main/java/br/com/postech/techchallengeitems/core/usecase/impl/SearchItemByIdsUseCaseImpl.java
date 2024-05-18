package br.com.postech.techchallengeitems.core.usecase.impl;

import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import br.com.postech.techchallengeitems.core.usecase.SearchItemByIdsUseCase;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SearchItemByIdsUseCaseImpl implements SearchItemByIdsUseCase {

  private ItemGateway itemGateway;

  @Override
  public List<Item> execute(List<Integer> itemIds) {
    return itemGateway.searchItemByIds(itemIds);
  }
}
