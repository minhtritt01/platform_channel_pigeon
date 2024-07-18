package com.example.volumelevel

import android.media.AudioManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity(), VolumeApi{
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        FlutterEngineCache
            .getInstance()
            .put(ENGINE_ID, flutterEngine)
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        VolumeApi.setUp(flutterEngine.dartExecutor.binaryMessenger,this)
    }
    override fun getVolumeDevice(): Volume {
        val am = getSystemService(AUDIO_SERVICE) as AudioManager
        val volumeLevel: Double = am.getStreamVolume(AudioManager.STREAM_MUSIC).toDouble()
        val maxVolumeLevel: Int = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val volumePercent = ((volumeLevel.toFloat() / maxVolumeLevel)).toDouble()
        return Volume(value = volumePercent)
    }
    companion object {
        const val ENGINE_ID = "volume_flutter_engine"
    }
}
