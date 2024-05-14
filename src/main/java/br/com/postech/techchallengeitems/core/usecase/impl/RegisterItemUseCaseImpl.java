package br.com.postech.techchallengeitems.core.usecase.impl;


import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import br.com.postech.techchallengeitems.core.usecase.RegisterItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterItemUseCaseImpl implements RegisterItemUseCase {

  private final ItemGateway itemGateway;

  @Override
  public Item execute(Item item) {
    return itemGateway.registerItem(item);
  }
}
