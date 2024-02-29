package com.mmk.kmpnotifier.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMobileAds.GADAdSizeFromCGSize
import cocoapods.GoogleMobileAds.GADBannerView
import cocoapods.GoogleMobileAds.GADRequest
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGSizeMake
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AdBannerView(
    modifier : Modifier
){
    val bannerView = remember {
        GADBannerView(adSize = GADAdSizeFromCGSize(CGSizeMake(width = 320.0, height = 60.0)))
    }

    Box(
        modifier = modifier
    ){
        UIKitView(
            modifier = modifier.fillMaxSize().background(Color.White),
            factory = {
                val bannerContainer = UIView()
                bannerContainer.addSubview(bannerView)
                bannerContainer
            },
            onResize = { view: UIView, rect: CValue<CGRect> ->
                bannerView.adUnitID = "ca-app-pub-3940256099942544/2934735716"
                bannerView.loadRequest(GADRequest())
            }
        )
    }
}