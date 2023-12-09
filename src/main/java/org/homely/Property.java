package org.homely;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "properties")
public class Property {
    @Id
    private String PropertyId;
    private String propertyName;
    private String propertyType;
    private String address;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private List<String> photos;

    private LocalDate startDate;

    private LocalDate endDate;
    private String userId;

    public Property() {
        this.photos = new ArrayList<>();
    }

    public Property(String PropertyId, String propertyName, String propertyType, String address, double price, int bedrooms, int bathrooms, LocalDate startDate, LocalDate endDate) {
        this.PropertyId = PropertyId;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.address = address;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.photos = new ArrayList<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void addPhoto(String filename) {
        this.photos.add(filename);
    }

    public String getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(String PropertyId) {
        this.PropertyId = PropertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + PropertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", photos=" + photos +
                '}';
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}