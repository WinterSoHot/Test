package cn.dx.mocktio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author gudongxian
 * @date 2022/2/10
 */
public class MockObject {
    private List<String> mockList;
    private List<String> spyList;

    @BeforeEach
    void setUp() {
        mockList = mock(List.class);
        spyList = spy(new ArrayList<String>());
    }

    @Test
    void mockObject() {
        // Mock 对象的行为返回都是默认值
        mockList.add("one");
        assertEquals(0, mockList.size());
    }

    @Test
    void verifyAction() {
        mockList.add("one");
        mockList.clear();
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    @Test
    void whenAction() {
        when(mockList.get(1)).then((index) -> "123");
        assertEquals("123", mockList.get(1));
    }

    @Test
    void spyObject() {
        spyList.add("123");
        assertEquals(1, spyList.size());
        when(spyList.get(0)).then((index) -> "aaa");
        verify(spyList, times(1)).add("123");
        assertEquals("aaa", spyList.get(0));
    }
}
