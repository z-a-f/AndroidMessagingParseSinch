package cc.zafar.pingme;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by ZafarTakhirov on 4/15/15.
 */
public class MainApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize Parse:
		parseInit();

	}

	private void parseInit() {
		// Enable Local Datastore.
		// Parse.enableLocalDatastore(this);
		Parse.initialize(this, getString(cc.zafar.pingme.R.string.APP_ID),
		                 getString(cc.zafar.pingme.R.string.CLIENT_KEY));
	}
}
