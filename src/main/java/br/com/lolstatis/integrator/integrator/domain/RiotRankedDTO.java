package br.com.lolstatis.integrator.integrator.domain;

import java.util.List;

public class RiotRankedDTO {
  private List<RiotRanked> riotRankedList;

  public RiotRankedDTO(List<RiotRanked> riotRankedList) {
    this.riotRankedList = riotRankedList;
  }

  public List<RiotRanked> getRiotRankedList() {
    return riotRankedList;
  }

  public void setRiotRankedList(List<RiotRanked> riotRankedList) {
    this.riotRankedList = riotRankedList;
  }
}
