package icefrog.com.latte.ec.sign;

import icefrog.com.latte.core.app.AccountManager;

public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener signListener) {
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
