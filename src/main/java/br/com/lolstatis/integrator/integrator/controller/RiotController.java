package br.com.lolstatis.integrator.integrator.controller;

import br.com.lolstatis.integrator.integrator.domain.Riot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class RiotController {

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping(value = "/champion-rotations", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<?>> getRotationsChmapions() {
        DeferredResult<ResponseEntity<?>> response = new DeferredResult();
        String uri = "https://br1.api.riotgames.com/lol/platform/v3/champion-rotations";
        HttpHeaders header = new HttpHeaders();
        header.set("X-Riot-Token","RGAPI-ebb577c8-3fc5-4ce4-a95e-8c09203869ea");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", header);
        ResponseEntity<Riot> riotTo = restTemplate.exchange(uri, HttpMethod.GET,entity,Riot.class);
        response.setResult(new ResponseEntity<Riot>(riotTo.getBody(), HttpStatus.OK));
        return response;
    }
    
    @GetMapping(value = "/{queue}/{tier}/{division}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<?>> getRanked(@PathVariable("queue") String queue,
                                                       @PathVariable("tier") String tier,
                                                       @PathVariable("division")String division){
        return null;
    }
}
