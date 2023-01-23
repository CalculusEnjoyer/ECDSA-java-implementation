import com.cryptography.ecdsa.Curve;
import com.cryptography.ecdsa.PrivateKey;
import com.cryptography.ecdsa.PublicKey;
import com.cryptography.ecdsa.Signature;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EADSAtests {

  @Test
  void ecdsaValid() throws NoSuchAlgorithmException {
    PrivateKey privateKey = new PrivateKey(Curve.Secp256k1.getOrder());
    PublicKey publicKey = new PublicKey(Curve.Secp256k1, privateKey);
    Signature sign = Signature.signMessage(privateKey, "Hello", Curve.Secp256k1);

    Assertions.assertTrue(Signature.verifyMessage("Hello", sign, publicKey, Curve.Secp256k1));
  }

  @Test
  void ecdsaInvalidMessage() throws NoSuchAlgorithmException {
    PrivateKey privateKey = new PrivateKey(Curve.Secp256k1.getOrder());
    PublicKey publicKey = new PublicKey(Curve.Secp256k1, privateKey);
    Signature sign = Signature.signMessage(privateKey, "Hello my friend", Curve.Secp256k1);

    Assertions.assertFalse(
        Signature.verifyMessage("Hello your enemy", sign, publicKey, Curve.Secp256k1));
  }

  @Test
  void ecdsaInvalidPublicKey() throws NoSuchAlgorithmException {
    PrivateKey privateKey = new PrivateKey(Curve.Secp256k1.getOrder());
    PublicKey publicKey = new PublicKey(Curve.Secp256k1, privateKey);
    Signature sign = Signature.signMessage(privateKey, "Hello my friend", Curve.Secp256k1);

    Assertions.assertFalse(
        Signature.verifyMessage("Hello my friend", sign,
            new PublicKey(Curve.Secp256k1, new PrivateKey(Curve.Secp256k1.getOrder())),
            Curve.Secp256k1));
  }

  @Test
  void ecdsaInvalidSignKey() throws NoSuchAlgorithmException {
    PrivateKey privateKey = new PrivateKey(Curve.Secp256k1.getOrder());
    PublicKey publicKey = new PublicKey(Curve.Secp256k1, privateKey);
    Signature sign = Signature.signMessage(privateKey, "Hello my friend", Curve.Secp256k1);
    Signature wrongSign = Signature.signMessage(new PrivateKey(Curve.Secp256k1.getOrder()),
        "Hello my friend", Curve.Secp256k1);

    Assertions.assertFalse(
        Signature.verifyMessage("Hello my friend", wrongSign, publicKey, Curve.Secp256k1));
  }
}
