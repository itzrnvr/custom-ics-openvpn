
# jPipe an OpenVPN based VPN client!

### Description

This project is a fork of the [Android Open-VPN client](https://github.com/schwabe/ics-openvpn) which includes the ability to import (.OVPNB) files.

What are (.OVPNB) files? These files contain multiple OVPN files bundled together and encrypted. The B in the end stands for bundle.

### Usage Instructions
- Click the import profiles button on the action bar of profiles fragment.
- Select to choose OVPNB files.
- Locate the files and import them.
- The app will automatically decrypt, extract and load the config files one by one.

### Build instructions:
test
- Install sdk, ndk, cmake (e.g. with Android studio), swig (3.0+), on
  Windows perl might be needed for mbedtls

- Fetch the git submodules (the default urls for the submodules work as long as the main repo url is on github):

  - git submodule init
  - git submodule update

- Build the project using "gradle build" (Or use Android Studio).
  Ensure that the swig executable is the path, otherwise the build will fail.

- Android studio tends to the whole build of binaries in its sync
  gradle phase to 15 minutes for initial gradle sync are completely
  normal.

- To have a version with UI be sure to select the UI variant in Android studio under build variants.

- **Prefer to use the same NDK version on your system as pre-defined in the project to avoid any unnecessary build failures.**
- **After installing swig, make sure to add Environment Variables and restart your system.**

##	 NOTES:
**UI Package**:
- VPNProfileList fragment contains the starting point of all the changes related to the bundle imports.
- BundleReader class inside the core package of the UI category handles the decryption and extraction of data from .OVPNB files. The process is self-explanatory.
- Encryption and decryption method used is AES256. It uses a security key to encrypt and decrypt files.
- The OVPNB files will only work inside JPIPE Client, they are unusable inside other Open VPN clients.

#### Extra libraries Used:
ZIP4J - https://github.com/srikanth-lingala/zip4
