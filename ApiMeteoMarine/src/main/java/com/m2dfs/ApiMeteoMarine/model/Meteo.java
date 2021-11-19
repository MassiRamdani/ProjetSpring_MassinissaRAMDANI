package com.m2dfs.ApiMeteoMarine.model;

public class Meteo {
    private Integer directionVent;
    private Integer vitessVent;
    private Integer directionHoule;
    private Integer langeurVague;
    private Integer pression;
    private String plage;

    public Meteo() {
    }

    public Meteo(Integer directionVent, Integer vitessVent,
                 Integer directionHoule, Integer langeurVague,
                 Integer pression, String plage) {
        super();
        this.directionVent = directionVent;
        this.vitessVent = vitessVent;
        this.directionHoule = directionHoule;
        this.langeurVague = langeurVague;
        this.pression = pression;
        this.plage = plage;
    }

    public Integer getDirectionVent() {
        return directionVent;
    }

    public void setDirectionVent(Integer directionVent) {
        this.directionVent = directionVent;
    }

    public Integer getVitessVent() {
        return vitessVent;
    }

    public void setVitessVent(Integer vitessVent) {
        this.vitessVent = vitessVent;
    }

    public Integer getDirectionHoule() {
        return directionHoule;
    }

    public void setDirectionHoule(Integer directionHoule) {
        this.directionHoule = directionHoule;
    }

    public Integer getLangeurVague() {
        return langeurVague;
    }

    public void setLangeurVague(Integer langeurVague) {
        this.langeurVague = langeurVague;
    }

    public Integer getPression() {
        return pression;
    }

    public void setPression(Integer pression) {
        this.pression = pression;
    }

    public String getPlage() {
        return plage;
    }

    public void setPlage(String plage) {
        this.plage = plage;
    }

    @Override
    public String toString() {
        return "Meteo{" +
                "directionVent=" + directionVent +
                ", vitessVent=" + vitessVent +
                ", directionHoule=" + directionHoule +
                ", langeurVague=" + langeurVague +
                ", pression=" + pression +
                ", plage='" + plage + '\'' +
                '}';
    }
}
