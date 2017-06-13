import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.gsma.mobileconnect.r2.android.demo.activity.ResultsActivity;
import com.gsma.mobileconnect.r2.android.demo.fragments.BaseAuthFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by d.korbovsky on 9.6.17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(BaseAuthFragment.class)
public class DisplayResultTest {

    @InjectMocks
    BaseAuthFragment baseAuthFragment = Mockito.spy(BaseAuthFragment.class);

    @Mock
    FragmentActivity fragmentActivity;

    @Test
    public void test() throws Exception {
        final Intent intent = new Intent(fragmentActivity, ResultsActivity.class);
        PowerMockito.whenNew(Intent.class).withAnyArguments().thenReturn(intent);
        doNothing().when(baseAuthFragment).startActivity(intent);
        Method method = baseAuthFragment.getClass().getDeclaredMethod("displayResult");
        method.setAccessible(true);
        method.invoke(baseAuthFragment);
        verify(baseAuthFragment).startActivity(intent);
    }
}
