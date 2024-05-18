package br.com.postech.techchallengeitems.adapters.gateway.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.ItemTestProvider;
import br.com.postech.techchallengeitems.adapters.gateway.database.entity.ItemEntity;
import br.com.postech.techchallengeitems.adapters.gateway.database.repository.ItemRepository;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import br.com.postech.techchallengeitems.infra.ModelMapperConfig;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class ItemGatewayImplTest extends ItemTestProvider {

  @InjectMocks
  private ItemGatewayImpl itemGateway;

  @Mock
  private ItemRepository itemRepository;

  @BeforeEach
  public void beforeEach() {
    ReflectionTestUtils.setField(itemGateway, "modelMapper", new ModelMapperConfig().modelMapper());
  }

  @Test
  void registerItemWithSuccess() {
    //Arrange
    Item inputItem = getFakeInputItem();
    ItemEntity outPut = getFakeItemEntity();
    when(itemRepository.save(any())).thenReturn(outPut);

    //Act
    Item item = itemGateway.registerItem(inputItem);

    //Assert
    assertNotNull(item);
    assertNotNull(item.getId());
    verify(itemRepository).save(any());
  }

  @Test
  void deleteItemByIdWithSuccess() {
    //Arrange
    var itemIdToDelete = 1;
    when(itemRepository.findById(any()))
        .thenReturn(Optional.of(getFakeItemEntity()));

    doNothing()
        .when(itemRepository).delete(any());

    //Act
    itemGateway.deleteItemById(itemIdToDelete);

    //Assert
    verify(itemRepository).findById(any());
    verify(itemRepository).delete(any());
  }

  @Test
  void deleteItemByIdWhenItemIsNotFoundThenThrowsInvalidArgumentException() {
    //Arrange
    var itemIdToDelete = 1;
    when(itemRepository.findById(any()))
        .thenReturn(Optional.empty());

    //Act and Assert
    assertThrows(IllegalArgumentException.class, () -> itemGateway.deleteItemById(itemIdToDelete));

    //Assert
    verify(itemRepository).findById(any());
    verify(itemRepository, never()).delete(any());
  }

  @Test
  void editItemWithSuccess() {
    //Arrange
    var itemIdToEdit = 1;
    Item inputItem = getFakeInputItem();

    when(itemRepository.findById(any()))
        .thenReturn(Optional.of(getFakeItemEntity()));

    when(itemRepository.save(any()))
        .thenReturn(getFakeItemEntity());

    //Act
    itemGateway.editItem(itemIdToEdit, inputItem);

    //Assert
    verify(itemRepository).findById(any());
    verify(itemRepository).save(any());
  }

  @Test
  void editItemWhenItemIsNotFoundThenThrowsInvalidArgumentException() {
    //Arrange
    var itemIdToEdit = 1;
    Item inputItem = getFakeInputItem();

    when(itemRepository.findById(any()))
        .thenReturn(Optional.empty());

    //Act
    assertThrows(IllegalArgumentException.class, () -> itemGateway.editItem(itemIdToEdit, inputItem));

    //Assert
    verify(itemRepository).findById(any());
    verify(itemRepository, never()).save(any());
  }

  @Test
  void searchItemByTypeWithSuccess() {
    // Arrange
    var itemType = ItemType.BEVERAGE;
    when(itemRepository.findByType(any()))
        .thenReturn(List.of(getFakeItemEntity()));

    //Act
    List<Item> items = itemGateway.searchItemByType(itemType);

    //Assert
    assertNotNull(items);
    assertFalse(items.isEmpty());
    verify(itemRepository).findByType(any());
  }

  @Test
  void searchItemByIdsWithSuccess() {
    // Arrange
    var idsToSearch = List.of(1);

    when(itemRepository.findAllById(idsToSearch))
        .thenReturn(List.of(getFakeItemEntity()));

    //Act
    List<Item> items = itemGateway.searchItemByIds(idsToSearch);

    //Assert
    assertNotNull(items);
    assertFalse(items.isEmpty());
    verify(itemRepository).findAllById(any());
  }


}