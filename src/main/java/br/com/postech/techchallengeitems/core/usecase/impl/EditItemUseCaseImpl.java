package br.com.postech.techchallengeitems.core.usecase.impl;


import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.exceptions.NotFoundException;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import br.com.postech.techchallengeitems.core.usecase.EditItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditItemUseCaseImpl implements EditItemUseCase {
  private final ItemGateway itemGateway;

  @Override
  public Item execute(Integer id, Item item) {
    try {
      return itemGateway.editItem(id, item);
    } catch (IllegalArgumentException e) {
      throw new NotFoundException("Item não encontrado!");
    }
  }
}
