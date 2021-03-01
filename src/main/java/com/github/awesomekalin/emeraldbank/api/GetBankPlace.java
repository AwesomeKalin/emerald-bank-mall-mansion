package com.github.awesomekalin.emeraldbank.api;

import com.github.awesomekalin.emeraldbank.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class GetBankPlace {
    public static int getBankPlace(String bank) {
        ArrayList<String> tempBanks = new ArrayList<>(Main.banks);
        int banks = tempBanks.size();
        int place = 0;
        int i = 0;
        boolean found = false;
        while (!found) {
            if (Main.banks.get(i).equals(bank)) {
                place = i;
                found = true;
            } else {
                i += 1;
                if(i >= banks) {
                    place = -1;
                    found = true;
                }
            }
        }
        return place;
    }
}
