package br.com.postech.techchallengeitems.adapters.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.postech.techchallengeitems.core.domain.enums.ItemType;
import br.com.postech.techchallengeitems.core.usecase.impl.SearchItemByIdsUseCaseImpl;
import br.com.postech.techchallengeitems.core.usecase.impl.SearchItemByTypeUseCaseImpl;
import br.com.postech.techchallengeitems.utils.ItemTestProvider;
import br.com.postech.techchallengeitems.core.usecase.impl.DeleteItemUseCaseImpl;
import br.com.postech.techchallengeitems.core.usecase.impl.EditItemUseCaseImpl;
import br.com.postech.techchallengeitems.core.usecase.impl.RegisterItemUseCaseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest extends ItemTestProvider {

  private static final String BASE_URL = "/techchallenge/items";

  @MockBean
  private DeleteItemUseCaseImpl deleteItemUseCase;

  @MockBean
  private RegisterItemUseCaseImpl registerItemUseCase;

  @MockBean
  private SearchItemByTypeUseCaseImpl searchItemByTypeUseCase;

  @MockBean
  private SearchItemByIdsUseCaseImpl searchItemByIdsUseCase;

  @MockBean
  private EditItemUseCaseImpl editItemUseCase;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  void itemRegistration() throws Exception {
    //Arrange
    var inputItem = getFakeItemRegistrationRequest();
    var outPutItem = getFakeOutputItem();
    when(registerItemUseCase.execute(any())).thenReturn(outPutItem);


    //Act + Assert
    mockMvc.perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(inputItem)))
        .andExpect(status().isCreated());
  }

  @Test
  void itemEdition() throws Exception {
    //Arrange
    var inputItem = getFakeItemEditionRequest();
    var outPutItem = getFakeOutputItem();
    var itemToEdit = 1;
    when(editItemUseCase.execute(anyInt(), any())).thenReturn(outPutItem);


    //Act + Assert
    mockMvc.perform(
            patch(BASE_URL + "/{id}", itemToEdit)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(inputItem)))
        .andExpect(status().isOk());
  }

  @Test
  void itemSearchByType() throws Exception {
    //Arrange
    var type = ItemType.SNACK;
    var outPutItems = List.of(getFakeOutputItem());
    when(searchItemByTypeUseCase.execute(type)).thenReturn(outPutItems);

    //Act + Assert
    mockMvc.perform(
            get(BASE_URL)
                .param("type", type.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void itemSearchByIds() throws Exception {
    //Arrange
    var outPutItems = List.of(getFakeOutputItem());

    var ids = List.of(1);
    when(searchItemByIdsUseCase.execute(ids)).thenReturn(outPutItems);

    //Act + Assert
    mockMvc.perform(
            get(BASE_URL + "?ids=1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void itemDelete() throws Exception {
    //Arrange
    doNothing().when(deleteItemUseCase).execute(1);


    //Act + Assert
    mockMvc.perform(
            delete(BASE_URL + "/{id}", 1))
        .andExpect(status().isOk());
  }
}