package br.com.lolstatis.integrator.integrator.service;

import br.com.lolstatis.integrator.integrator.config.IntegratorRestTemplate;
import br.com.lolstatis.integrator.integrator.domain.Riot;
import br.com.lolstatis.integrator.integrator.domain.RiotRanked;
import br.com.lolstatis.integrator.integrator.domain.RiotRankedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class RiotService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private IntegratorRestTemplate integratorRestTemplate;

  public Riot getRotation() {
    ResponseEntity<Riot> response = this.restTemplate.exchange(
        this.integratorRestTemplate.uriRotation(), HttpMethod.GET,
        this.integratorRestTemplate.riotToken(), Riot.class);
    return response.getBody();
  }
  public RiotRankedDTO getRanked(String queue, String tier, String division) {
    ResponseEntity<List<RiotRanked>> response = (this.restTemplate
        .exchange(this.integratorRestTemplate.uriRanked(queue, tier, division), HttpMethod.GET,
            this.integratorRestTemplate.riotToken(), new ParameterizedTypeReference<List<RiotRanked>>() {
            }));
    RiotRankedDTO rankedDTO = new RiotRankedDTO(response.getBody());
    return rankedDTO;
  }
}
