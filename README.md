<h1 align="center">
  <br>
  <img src="app/src/main/res/drawable-xhdpi/app_icons_hash.png" width="150">
  <br>
 Hash Generator App
 <br>
</h1>

<h4 align="center">Download the latest android release application build.</h4>

<p align="center">
  <a href="https://github.com/thisisvd/Hash-Generator-App/blob/master/support/application/Hash-generator-android-v1.1.0-b2-release.apk">
    <img src="https://img.shields.io/badge/-Github-231F20?logo=github&logoColor=white" width="85">
  </a>
</p>

<p align="center">
 <a href="#introduction">Introduction</a> •
 <a href="#algo-used">Algo-used</a> •
 <a href="#screenshots">Screenshots</a> •
 <a href="#tech-stack">Tech Stack</a>
</p>

## Introduction

The `hash generator` application converts the text into a particular hashed string by using some algorithms like SHA-256, SHA-512, and MD5. The application is built with the Kotlin language. It contains three simple screens: a splash screen, a main screen where we can select an appropriate algorithm, type text, and also clear that text according to our needs, and a last screen where the generated hash text has been displayed. The user enters the text and then selects the algorithm from SHA256, SHA512, or MD5, any one of them, and on pressing the click button with a nice animation, a new screen has been loaded showing a generated hashed text. Android Jetpack Architecture is used, including MVVM (Model-View-ViewModel), Navigation-Args, Navhost etc.

## Algo-used

`SHA-256:` SHA-256 (Secure Hash Algorithm 256-bit) is a cryptographic hash function that takes an input (or "message") and returns a fixed 256-bit (32-byte) hash value, which is typically represented as a 64-character hexadecimal number. It is widely used in security protocols like SSL/TLS for data integrity and in blockchain technologies like Bitcoin to ensure the integrity of data and prevent tampering. The algorithm is designed to be collision-resistant, meaning it is computationally infeasible to find two different inputs that produce the same hash.

`SHA-512:` SHA-512 (Secure Hash Algorithm 512-bit) is similar to SHA-256 but produces a 512-bit (64-byte) hash value, typically represented as a 128-character hexadecimal number. Like SHA-256, it is a cryptographic hash function used for data integrity and security purposes. SHA-512 is part of the SHA-2 family and is designed to be more secure, providing a higher level of protection due to its larger hash output. It is commonly used in applications requiring stronger security, such as in digital signatures, certificates, and blockchain systems. It also shares the same resistance to collisions as SHA-256, making it computationally infeasible to generate two different inputs that produce the same hash.

`MD5:` MD5 (Message Digest Algorithm 5) is a widely used cryptographic hash function that produces a 128-bit hash value from an input of any size. It is commonly used for verifying data integrity, generating checksums, and storing hashed passwords. However, MD5 is considered cryptographically broken and unsuitable for further security use due to vulnerabilities, such as susceptibility to collision attacks (where two different inputs produce the same hash). As a result, MD5 is generally replaced by more secure hashing algorithms like SHA-256 in security-critical applications.

## Screenshots

<p float="left">
<img src="https://github.com/thisisvd/Hash-Generator-App/blob/master/support/screenshots/hg_1.png" width="250">
<img src="https://github.com/thisisvd/Hash-Generator-App/blob/master/support/screenshots/hg_2.png" width="250">
<img src="https://github.com/thisisvd/Hash-Generator-App/blob/master/support/screenshots/hg_3.png" width="250">
</p>

## Tech Stack

Kotlin, MVVM, XML, Navigation-Args, Animations, Hashing Algorithms etc.
