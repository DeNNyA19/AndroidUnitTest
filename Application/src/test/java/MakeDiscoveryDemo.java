import com.gsma.mobileconnect.r2.MobileConnectConfig;
import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;
import com.gsma.mobileconnect.r2.android.main.MobileConnectAndroidView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by d.korbovsky on 9.6.17.
 */

@RunWith(PowerMockRunner.class)
public class MakeDiscoveryDemo {

    @InjectMocks
    BaseAuthFragment baseAuthFragment;

    @Mock
    MobileConnectConfig mobileConnectConfig;

    @Mock
    MobileConnectAndroidView mobileConnectAndroidView;

    @Test
    public void test() {

    }
}
