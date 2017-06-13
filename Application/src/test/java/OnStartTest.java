import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;
import com.gsma.mobileconnect.r2.android.main.MobileConnectAndroidView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by d.korbovsky on 7.6.17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({android.support.v4.app.Fragment.class})
public class OnStartTest {

    @InjectMocks
    BaseAuthFragment baseAuthFragment = Mockito.spy(BaseAuthFragment.class);

    @Mock
    MobileConnectAndroidView mobileConnectAndroidView;


    @Test
    public void test() throws Exception {

        Method method = android.support.v4.app.Fragment.class.getMethod("onStart");
        PowerMockito.replace(method).with(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "";
            }
        });
        doNothing().when((android.support.v4.app.Fragment) baseAuthFragment).onStart();
        doNothing().when(mobileConnectAndroidView).initialise();
        BaseAuthFragment.mobileConnectAndroidView = mobileConnectAndroidView;
        baseAuthFragment.onStart();
        verify(mobileConnectAndroidView).initialise();

    }






























    /* @InjectMocks
    BaseAuthFragment spiedBaseAuthFragment;
*//*    @Mock
    OperatorUrls.Builder mockedBuilder;*//*

    @Mock
    OperatorUrls mockedOperatorUrls;

    @Test
    public void simpleTest() throws Exception{
        spiedBaseAuthFragment = Mockito.spy(BaseAuthFragment.class);
        Bundle mockedBundle = Mockito.mock(Bundle.class);
        when(mockedBundle.getString("auth")).thenReturn("auth");
        when(mockedBundle.getString("requestToken")).thenReturn("requestToken");
        when(mockedBundle.getString("userInfo")).thenReturn("userInfo");
        when(mockedBundle.getString("revokeToken")).thenReturn("revokeToken");
        MobileConnectAndroidView mobileConnectAndroidView = Mockito.mock(MobileConnectAndroidView.class);
        doNothing().when(mobileConnectAndroidView).generateDiscoveryManually("secret", "id", "subId", "name", mockedOperatorUrls, spiedBaseAuthFragment);
        //PowerMockito.whenNew(MobileConnectAndroidView.class).withAnyArguments().thenReturn(mobileConnectAndroidView);
*//*        when(mockedBuilder.withAuthorizationUrl("auth")).thenAnswer(setAuthUrl());
        when(mockedBuilder.withRequestTokenUrl("requestToken")).thenAnswer(setRequestTokenUrl());
        when(mockedBuilder.withUserInfoUrl("userInfo")).thenAnswer(setUserInfoUrl());
        when(mockedBuilder.withRevokeTokenUrl("revokeToken")).thenAnswer(setRevokeTokenUrl());
        when(mockedBuilder.build()).thenAnswer(buildOperatorUrls());*//*
        mockedOperatorUrls = new OperatorUrls.Builder()
                .withAuthorizationUrl(mockedBundle.getString("auth"))
                .withRequestTokenUrl(mockedBundle.getString("requestToken"))
                .withUserInfoUrl(mockedBundle.getString("userInfo"))
                .withRevokeTokenUrl(mockedBundle.getString("revokeToken"))
                .build();
        Mockito.doNothing().when(mobileConnectAndroidView).generateDiscoveryManually("secret", "id", "subId", "name", mockedOperatorUrls, spiedBaseAuthFragment);
        spiedBaseAuthFragment.makeManualDiscovery("id", "secret", "subId", "providermetadata", "name", mockedBundle);

        verify(mobileConnectAndroidView).generateDiscoveryManually("secret", "id", "subId", "name", mockedOperatorUrls, spiedBaseAuthFragment);
        Assert.assertTrue(true);

    }

*//*    private Answer<OperatorUrls.Builder> setAuthUrl() {
        return new Answer<OperatorUrls.Builder>() {
            public OperatorUrls.Builder answer(InvocationOnMock invocation) throws Throwable {
                Field field = OperatorUrls.Builder.class.getDeclaredField("authorizationUrl");
                field.setAccessible(true);
                field.set(mockedBuilder, invocation.getArgument(0));
                field.setAccessible(false);
                return mockedBuilder;
            }
        };
    }

    private Answer<OperatorUrls.Builder> setRequestTokenUrl() {
        return new Answer<OperatorUrls.Builder>() {
            public OperatorUrls.Builder answer(InvocationOnMock invocation) throws Throwable {
                Field field = OperatorUrls.class.getDeclaredField("requestTokenUrl");
                field.setAccessible(true);
                field.set(mockedBuilder, invocation.getArgument(0));
                field.setAccessible(false);
                return mockedBuilder;
            }
        };
    }

    private Answer<OperatorUrls.Builder> setUserInfoUrl() {
        return new Answer<OperatorUrls.Builder>() {
            public OperatorUrls.Builder answer(InvocationOnMock invocation) throws Throwable {
                Field field = OperatorUrls.Builder.class.getDeclaredField("userInfoUrl");
                field.setAccessible(true);
                field.set(mockedBuilder, invocation.getArgument(0));
                field.setAccessible(false);
                return mockedBuilder;
            }
        };
    }

    private Answer<OperatorUrls.Builder> setRevokeTokenUrl() {
        return new Answer<OperatorUrls.Builder>() {
            public OperatorUrls.Builder answer(InvocationOnMock invocation) throws Throwable {
                Field field = OperatorUrls.Builder.class.getDeclaredField("revokeTokenUrl");
                field.setAccessible(true);
                field.set(mockedBuilder, invocation.getArgument(0));
                field.setAccessible(false);
                return mockedBuilder;
            }
        };
    }

    private Answer<OperatorUrls> buildOperatorUrls() {
        return new Answer<OperatorUrls>() {
            @Override
            public OperatorUrls answer(InvocationOnMock invocation) throws Throwable {
                Field authField = OperatorUrls.class.getDeclaredField("authorizationUrl");
                authField.setAccessible(true);
                Field requestTokenField = OperatorUrls.class.getDeclaredField("requestTokenUrl");
                requestTokenField.setAccessible(true);
                Field userInfoUrlField = OperatorUrls.class.getDeclaredField("userInfoUrl");
                userInfoUrlField.setAccessible(true);
                Field revokeTokenUrlField = OperatorUrls.class.getDeclaredField("revokeTokenUrl");
                revokeTokenUrlField.setAccessible(true);

                Field builderAuthField = OperatorUrls.Builder.class.getDeclaredField("authorizationUrl");
                builderAuthField.setAccessible(true);
                authField.set(mockedOperatorUrls, builderAuthField.get(mockedBuilder));
                Field builderRequestTokenField = OperatorUrls.Builder.class.getDeclaredField("requestTokenUrl");
                builderRequestTokenField.setAccessible(true);
                builderRequestTokenField.set(mockedOperatorUrls, builderRequestTokenField.get(mockedBuilder));
                Field builderUserInfoUrlField = OperatorUrls.Builder.class.getDeclaredField("userInfoUrl");
                builderUserInfoUrlField.setAccessible(true);
                builderUserInfoUrlField.set(mockedOperatorUrls, builderUserInfoUrlField.get(mockedBuilder));
                Field builderRevokeTokenUrlField = OperatorUrls.Builder.class.getDeclaredField("revokeTokenUrl");
                builderRevokeTokenUrlField.setAccessible(true);
                builderRevokeTokenUrlField.set(mockedOperatorUrls, builderRevokeTokenUrlField.get(mockedBuilder));
                return mockedOperatorUrls;
            }
        };
    }*//*

*/
}
