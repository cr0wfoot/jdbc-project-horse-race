/**
 * **************************************************************************
 * Module: Creator.java Author: Hrytsiuk Purpose: Defines the Class Creator
 * **************************************************************************
 */
package model.entities.ratetype.factory;

import model.entities.ratetype.types.DuosRate;
import model.entities.ratetype.types.GoldenRate;
import model.entities.ratetype.types.SimpleRate;

/**
 * Create an Object of TypeInterface
 * @see CreatorInterface
 */
public class TypeFactory implements Creator {

    public enum Rates {
            
        SIMPLE( new SimpleRate() ),
        DUOS(   new DuosRate()   ),
        GOLDEN( new GoldenRate() );

        private final RateType type;

        Rates(RateType type) {
            this.type = type;
        }
            
        public RateType type() {
            return type;
        }
            
    }
    
    @Override
    public RateType getType(Rates rate) {
        return rate.type();       
    }
    
}
