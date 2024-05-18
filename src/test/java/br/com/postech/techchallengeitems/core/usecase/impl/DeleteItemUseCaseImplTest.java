package br.com.postech.techchallengeitems.core.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.postech.techchallengeitems.core.exceptions.NotFoundException;
import br.com.postech.techchallengeitems.core.gateway.database.ItemGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteItemUseCaseImplTest {

  @InjectMocks
  private DeleteItemUseCaseImpl deleteItemUseCase;

  @Mock
  private ItemGateway itemGateway;

  @Test
  void executeWithSuccess() {
    // Arrange
    var itemToDelete = 1;
    doNothing().when(itemGateway)
        .deleteItemById(itemToDelete);

    //Act
    deleteItemUseCase.execute(itemToDelete);

    //Assert
    verify(itemGateway).deleteItemById(itemToDelete);
  }

  @Test
  void executeWhenItemIsNotFoundThenThrowsNotFoundException() {
    // Arrange
    var itemToDelete = 1;
    doThrow(new IllegalArgumentException()).when(itemGateway)
        .deleteItemById(itemToDelete);

    //Act + Assert
    assertThrows(NotFoundException.class, () -> deleteItemUseCase.execute(itemToDelete));

    //Assert
    verify(itemGateway).deleteItemById(itemToDelete);
  }
}