package getRequest;

public class MarketData {
    public Integer mappedId;
    public Integer sportId;
    public Integer periodTypeId;
    public Boolean parameterized;
    public Integer outcomesNumber;
    public String name;
//    public String translations;
//    public String shortNameTranslations;

    public MarketData(Integer mappedId, Integer sportId, Integer periodTypeId, Boolean parameterized,
                      Integer outcomesNumber, String name/* String translations, String shortNameTranslations*/) {
        this.mappedId = mappedId;
        this.sportId = sportId;
        this.periodTypeId = periodTypeId;
        this.parameterized = parameterized;
        this.outcomesNumber = outcomesNumber;
        this.name = name;
//        this.translations = translations;
//        this.shortNameTranslations = shortNameTranslations;
    }

    public Integer getMappedId() {
        return mappedId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public Integer getPeriodTypeId() {
        return periodTypeId;
    }

    public Boolean getParameterized() {
        return parameterized;
    }

    public Integer getOutcomesNumber() {
        return outcomesNumber;
    }

    public String getName() {
        return name;
    }

//    public String getTranslations() {
//        return translations;
//    }
//
//    public String getShortNameTranslations() {
//        return shortNameTranslations;
//    }
}
