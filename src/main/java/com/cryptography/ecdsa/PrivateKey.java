package com.cryptography.ecdsa;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PrivateKey {

  public BigInteger key;

  public PrivateKey(BigInteger order) {
    key = Calculator.generateRandomIntegerInOrder(order);
  }
}
