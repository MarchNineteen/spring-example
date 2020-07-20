package com.wyb.test.java.hook;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.POINT;

interface LowLevelMouseProc extends HOOKPROC {
    LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);
}

// Structure：表示具有Java对等类的本机结构。
public class MOUSEHOOKSTRUCT extends Structure {

    // 标记接口为了指示结构类型实例的地址，将在结构定义中使用，而不是嵌套完整的结构内容。
    // 默认行为是内联结构字段。
    public class ByReference extends MOUSEHOOKSTRUCT implements Structure.ByReference {
    }

    public POINT pt; // POINT结构定义了一个点的X坐标和y坐标
    public HWND hwnd; // HWND 标识将接收与鼠标事件对应的鼠标消息的窗口
    public int wHitTestCode; // 指定命中测试值？？
    // 当光标移动时，或当按下或释放鼠标按钮时，WM_NCHITTEST消息将发送到窗口。
    // 如果鼠标未被捕获，则会将消息发送到光标下方的窗口。否则，消息将发布到已捕获鼠标的窗口。
    // 返回光标热点的位置？坐标是相对于屏幕左上角的
    public ULONG_PTR dwExtraInfo; // 指定与消息相关联的额外信息
}
