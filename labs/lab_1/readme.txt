Завдання лабораторної:

Используя технологию web-sockets и WebGL создать приложение:
Реализовать полет ядра из пушки. Ядро летит по баллистической траектории. 
В программе должен быть произведен расчет траектории полета с учетом гравитации.

In this app we use Three.js and WebSockets
WebSocketServer was written on java and WebSocketClient on javascript
WebSocketSession is a wrapper on Session object
WebSocketServer receives message with command "fire" and starts to receives to
client points of trajectory and client only draw sphere on the new point in browser.