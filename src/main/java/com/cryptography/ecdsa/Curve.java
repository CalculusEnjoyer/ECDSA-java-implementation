package com.cryptography.ecdsa;

import java.math.BigInteger;
import java.util.Random;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Curve {

  /**
   * y^2 = *x^3 + a*x + b (mod p)  has been used.
   * <p>
   * generator is a generation point.
   */

  private BigInteger a;
  private BigInteger b;
  private BigInteger p;
  private BigInteger order;
  private BigPoint generator;


  public Curve(BigInteger a, BigInteger b, BigInteger p, BigInteger order, BigPoint generator) {
    this.a = a;
    this.b = b;
    this.p = p;
    this.order = order;
    this.generator = generator;
  }

  /**
   * Checks if point belongs to the curve
   */

  public boolean validateIfOnCurve(BigPoint point) {

    return point.x.pow(3)
        .add(this.getA().multiply(point.x))
        .add(this.getB())
        .subtract(point.y.pow(2))
        .mod(this.getP())
        .equals(BigInteger.ZERO);
  }

  public BigInteger generateRandomIntegerInOrder() {
    return Calculator.generateRandomIntegerInOrder(order);
  }

  /**
   * Secp256k1 curve properties
   */

  public static final Curve Secp256k1 = new Curve(
      BigInteger.ZERO,
      BigInteger.valueOf(7),
      new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffffffffffefffffc2f", 16),
      new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16),
      new BigPoint(
          new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16),
          new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16))
  );
}
