package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;


    public List<TrelloBoardsDto> getTrelloBoards(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/ukaszgrzegorzak/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields","name,id")
                .build().encode().toUri();

        TrelloBoardsDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardsDto[].class);
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
