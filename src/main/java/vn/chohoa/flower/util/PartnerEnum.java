package vn.chohoa.flower.util;

public enum PartnerEnum {
    ios("ios"),
    android("android"),
    web("web");

    private final String type;

    PartnerEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
