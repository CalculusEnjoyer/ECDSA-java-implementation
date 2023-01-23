# Java implementation of the Elliptic Curve Digital Signature Algorithm

This is a mini ECDSA library that provides opportunity for creating signatures and validating String messages. In Curve class you can find constant of Secp256k1 curve (btw it is also used in Bitcoin network as well) for quick testing. As well it was covered by unit tests.

## Main methods overview

### Signature.signMessage(PrivateKey k, String m, Curve c) ⟹ `Signature`
Signs the message and returns the Signature

### Signature.verifyMessage(String m, Signature s, PublicKey k, Curve c) ⟹ `boolean`
Returns true if the message is verified 

An example of signing a message is shown in Main.java.






