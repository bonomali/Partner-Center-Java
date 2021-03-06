// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license. See the LICENSE file in the project root for full license information.

package com.microsoft.store.partnercenter.extensions.products;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.products.InventoryCheckRequest;
import com.microsoft.store.partnercenter.models.products.InventoryItem;
import com.microsoft.store.partnercenter.utils.StringHelper;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;

/**
 * Product extensions operations implementation by country.
 */
public class ProductExtensionsByCountryOperations 
    extends BasePartnerComponentString implements IProductExtensionsByCountry 
{
    /**
     * Initializes a new instance of the ProductExtensionsByCountryOperations class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param country               The country on which to base the corresponding products.
     */
    public ProductExtensionsByCountryOperations(IPartner rootPartnerOperations, String country) {
        super(rootPartnerOperations, country);

        if (StringHelper.isNullOrWhiteSpace(country)) {
            throw new IllegalArgumentException("country must be set");
        }
    }

	/**
	 * Retrieves inventory validation results for the provided country.
	 * 
	 * @param checkRequest The request for the inventory check.
	 * @return The inventory check results.
	 */
    public List<InventoryItem> checkInventory(InventoryCheckRequest checkRequest)
    {
        Collection<KeyValuePair<String, String>> parameters = new ArrayList<KeyValuePair<String, String>>();

        parameters.add(
            new KeyValuePair<String, String>(
                PartnerService.getInstance().getConfiguration().getApis().get("CheckInventory").getParameters().get("Country"),
                this.getContext()));

        return this.getPartner().getServiceClient().post(
            this.getPartner(),
            new TypeReference<List<InventoryItem>>(){}, 
            PartnerService.getInstance().getConfiguration().getApis().get("CheckInventory").getPath(),
            parameters);
    }
}