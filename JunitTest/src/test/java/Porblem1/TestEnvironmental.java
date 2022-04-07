package Porblem1;

import Problem1.Checker;
import Problem1.Environmental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static org.mockito.Mockito.*;

public class TestEnvironmental {

    @Mock
    private Environmental env;

    @InjectMocks
    private Checker checker;

    @BeforeEach
    public void before(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckerRemain_start(){
        // 设置当前时间为17点
        Calendar cal = Calendar.getInstance();
        cal.set(2022,3,28,17,33,23);
        when(env.getTime()).thenReturn(cal.getTimeInMillis());

        checker.reminder();
        verify(env).getTime();
        verify(env).playWavFile("quit_whistle.wav");
    }

}
