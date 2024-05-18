package br.com.postech.techchallengeitems.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.utils.ItemTestProvider;
import br.com.postech.techchallengeitems.core.domain.entity.Item;
import br.com.postech.techchallengeitems.core.exceptions.NotFoundException;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EditItemUseCaseImplTest extends ItemTestProvider {

  @InjectMocks
  private EditItemUseCaseImpl editItemUseCase;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    //Arrange
    var itemToEdit = 1;
    var inputItem = getFakeInputItem();
    var outputItem = getFakeOutputItem();

    when(itemGateway.editItem(itemToEdit, inputItem))
        .thenReturn(outputItem);

    //Act
    Item editedItem = editItemUseCase.execute(itemToEdit, inputItem);

    //Assert
    assertNotNull(editedItem);
    assertNotEquals(inputItem, editedItem);
    verify(itemGateway).editItem(itemToEdit, inputItem);

  }

  @Test
  void executeWhenItemIsNotFoundThenThrowsNotFoundException() {
    //Arrange
    var itemToEdit = 1;
    var inputItem = getFakeInputItem();
    var outputItem = getFakeOutputItem();

    when(itemGateway.editItem(itemToEdit, inputItem))
        .thenThrow(new IllegalArgumentException());

    //Act + Assert
    assertThrows(NotFoundException.class, () -> editItemUseCase.execute(itemToEdit, inputItem));

    //Assert
    verify(itemGateway).editItem(itemToEdit, inputItem);
  }
}