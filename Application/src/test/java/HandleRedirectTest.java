import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.gsma.mobileconnect.r2.MobileConnectConfig;
import com.gsma.mobileconnect.r2.MobileConnectStatus;
import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;
import com.gsma.mobileconnect.r2.android.main.MobileConnectAndroidView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;
import java.net.URI;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

/**
 * Created by d.korbovsky on 9.6.17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({BaseAuthFragment.class, URI.class})
public class HandleRedirectTest {

    BaseAuthFragment instance;
    Method handleRedirect;

    @InjectMocks
    BaseAuthFragment baseAuthFragment = Mockito.spy(BaseAuthFragment.class);

    @Mock
    MobileConnectStatus mobileConnectStatus;

    @Mock
    MobileConnectAndroidView mobileConnectAndroidView;

    @Mock
    FragmentActivity fragmentActivity;

    @Mock
    MobileConnectConfig mobileConnectConfig;

    URI uri;

    @Test
    public void test() throws Exception { // for COMPLETE, throws Exception
        uri = PowerMockito.mock(URI.class);
        instance = PowerMockito.mock(BaseAuthFragment.class, Mockito.CALLS_REAL_METHODS); //for ability to make some methods doNothing()
        //BaseAuthFragment.mobileConnectStatus = mobileConnectStatus;
        handleRedirect = baseAuthFragment.getClass().getDeclaredMethod("handleRedirect", MobileConnectStatus.class);
        handleRedirect.setAccessible(true);
        when(mobileConnectStatus.getState()).thenReturn("state");
        when(mobileConnectStatus.getNonce()).thenReturn("nonce");
        when(mobileConnectStatus.getUrl()).thenReturn("url");
        when(mobileConnectConfig.getRedirectUrl()).thenReturn(uri);
        when(uri.toString()).thenReturn("uri");

        when(mobileConnectStatus.getResponseType()).thenReturn(MobileConnectStatus.ResponseType.COMPLETE);
        testComplete();
        when(mobileConnectStatus.getResponseType()).thenReturn(MobileConnectStatus.ResponseType.AUTHENTICATION);
        testAuthentication();
        when(mobileConnectStatus.getResponseType()).thenReturn(MobileConnectStatus.ResponseType.OPERATOR_SELECTION);
        //testSelection();
        when(mobileConnectStatus.getResponseType()).thenReturn(MobileConnectStatus.ResponseType.START_AUTHENTICATION);
        testStart();
    }

    public void testComplete() throws Exception {
        PowerMockito.doNothing().when(instance, "displayResult");
        handleRedirect.invoke(instance, mobileConnectStatus);
        Assert.assertEquals(mobileConnectStatus, BaseAuthFragment.mobileConnectStatus);
        verifyPrivate(instance, times(1)).invoke("displayResult");
    }

    public void testAuthentication() throws Exception {
        BaseAuthFragment.mobileConnectAndroidView = mobileConnectAndroidView;
        PowerMockito.doReturn(fragmentActivity).when((Fragment)instance).getActivity();
        doNothing().when(mobileConnectAndroidView).attemptAuthenticationWithWebView(fragmentActivity, instance, "url", "state", "nonce", null);
        handleRedirect.invoke(instance, mobileConnectStatus);
        verify(mobileConnectAndroidView).attemptAuthenticationWithWebView(fragmentActivity, instance, "url", "state", "nonce", null);
    }

    public void testSelection() throws Exception {
        BaseAuthFragment.mobileConnectAndroidView = mobileConnectAndroidView;
        PowerMockito.doReturn(fragmentActivity).when((Fragment)instance).getActivity();
        //PowerMockito.doReturn(uri).when(mobileConnectConfig.getRedirectUrl());

        doNothing().when(mobileConnectAndroidView).attemptDiscoveryWithWebView(fragmentActivity, instance, "url", "uri", null);
        handleRedirect.invoke(instance, mobileConnectStatus);
        verify(mobileConnectAndroidView).attemptDiscoveryWithWebView(fragmentActivity, instance, "url", "uri", null);
    }

    public void testStart() {

    }
}
