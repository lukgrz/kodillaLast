package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Mock
    private List<TrelloListDto> mockTrelloListDtos;
    @Mock
    private List<TrelloList> mockTrelloLists;

    @Test
    public  void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1","name1",true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2","name2",false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto1);
        trelloListDtos.add(trelloListDto2);
        //When
        List<TrelloList> theList = trelloMapper.mapToList(trelloListDtos);
        Boolean isClosed = theList.get(0).isClosed();
        //Then
        assertEquals(2, theList.size());
        assertTrue(isClosed);
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1","name1",false);
        TrelloList trelloList2 = new TrelloList("2","name2",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        //When
        List<TrelloListDto> theList = trelloMapper.mapToListDto(trelloLists);
        Boolean isClosed = theList.get(1).isClosed();
        //Then
        assertEquals(2, theList.size());
        assertTrue(isClosed);
    }

    @Test
    public void testMapToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "name1", mockTrelloListDtos);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "name2", mockTrelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        //When
        List <TrelloBoard> theList = trelloMapper.mapToBoards(trelloBoardDtos);
        String theName = theList.get(0).getName();
        //Then
        assertEquals(2, theList.size());
        assertEquals("name1", theName);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "name1", mockTrelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "name2", mockTrelloLists);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard1);
        boardList.add(trelloBoard2);
        //When
        List <TrelloBoardDto> theList = trelloMapper.mapToBoardsDto(boardList);
        //Then
        assertEquals(2, theList.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1","top","1");
        //When
        TrelloCard theCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("name1", theCard.getName());
    }


    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "description1","top","1");
        //When
        TrelloCardDto theCard = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("top", theCard.getPos());
    }
}
