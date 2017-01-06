/**
 * ***********************************************************************************************
 * Module: CreatorInterface.java Author: Hrytsiuk Purpose: Defines the Interface CreatorInterface
 * ***********************************************************************************************
 */
package model.entities.ratetype.factory;

import model.entities.ratetype.factory.TypeFactory.Rates;

/**
 * Creator Object of rate type interface
 * @see TypeInterface
 */
public interface Creator {
    
    /**
     * Returns an Object of rate type
     * @param type
     * the value of rate type: 1, 2, 3
     * @return Object TypeInterface
     */
    RateType getType(Rates rate);
    
}
