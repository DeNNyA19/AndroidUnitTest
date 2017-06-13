import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;
import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragmentRealization;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

/**
 * Created by denis on 08.06.2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(BaseAuthFragment.class)
public class ConnectMobileWithoudDiscoveryTest {

    BaseAuthFragment baseAuthFragment = new BaseAuthFragmentRealization();

    private boolean isCalled = false;

    @Test
    public void test() throws Exception { // throws Exception
        Method method = BaseAuthFragment.class.getDeclaredMethod("setupMobileConnect", boolean.class);
        method.setAccessible(true);
        Mockito.when(method.invoke(baseAuthFragment, false)).thenAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                isCalled = true;
                return null;
            }
        });//error, calls real method. setupMobileConnect must be stubbed, but without calling(problem is - its private)
        baseAuthFragment.connectMobileDemo();
        Assert.assertEquals(isCalled, true);
    }
}

