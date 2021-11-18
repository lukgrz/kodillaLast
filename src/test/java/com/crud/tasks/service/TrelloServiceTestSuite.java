package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private List<TrelloListDto> trelloListDtos;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(List.of());
        //When
        List<TrelloBoardDto> theList = trelloService.fetchTrelloBoards();
        //Then
        assertThat(theList).isNotNull();
        Assertions.assertThat(theList.size()).isEqualTo(0);
    }

    @Test
    public void shouldFetchList() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(
                new TrelloBoardDto("id","name", trelloListDtos));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> theList = trelloService.fetchTrelloBoards();
        //Then
        assertThat(theList).isNotNull();
        assertThat(theList.size()).isEqualTo(1);
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "descrition", "top", "1");
        TrelloDto trelloDto = new TrelloDto(1, 1);
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("id", "name", "url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCardDto);
        when(adminConfig.getAdminMail()).thenReturn("mail@gmail.com");
        //When
        CreatedTrelloCardDto theCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertThat(theCard.getId()).isEqualTo("id");
        assertThat(theCard.getName()).isEqualTo("name");
        assertThat(theCard.getShortUrl()).isEqualTo("url");
        assertThat(createdCardDto.getName()).isEqualTo("name");
    }
}
