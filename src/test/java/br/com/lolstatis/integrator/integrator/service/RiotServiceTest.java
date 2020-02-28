package br.com.lolstatis.integrator.integrator.service;

import br.com.lolstatis.integrator.integrator.config.IntegratorRestTemplate;
import br.com.lolstatis.integrator.integrator.domain.Riot;
import br.com.lolstatis.integrator.integrator.domain.RiotRanked;
import br.com.lolstatis.integrator.integrator.domain.RiotRankedDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RiotServiceTest {
    @InjectMocks
    private RiotService riotService;

    @Mock
    private IntegratorRestTemplate restTemplate;
    @Mock
    private IntegratorRestTemplate integratorRestTemplate;
    private Riot riot;
    private RiotRanked riotRanked;
    private List<RiotRanked> riotRankedList;
    private RiotRankedDTO riotRankedDTO;

    @Before
    public void setUp(){
        this.riot = new Riot();
        this.riot.setMaxNewPlayerLevel(3);
        this.riot.setFreeChampionIds(null);
        this.riot.setFreeChampionIdsForNewPlayers(null);

        this.riotRanked = new RiotRanked();
        this.riotRanked.setFreshBlood(Boolean.TRUE);
        this.riotRanked.setHotStreak(Boolean.TRUE);
        this.riotRanked.setInactive(Boolean.TRUE);
        this.riotRanked.setLeagueId(UUID.randomUUID());
        this.riotRanked.setLeaguePoints(5);
        this.riotRanked.setLosses(5);
        this.riotRanked.setQueueType("type");
        this.riotRanked.setRank("rank");
        this.riotRanked.setSummonerId("id");
        this.riotRanked.setSummonerName("name");
        this.riotRanked.setTier("tier");
        this.riotRanked.setVeteran(Boolean.TRUE);
        this.riotRanked.setWins(5);
        this.riotRankedList = new ArrayList<>();
        this.riotRankedList.add(this.riotRanked);
        this.riotRankedDTO = new RiotRankedDTO(this.riotRankedList);
    }
    @Test
    public void shouldBySuccessWithGetRanked(){
        Mockito.when(restTemplate.getForObject(Mockito.anyString(),Mockito.any(Class.class))).thenReturn(this.riotRankedDTO);
        RiotRankedDTO riotEntity = riotService.getRanked("queue","tier","division");
        Assert.assertEquals(riotEntity,this.riotRanked);
    }
    @Test
    public void shouldBySuccessWithGetRotation(){
        Mockito.when(restTemplate.exchange(this.integratorRestTemplate.uriRanked("queue", "tier", "division")
            , HttpMethod.GET, this.integratorRestTemplate.riotToken(),new ParameterizedTypeReference<List<RiotRanked>>() {}));
    }
}
