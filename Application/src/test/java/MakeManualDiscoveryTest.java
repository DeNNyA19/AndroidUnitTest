import android.os.Bundle;

import com.gsma.mobileconnect.r2.MobileConnectConfig;
import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;
import com.gsma.mobileconnect.r2.android.main.MobileConnectAndroidView;
import com.gsma.mobileconnect.r2.discovery.OperatorUrls;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by denis on 09.06.2017.
 */
@RunWith(PowerMockRunner.class)

public class MakeManualDiscoveryTest {

    @InjectMocks
    BaseAuthFragment baseAuthFragment = Mockito.spy(BaseAuthFragment.class);

    @Mock
    Bundle bundle;

    @Mock
    MobileConnectAndroidView mobileConnectAndroidView;

    @Mock
    MobileConnectConfig mobileConnectConfig;



    @Test
    public void simpleTest() throws Exception {

        when(bundle.getString("authorization_url")).thenReturn("auth");
        when(bundle.getString("requestToken_url")).thenReturn("requestToken");
        when(bundle.getString("userInfo_url")).thenReturn("userInfo");
        when(bundle.getString("revokeToken_url")).thenReturn("revokeToken");
        //doNothing().when(mobileConnectAndroidView).generateDiscoveryManually("secret", "id", "subId", "name", mockedOperatorUrls, spiedBaseAuthFragment);
        BaseAuthFragment.mobileConnectAndroidView = mobileConnectAndroidView;

        //verify(mobileConnectAndroidView).generateDiscoveryManually("secret", "id", "subId", "name", mockedOperatorUrls, spiedBaseAuthFragment);
        baseAuthFragment.makeManualDiscovery("id", "secret", "subId", "providermetadata", "name", bundle);
        verify(mobileConnectAndroidView).generateDiscoveryManually(eq("secret"), eq("id"), eq("subId"), eq("name"), (OperatorUrls) any(), eq(baseAuthFragment));
    }
}
