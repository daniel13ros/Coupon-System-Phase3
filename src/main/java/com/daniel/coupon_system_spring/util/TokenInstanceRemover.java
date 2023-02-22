package com.daniel.coupon_system_spring.util;

import com.daniel.coupon_system_spring.security.Information;

import java.util.Map;
import java.util.UUID;

/**
 * Created by danielR on 22/02/2023
 */
public class TokenInstanceRemover {

    public static void removePreviousInstance(Map<UUID, Information> map, Information info) {
        UUID keyToRemove = null;
        for (Map.Entry<UUID, Information> entry : map.entrySet()) {
            if (entry.getValue().getId()==(info.getId())) {
                keyToRemove = entry.getKey();
                break;
            }
        }
        if (keyToRemove != null) {
            map.remove(keyToRemove);
        }
    }
}
