package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums;

public enum POST {
    BRIGADE_COMMANDER("Командир бригади"),
    REGIMENT_COMMANDER("Командир полку"),
    BATTALION_COMMANDER("Командир батальйону"),
    COMPANY_COMMANDER("Командир роти"),
    PLAT_COMMANDER("Командир взводу"),
    LOGISTIC_COMMANDER("Командир логістичної роти");

    private final String post;

    POST(String post) {
        this.post = post;
    }

    public String getRole() {
        return post;
    }

}
