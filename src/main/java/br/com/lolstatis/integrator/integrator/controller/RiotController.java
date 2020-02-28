package br.com.lolstatis.integrator.integrator.controller;

import br.com.lolstatis.integrator.integrator.domain.RiotRankedDTO;
import br.com.lolstatis.integrator.integrator.service.RiotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public RiotService riotService;

    @GetMapping(value = "/champion-rotations", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<?>> getRotationsChampions() {
        DeferredResult<ResponseEntity<?>> response = new DeferredResult();
        response.setResult(new ResponseEntity<>(this.riotService.getRotation(),HttpStatus.OK));
        return response;
    }
    //Adicionar os queryParams
    @GetMapping(value = "/{queue}/{tier}/{division}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity<RiotRankedDTO>> getRanked(@PathVariable("queue") String queue,
                                                                   @PathVariable("tier") String tier,
                                                                   @PathVariable("division")String division){
        DeferredResult<ResponseEntity<RiotRankedDTO>> response = new DeferredResult();
        response.setResult(new ResponseEntity<>(this.riotService.getRanked(queue,tier,division),HttpStatus.OK));
        return response;
    }
}
