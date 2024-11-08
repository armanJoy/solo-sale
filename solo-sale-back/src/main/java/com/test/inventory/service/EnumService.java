package com.test.inventory.service;

import com.test.inventory.common.routes.ApiConstants;
import com.test.inventory.model.EnumBase;
import com.test.inventory.dto.response.EnumResponse;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class EnumService {
    private static <T extends EnumBase> Set<EnumResponse> getEnumResponse(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        Set<EnumResponse> enumResponseSet = new LinkedHashSet<>();

        for (T enumValue : enumConstants) {
            Integer id = enumValue.getId(); // Getting the ordinal of the enum value
            String displayName = enumValue.getName(); // Getting the name of the enum value

            EnumResponse response = EnumResponse.builder()
                    .id(id)
                    .displayName(displayName)
                    .build();

            enumResponseSet.add(response);
        }
        return enumResponseSet;
    }

//    public static String getEnumDisplayName(String enumName) {
//        String[] enumArray = enumName.split(ApplicationConstant.HYPEN);
//        StringBuilder enumDisplayName = new StringBuilder();
//
//        for (String name : enumArray) {
//            enumDisplayName.append(name).append(ApplicationConstant.SPACE);
//        }
//        return enumDisplayName.toString();
//    }

    public <T extends Enum<T>> Set<EnumResponse> getEnumResponse(String enumType) {

        return switch (enumType) {
            default -> null;
        };
    }

}