var camera, scene, renderer;
var connected;
var core;
var gravity, angle, v0;

var gravityParagraph;
var angleParagraph;
var v0Paragraph;

var wsocket;

var startPosition = {x: 80., y: 67., z: 0};

init();
animate();

function connect() {
    wsocket = new WebSocket("ws://localhost:8087/fire");
    wsocket.onmessage = onMessage;
    connected = true;
}

function onMessage(event) {
    var json = JSON.parse(event.data);
    if(json.command == "new_position"){
        x = json.new_x;
        y = json.new_y;
        setCorePosition(x, y);
    }
    else if(json.command == "finish"){
        setCorePosition(startPosition.x, startPosition.y);
    }
    else if(json.command == "gravity_angle"){
        gravity = json.gravity;
        angle = json.angle;
        v0 = json.v0;

        gravityParagraph.innerHTML = "gravity = " + gravity;
        angleParagraph.innerHTML = "angle = " + angle;
        v0Paragraph.innerHTML = "v0 = " + v0;
    }
}

function init() {
    var divForImage = document.getElementById("image");

    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 10000);
    camera.position.set(300, 300, 300);
    scene.add(camera);

    scene.add(new THREE.AmbientLight(0xf0f0f0));
    var light = new THREE.SpotLight(0xffffff, 1.5);
    light.position.set(300, 300, 300);
    scene.add(light);
    spotlight = light;

    var loader = new THREE.OBJLoader();
    loader.load('cannon.obj', function ( object ) {
            object.scale.set(20,20,20);
            object.color = "#f2c539";
            scene.add( object );
        }
    );

    var helper = new THREE.GridHelper(3000, 300);
    helper.position.y = 0;
    helper.material.opacity = 0.25;
    helper.material.transparent = true;
    scene.add(helper);

    renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setClearColor(new THREE.Color( 'skyblue' ));
    renderer.setPixelRatio(window.devicePixelRatio);
    renderer.setSize(window.innerWidth, window.innerHeight);
    divForImage.appendChild(renderer.domElement);

    orbitControls = new THREE.OrbitControls(camera, renderer.domElement);
    transformControls = new THREE.TransformControls(camera, renderer.domElement);

    //create sphere
    var sphereGeometry = new THREE.SphereGeometry(8, 50, 50, 0, Math.PI * 2, 0, Math.PI * 2);
    core = new THREE.Mesh(sphereGeometry, new THREE.MeshStandardMaterial({color: "#20296d"}))
    core.position.x = startPosition.x;
    core.position.y = startPosition.y;
    core.position.z = startPosition.z;
    scene.add(core);
    sphereGeometry.dynamic = true;

    gravityParagraph = document.getElementById("gravity");
    angleParagraph = document.getElementById("angle");
    v0Paragraph = document.getElementById("v0");
}

function setCorePosition(x, y) {
    core.position.x = x;
    core.position.y = y;
}

function animate() {
    requestAnimationFrame(animate);
    render();
}

function render() {
    renderer.render(scene, camera);
    transformControls.update();
    orbitControls.update();
}

window.addEventListener("keypress", manageKey, false);
function manageKey(e) {
    if(e.keyCode == 32){
        if(!connected || wsocket.closed) {
            connect();
        }
        var obj = {command: "fire", "x": startPosition.x, "y": startPosition.y};
        var msg = JSON.stringify(obj);

        sendMessage(msg);
    }
}

function sendMessage(msg){
    // Wait until the state of the socket is not ready and send the message when it is
    waitForSocketConnection(wsocket, function(){
        console.log("message sent!!!");
        wsocket.send(msg);
    });
}

// Make the function wait until the connection is made...
function waitForSocketConnection(socket, callback){
    setTimeout(
        function () {
            if (socket.readyState === 1) {
                console.log("Connection is made")
                if(callback != null){
                    callback();
                }
                return;

            } else {
                console.log("wait for connection...")
                waitForSocketConnection(socket, callback);
            }

        }, 5); // wait 5 milisecond for the connection
}
