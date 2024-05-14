package br.com.postech.techchallengeitems.core.gateway.database;

import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import java.util.List;

public interface ItemGateway {
  Item registerItem(Item item);

  void deleteItemById(Integer id);

  Item editItem(Integer id, Item item);

  List<Item> searchItemByType(ItemType type);

}
