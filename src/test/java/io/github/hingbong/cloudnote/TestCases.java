package io.github.hingbong.cloudnote;

import java.util.UUID;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.jupiter.api.Test;

class TestCases {


  @Test
  void testSha256() {
    Sha256Hash sha256Hash = new Sha256Hash("1234", UUID.randomUUID().toString(), 3);
    System.out.println(sha256Hash.toHex());
  }
}
