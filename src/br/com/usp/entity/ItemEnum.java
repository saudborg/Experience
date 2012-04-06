package br.com.usp.entity;


public enum ItemEnum {
    
    TERRIBLE(1, "terrible"),

    BAD(2, "bad"),

    NORMAL(3, "normal"),

    GOOD(4, "good"),
    
    EXCELLENT(5, "excellent");

    private Integer value;

    private String name;

    private ItemEnum(Integer value, String table) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
