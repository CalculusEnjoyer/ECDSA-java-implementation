package com.cryptography.ecdsa;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Signature {

  BigInteger r;
  BigInteger s;

  public static Signature signMessage(PrivateKey privateKey, String message, Curve curve)
      throws NoSuchAlgorithmException {
    BigInteger numberMessage = getHashedInteger(message);
    BigInteger randNum = Calculator.generateRandomIntegerInOrder(curve.getOrder());
    BigPoint randomSignPoint = Calculator.multiplyPoint(curve.getGenerator(), randNum, curve);
    BigInteger r = randomSignPoint.x.mod(curve.getOrder());
    BigInteger s = ((numberMessage.add(r.multiply(privateKey.getKey()))).multiply(
        randNum.modInverse(Curve.Secp256k1.getOrder()))).mod(curve.getOrder());
    return new Signature(r, s);
  }

  public static boolean verifyMessage(String message, Signature signature, PublicKey publicKey,
      Curve curve)
      throws NoSuchAlgorithmException {
    BigInteger numberMessage = getHashedInteger(message);
    BigInteger r = signature.r;
    BigInteger s = signature.s;

    if (r.compareTo(new BigInteger(String.valueOf(1))) < 0) {
      return false;
    }
    if (r.compareTo(curve.getOrder()) >= 0) {
      return false;
    }
    if (s.compareTo(new BigInteger(String.valueOf(1))) < 0) {
      return false;
    }
    if (s.compareTo(curve.getOrder()) >= 0) {
      return false;
    }

    BigInteger w = s.modInverse(curve.getOrder());
    BigPoint u1 = Calculator.multiplyPoint(curve.getGenerator(),
        numberMessage.multiply(w).mod(curve.getOrder()), curve);
    BigPoint u2 = Calculator.multiplyPoint(publicKey.getKeyPoint(),
        r.multiply(w).mod(curve.getOrder()), curve);
    BigPoint v = Calculator.addPoint(u1, u2, curve);

    return v.x.mod(curve.getOrder()).equals(r);
  }

  public static BigInteger getHashedInteger(String message) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    byte[] temp = md.digest(message.getBytes());
    return new BigInteger(1, temp);
  }

  @Override
  public String toString() {
    return "Signature{" +
        "r=" + r +
        ", s=" + s +
        '}';
  }
}
