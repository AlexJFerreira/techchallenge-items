package br.com.postech.techchallengeitems.core.usecase;


import br.com.postech.techchallengeitems.core.domain.entity.Item;

public interface RegisterItemUseCase {
  Item execute(Item item);
}
