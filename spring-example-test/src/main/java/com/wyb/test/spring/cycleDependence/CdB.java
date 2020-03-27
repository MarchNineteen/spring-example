package com.wyb.test.spring.cycleDependence;

/**
 * @author Marcher丶
 */
public class CdB {

    private CdA cdA;

//    public CdB(CdA cdA) {
//        this.cdA = cdA;
//    }


    public void setCdA(CdA cdA) {
        this.cdA = cdA;
    }
}
