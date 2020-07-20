package com.wyb.test.java.hook;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;

import java.awt.*;

public class MouseLLHook {

    // 鼠标钩子函数里判断按键类型的常数
    public static Point mousepoint;
    public static MouseInfo mouseInfo = null;
    static Color pixel = new Color(0, 0, 0);
    static Integer R = 0, G = 0, B = 0;
    static Integer X = 0, Y = 0;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_LBUTTONDBLCLK = 515;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_CLOSE = 16;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_MOUSEHWHEEL = 526;
    public static final int WM_MOUSEWHEEL = 522;
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_DESTROY = 2;
    public static final int SC_CLOSE = 0xF060;

    static HHOOK mouseHHK, keyboardHHK, windowHHK;// 鼠标、键盘钩子的句柄
    static LowLevelMouseProc mouseHook;// 鼠标钩子函数

    // 安装钩子
    void setHook() {
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null); // Handle to a module. The value is the base address of
        // the module.
        mouseHHK = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_MOUSE_LL, // 安装类型的钩子，WinUser.WH_MOUSE_LL是常量值
                mouseHook, hMod, 0);
    }

    // 卸载钩子
    void unhook() {
        User32.INSTANCE.UnhookWindowsHookEx(mouseHHK);
    }

    public void listener() {
        // 该函数参数的意思参考：http://msdn.microsoft.com/en-us/library/windows/desktop/ms644986(v=vs.85).aspx
        mouseHook = (nCode, wParam, lParam) -> { // wParam：指定鼠标消息的标识符
            // lParam：指向MOUSEHOOKSTRUCT结构
            switch (wParam.intValue()) { // 类Number中的intValue

                // 当光标移动时，WM_MOUSEMOVE消息会发布到窗口。如果未捕获鼠标，则会将消息发布到包含光标的窗口。否则，消息将发布到已捕获鼠标的窗口
                // case WM_MOUSEMOVE:
                // System.out.print("mouse moved:");
                // colorFrame.getOutputTextTop().setText("mouse moved:");//setText(String text)：定义此组件将要显示的单行文本
                // break;
                case WM_LBUTTONDBLCLK: // 鼠标双击
                    System.out.println("mouse left button double click");
//                    colorFrame.getOutputTextTop().setText("mouse left button double click");
                    break;
                case WM_LBUTTONDOWN:
                    System.out.println("mouse left button down");
//                    colorFrame.getOutputTextTop().setText("mouse left button up");
                    break;
                case WM_LBUTTONUP: // 当用户在窗口的客户端区域中释放鼠标左键时，会发布WM_LBUTTONUP消息
                    System.out.println("mouse left button up");
//                    colorFrame.getOutputTextTop().setText("mouse left button up");
                    break;
                case WM_RBUTTONUP: // 释放右键
                    System.out.println("mouse right button up:");
//                    colorFrame.getOutputTextTop().setText("mouse right button up");
                    break;
                case WM_RBUTTONDOWN: // 当用户在窗口的客户区域中按住鼠标右键时，会发出WM_RBUTTONDOWN消息
                    System.out.println("mouse right button down:");
//                    colorFrame.getOutputTextTop().setText("mouse right button down:");
                    break;
            }
            // System.out.println("(" + lParam.pt.x + "," + lParam.pt.y + ")");
            // colorFrame.getOutputTextButtom().setText("(" + lParam.pt.x + "," + lParam.pt.y + ")");
            return User32.INSTANCE.CallNextHookEx(mouseHHK, nCode, wParam, lParam.getPointer());
        };

        setHook();

        int result;
        MSG msg = new MSG(); // public static class WinUser.MSG extends Structure
        // MSG结构包含来自线程的消息队列的消息信息
        // 消息循环
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) { // msg 具有消息结构的地址
            // 如果函数检索到除WM_QUIT之外的消息，则返回值不为零。如果函数检索WM_QUIT消息，返回值为零
            // 如果有错误，返回值为-1。例如，如果【的hWnd】是无效的窗口句柄，则该函数将失败。
            // WM_QUIT消息指示终止应用程序的请求，并在应用程序调用{??989796010}函数时生成。它使GetMessage函数返回零。
            if (result == -1) {
                System.err.println("error in GetMessage");
                unhook();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);// 将虚拟键消息转换为字符消息.字符消息被寄送到调用线程的消息队列里，当下一次线程调用函数GetMessage或PeekMessage时被读出。
                // 如果消息被转换（即，字符消息被寄送到调用线程的消息队列里），返回非零值。
                // 如果消息是WM_kEYDOWN，WM_KEYUP WM_SYSKEYDOWN或WM_SYSKEYUP，返回非零值，不考虑转换。如果消息没被转换（即，字符消息没被寄送到调用线程的消息队列里），返回值是零。
                User32.INSTANCE.DispatchMessage(msg); // 该函数调度一个消息给窗口程序。。它通常用于调度由GetMessage函数检索到的消息。
                // 返回值是窗口程序返回的值。尽管返回值的含义依赖于被调度的消息，但返回值通常被忽略
                System.out.println(msg);
            }
        }
        unhook();
    }

    public static void main(String[] args) {
        MouseLLHook hook = new MouseLLHook();
        hook.listener();
    }
}
