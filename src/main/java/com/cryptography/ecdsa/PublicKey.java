package com.cryptography.ecdsa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PublicKey {

  public BigPoint keyPoint;

  public PublicKey(Curve curve, PrivateKey privateKey) {
    keyPoint = Calculator.multiplyPoint(curve.getGenerator(), privateKey.getKey(), curve);
  }
}