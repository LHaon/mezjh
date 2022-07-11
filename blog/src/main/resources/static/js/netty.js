var socket;
if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
}

if (window.WebSocket) {
    socket = new WebSocket("ws://chat.mezjh.com/ws");
    socket.onmessage = function (event) {
        var ta = document.getElementById('chat-content');
        ta.value = ta.value + '\n' + event.data;
        var scrollLocation = document.getElementById("chat-content");
        scrollLocation.scrollTop = scrollLocation.scrollHeight;
    };
    socket.onopen = function (event) {
        var ta = document.getElementById('chat-content');
        ta.value = "连接服务器成功";
        document.getElementById('chat-online').innerText='100';
    };
    socket.onclose = function (event) {
        var ta = document.getElementById('chat-content');
        ta.value = ta.value + "服务器已关闭";
    };
} else {
    alert("你的浏览器好像不支持 WebSocket！");
}

function send(message) {
    if (!window.WebSocket) {
        return;
    }

    if (socket.readyState == WebSocket.OPEN) {
        socket.send(message);
        document.getElementById('form-content').value = '';
    } else {
        alert("服务器未连接成功.");
    }
}

document.onkeydown = function (event) {
    e = event ? event : (window.event ? window.event : null);
    if (e.keyCode == 13 || e.keyCode == 9) {
        send(document.getElementById('form-content').value);
    }
}

