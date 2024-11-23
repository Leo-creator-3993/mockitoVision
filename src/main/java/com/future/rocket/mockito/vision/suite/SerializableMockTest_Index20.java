package com.future.rocket.mockito.vision.suite;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SerializableMockTest_Index20 {

    @Test
    public void testSerializableWriteFile() throws IOException, ClassNotFoundException {
        List<String> serializableMock = mock(List.class, withSettings().serializable());
        when(serializableMock.get(0)).thenReturn("SerializableMockTest_Index20");
        System.out.println("mockValue = " + serializableMock.get(0));

        //序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mock.dat"))) {
            oos.writeObject(serializableMock);
        }

        //反序列化
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mock.dat"))) {
            List<String> deserializedMock = (List<String>) ois.readObject();
            System.out.println("Deserialized Mocked Value: " + deserializedMock.get(0)); // 输出: Serializable Mock
        }
    }

    @Test
    public void testSerializableWriteFile_Exception() {
        //未设置序列化,输出流时抛异常
        List<String> serializableMock = mock(List.class);
        when(serializableMock.get(0)).thenReturn("testSerializableWriteFile_Exception");
        System.out.println("newMockValue = " + serializableMock.get(0));

        //序列化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mock1.dat"))) {
            oos.writeObject(serializableMock);
        } catch (IOException e) {
            System.out.println("捕获异常 ==> " + e);
        }
    }

    @Test
    public void testSerializableSpyWriteFile() throws IOException, ClassNotFoundException {
        List<String> realList = new ArrayList<>();
        realList.add("testSerializableSpyWriteFile");

        List<String> serializableSpy = mock(ArrayList.class, withSettings().spiedInstance(realList).defaultAnswer(CALLS_REAL_METHODS).serializable());
        System.out.println("Spy real value ==> " + serializableSpy.get(0));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mock.dat"))) {
            oos.writeObject(serializableSpy);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mock.dat"))) {
            List<String> deserializedSpy = (List<String>) ois.readObject();
            System.out.println("Deserialized Spy Value ==> " + deserializedSpy.get(0));
        }
    }

    @Test
    public void testSerializableSpyWriteFile_abnormal() {
        List<String> realList = new ArrayList<>();
        realList.add("testSerializableSpyWriteFile_abnormal");

        List<String> serializableSpy = mock(ArrayList.class, withSettings().spiedInstance(realList).defaultAnswer(CALLS_REAL_METHODS));
        System.out.println("Spy real value ==> " + serializableSpy.get(0));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mock.dat"))) {
            oos.writeObject(serializableSpy);
        } catch (IOException e) {
            System.out.println("捕获到异常 ==> " + e);
        }
    }
}
