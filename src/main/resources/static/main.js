const url = "wss://echo-ws-service.herokuapp.com";

const text_message = document.getElementById('text_message');
const send_btn = document.getElementById('send_btn');
const chat_window = document.getElementById('chat_window');
const chat_messages = document.getElementById('chat_messages');

//Соединение:
let websocket;
let activeUser;

websocket = new WebSocket(url);
websocket.onopen = function (evt) {
  console.log("CONNECTED");
};
websocket.onclose = function (evt) {
  console.log("DISCONNECTED");
};
websocket.onmessage = function (evt) {
  writeToScreen('<span style="color: blue;">herokuapp answer: ' + evt.data + '</span>', 'flex-start')
};
websocket.onerror = function (evt) {
  writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data, 'flex-start')
};

//Отправка сообщения:
send_btn.addEventListener('click', () => {
  const message = text_message.value;
  writeToScreen("you write: " + message);
  addMessage(message);
  websocket.send(message);
});


//Вывод сообщения:
function writeToScreen(message, flex_position = 'flex-end') {
  let p = `<p class='messages' style='align-self: ${flex_position}'>${message}</p>`;
  chat_messages.innerHTML += p;
  chat_window.scrollTop = chat_window.scrollHeight;
}

function submit() {
  let userName = document.getElementById("name").value;
  sessionStorage.setItem("userName", userName);
  console.log(userName);
  activeUser = userName;
  addUser();
  window.open("/index");
}

function onPageLoad() {
  let userName = sessionStorage.getItem("userName");
  document.getElementById("userName").innerHTML = "User: " + userName;
  console.log(userName);
  getList(userName);
}

function addUser() {
  let user = document.getElementById("name").value;

  fetch("/users/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      name: user
    })
  })
      .then(response => response.json())
      .catch(error => console.error(error));
}

function addMessage(text){
  let user = sessionStorage.getItem("userName");

  fetch("/messages/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      text: text,
      user: user
    }),

  })
      .then(response => response.json())
      .catch(error => console.error(error));
}

function getList(userName){
console.log("sending to server user: " + userName);

  fetch("/list/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify({
      name: userName
    })
  })
      .then((response) => response.json())
      .then((response) => {
        console.log("js got the list: " + response)
        response.forEach((message) => {
          writeToScreen(message);
        });
      })
      .catch((error) => console.error(error));
}