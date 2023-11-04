package com.example.hceonnfcwithservice;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

public class HCEApduService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        // Verify that APDU is a SELECT command compatible with one of your AIDs.
        if (isSelectAidApdu(apdu)) {
            // Return some numeric variable as the answer.
            byte[] response = convertNumberToResponse(12345);
            return concatArrays(response, new byte[]{(byte) 0x90, (byte) 0x00});
        } else {
            // If it's not a SELECT command, return the status word 'unknown'.
            return new byte[]{(byte) 0x6F, (byte) 0x00};
        }
    }

    @Override
    public void onDeactivated(int reason) {
    }

    private boolean isSelectAidApdu(byte[] apdu) {
        // Check if APDU is via SELECT.
        // (Here you should check that the APDU matches the format of the SELECT command and that the AID is one of your AIDs).
        return true;
    }

    private byte[] convertNumberToResponse(int number) {
        // Convert the number to the appropriate APDU response format.
        // (Here you should convert the number to an appropriate format, such as a string of bytes).
        return new byte[]{(byte) number};
    }

    private byte[] concatArrays(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}

