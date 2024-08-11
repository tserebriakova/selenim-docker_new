package org.aviaticket.tests.vendorportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VendorPortalTestData {

    private String username;
    private String password;
    private String monthlyEarning;
    private String annualEarning;
    private String profitMargin;
    private String availableInventory;
    private String searchKeyword;
    private Integer searchResultsCount;
}