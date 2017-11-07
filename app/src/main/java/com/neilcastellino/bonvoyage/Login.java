package com.neilcastellino.bonvoyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity
//        implements GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener, ResultCallback<People.LoadPeopleResult>
{

//    private static final String TAG = Login.class.getSimpleName();
//
//    /* *************************************
//     *              GENERAL                *
//     ***************************************/
//    /* A dialog that is presented until the Firebase authentication finished. */
//    private ProgressDialog mAuthProgressDialog;
//
//    /* A reference to the Firebase */
//    private Firebase mFirebaseRef;
//
//    /* Data from the authenticated user */
//    private AuthData mAuthData;
//
//    /* Listener for Firebase session changes */
//    private Firebase.AuthStateListener mAuthStateListener;
//
//    /* *************************************
//     *              FACEBOOK               *
//     ***************************************/
//    /* The login button for Facebook */
//    private LoginButton mFacebookLoginButton;
//    /* The callback manager for Facebook */
//    private CallbackManager mFacebookCallbackManager;
//    /* Used to track user logging in/out off Facebook */
//    private AccessTokenTracker mFacebookAccessTokenTracker;
//
//
//    /* *************************************
//     *              GOOGLE                 *
//     ***************************************/
//    /* Request code used to invoke sign in user interactions for Google+ */
//    public static final int RC_GOOGLE_LOGIN = 1;
//
//    /* Client used to interact with Google APIs. */
//    private GoogleApiClient mGoogleApiClient;
//
//    /* A flag indicating that a PendingIntent is in progress and prevents us from starting further intents. */
//    private boolean mGoogleIntentInProgress;
//
//    /* Track whether the sign-in button has been clicked so that we know to resolve all issues preventing sign-in
//     * without waiting. */
//    private boolean mGoogleLoginClicked;
//
//    /* Store the connection result from onConnectionFailed callbacks so that we can resolve them when the user clicks
//     * sign-in. */
//    private ConnectionResult mGoogleConnectionResult;
//
//    /* The login button for Google */
//    private SignInButton mGoogleLoginButton;
//
//    static String personName, gmail;

    String email, password;
    Button Login, Register;
    EditText USERNAME, USERPASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        USERNAME = (EditText) findViewById(R.id.login_username);
        USERPASS = (EditText) findViewById(R.id.login_password);

        Login = (Button) findViewById(R.id.login_button);
        final Firebase ref = new Firebase(Constants.FIREBASE_URL);

        Register = (Button) findViewById(R.id.register_button);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = USERNAME.getText().toString();
                password = USERPASS.getText().toString();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    final String emailAddress = email;

                    //Login with an email/password combination
                    ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            // Authenticated successfully with payload authData
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("email", emailAddress);
                            ref.child("users").child(authData.getUid()).updateChildren(map);

                            Intent intent = new Intent(Login.this, Dashboard.class);
                            intent.putExtra("email_main", email);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // Authenticated failed with error firebaseError
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle(R.string.login_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }
        });
