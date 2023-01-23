package com.cryptography;

import com.cryptography.ecdsa.BigPoint;
import com.cryptography.ecdsa.Calculator;
import com.cryptography.ecdsa.Curve;
import com.cryptography.ecdsa.PrivateKey;
import com.cryptography.ecdsa.PublicKey;
import com.cryptography.ecdsa.Signature;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Main {

  public static void main(String[] args) throws NoSuchAlgorithmException {
    PrivateKey privateKey = new PrivateKey(Curve.Secp256k1.getOrder());
    PublicKey publicKey = new PublicKey(Curve.Secp256k1, privateKey);
    Signature sign = Signature.signMessage(privateKey, "Hello", Curve.Secp256k1);
    System.out.println(Signature.verifyMessage( "Hello",sign,  publicKey, Curve.Secp256k1));
  }
}