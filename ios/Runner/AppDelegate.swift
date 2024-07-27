import Flutter
import UIKit
import AVFoundation
@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate, VolumeApi {
    func getVolumeDevice() throws -> Volume {
        let audioSession = AVAudioSession.sharedInstance()
        let vol=audioSession.outputVolume
        do {
            try audioSession.setActive(true)
        } catch {
            print("Error Setting Up Audio Session")
        }
        print("volume \(type(of: vol))")
        var result = Volume.init()
        result.value = Double(vol)
        return result
    }
    
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
      let controller = window?.rootViewController as! FlutterViewController
      VolumeApiSetup.setUp(binaryMessenger: controller.binaryMessenger, api: self)
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
