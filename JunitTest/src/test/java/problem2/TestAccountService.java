package problem2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class TestAccountService {
    @Mock private AccountManager mockManager;

    @InjectMocks
    private AccountService service;

    @BeforeEach
    public void before(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("测试转账操作")
    @Test
    public void TestTransfer(){
        Account account1 = new Account("user1",5000);
        Account account2 = new Account("user2",5000);

        // 打桩
        when(mockManager.findAccountForUser("user1")).thenReturn(account1);
        when(mockManager.findAccountForUser("user2")).thenReturn(account2);

        // 验证
        service.transfer("user1","user2",2000);
        assertThat(account1.getBalance()).isEqualTo(3000);
        assertThat(account2.getBalance()).isEqualTo(7000);

        verify(mockManager).updateAccount(account1);
        verify(mockManager).updateAccount(account2);

    }
}
