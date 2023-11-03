package com.example.hceonnfcwithservice;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

public class HCEApduService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
       return new byte[0];
    }
    @Override
    public void onDeactivated(int reason) {
    }
}
