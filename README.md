# FIAM

Considering [this issue](https://github.com/firebase/firebase-android-sdk/issues/2681), to reproduce:
1. Clone repo
2. Use device with Android Version >= 9
3. Run MainActivity
4. Check exception being thrown at `deserializeKeyPair()` method.
5. Disable 
    `//FIAM
    implementation 'com.google.firebase:firebase-inappmessaging-display:20.0.0'
    implementation 'com.google.firebase:firebase-analytics:19.0.0'` on gradle and re-run MainActivity, `ClassNotFoundException` will not be thrown.
