package com.epam.junitmodule;

import org.easymock.*;
import org.junit.*;
import org.junit.runner.*;

import zeyt.marketplace.hr.background.*;
import zeyt.model.*;
import zeyt.program.*;
import zeyt.ui.*;
import zeyt.web.*;

@RunWith(EasyMockRunner.class)
public class TestEasyBackgroundDownload extends EasyMockSupport {
    
    @Mock
    private IDownloader downloaderMock;
    
    @Mock
    private UiPage uiPageMock;
    
    @Mock
    private UserConnection userConnectionMock;
    
    @Mock
    private Account accountMock;
    
    @Mock
    private EasyBackgroundCheck easyBackgroundCheckMock;
    
    @Mock
    private AccountHome accountHomeMock;// = AccountHome.getInstance();
    
    @Mock
    private EasyBackgroundCheckHome easyBackgroundCheckHomeMock;// = EasyBackgroundCheckHome.getInstance();
    
    @Mock
    private Program programMock;
    
    private final AccountHome acHome = AccountHome.getInstance();
    private final EasyBackgroundCheckHome bcHome = EasyBackgroundCheckHome.getInstance();
    
    @TestSubject
    private final EasyBackgrounds_Impl_Default classUnderTest = new EasyBackgrounds_Impl_Default();
    
    @Test
  public void testDownload() {
    ObjectProperties programProperties = new ObjectProperties();
    programProperties.setProperty("EASY_BACKGROUNDS_DIR", "\\ZeytUpload\\EasyBackgrounds");

    try {
      java.lang.reflect.Field f = Program.class.getDeclaredField("dbStrings");
      f.setAccessible(true);
      f.set(programMock, programProperties);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }

    ObjectId accountObjId = new ObjectId(acHome, 53248);
    ObjectRealId accountObjRealId = new ObjectRealId(acHome, 53248);

    EasyMock.expect(accountHomeMock.getAccount(EasyMock.anyString())).andReturn(accountMock);
    EasyMock.expect(accountHomeMock.getAccount(EasyMock.anyObject(Id.class))).andReturn(accountMock);
    EasyMock.expect(accountHomeMock.getAccount(EasyMock.anyObject(IAccount.class))).andReturn(accountMock);
    EasyMock.expect(accountHomeMock.parseId(EasyMock.anyInt())).andReturn(accountObjId);
    EasyMock.expect(accountHomeMock.parseId(EasyMock.anyLong())).andReturn(accountObjId);
    EasyMock.expect(accountHomeMock.parseId(EasyMock.anyObject())).andReturn(accountObjId);

    EasyMock.replay(accountHomeMock);

    try {
      java.lang.reflect.Field f = BaseObject.class.getDeclaredField("id");
      f.setAccessible(true);
      f.set(accountMock, accountObjRealId);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }

    EasyMock.expect(userConnectionMock.hasAccess(EasyMock.anyString())).andReturn(Boolean.TRUE);

    EasyMock.replay(userConnectionMock);

    EasyMock.expect(accountMock.getFullName()).andReturn("Test");
    EasyMock.replay(accountMock);

    EasyMock.expect(uiPageMock.getUserConnection()).andReturn(userConnectionMock);
    EasyMock.expect(uiPageMock.getUser()).andReturn(accountMock);

    EasyMock.replay(uiPageMock);

    ObjectId bcObjId = new ObjectId(bcHome, 53248);
    ObjectRealId bcObjRealId = new ObjectRealId(bcHome, 53248);

    EasyMock.expect(easyBackgroundCheckHomeMock.getBackgroundCheck(EasyMock.anyObject())).andReturn(
      easyBackgroundCheckMock);
    EasyMock.expect(easyBackgroundCheckHomeMock.getBackgroundCheck(EasyMock.anyObject(Id.class))).andReturn(
      easyBackgroundCheckMock);
    EasyMock.expect(easyBackgroundCheckHomeMock.getBackgroundCheck(EasyMock.anyObject(IAccount.class))).andReturn(
      easyBackgroundCheckMock);
    EasyMock.expect(easyBackgroundCheckHomeMock.parseId(EasyMock.anyInt())).andReturn(bcObjId);
    EasyMock.expect(easyBackgroundCheckHomeMock.parseId(EasyMock.anyLong())).andReturn(bcObjId);
    EasyMock.expect(easyBackgroundCheckHomeMock.parseId(EasyMock.anyObject())).andReturn(bcObjId);

    EasyMock.replay(easyBackgroundCheckHomeMock);

    try {
      java.lang.reflect.Field f = BaseObject.class.getDeclaredField("id");
      f.setAccessible(true);
      f.set(easyBackgroundCheckMock, bcObjRealId);
    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
    }

    EasyMock.expect(easyBackgroundCheckMock.getAccountId()).andReturn(accountObjId);
    EasyMock.expect(easyBackgroundCheckMock.getCompanyId()).andReturn(accountObjId);
    EasyMock.expect(easyBackgroundCheckMock.getAccount()).andReturn(accountMock);

    EasyMock.replay(easyBackgroundCheckMock);

    classUnderTest.setProgram(programMock);
    classUnderTest.setDownloader(downloaderMock);
    classUnderTest.setAHome(accountHomeMock);
    classUnderTest.setBcHome(easyBackgroundCheckHomeMock);
    classUnderTest.download(uiPageMock, easyBackgroundCheckMock.getId(), true);
  }
    
    @After
    public void afterTest() {
        EasyMock.verify(uiPageMock);
        EasyMock.verify(userConnectionMock);
        EasyMock.verify(accountMock);
    }
    
}
