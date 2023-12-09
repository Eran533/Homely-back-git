package org.homely;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> allProperties(){
        return propertyRepository.findAll();
    }

    public String createProperty(Property request) {
        Property newProperty = new Property(
                request.getPropertyId(),
                request.getPropertyName(),
                request.getPropertyType(),
                request.getAddress(),
                request.getPrice(),
                request.getBedrooms(),
                request.getBathrooms(),
                request.getStartDate(),
                request.getEndDate()
        );
        newProperty.setPhotos(request.getPhotos());

        // Save the new property
        Property savedProperty = propertyRepository.save(newProperty);

        // Return the ID of the newly created property
        if (savedProperty != null) {
            return String.valueOf(savedProperty.getPropertyId());
        } else {
            // Handle error if the property couldn't be saved
            return null;
        }
    }

    public List<Property> getPropertiesByDateRange(LocalDate startDate, LocalDate endDate) {
        return propertyRepository.findByStartDateBetween(startDate, endDate);
    }
}
