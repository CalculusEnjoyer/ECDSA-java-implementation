package com.cryptography.ecdsa;

import java.math.BigInteger;
import java.util.Random;

public class Calculator {

  private Calculator() {
  }

  public static BigPoint multiplyPoint(BigPoint point, BigInteger multipler, Curve curve) {
    if (multipler.equals(BigInteger.ZERO)) {
      return new BigPoint(BigInteger.ZERO, BigInteger.ZERO);
    } else if (multipler.equals(BigInteger.ONE)) {
      return point;
    } else if (multipler.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
      return addPoint(point, multiplyPoint(point, multipler.subtract(BigInteger.ONE), curve),
          curve);
    } else {
      return multiplyPoint(doublePoint(point, curve), multipler.divide(BigInteger.TWO), curve);
    }
  }

  public static BigPoint addPoint(BigPoint point1, BigPoint point2, Curve curve) {
    BigInteger a = (point2.y.subtract(point1.y));
    BigInteger b = (point2.x.subtract(point1.x));
    b = b.modInverse(curve.getP());
    a = a.multiply(b).mod(curve.getP());
    b = a.multiply(a);
    b = ((b.subtract(point1.x)).subtract(point2.x)).mod(curve.getP());

    return new BigPoint(b, (a.multiply(point1.x.subtract(b))).subtract(point1.y).mod(curve.getP()));
  }

  public static BigPoint doublePoint(BigPoint point, Curve curve) {
    BigInteger i = point.x.multiply(point.x).multiply(BigInteger.valueOf(3)).add(curve.getA());
    BigInteger j = (point.y.multiply(BigInteger.valueOf(2))).modInverse(curve.getP());
    i = (i.multiply(j)).mod(curve.getP());
    j = i.multiply(i);
    j = (j.subtract(point.x.multiply(BigInteger.valueOf(2)))).mod(curve.getP());

    return new BigPoint(j, (i.multiply(point.x.subtract(j))).subtract(point.y).mod(curve.getP()));
  }

  public static BigInteger generateRandomIntegerInOrder(BigInteger order) {
    Random rand = new Random();
    BigInteger result;
    do {
      result = new BigInteger(order.bitLength(), rand);
    } while (result.compareTo(order) >= 0);
    return result;
  }
}
