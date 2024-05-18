package br.com.postech.techchallengeitems.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.utils.ItemTestProvider;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchItemByIdsUseCaseImplTest extends ItemTestProvider {

  @InjectMocks
  private SearchItemByIdsUseCaseImpl searchItemByIdsUseCase;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var itemIds = List.of(1);
    when(itemGateway.searchItemByIds(itemIds))
        .thenReturn(List.of(getFakeOutputItem()));

    //Act
    List<Item> items = searchItemByIdsUseCase.execute(itemIds);

    //Assert
    assertNotNull(items);
    assertFalse(items.isEmpty());
    verify(itemGateway).searchItemByIds(itemIds);
  }
}