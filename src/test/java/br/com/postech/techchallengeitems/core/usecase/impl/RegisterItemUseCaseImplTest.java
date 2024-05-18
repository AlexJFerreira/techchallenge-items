package br.com.postech.techchallengeitems.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.utils.ItemTestProvider;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterItemUseCaseImplTest extends ItemTestProvider {

  @InjectMocks
  private RegisterItemUseCaseImpl registerItemUseCase;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    Item inputItem = getFakeInputItem();
    Item outPutItem = getFakeOutputItem();

    when(itemGateway.registerItem(inputItem))
        .thenReturn(outPutItem);

    //Act
    Item useCaseItem = registerItemUseCase.execute(inputItem);

    //Asser
    assertNotNull(useCaseItem);
    assertNotNull(useCaseItem.getId());
    verify(itemGateway).registerItem(inputItem);

  }
}