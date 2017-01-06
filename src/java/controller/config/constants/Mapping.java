package controller.config.constants;

public enum Mapping {
    
    /**
     * Store url for user main page
     */
     USR_PG(      "WEB-INF/pages/user/userpage.jsp"         ),
            
    /**
     * Store url for user set rate
     */
     USR_BET(     "WEB-INF/pages/user/userrate.jsp"         ),
            
     /**
      * Store url for show results for user
      */
     USR_RES(     "WEB-INF/pages/user/usershow.jsp"         ),
            
     /**
      * Store url for user refill budget
      */
     USR_BDGT(    "WEB-INF/pages/user/userbudget.jsp"       ),
            
     /**
      * Store url for show user's profile
      */
     USR_ACC(     "WEB-INF/pages/user/useraccount.jsp"      ),
            
     /**
      * Store url for bookie main page
      */
     BOO_PG(      "WEB-INF/pages/bookie/bookiepage.jsp"     ),
            
     /**
      * Store url for bookie set coefficients
      */
     BOO_CFF(     "WEB-INF/pages/bookie/bookiecoeff.jsp"    ),
            
     /**
      * Store url for admin show races
      */
     ADM_RC(      "WEB-INF/pages/admin/adminrace.jsp"       ),
            
     /**
      * Store url for admin change race
      */
     ADM_CHNG_RC( "WEB-INF/pages/admin/adminchangerace.jsp" ),
           
     /**
      * Store url for admin show rates
      */
     ADM_RT(      "WEB-INF/pages/admin/adminrate.jsp"       ),
            
     /**
      * Store url for admin fix races results
      */
     ADM_FIXR(    "WEB-INF/pages/admin/adminsetresults.jsp" ),
            
     /**
      * Store url for admin main page
      */
     ADM_PG(      "WEB-INF/pages/admin/adminpage.jsp"       ),
            
     /**
      * Store url for admin show horses
      */
     ADM_HRS(     "WEB-INF/pages/admin/adminhorse.jsp"      ),
         
     /**
      * Store url for admin show users
      */
     ADM_USR(     "WEB-INF/pages/admin/adminuser.jsp"       ),
         
     /**
      * Store url for admin insert race
      */
     ADM_NEWR(    "WEB-INF/pages/admin/adminnewrace.jsp"    ),
         
     /**
      * Store url for show info: error messages, info of any object
      */
     INFO(        "info.jsp"                                ),
         
      /**
       * Store url for registration
       */
      REG(         "registration.jsp"                        ),
           
      /**
       * Store url for index page (log in page)
       */
      INDEX(       "index.jsp"                               );
            
      /**
       * The value of URL
       */
      private final String url;
        
      /**
       * Constructor with one argument initialize field {@link PagesURL#url}
       * @param url    the value of URL
       */
      Mapping(String url) {
          this.url = url;
      }
            
      /**
       * Get the value of URL
       * @return the String value of URL
       */
      public String getURL() {
           return url;
      }
            
}
