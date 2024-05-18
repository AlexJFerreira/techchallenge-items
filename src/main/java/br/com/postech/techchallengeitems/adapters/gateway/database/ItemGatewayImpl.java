package br.com.postech.techchallengeitems.adapters.gateway.database;


import br.com.postech.techchallengeitems.adapters.gateway.database.entity.ItemEntity;
import br.com.postech.techchallengeitems.adapters.gateway.database.repository.ItemRepository;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemGatewayImpl implements ItemGateway {

  private final ItemRepository itemRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public Item registerItem(Item item) {
    var itemEntity = modelMapper.map(item, ItemEntity.class);
    var teste = itemRepository.save(itemEntity);
    return modelMapper.map(teste, Item.class);
  }

  @Override
  @Transactional
  public void deleteItemById(Integer id) {
    var item = itemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    itemRepository.delete(item);
  }

  @Override
  @Transactional
  public Item editItem(Integer id, Item item) {
    var itemEntity = itemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    modelMapper.map(item, itemEntity);
    return modelMapper.map(itemRepository.save(itemEntity), Item.class);
  }

  @Override
  @Transactional
  public List<Item> searchItemByType(ItemType type) {
    var items = itemRepository.findByType(type);
    return items.stream()
        .map(itemEntity -> modelMapper.map(itemEntity, Item.class))
        .toList();
  }

  @Override
  public List<Item> searchItemByIds(List<Integer> ids) {
    var items = itemRepository.findAllById(ids);
    return items.stream()
        .map(itemEntity -> modelMapper.map(itemEntity, Item.class))
        .toList();
  }
}
