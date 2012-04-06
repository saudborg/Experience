package br.com.usp.entity;

/**
 * @author Saulo Dias Borges
 *
 * Class that represents items that will be evaluated
 */
public class Item {
    
    /**
     * Reference Id
     */
    private Integer id;
    
    /**
     * Item description
     */
    private String name;
    
    /**
     * The price of a item calculated in R$
     */
    private double price;
    
    /**
     * The distance between the client and the service provider location - calculated in KM
     */
    private double distance;
    
    /**
     * The period that service is valid, counted in number of calendar days
     */
    private int validity;
    
    /**
     * The score of a service provider
     * class ItemEnum to see the possibilities
     */
    private int serviceProviderRating;
    
    /**
     * The flag of a service that indicates it´s on sale or not
     */
    private boolean isOnSale;

    public Item(Integer id, String name, double price, double distance, int validity, int serviceProviderRating,
            boolean isOnSale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.distance = distance;
        this.validity = validity;
        this.serviceProviderRating = serviceProviderRating;
        this.isOnSale = isOnSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public int getServiceProviderRating() {
        return serviceProviderRating;
    }

    public void setServiceProviderRating(int serviceProviderRating) {
        this.serviceProviderRating = serviceProviderRating;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }
    
    
    

}
