package proquartiano.it.proquartianobe.entities.articles.helpers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

// learn
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String[]> {

    @Override
    public String[] convertToDatabaseColumn(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) {
            return null;
        }
        return stringList.toArray(new String[0]);
    }

    @Override
    public List<String> convertToEntityAttribute(String[] stringArray) {
        if (stringArray == null || stringArray.length == 0) {
            return null;
        }
        return Arrays.asList(stringArray);
    }
}
