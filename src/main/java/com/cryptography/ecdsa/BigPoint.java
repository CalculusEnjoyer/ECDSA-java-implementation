package com.cryptography.ecdsa;

import java.math.BigInteger;

public class BigPoint {

  public BigInteger x;
  public BigInteger y;

  public BigPoint() {
  }

  public BigPoint(BigInteger x, BigInteger y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "BigPoint{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
