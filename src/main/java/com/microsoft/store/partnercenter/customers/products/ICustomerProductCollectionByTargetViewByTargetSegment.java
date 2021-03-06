// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license. See the LICENSE file in the project root for full license information.

package com.microsoft.store.partnercenter.customers.products;

import com.microsoft.store.partnercenter.IPartnerComponent;
import com.microsoft.store.partnercenter.genericoperations.IEntireEntityCollectionRetrievalOperations;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.products.Product;
import com.microsoft.store.partnercenter.models.utils.TripletTuple;

/**
 * Holds operations that can be performed on products in a given catalog view and that apply to a given customer, filtered by target segment.
 */
public interface ICustomerProductCollectionByTargetViewByTargetSegment
    extends IPartnerComponent<TripletTuple<String, String, String>>,
    IEntireEntityCollectionRetrievalOperations<Product, ResourceCollection<Product>>
{
    /**
     * Gets the operations that can be applied on customer products filtered by a specific reservation scope.
     * 
     * @param reservationScope The reservation scope filter.
     * @return The customer products collection operations by reservation scope.
     */
    ICustomerProductCollectionByTargetViewByTargetSegmentByReservationScope byReservationScope(String reservationScope);
}