package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums;

public enum RANK {

    MAJOR_GENERAL("Генерал майор"),
    COLONEL("Полковник"),
    MAJOR("Майор"),
    LIEUTENANT_COLONEL("Підполковник"),
    SENIOR_LIEUTENANT("Старший лейтенант"),
    JUNIOR_LIEUTENANT("Молодший летенант"),
    SCANNING_DEVICE("Скануючий дивайс");
    private final String rank;

    RANK(String rank){
        this.rank = rank;
    }

    public String getRank(){
        return this.rank;
    }


}
