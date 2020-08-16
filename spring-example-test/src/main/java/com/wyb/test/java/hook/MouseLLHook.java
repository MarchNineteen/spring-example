//package com.wyb.test.java.hook;
//
//import com.sun.jna.platform.win32.Kernel32;
//import com.sun.jna.platform.win32.User32;
//import com.sun.jna.platform.win32.WinDef.HMODULE;
//import com.sun.jna.platform.win32.WinUser;
//import com.sun.jna.platform.win32.WinUser.HHOOK;
//import com.sun.jna.platform.win32.WinUser.MSG;
//
//import java.awt.*;
//
//public class MouseLLHook {
//
//    // ��깳�Ӻ������жϰ������͵ĳ���
//    public static Point mousepoint;
//    public static MouseInfo mouseInfo = null;
//    static Color pixel = new Color(0, 0, 0);
//    static Integer R = 0, G = 0, B = 0;
//    static Integer X = 0, Y = 0;
//    public static final int WM_LBUTTONUP = 514;
//    public static final int WM_LBUTTONDBLCLK = 515;
//    public static final int WM_LBUTTONDOWN = 513;
//    public static final int WM_RBUTTONUP = 517;
//    public static final int WM_CLOSE = 16;
//    public static final int WM_RBUTTONDOWN = 516;
//    public static final int WM_MOUSEHWHEEL = 526;
//    public static final int WM_MOUSEWHEEL = 522;
//    public static final int WM_MOUSEMOVE = 512;
//    public static final int WM_DESTROY = 2;
//    public static final int SC_CLOSE = 0xF060;
//
//    static HHOOK mouseHHK, keyboardHHK, windowHHK;// ��ꡢ���̹��ӵľ��
//    static LowLevelMouseProc mouseHook;// ��깳�Ӻ���
//
//    // ��װ����
//    void setHook() {
//        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null); // Handle to a module. The value is the base address of
//        // the module.
//        mouseHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_MOUSE_LL, // ��װ���͵Ĺ��ӣ�WinUser.WH_MOUSE_LL�ǳ���ֵ
//                mouseHook, hMod, 0);
//    }
//
//    // ж�ع���
//    void unhook() {
//        User32.INSTANCE.UnhookWindowsHookEx(mouseHHK);
//    }
//
//    public void listener() {
//        // �ú�����������˼�ο���http://msdn.microsoft.com/en-us/library/windows/desktop/ms644986(v=vs.85).aspx
//        mouseHook = (nCode, wParam, lParam) -> { // wParam��ָ�������Ϣ�ı�ʶ��
//            // lParam��ָ��MOUSEHOOKSTRUCT�ṹ
//            switch (wParam.intValue()) { // ��Number�е�intValue
//
//                // ������ƶ�ʱ��WM_MOUSEMOVE��Ϣ�ᷢ�������ڡ����δ������꣬��Ὣ��Ϣ�������������Ĵ��ڡ�������Ϣ���������Ѳ������Ĵ���
//                // case WM_MOUSEMOVE:
//                // System.out.print("mouse moved:");
//                // colorFrame.getOutputTextTop().setText("mouse moved:");//setText(String text)������������Ҫ��ʾ�ĵ����ı�
//                // break;
//                case WM_LBUTTONDBLCLK: // ���˫��
//                    System.out.println("mouse left button double click");
////                    colorFrame.getOutputTextTop().setText("mouse left button double click");
//                    break;
//                case WM_LBUTTONDOWN:
//                    System.out.println("mouse left button down");
////                    colorFrame.getOutputTextTop().setText("mouse left button up");
//                    break;
//                case WM_LBUTTONUP: // ���û��ڴ��ڵĿͻ����������ͷ�������ʱ���ᷢ��WM_LBUTTONUP��Ϣ
//                    System.out.println("mouse left button up");
////                    colorFrame.getOutputTextTop().setText("mouse left button up");
//                    break;
//                case WM_RBUTTONUP: // �ͷ��Ҽ�
//                    System.out.println("mouse right button up:");
////                    colorFrame.getOutputTextTop().setText("mouse right button up");
//                    break;
//                case WM_RBUTTONDOWN: // ���û��ڴ��ڵĿͻ������а�ס����Ҽ�ʱ���ᷢ��WM_RBUTTONDOWN��Ϣ
//                    System.out.println("mouse right button down:");
////                    colorFrame.getOutputTextTop().setText("mouse right button down:");
//                    break;
//            }
//            // System.out.println("(" + lParam.pt.x + "," + lParam.pt.y + ")");
//            // colorFrame.getOutputTextButtom().setText("(" + lParam.pt.x + "," + lParam.pt.y + ")");
//            return User32.INSTANCE.CallNextHookEx(mouseHHK, nCode, wParam, lParam.getPointer());
//        };
//
//        setHook();
//
//        int result;
//        MSG msg = new MSG(); // public static class WinUser.MSG extends Structure
//        // MSG�ṹ���������̵߳���Ϣ���е���Ϣ��Ϣ
//        // ��Ϣѭ��
//        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) { // msg ������Ϣ�ṹ�ĵ�ַ
//            // ���������������WM_QUIT֮�����Ϣ���򷵻�ֵ��Ϊ�㡣�����������WM_QUIT��Ϣ������ֵΪ��
//            // ����д��󣬷���ֵΪ-1�����磬�������hWnd������Ч�Ĵ��ھ������ú�����ʧ�ܡ�
//            // WM_QUIT��Ϣָʾ��ֹӦ�ó�������󣬲���Ӧ�ó������{??989796010}����ʱ���ɡ���ʹGetMessage���������㡣
//            if (result == -1) {
//                System.err.println("error in GetMessage");
//                unhook();
//                break;
//            } else {
//                User32.INSTANCE.TranslateMessage(msg);// ���������Ϣת��Ϊ�ַ���Ϣ.�ַ���Ϣ�����͵������̵߳���Ϣ���������һ���̵߳��ú���GetMessage��PeekMessageʱ��������
//                // �����Ϣ��ת���������ַ���Ϣ�����͵������̵߳���Ϣ����������ط���ֵ��
//                // �����Ϣ��WM_kEYDOWN��WM_KEYUP WM_SYSKEYDOWN��WM_SYSKEYUP�����ط���ֵ��������ת���������Ϣû��ת���������ַ���Ϣû�����͵������̵߳���Ϣ�����������ֵ���㡣
//                User32.INSTANCE.DispatchMessage(msg); // �ú�������һ����Ϣ�����ڳ��򡣡���ͨ�����ڵ�����GetMessage��������������Ϣ��
//                // ����ֵ�Ǵ��ڳ��򷵻ص�ֵ�����ܷ���ֵ�ĺ��������ڱ����ȵ���Ϣ��������ֵͨ��������
//                System.out.println(msg);
//            }
//        }
//        unhook();
//    }
//
//    public static void main(String[] args) {
//        MouseLLHook hook = new MouseLLHook();
//        hook.listener();
//    }
//}