//
//
//        /* *************************************
//         *              FACEBOOK               *
//         ***************************************/
//        /* Load the Facebook login button and set up the tracker to monitor access token changes */
//        mFacebookCallbackManager = CallbackManager.Factory.create();
//        mFacebookLoginButton = (LoginButton) findViewById(R.id.login_button_fb);
//        mFacebookAccessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                Log.i(TAG, "Facebook.AccessTokenTracker.OnCurrentAccessTokenChanged");
//                Login.this.onFacebookAccessTokenChange(currentAccessToken);
//            }
//        };
//
//        /* *************************************
//         *               GOOGLE                *
//         ***************************************/
//        /* Load the Google login button */
//        mGoogleLoginButton = (SignInButton) findViewById(R.id.login_with_google);
//        mGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGoogleLoginClicked = true;
//                if (!mGoogleApiClient.isConnecting()) {
//                    if (mGoogleConnectionResult != null) {
//                        resolveSignInError();
//                    } else if (mGoogleApiClient.isConnected()) {
//                        getGoogleOAuthTokenAndLogin();
//                    } else {
//                    /* connect API now */
//                        Log.d(TAG, "Trying to connect to Google API");
//                        mGoogleApiClient.connect();
//                    }
//                }
//            }
//        });
//        /* Setup the Google API object to allow Google+ logins */
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(Plus.API, Plus.PlusOptions.builder().build())
//                .addScope(Plus.SCOPE_PLUS_LOGIN)
//                .build();
//        /* *************************************
//         *               GENERAL               *
//         ***************************************/
//        Constants con = new Constants();
//        /* Create the Firebase ref that is used for all authentication with Firebase */
//        mFirebaseRef = new Firebase(con.FIREBASE_URL);
//
//        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
//        mAuthProgressDialog = new ProgressDialog(this);
//        mAuthProgressDialog.setTitle("Loading");
//        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
//        mAuthProgressDialog.setCancelable(false);
//        mAuthProgressDialog.show();
//
//        mAuthStateListener = new Firebase.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(AuthData authData) {
//                mAuthProgressDialog.hide();
//                setAuthenticatedUser(authData);
//            }
//        };
//        /* Check if the user is authenticated with Firebase already. If this is the case we can set the authenticated
//         * user and hide hide any login buttons */
//        mFirebaseRef.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // if user logged in with Facebook, stop tracking their token
//        if (mFacebookAccessTokenTracker != null) {
//            mFacebookAccessTokenTracker.stopTracking();
//        }
//
//        // if changing configurations, stop tracking firebase session.
//        mFirebaseRef.removeAuthStateListener(mAuthStateListener);
//    }
//
//    /**
//     * This method fires when any startActivityForResult finishes. The requestCode maps to
//     * the value passed into startActivityForResult.
//     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Map<String, String> options = new HashMap<String, String>();
//        if (requestCode == RC_GOOGLE_LOGIN) {
//            /* This was a request by the Google API */
//            if (resultCode != RESULT_OK) {
//                mGoogleLoginClicked = false;
//            }
//            mGoogleIntentInProgress = false;
//            if (!mGoogleApiClient.isConnecting()) {
//                mGoogleApiClient.connect();
//            }
//        } else {
//            /* Otherwise, it's probably the request by the Facebook login button, keep track of the session */
//            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        /* If a user is currently authenticated, display a logout menu */
//        if (this.mAuthData != null) {
//            getMenuInflater().inflate(R.menu.menu_homepage, menu);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_logout) {
//            logout();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Unauthenticate from Firebase and from providers where necessary.
//     */
//    private void logout() {
//        if (this.mAuthData != null) {
//            /* logout of Firebase */
//            mFirebaseRef.unauth();
//            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
//             * Facebook/Google+ after logging out of Firebase. */
//            if (this.mAuthData.getProvider().equals("facebook")) {
//                /* Logout from Facebook */
//                LoginManager.getInstance().logOut();
//            } else if (this.mAuthData.getProvider().equals("google")) {
//                /* Logout from Google+ */
//                if (mGoogleApiClient.isConnected()) {
//                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//                    mGoogleApiClient.disconnect();
//                }
//            }
//            /* Update authenticated user and show login buttons */
//            setAuthenticatedUser(null);
//        }
//    }
//
//    /**
//     * This method will attempt to authenticate a user to firebase given an oauth_token (and other
//     * necessary parameters depending on the provider)
//     */
//    private void authWithFirebase(final String provider, Map<String, String> options) {
//        if (options.containsKey("error")) {
//            showErrorDialog(options.get("error"));
//        } else {
//            mAuthProgressDialog.show();
//            if (provider.equals("twitter")) {
//                // if the provider is twitter, we must pass in additional options, so use the options endpoint
//                mFirebaseRef.authWithOAuthToken(provider, options, new AuthResultHandler(provider));
//            } else {
//                // if the provider is not twitter, we just need to pass in the oauth_token
//                mFirebaseRef.authWithOAuthToken(provider, options.get("oauth_token"), new AuthResultHandler(provider));
//            }
//        }
//    }
//
//    /**
//     * Once a user is logged in, take the mAuthData provided from Firebase and "use" it.
//     */
//    private void setAuthenticatedUser(AuthData authData) {
//        if (authData != null) {
//            /* Hide all the login buttons */
//            mFacebookLoginButton.setVisibility(View.GONE);
//            mGoogleLoginButton.setVisibility(View.GONE);
//            /* show a provider specific status text */
//            String name = null;
//            if (authData.getProvider().equals("facebook")
//                    || authData.getProvider().equals("google")) {
//                name = (String) authData.getProviderData().get("displayName");
//            } else if (authData.getProvider().equals("anonymous")
//                    || authData.getProvider().equals("password")) {
//                name = authData.getUid();
//            } else {
//                Log.e(TAG, "Invalid provider: " + authData.getProvider());
//            }
//            if (name != null) {
//                getProfileInformation();
//                Intent intent = new Intent(Login.this, Dashboard.class);
//                intent.putExtra("email_main", gmail);
//                intent.putExtra("name", personName);
//                intent.putExtra("provider", "yes");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        } else {
//            /* No authenticated user show all the login buttons */
//            mFacebookLoginButton.setVisibility(View.VISIBLE);
//            mGoogleLoginButton.setVisibility(View.VISIBLE);
//        }
//        this.mAuthData = authData;
//        /* invalidate options menu to hide/show the logout button */
//        supportInvalidateOptionsMenu();
//    }
//
//    /**
//     * Show errors to users
//     */
//    private void showErrorDialog(String message) {
//        new AlertDialog.Builder(this)
//                .setTitle("Error")
//                .setMessage(message)
//                .setPositiveButton(android.R.string.ok, null)
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//    }
//
//    /**
//     * Utility class for authentication results
//     */
//    private class AuthResultHandler implements Firebase.AuthResultHandler {
//
//        private final String provider;
//
//        public AuthResultHandler(String provider) {
//            this.provider = provider;
//        }
//
//        @Override
//        public void onAuthenticated(AuthData authData) {
//            mAuthProgressDialog.hide();
//            Log.i(TAG, provider + " auth successful");
//            setAuthenticatedUser(authData);
//        }
//
//        @Override
//        public void onAuthenticationError(FirebaseError firebaseError) {
//            mAuthProgressDialog.hide();
//            showErrorDialog(firebaseError.toString());
//        }
//    }
//
//    /* ************************************
//     *             FACEBOOK               *
//     **************************************
//     */
//    private void onFacebookAccessTokenChange(AccessToken token) {
//        if (token != null) {
//            mAuthProgressDialog.show();
//            mFirebaseRef.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
//        } else {
//            // Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout
//            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
//                mFirebaseRef.unauth();
//                setAuthenticatedUser(null);
//            }
//        }
//    }
//
//    /* ************************************
//     *              GOOGLE                *
//     **************************************
//     */
//    /* A helper method to resolve the current ConnectionResult error. */
//    private void resolveSignInError() {
//        if (mGoogleConnectionResult.hasResolution()) {
//            try {
//                mGoogleIntentInProgress = true;
//                mGoogleConnectionResult.startResolutionForResult(this, RC_GOOGLE_LOGIN);
//            } catch (IntentSender.SendIntentException e) {
//                // The intent was canceled before it was sent.  Return to the default
//                // state and attempt to connect to get an updated ConnectionResult.
//                mGoogleIntentInProgress = false;
//                mGoogleApiClient.connect();
//            }
//        }
//    }
//
//    private void getGoogleOAuthTokenAndLogin() {
//        mAuthProgressDialog.show();
//        /* Get OAuth token in Background */
//        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
//            String errorMessage = null;
//
//            @Override
//            protected String doInBackground(Void... params) {
//                String token = null;
//
//                try {
//                    String scope = String.format("oauth2:%s", Scopes.PLUS_LOGIN);
//                    token = GoogleAuthUtil.getToken(Login.this, Plus.AccountApi.getAccountName(mGoogleApiClient), scope);
//                } catch (IOException transientEx) {
//                    /* Network or server error */
//                    Log.e(TAG, "Error authenticating with Google: " + transientEx);
//                    errorMessage = "Network error: " + transientEx.getMessage();
//                } catch (UserRecoverableAuthException e) {
//                    Log.w(TAG, "Recoverable Google OAuth error: " + e.toString());
//                    /* We probably need to ask for permissions, so start the intent if there is none pending */
//                    if (!mGoogleIntentInProgress) {
//                        mGoogleIntentInProgress = true;
//                        Intent recover = e.getIntent();
//                        startActivityForResult(recover, RC_GOOGLE_LOGIN);
//                    }
//                } catch (GoogleAuthException authEx) {
//                    /* The call is not ever expected to succeed assuming you have already verified that
//                     * Google Play services is installed. */
//                    Log.e(TAG, "Error authenticating with Google: " + authEx.getMessage(), authEx);
//                    errorMessage = "Error authenticating with Google: " + authEx.getMessage();
//                }
//                return token;
//            }
//
//            @Override
//            protected void onPostExecute(String token) {
//                mGoogleLoginClicked = false;
//                if (token != null) {
//                    /* Successfully got OAuth token, now login with Google */
//                    mFirebaseRef.authWithOAuthToken("google", token, new AuthResultHandler("google"));
//                } else if (errorMessage != null) {
//                    mAuthProgressDialog.hide();
//                    showErrorDialog(errorMessage);
//                }
//            }
//        };
//        task.execute();
//    }
//
//    @Override
//    public void onConnected(final Bundle bundle) {
//        /* Connected with Google API, use this to authenticate with Firebase */
//        getGoogleOAuthTokenAndLogin();
//    }
//
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        if (!mGoogleIntentInProgress) {
//            /* Store the ConnectionResult so that we can use it later when the user clicks on the Google+ login button */
//            mGoogleConnectionResult = result;
//
//            if (mGoogleLoginClicked) {
//                /* The user has already clicked login so we attempt to resolve all errors until the user is signed in,
//                 * or they cancel. */
//                resolveSignInError();
//            } else {
//                Log.e(TAG, result.toString());
//            }
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        // ignore
//    }
//
//    private void getProfileInformation() {
//        try {
//            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
//                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
//                personName = currentPerson.getDisplayName();
//                gmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
