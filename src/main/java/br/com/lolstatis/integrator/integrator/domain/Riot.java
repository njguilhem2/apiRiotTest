package br.com.lolstatis.integrator.integrator.domain;

import java.util.List;

public class Riot {
    private List<Integer> freeChampionIdsForNewPlayers;
    private Integer maxNewPlayerLevel;
    private List<Integer> freeChampionIds;

    public List<Integer> getFreeChampionIdsForNewPlayers() {
        return freeChampionIdsForNewPlayers;
    }

    public void setFreeChampionIdsForNewPlayers(List<Integer> freeChampionIdsForNewPlayers) {
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
    }

    public Integer getMaxNewPlayerLevel() {
        return maxNewPlayerLevel;
    }

    public void setMaxNewPlayerLevel(Integer maxNewPlayerLevel) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }

    public List<Integer> getFreeChampionIds() {
        return freeChampionIds;
    }

    public void setFreeChampionIds(List<Integer> freeChampionIds) {
        this.freeChampionIds = freeChampionIds;
    }
}
