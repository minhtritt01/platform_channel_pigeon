import 'package:pigeon/pigeon.dart';

class Volume {
  double? value;
}

@HostApi()
abstract class VolumeApi {
  Volume getVolumeDevice();
}
