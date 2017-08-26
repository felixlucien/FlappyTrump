package felix.flappytrump;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import felix.flappytrump.FlappyTrump;

public class AndroidLauncher extends AndroidApplication {

	AdView adView;
	RelativeLayout layout;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		layout = new RelativeLayout(this);

		// Create the libgdx View
		View gameView = initializeForView(new FlappyTrump(), config);
		layout.addView(gameView);

		// Create and setup the AdMob view
		adView = new AdView(this); // Put in your secret key here
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdFailedToLoad(int i) {
				super.onAdFailedToLoad(i);
				Gdx.app.log("AD", "AD FAILED TO LOAD");
			}

			@Override
			public void onAdOpened() {
				super.onAdOpened();
				Gdx.app.log("AD", "AD OPENED");
			}

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				layout.requestLayout();
				Gdx.app.log("AD", "AD LOADED");
			}
		});
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-5839099031964983/3088100254");
		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("776AB001A2A5B903448858276B9B152C");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		layout.addView(adView, adParams);
		adView.loadAd(builder.build());


		setContentView(layout);


	}


	@Override
	protected void onPause() {
		super.onPause();
		adView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		adView.destroy();
	}
}
