//package com.wyb.test.java.hook;
//
//import com.sun.jna.Structure;
//import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
//import com.sun.jna.platform.win32.WinDef.HWND;
//import com.sun.jna.platform.win32.WinDef.LRESULT;
//import com.sun.jna.platform.win32.WinDef.WPARAM;
//import com.sun.jna.platform.win32.WinUser.HOOKPROC;
//import com.sun.jna.platform.win32.WinUser.POINT;
//
//interface LowLevelMouseProc extends HOOKPROC {
//    LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);
//}
//
//// Structure����ʾ����Java�Ե���ı����ṹ��
//public class MOUSEHOOKSTRUCT extends Structure {
//
//    // ��ǽӿ�Ϊ��ָʾ�ṹ����ʵ���ĵ�ַ�����ڽṹ������ʹ�ã�������Ƕ�������Ľṹ���ݡ�
//    // Ĭ����Ϊ�������ṹ�ֶΡ�
//    public class ByReference extends MOUSEHOOKSTRUCT implements Structure.ByReference {
//    }
//
//    public POINT pt; // POINT�ṹ������һ�����X�����y����
//    public HWND hwnd; // HWND ��ʶ������������¼���Ӧ�������Ϣ�Ĵ���
//    public int wHitTestCode; // ָ�����в���ֵ����
//    // ������ƶ�ʱ���򵱰��»��ͷ���갴ťʱ��WM_NCHITTEST��Ϣ�����͵����ڡ�
//    // ������δ��������Ὣ��Ϣ���͵�����·��Ĵ��ڡ�������Ϣ���������Ѳ������Ĵ��ڡ�
//    // ���ع���ȵ��λ�ã��������������Ļ���Ͻǵ�
//    public ULONG_PTR dwExtraInfo; // ָ������Ϣ������Ķ�����Ϣ
//}
