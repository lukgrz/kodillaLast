package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardsDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardsDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            if (!trelloBoardDto.getId().equals(null)||!trelloBoardDto.getName().equals(null)) {
                if (trelloBoardDto.getName().contains("Kodilla")) {
                    System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
                }
            }
        });
    }
}
