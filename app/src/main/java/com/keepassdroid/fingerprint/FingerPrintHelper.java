/*
 * Copyright 2017 Hans Cappelle
 *
 * This file is part of KeePassDroid.
 *
 *  KeePassDroid is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDroid is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.keepassdroid.fingerprint;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.util.Base64;

import com.keepassdroid.compat.BuildCompat;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class FingerPrintHelper {

    private static final String ALIAS_KEY = "example-key";

    private FingerprintManagerCompat fingerprintManager;
    private KeyStore keyStore = null;
    private KeyGenerator keyGenerator = null;
    private Cipher cipher = null;
    private KeyguardManager keyguardManager = null;
    private FingerprintManagerCompat.CryptoObject cryptoObject = null;

    private boolean initOk = false;
    private FingerPrintCallback fingerPrintCallback;
    private CancellationSignal cancellationSignal;
    private FingerprintManagerCompat.AuthenticationCallback authenticationCallback;

    public void setAuthenticationCallback(final FingerprintManagerCompat.AuthenticationCallback authenticationCallback) {
        this.authenticationCallback = authenticationCallback;
    }

    public void startListening() {

        // starts listening for fingerprints with the initialised crypto object
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(
                cryptoObject,
                0 /* flags */,
                cancellationSignal,
                authenticationCallback,
                null);
    }

    public void stopListening() {
        if (!isFingerprintInitialized()) {
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            cancellationSignal = null;
        }
    }

    public interface FingerPrintCallback {
        void handleEncryptedResult(String value, String ivSpec);
        void handleDecryptedResult(String value);
        void onInvalidKeyException();
        void onFingerprintException(Exception e);
    }

    @TargetApi(BuildCompat.VERSION_CODE_M)
    public FingerPrintHelper(
            final Context context,
            final FingerPrintCallback fingerPrintCallback) {

        this.fingerprintManager = FingerprintManagerCompat.from(context);
        if (!isFingerprintSupported(fingerprintManager)) {
            // really not much to do when no fingerprint support found
            setInitOk(false);
            return;
        }
        this.keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
        this.fingerPrintCallback = fingerPrintCallback;

        if (hasEnrolledFingerprints()) {
            try {
                this.keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyGenerator = KeyGenerator.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES,
                        "AndroidKeyStore");
                this.cipher = Cipher.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES + "/"
                                + KeyProperties.BLOCK_MODE_CBC + "/"
                                + KeyProperties.ENCRYPTION_PADDING_PKCS7);
                this.cryptoObject = new FingerprintManagerCompat.CryptoObject(cipher);
                setInitOk(true);
            } catch (final Exception e) {
                setInitOk(false);
                fingerPrintCallback.onFingerprintException(e);
            }
        }
    }

    public boolean isFingerprintSupported(FingerprintManagerCompat fingerprintManager) {
        return Build.VERSION.SDK_INT >= BuildCompat.VERSION_CODE_M
                && fingerprintManager.isHardwareDetected();
    }

    public boolean isFingerprintInitialized() {
        boolean isFingerprintInit = hasEnrolledFingerprints() && initOk;
        if (!isFingerprintInit && fingerPrintCallback != null) {
            fingerPrintCallback.onFingerprintException(new Exception("FingerPrint not initialized"));
        }
        return isFingerprintInit;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initEncryptData() {
        if (!isFingerprintInitialized()) {
            return;
        }
        try {
            createNewKeyIfNeeded(false); // no need to keep deleting existing keys
            keyStore.load(null);
            final SecretKey key = (SecretKey) keyStore.getKey(ALIAS_KEY, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            stopListening();
            startListening();

        } catch (final KeyPermanentlyInvalidatedException invalidKeyException) {
            fingerPrintCallback.onInvalidKeyException();
        } catch (final Exception e) {
            fingerPrintCallback.onFingerprintException(e);
        }
    }

    public void encryptData(final String value) {
        if (!isFingerprintInitialized()) {
            return;
        }
        try {
            // actual do encryption here
            byte[] encrypted = cipher.doFinal(value.getBytes());
            final String encryptedValue = Base64.encodeToString(encrypted, 0 /* flags */);

            // passes updated iv spec on to callback so this can be stored for decryption
            final IvParameterSpec spec = cipher.getParameters().getParameterSpec(IvParameterSpec.class);
            final String ivSpecValue = Base64.encodeToString(spec.getIV(), Base64.DEFAULT);
            fingerPrintCallback.handleEncryptedResult(encryptedValue, ivSpecValue);

        } catch (final Exception e) {
            fingerPrintCallback.onFingerprintException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initDecryptData(final String ivSpecValue) {
        if (!isFingerprintInitialized()) {
            return;
        }
        try {
            createNewKeyIfNeeded(false);
            keyStore.load(null);
            final SecretKey key = (SecretKey) keyStore.getKey(ALIAS_KEY, null);

            // important to restore spec here that was used for decryption
            final byte[] iv = Base64.decode(ivSpecValue, Base64.DEFAULT);
            final IvParameterSpec spec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            stopListening();
            startListening();

        } catch (final KeyPermanentlyInvalidatedException invalidKeyException) {
            fingerPrintCallback.onInvalidKeyException();
        } catch (final Exception e) {
            fingerPrintCallback.onFingerprintException(e);
        }
    }

    public void decryptData(final String encryptedValue) {
        if (!isFingerprintInitialized()) {
            return;
        }
        try {
            // actual decryption here
            final byte[] encrypted = Base64.decode(encryptedValue, 0);
            byte[] decrypted = cipher.doFinal(encrypted);
            final String decryptedString = new String(decrypted);

            //final String encryptedString = Base64.encodeToString(encrypted, 0 /* flags */);
            fingerPrintCallback.handleDecryptedResult(decryptedString);
        } catch (final Exception e) {
            fingerPrintCallback.onFingerprintException(e);
        }
    }

    @SuppressLint("NewApi")
    private void createNewKeyIfNeeded(final boolean allowDeleteExisting) {
        if (!isFingerprintInitialized()) {
            return;
        }
        try {
            keyStore.load(null);
            if (allowDeleteExisting
                    && keyStore.containsAlias(ALIAS_KEY)) {

                keyStore.deleteEntry(ALIAS_KEY);
            }

            // Create new key if needed
            if (!keyStore.containsAlias(ALIAS_KEY)) {
                // Set the alias of the entry in Android KeyStore where the key will appear
                // and the constrains (purposes) in the constructor of the Builder
                keyGenerator.init(
                        new KeyGenParameterSpec.Builder(
                                ALIAS_KEY,
                                KeyProperties.PURPOSE_ENCRYPT |
                                        KeyProperties.PURPOSE_DECRYPT)
                                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                                // Require the user to authenticate with a fingerprint to authorize every use
                                // of the key
                                .setUserAuthenticationRequired(true)
                                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                                .build());
                keyGenerator.generateKey();
            }
        } catch (final Exception e) {
            fingerPrintCallback.onFingerprintException(e);
        }
    }

    @SuppressLint("NewApi")
    public boolean hasEnrolledFingerprints() {
        // fingerprint hardware supported and api level OK
        return isFingerprintSupported(fingerprintManager)
                && fingerprintManager != null
                && fingerprintManager.isHardwareDetected()
                // fingerprints enrolled
                && fingerprintManager.hasEnrolledFingerprints()
                // and lockscreen configured
                && keyguardManager.isKeyguardSecure();
    }

    private void setInitOk(final boolean initOk) {
        this.initOk = initOk;
    }

}