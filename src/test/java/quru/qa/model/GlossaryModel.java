package quru.qa.model;

public class GlossaryModel {
    private String heroName;
    private String typeOfHero;
    private int maxLvl;
    private String lane;
    private String mostUsedItems;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getTypeOfHero() {
        return typeOfHero;
    }

    public void setTypeOfHero(String typeOfHero) {
        this.typeOfHero = typeOfHero;
    }

    public int getMaxLvl() {
        return maxLvl;
    }

    public void setMaxLvl(int maxLvl) {
        this.maxLvl = maxLvl;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getMostUsedItems() {
        return mostUsedItems;
    }

    public void setMostUsedItems(String mostUsedItems) {
        this.mostUsedItems = mostUsedItems;
    }

    public String getBestVersus() {
        return bestVersus;
    }

    public void setBestVersus(String bestVersus) {
        this.bestVersus = bestVersus;
    }

    private String bestVersus;
}
