package com.cryptography.ecdsa;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PublicKey {

  public BigPoint keyPoint;

  public PublicKey(Curve curve, PrivateKey privateKey) {
    keyPoint = Calculator.multiplyPoint(curve.getGenerator(), privateKey.getKey(), curve);
  }
}
