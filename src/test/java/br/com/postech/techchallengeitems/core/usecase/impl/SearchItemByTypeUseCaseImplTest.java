package br.com.postech.techchallengeitems.core.usecase.impl;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.ItemTestProvider;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchItemByTypeUseCaseImplTest extends ItemTestProvider {

  @InjectMocks
  private SearchItemByTypeUseCaseImpl searchItemByTypeUseCase;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var type = ItemType.BEVERAGE;
    when(itemGateway.searchItemByType(type))
        .thenReturn(List.of(getFakeOutputItem()));

    //Act
    List<Item> items = searchItemByTypeUseCase.execute(type);

    //Assert
    assertNotNull(items);
    assertFalse(items.isEmpty());
    verify(itemGateway).searchItemByType(type);
  }

  @Test
  void executeWhenTypeIsInvalidThenThrowsIllegalArgumentException() {
    //Arrange
    var type = ItemType.BEVERAGE;
    when(itemGateway.searchItemByType(type))
        .thenThrow(new IllegalArgumentException());

    //Act + Assert
    assertThrows(IllegalArgumentException.class, () -> searchItemByTypeUseCase.execute(type));

    //Assert
    verify(itemGateway).searchItemByType(type);
  }
}