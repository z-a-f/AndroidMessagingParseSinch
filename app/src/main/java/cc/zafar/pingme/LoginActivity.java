package cc.zafar.pingme;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends ActionBarActivity {

	EditText usernameEditText, passwordEditText, passwordConfirmEditText, emailEditText;
	Button loginButton, registerButton;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		usernameEditText = (EditText) findViewById(R.id.username_edit_text);
		passwordEditText = (EditText) findViewById(R.id.password_edit_text);
		passwordConfirmEditText = (EditText) findViewById(R.id.password_confirm_edit_text);
		emailEditText = (EditText) findViewById(R.id.email_edit_text);

		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.register_button);

		init();

		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				parseLogin(usernameEditText.getText().toString(),
				           passwordEditText.getText().toString());
			}
		});

		registerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (passwordConfirmEditText.getVisibility() != View.VISIBLE) {
					passwordConfirmEditText.setVisibility(View.VISIBLE);
					emailEditText.setVisibility(View.VISIBLE);
				} else {
					if (passwordEditText.getText().toString()
					                    .equals(passwordConfirmEditText.getText().toString())){
						parseRegister();
					} else {
						Toast.makeText(getApplicationContext(),
						               getString(R.string.passwords_dont_match),
						               Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

	public void init() {
		checkIfLoggedIn();
		passwordConfirmEditText.setVisibility(View.INVISIBLE);
		emailEditText.setVisibility(View.INVISIBLE);
	}

	private void checkIfLoggedIn() {
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			// TODO: switch to another activity
		} else {
			// TODO: show the signup or login screen
		}
	}

	private void parseLogin(String email, String password) {
		ParseUser.logInInBackground(email, password, new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					// TODO: Hooray! The user is logged in.
					Toast.makeText(getApplicationContext(),
					               "Logged in!",
					               Toast.LENGTH_LONG).show();
				} else {
					// TODO: Login failed. Look at the ParseException to see what happened.
					Toast.makeText(getApplicationContext(),
					               e.getMessage(),
					               Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void parseRegister(){
		// TODO: Register with Parse
		ParseUser user = new ParseUser();
		user.setUsername(usernameEditText.getText().toString());
		user.setPassword(passwordEditText.getText().toString());
		user.setEmail(emailEditText.getText().toString());

		// other fields can be set just like with ParseObject
		// user.put("phone", "650-253-0000");

		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					// TODO: Hooray! Let them use the app now.
					Toast.makeText(getApplicationContext(),
					               "Registered!",
					               Toast.LENGTH_LONG).show();
				} else {
					// TODO: Sign up didn't succeed. Look at the ParseException to figure
					// out what went wrong
					Toast.makeText(getApplicationContext(),
					               e.getMessage(),
					               Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
