package com.wyb.test.spring.cycleDependence;

/**
 * @author Marcher丶
 */
public class CdA {
    private CdB cdB;

//    public CdA(CdB cdB) {
//        this.cdB = cdB;
//    }


    public void setCdB(CdB cdB) {
        this.cdB = cdB;
    }
}
