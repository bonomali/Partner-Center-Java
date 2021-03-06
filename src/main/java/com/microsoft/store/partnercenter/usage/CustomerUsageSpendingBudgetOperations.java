// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license. See the LICENSE file in the project root for full license information.

package com.microsoft.store.partnercenter.usage;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.usage.SpendingBudget;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * This class implements the operations available on a customer's usage spending budget.
 */
public class CustomerUsageSpendingBudgetOperations
    extends BasePartnerComponentString
    implements ICustomerUsageSpendingBudget
{
    /**
     * Initializes a new instance of the CustomerUsageSpendingBudgetOperations class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The customer identifier.
     */
    public CustomerUsageSpendingBudgetOperations(IPartner rootPartnerOperations, String customerId)
    {
        super(rootPartnerOperations, customerId);
        if (StringHelper.isNullOrWhiteSpace(customerId))
        {
            throw new IllegalArgumentException("customerId must be set.");
        }
    }

    /**
     * Gets the usage spending budget allocated to a customer by the partner.
     * 
     * @return The customer usage spending budget.
     */
    @Override
    public SpendingBudget get()
    {
        return this.getPartner().getServiceClient().get(
            this.getPartner(),
            new TypeReference<SpendingBudget>(){}, 
            MessageFormat.format(
                PartnerService.getInstance().getConfiguration().getApis().get("GetCustomerUsageSpendingBudget").getPath(),
                this.getContext()));
    }

    /**
     * Patches the usage spending budget allocated to a customer by the partner.
     * 
     * @param usageSpendingBudget The new customer usage spending budget.
     * @return The updated customer usage spending budget.
     */
    @Override
    public SpendingBudget patch(SpendingBudget usageSpendingBudget)
    {
        if (usageSpendingBudget == null)
        {
            throw new IllegalArgumentException("usage spending budget is required.");
        }

        return this.getPartner().getServiceClient().patch(
            this.getPartner(),
            new TypeReference<SpendingBudget>(){}, 
            MessageFormat.format(
                PartnerService.getInstance().getConfiguration().getApis().get("PatchCustomerUsageSpendingBudget").getPath(),
                this.getContext()),
            usageSpendingBudget);
    }
}