# TIFF-Implementation-Checker
TIFF Implementation Checker

## Usage
Usage: tiffimplementation-checker <input_file> [ISO]

Available ISOs:

- TIFF_Baseline_Core_6_0
- TIFF_Baseline_Extended_6_0
- TIFF_EP
- TiffITProfileChecker
- TiffITP1ProfileChecker
- TiffITP2ProfileChecker
- TIAProfileChecker

## Compile

### 1 Check Java version

`java -version`

If not installed, or the version is lower than 1.8 then Java 8 needs to be installed.

### 2 Install with Maven

Type

`mvn install`

Alternatively, for a faster build (without javadoc, testing and gpg singing), you can type

`mvn install -Dmaven.javadoc.skip=true -DskipTests -Dgpg.skip`
